import java.io.*;
import java.time.LocalDate;
public class Library {

    Book head;
    public void addBook(int id, String title, String author) {
        // Check whether this ID already exists
        Book current = head;
        while (current != null) {
            if (current.id == id) {
                System.out.println("Error: Book ID already exists!");
                return;
            }
            current = current.next;
        }
        // Create the new book
        Book newBook = new Book(id, title, author);
        // If the list is empty
        if (head == null) {
            head = newBook;
        } else {
            current = head;
            // Reach the last node
            while (current.next != null) {
                current = current.next;
            }
            // Connect the last node to the new book
            current.next = newBook;
        }
        System.out.println("Book added successfully!");
    }

    public void displayBooks() {

        if (head == null) {
            System.out.println("No books available.");
            return;
        }

        Book current = head;

        while (current != null) {
            System.out.println(
                    current.id + " | " +
                            current.title + " | " +
                            current.author + " | " +
                            (current.isIssued ? "Issued" : "Available")
            );

            if (current.isIssued) {
                System.out.println("Issued To: " + current.issuedTo);
                System.out.println("Due Date: " + current.dueDate);
            }

            System.out.println("--------------------------------");

            current = current.next;
        }
    }



    public void searchBook(int id) {

        Book current = head;

        while (current != null) {

            if (current.id == id) {
                System.out.println("Book found!");
                System.out.println("ID: " + current.id);
                System.out.println("Title: " + current.title);
                System.out.println("Author: " + current.author);
                System.out.println("Issued: " + current.isIssued);
                return;
            }

            current = current.next;
        }

        System.out.println("Book not found!");
    }


    public void removeBook(int id) {

        Book current = head;
        Book previous = null;

        while (current != null && current.id != id) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Book not found!");
            return;
        }

        if (current.isIssued) {
            System.out.println("Cannot remove an issued book!");
            return;
        }

        if (previous == null) {
            head = current.next;
        } else {
            previous.next = current.next;
        }

        System.out.println("Book removed successfully!");
    }

    public void issueBook(int id, String studentName) {
        Book current = head;
        while (current != null) {
            if (current.id == id) {
                if (!current.isIssued) {
                    current.isIssued = true;
                    current.issuedTo = studentName;

                    current.issueDate = java.time.LocalDate.now();
                    current.dueDate = current.issueDate.plusDays(14);

                    System.out.println(
                            "Book issued to " + studentName
                    );
                } else {
                    current.waitingList.enqueue(studentName);
                    System.out.println(
                            studentName + " added to waiting list."
                    );
                }
                return;
            }
            current = current.next;
        }
        System.out.println("Book not found!");
    }




    public void returnBook(int id) {

        Book current = head;

        while (current != null) {

            if (current.id == id) {

                if (!current.isIssued) {
                    System.out.println("Book was not issued!");
                    return;
                }

                String nextStudent = current.waitingList.dequeue();

                if (nextStudent != null) {
                    current.issuedTo = nextStudent;
                    current.isIssued = true;

                    current.issueDate = java.time.LocalDate.now();
                    current.dueDate = current.issueDate.plusDays(14);

                    System.out.println(
                            "Book returned and issued to " + nextStudent
                    );
                } else {
                    current.issuedTo = null;
                    current.isIssued = false;

                    current.issueDate = null;
                    current.dueDate = null;

                    System.out.println(
                            "Book returned. It is now available."
                    );
                }

                return;
            }

            current = current.next;
        }

        System.out.println("Book not found!");
    }

    public void showOverdueBooks() {
        Book current = head;
        LocalDate today = LocalDate.now();
        boolean found = false;

        System.out.println("\n--- Overdue Books ---");

        while (current != null) {

            if (current.isIssued
                    && current.dueDate != null
                    && current.dueDate.isBefore(today)) {
                System.out.println("Book ID: " + current.id);
                System.out.println("Title: " + current.title);
                System.out.println("Issued To: " + current.issuedTo);
                System.out.println("Due Date: " + current.dueDate);
                System.out.println("--------------------");
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No overdue books found.");
        }
    }

    public void showWaitingList(int id) {

        Book current = head;

        while (current != null) {

            if (current.id == id) {
                System.out.println("\n--- Waiting List for " + current.title + " ---");
                current.waitingList.displayQueue();
                return;
            }

            current = current.next;
        }

        System.out.println("Book not found!");
    }


    public void saveBooks() {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter("books.txt"))) {

            Book current = head;

            while (current != null) {
                writer.println(
                        current.id + "|" +
                                current.title + "|" +
                                current.author + "|" +
                                current.isIssued + "|" +
                                (current.issuedTo == null ? "" : current.issuedTo) + "|" +
                                (current.issueDate == null ? "" : current.issueDate) + "|" +
                                (current.dueDate == null ? "" : current.dueDate) + "|" +
                                current.waitingList.toStorageString()
                );

                current = current.next;
            }

            System.out.println("Books and waiting lists saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }



    public void loadBooks() {
        File file = new File("books.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);

                // Accept old 7-field records and new 8-field records.
                if (data.length != 7 && data.length != 8) {
                    System.out.println("Skipping invalid book record.");
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0]);

                    if (findBook(id) != null) {
                        continue;
                    }

                    Book book = new Book(id, data[1], data[2]);
                    book.isIssued = Boolean.parseBoolean(data[3]);
                    book.issuedTo = data[4].isEmpty() ? null : data[4];
                    book.issueDate = data[5].isEmpty()
                            ? null : LocalDate.parse(data[5]);
                    book.dueDate = data[6].isEmpty()
                            ? null : LocalDate.parse(data[6]);

                    if (data.length == 8) {
                        book.waitingList.loadFromStorageString(data[7]);
                    }

                    if (head == null) {
                        head = book;
                    } else {
                        Book current = head;

                        while (current.next != null) {
                            current = current.next;
                        }

                        current.next = book;
                    }

                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid book record.");
                }
            }

            System.out.println("Books loaded successfully!");

        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }


    private Book findBook(int id) {

        Book current = head;

        while (current != null) {
            if (current.id == id) {
                return current;
            }

            current = current.next;
        }

        return null;
    }









}

