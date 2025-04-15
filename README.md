# Notes Keeping App - Kotlin with SQLite

## Overview

The **Notes Keeping App** is an Android application built using Kotlin and SQLite. It allows users to create, edit, view, and delete notes securely. The app includes a simple login/signup system and incorporates features like validation of email and passwords. All user data is stored locally using SQLite for efficient data handling.

## Features

- **User Authentication**: Signup and login functionality with email and password validation.
- **CRUD Operations**: Create, Read, Update, and Delete notes.
- **SQLite Database**: Data is stored locally using SQLite for persistent storage.
- **UI**: Simple and intuitive UI for managing notes.

## File Structure

### `java/com/example/notesapp`

- **AccountModelClass.kt**: Contains data models related to user accounts, like storing email, username, and password.
- **DatModelClass.kt**: A possible typo, but likely refers to data models for the notes or user data.
- **DataModelClass.kt**: Contains the structure of the notes data, possibly a class for managing individual note entries.
- **DatabaseHandler.kt**: Responsible for handling SQLite database operations like creating, updating, and deleting records.
- **DeleteItemAdapter.kt**: Adapter for displaying items that are eligible for deletion in the app.
- **DeleteNotesActivity.kt**: Activity that facilitates deleting notes.
- **EditItemAdapter.kt**: Adapter used for editing notes.
- **EditNotesActivity.kt**: Activity for editing existing notes.
- **EmailValidator.kt**: Utility class to validate the email format.
- **InsertNotesActivity.kt**: Activity for inserting new notes into the database.
- **ItemAdapter.kt**: Adapter for displaying list items such as notes.
- **LoginActivity.kt**: Activity for logging in users.
- **MainActivity.kt**: Main entry point of the app, where users are directed after login.
- **NotesActivity.kt**: Activity for viewing the list of notes and navigating to edit or delete actions.
- **PasswordValidator.kt**: Utility class for password validation rules.
- **SignupActivity.kt**: Activity for user signup and creating a new account.
- **ViewNotesActivity.kt**: Activity to display a specific note in full view.

### `layout/`

- **activity_delete_notes.xml**: Layout file for the "Delete Notes" activity.
- **activity_edit_notes.xml**: Layout file for the "Edit Notes" activity.
- **activity_insert_notes.xml**: Layout file for inserting new notes.
- **activity_login.xml**: Layout file for the login screen.
- **activity_main.xml**: Main screen layout after user login, displaying the list of notes.
- **activity_notes.xml**: Layout for the notes-related UI components.
- **activity_signup.xml**: Layout file for the signup screen.
- **activity_view_notes.xml**: Layout for viewing a specific note.
- **delete_items_row.xml**: Layout for individual rows of items to be deleted.
- **dialog_update.xml**: Layout for a dialog that handles updates.
- **edit_items_row.xml**: Layout for individual rows of items in the editing interface.
- **items_row.xml**: Layout for displaying notes in list form.

## System Requirements

- Android Studio
- Kotlin SDK
- SQLite Database

## Installation

1. Clone the repository:

    ```bash
    git clone <repository-url>
    ```

2. Open the project in Android Studio.

3. Build and run the project on an Android emulator or device.

## Usage

Once the app is installed:

- **Signup**: Create a new account with a valid email and password.
- **Login**: Log into the app using your credentials.
- **Create Note**: Use the "Insert Notes" screen to add a new note.
- **View/Edit/Delete Notes**: View the list of notes, edit them, or delete them as needed.

## Contributing

We welcome contributions to improve the app! Follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add feature'`).
5. Push to the branch (`git push origin feature-name`).
6. Create a pull request.

Please ensure your code follows the project's style guidelines and includes tests where applicable.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

