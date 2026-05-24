package com.muneemji.app.parser

import com.muneemji.app.model.ParsedTransaction
import com.muneemji.app.model.TransactionType

class TransactionParser {
    // Basic heuristics and regex for transaction parsing
    private val debitKeywords = listOf("debited", "spent", "paid", "sent")
    private val creditKeywords = listOf("credited", "received", "added", "refunded")
    
    // Matches patterns like Rs.450, INR 1200, Rs 230, etc.
    private val amountRegex = Regex("(?i)(?:rs\\.?|inr)\\s*(\\d+(?:\\.\\d{1,2})?)")
    
    fun parse(smsBody: String, timestamp: Long, sender: String): ParsedTransaction? {
        val lowerBody = smsBody.lowercase()
        
        // Ignore OTP messages
        if (lowerBody.contains("otp") || lowerBody.contains("verification code")) {
            return null
        }
        
        val amountMatch = amountRegex.find(lowerBody) ?: return null
        val amount = amountMatch.groupValues[1].toDoubleOrNull() ?: return null
        
        val type = when {
            debitKeywords.any { lowerBody.contains(it) } -> TransactionType.DEBIT
            creditKeywords.any { lowerBody.contains(it) } -> TransactionType.CREDIT
            else -> TransactionType.UNKNOWN
        }
        
        // Simple merchant heuristic: words after "at" or "to" or "on"
        // E.g., "Rs.450 spent on Swiggy" -> "Swiggy"
        val merchant = extractMerchant(lowerBody) ?: sender
        
        val category = guessCategory(merchant)
        
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
            Regex("(?i)on\\s+([a-z0-9]+)"),
            Regex("(?i)to\\s+([a-z0-9]+)"),
            Regex("(?i)at\\s+([a-z0-9]+)")
        )
        
        for (pattern in patterns) {
            val match = pattern.find(body)
            if (match != null) {
                return match.groupValues[1]
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
