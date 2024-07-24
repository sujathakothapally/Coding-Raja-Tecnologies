import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books;
    private Map<Integer, Patron> patrons;
    private List<BorrowRecord> borrowRecords;

    public Library() {
        this.books = new HashMap<>();
        this.patrons = new HashMap<>();
        this.borrowRecords = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    public void borrowBook(int bookId, int patronId, String borrowDate) {
        Book book = books.get(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            borrowRecords.add(new BorrowRecord(
                borrowRecords.size() + 1,
                bookId,
                patronId,
                borrowDate,
                null,
                0.0
            ));
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(int bookId, String returnDate, double fine) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setAvailable(true);
            for (BorrowRecord record : borrowRecords) {
                if (record.getBookId() == bookId && record.getReturnDate() == null) {
                    record.setReturnDate(returnDate);
                    record.setFine(fine);
                    break;
                }
            }
        }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword) || book.getGenre().contains(keyword)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Patron> searchPatrons(String keyword) {
        List<Patron> result = new ArrayList<>();
        for (Patron patron : patrons.values()) {
            if (patron.getName().contains(keyword) || patron.getContactInfo().contains(keyword)) {
                result.add(patron);
            }
        }
        return result;
    }

    public List<BorrowRecord> generateReports() {
        return new ArrayList<>(borrowRecords);
    }
}
