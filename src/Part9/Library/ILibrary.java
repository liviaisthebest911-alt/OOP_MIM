package Part9.Library;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface ILibrary {
    void addBook(IBook book);
    java.util.List<IBook> getAllBooks();
    java.util.List<IBook> findBooksByAuthor(String author);
    java.util.List<IBook> findBooksByCategory(String category);
    java.util.List<IBook> findBooksInPriceRange(double minPrice, double maxPrice);
    java.util.List<IBook> getTop3MostExpensiveBooks();
    double getTotalValue();
    java.util.Map<String, Long> getBookCountByCategory();
    java.util.List<String> getAllAuthors();
    IBook getCheapestBook();
}
