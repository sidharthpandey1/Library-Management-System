# Library Management System

A console-based Library Management System developed using Java. This project manages books, book issuing and returning, waiting lists, and overdue book tracking.

## Features

- Add new books with unique IDs
- Display all books with their availability status
- Search books by ID
- Remove books, with protection against deleting issued books
- Issue and return books
- Automatically issue returned books to the first student in the waiting list
- Manage waiting lists using a manually implemented Queue
- Track overdue books using due dates
- Save and load book records using file handling
- Preserve waiting lists across program restarts
- Validate user input, including duplicate IDs, non-positive IDs, and empty book details

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Singly Linked List
- Queue (manual implementation)
- File Handling
- LocalDate for issue and due dates

## Project Structure

- `Main.java` — Menu and user interaction
- `Book.java` — Book data and linked-list node
- `Library.java` — Library operations and business logic
- `WaitQueue.java` — Manually implemented waiting queue
- `books.txt` — Persistent book and waiting-list data

## How to Run

1. Open the project in IntelliJ IDEA.
2. Ensure a compatible JDK is configured.
3. Open `Main.java`.
4. Run the `main()` method.
5. Use the console menu to manage books.

## Data Persistence

Book information, issue status, issue dates, due dates, and waiting lists are stored in `books.txt` so that data can be restored when the program starts again.

## Learning Outcomes

This project demonstrates linked lists, queues, Java classes and objects, input validation, date handling, and file-based persistence.

## Future Improvements

- Add a graphical user interface
- Store data in a database
- Add student accounts and authentication
- Add fine calculation for overdue books

---

**Project Type:** Java Console Application