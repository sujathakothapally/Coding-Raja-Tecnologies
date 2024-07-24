import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        // Sample Data
        library.addBook(new Book(1, "Book1", "Author1", "Genre1", true));
        library.addBook(new Book(2, "Book2", "Author2", "Genre2", true));
        library.addPatron(new Patron(1, "Patron1", "Contact1"));
        library.addPatron(new Patron(2, "Patron2", "Contact2"));

        boolean running = true;
        while (running) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Patron");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Search Patrons");
            System.out.println("7. Generate Reports");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Book
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Is Available (true/false): ");
                    boolean isAvailable = scanner.nextBoolean();
                    library.addBook(new Book(bookId, title, author, genre, isAvailable));
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    // Add Patron
                    System.out.print("Enter Patron ID: ");
                    int patronId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Patron Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Info: ");
                    String contactInfo = scanner.nextLine();
                    library.addPatron(new Patron(patronId, name, contactInfo));
                    System.out.println("Patron added successfully.");
                    break;

                case 3:
                    // Borrow Book
                    System.out.print("Enter Book ID to Borrow: ");
                    int borrowBookId = scanner.nextInt();
                    System.out.print("Enter Patron ID: ");
                    int borrowPatronId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Borrow Date (YYYY-MM-DD): ");
                    String borrowDate = scanner.nextLine();
                    library.borrowBook(borrowBookId, borrowPatronId, borrowDate);
                    System.out.println("Book borrowed successfully.");
                    break;

                case 4:
                    // Return Book
                    System.out.print("Enter Book ID to Return: ");
                    int returnBookId = scanner.nextInt();
                    System.out.print("Enter Return Date (YYYY-MM-DD): ");
                    String returnDate = scanner.nextLine();
                    System.out.print("Enter Fine Amount: ");
                    double fine = scanner.nextDouble();
                    library.returnBook(returnBookId, returnDate, fine);
                    System.out.println("Book returned successfully.");
                    break;

                case 5:
                    // Search Books
                    System.out.print("Enter keyword to search for books: ");
                    String bookKeyword = scanner.nextLine();
                    List<Book> books = library.searchBooks(bookKeyword);
                    for (Book book : books) {
                        System.out.println("Book ID: " + book.getId());
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Genre: " + book.getGenre());
                        System.out.println("Available: " + book.isAvailable());
                        System.out.println();
                    }
                    break;

                case 6:
                    // Search Patrons
                    System.out.print("Enter keyword to search for patrons: ");
                    String patronKeyword = scanner.nextLine();
                    List<Patron> patrons = library.searchPatrons(patronKeyword);
                    for (Patron patron : patrons) {
                        System.out.println("Patron ID: " + patron.getId());
                        System.out.println("Name: " + patron.getName());
                        System.out.println("Contact Info: " + patron.getContactInfo());
                        System.out.println();
                    }
                    break;

                case 7:
                    // Generate Reports
                    List<BorrowRecord> records = library.generateReports();
                    for (BorrowRecord record : records) {
                        System.out.println("Borrow Record ID: " + record.getId());
                        System.out.println("Book ID: " + record.getBookId());
                        System.out.println("Patron ID: " + record.getPatronId());
                        System.out.println("Borrow Date: " + record.getBorrowDate());
                        System.out.println("Return Date: " + record.getReturnDate());
                        System.out.println("Fine: " + record.getFine());
                        System.out.println();
                    }
                    break;

                case 8:
                    // Exit
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        }

        scanner.close();
    }
}
