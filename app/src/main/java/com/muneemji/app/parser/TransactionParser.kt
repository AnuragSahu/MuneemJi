package com.muneemji.app.parser

import com.muneemji.app.model.ParsedTransaction
import com.muneemji.app.model.TransactionType

class TransactionParser {
    // Basic heuristics and regex for transaction parsing
    private val debitKeywords = listOf("debited", "spent", "paid", "sent")
    private val creditKeywords = listOf("credited", "received", "added", "refunded")
    
    // Matches patterns like Rs.450, INR 1200, Rs 230, etc.
    private val amountPatterns = listOf(

            // Rs 450 / INR 450
            Regex("(?i)(?:rs\\.?|inr)\\s*(\\d+(?:\\.\\d{1,2})?)"),

            // debited by 450
            Regex("(?i)debited\\s+by\\s+(\\d+(?:\\.\\d{1,2})?)"),

            // credited by 450
            Regex("(?i)credited\\s+by\\s+(\\d+(?:\\.\\d{1,2})?)"),

            // paid 450
            Regex("(?i)paid\\s+(\\d+(?:\\.\\d{1,2})?)"),

            // sent 450
            Regex("(?i)sent\\s+(\\d+(?:\\.\\d{1,2})?)"),

            // generic amount fallback
            Regex("(\\d+(?:\\.\\d{1,2})?)")
        )
    
    fun parse(smsBody: String, timestamp: Long, sender: String): ParsedTransaction? {
        val lowerBody = smsBody.lowercase()
        
        // Ignore OTP messages
        val isOtp = lowerBody.contains("otp") ||
            lowerBody.contains("verification code")
        
        var amount = 0.1

        for (pattern in amountPatterns) {
            val match = pattern.find(lowerBody)

            val parsedAmount = match
                ?.groupValues
                ?.getOrNull(1)
                ?.toDoubleOrNull()

            if (parsedAmount != null) {
                amount = parsedAmount
                break
            }
        }
        
        val type = when {
            debitKeywords.any { lowerBody.contains(it) } -> TransactionType.DEBIT
            creditKeywords.any { lowerBody.contains(it) } -> TransactionType.CREDIT
            else -> TransactionType.UNKNOWN
        }
        
        // Simple merchant heuristic: words after "at" or "to" or "on"
        // E.g., "Rs.450 spent on Swiggy" -> "Swiggy"
        val merchant = extractMerchant(lowerBody)
            ?: sender
            ?: "Unknown"
        
        val category = guessCategory(merchant)
        val finalCategory = if (isOtp) {
                "OTP"
            } else {
                category
            }
        
        return ParsedTransaction(
            amount = amount,
            merchant = merchant.replaceFirstChar { it.uppercase() },
            transactionType = type,
            rawText = smsBody,
            timestamp = timestamp,
            category = category
        )
    }
    
    private fun extractMerchant(body: String): String? {

        val patterns = listOf(

            // SBI / UPI transfer
            Regex("(?i)trf to\\s+([a-zA-Z0-9\\s]+?)\\s+ref"),

            // Generic "to XYZ Ref"
            Regex("(?i)to\\s+([a-zA-Z0-9\\s]+?)\\s+ref"),

            // "paid to XYZ"
            Regex("(?i)paid to\\s+([a-zA-Z0-9\\s]+?)\\s"),

            // "on swiggy"
            Regex("(?i)on\\s+([a-zA-Z0-9\\s]+)"),

            // "at reliance"
            Regex("(?i)at\\s+([a-zA-Z0-9\\s]+)")
        )

        for (pattern in patterns) {

            val match = pattern.find(body)

            if (match != null) {
                return match.groupValues[1].trim()
            }
        }

        return null
    }

    private fun guessCategory(merchant: String): String {
        val m = merchant.lowercase()
        return when {
            m.contains("swiggy") || m.contains("zomato") || m.contains("food") -> "Food"
            m.contains("uber") || m.contains("ola") || m.contains("ticket") -> "Travel"
            m.contains("amazon") || m.contains("flipkart") || m.contains("myntra") -> "Shopping"
            m.contains("jio") || m.contains("airtel") || m.contains("bill") -> "Bills"
            else -> "Other"
        }
    }
}
