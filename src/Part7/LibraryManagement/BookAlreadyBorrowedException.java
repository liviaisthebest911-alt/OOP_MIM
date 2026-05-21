package Part7.LibraryManagement;

/**
 * Exception khi sách đã được mượn
 */
public class BookAlreadyBorrowedException extends Exception {
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}


