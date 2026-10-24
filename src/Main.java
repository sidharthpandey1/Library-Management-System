import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        library.loadBooks();

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Show Overdue Books");
            System.out.println("8. View Waiting List");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");

            int choice = readInt(sc);


            switch (choice) {


                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = readInt(sc);
                    if (id <= 0) {
                        System.out.println("Book ID must be a positive number!");
                        break;
                    }

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine().trim();

                    if (title.isEmpty()) {
                        System.out.println("Book title cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine().trim();

                    if (author.isEmpty()) {
                        System.out.println("Author name cannot be empty!");
                        break;
                    }

                    library.addBook(id, title, author);
                    break;


                case 2:
                    library.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to search: ");
                    id = readInt(sc);
                    library.searchBook(id);
                    break;

                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    id = readInt(sc);
                    library.removeBook(id);
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    id = readInt(sc);

                    System.out.print("Enter Student Name: ");
                    String student = sc.nextLine();

                    library.issueBook(id, student);
                    break;

                case 6:
                    System.out.print("Enter Book ID to return: ");
                    id = readInt(sc);
                    library.returnBook(id);
                    break;

                case 7:
                    library.showOverdueBooks();
                    break;


                case 8:
                    System.out.print("Enter Book ID: ");
                    int waitingBookId = readInt(sc);
                    library.showWaitingList(waitingBookId);
                    break;

                case 9:
                    System.out.println("Thank you for using Library Management System!");
                    sc.close();
                    return;


                default:
                    System.out.println("Invalid choice! Please select a number between 1 and 9.");
            }
            library.saveBooks();
        }
    }

    public static int readInt(Scanner sc) {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a number: ");
            }
        }
    }

}
