# MuneemJi

A production-quality Android starter app for AI-powered expense tracking. This first version reads SMS messages, parses likely financial transactions, and displays them in a modern Compose UI.

## Features implemented
- Clean MVVM architecture (Hilt, Room, Compose)
- SMS permissions requesting and handling
- Reading from `content://sms/inbox`
- Local persistence using Room Database
- Lightweight heuristic parser for transactions (Regex-based)
- Categorization based on merchants (Food, Travel, Shopping, Bills)
- Modern Jetpack Compose UI (Material 3)

## How to run
1. Open this directory in Android Studio.
2. Android Studio will automatically sync the project and download the correct Gradle wrapper based on `gradle/wrapper/gradle-wrapper.properties`.
3. Build and Run the app on an Android Emulator or Device (API 26+).
4. Note: ensure your device has some sample transaction SMS messages in its inbox to test the parser.
