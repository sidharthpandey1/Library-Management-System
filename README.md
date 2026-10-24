# Library Management System

A console-based Library Management System developed using Java. This project manages books, issuing and returning operations, student waiting lists, and overdue book tracking.

## Features

- Add books with unique IDs
- Display all available book records
- Search books by ID
- Remove books with protection against deleting issued books
- Issue and return books
- Automatically issue returned books to the first student in the waiting list
- Manage waiting lists using a manually implemented queue
- Track overdue books using due dates
- Save and load book records using file handling
- Preserve waiting lists across program restarts
- Validate user input, including duplicate and non-positive book IDs

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Singly Linked List
- Queue (manual implementation)
- File Handling
- `LocalDate` for issue and due dates

## Project Structure

```text
Library-Management-System/
├── src/
│   ├── Main.java
│   ├── Book.java
│   ├── Library.java
│   └── WaitQueue.java
├── README.md
├── .gitignore
└── books.txt
```

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA or another Java IDE.
3. Ensure a compatible JDK is installed.
4. Run `Main.java`.
5. Follow the menu displayed in the console.

## Data Storage

The application uses a local text file named `books.txt` to save book records and restore them when the program starts again.

## Learning Outcomes

- Understanding linked lists and queues
- Implementing basic CRUD operations
- Working with classes and objects in Java
- Handling files for data persistence
- Using dates to track book due dates
- Validating user input and handling common errors

## Author

**Sidharth Pandey**

GitHub: [sidharthpandey1](https://github.com/sidharthpandey1)
