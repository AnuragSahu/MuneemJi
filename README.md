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

## Google Sheets backup
MuneemJi connects directly to the Google Sheets API. Users tap `Continue with Google`, grant the requested Sheets/Drive permissions, and the app creates a private `MuneemJi Expenses` spreadsheet in their Google account.

The app creates these tabs automatically:
- `Expenses`
- `Categories`
- `Monthly Summary`

Required Google OAuth scopes:
- `https://www.googleapis.com/auth/spreadsheets`
- `https://www.googleapis.com/auth/drive.file`

Google Cloud setup:
1. Create or open a Google Cloud project.
2. Enable the Google Sheets API and Google Drive API.
3. Configure the OAuth consent screen with the app name, support email, privacy policy, and the two scopes above.
4. Create an Android OAuth client using package name `com.muneemji.app`.
5. Add the SHA-1 certificate fingerprint for each signing key you use, including debug and release.
