package Part9.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * CLASS: Library
 * MÔ TẢ: Class quản lý thư viện sách, sử dụng Java Stream và Lambda
 *
 * THUỘC TÍNH:
 * - books: List<IBook> - Danh sách các cuốn sách trong thư viện
 *
 * YÊU CẦU:
 * 1. Implement interface ILibrary
 * 2. Khởi tạo ArrayList trong constructor
 * 3. Implement các methods SAU ĐÂY PHẢI SỬ DỤNG JAVA STREAM VÀ LAMBDA:
 *
 *    - addBook(IBook book): Thêm sách vào thư viện
 *
 *    - getAllBooks(): Trả về tất cả sách
 *
 *    - findBooksByAuthor(String author): Tìm tất cả sách của một tác giả
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findBooksByCategory(String category): Tìm sách theo thể loại
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findBooksInPriceRange(double minPrice, double maxPrice):
 *      Tìm sách trong khoảng giá [minPrice, maxPrice]
 *
 *    - getTop3MostExpensiveBooks(): Lấy 3 cuốn sách đắt nhất
 *      (sắp xếp giảm dần theo giá, giới hạn 3 cuốn)
 *
 *    - getTotalValue(): Tính tổng giá trị tất cả sách
 *      (sử dụng reduce hoặc sum)
 *
 *    - getBookCountByCategory(): Đếm số sách theo từng thể loại
 *      (trả về Map<String, Long>, sử dụng Collectors.groupingBy và counting)
 *
 *    - getAllAuthors(): Lấy danh sách tác giả duy nhất, đã sắp xếp
 *      (sử dụng distinct và sorted)
 *
 *    - getCheapestBook(): Tìm sách rẻ nhất
 *      (sử dụng min với Comparator, trả về null nếu không có sách)
 *
 * 4. Override toString() để trả về:
 *    "Library{totalBooks=..., totalValue=...}"
 */
public class Library implements ILibrary {
    private List<IBook> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(IBook book) {
        books.add(book);
    }

    @Override
    public List<IBook> getAllBooks() {
        return books.stream()
                .collect(Collectors.toList());
    }
    @Override
    public List<IBook> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<IBook> findBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<IBook> findBooksInPriceRange(double minPrice, double maxPrice) {
        return books.stream()
                .filter(book -> book.getPrice() >= minPrice && book.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<IBook> getTop3MostExpensiveBooks() {
        return books.stream()
                .sorted(Comparator.comparingDouble(IBook::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalValue() {
        return books.stream()
                .mapToDouble(IBook::getPrice)
                .sum();
    }

    @Override
    public Map<String, Long> getBookCountByCategory() {
        return books.stream()
                .collect(Collectors.groupingBy(IBook::getCategory, Collectors.counting()));
    }

    @Override
    public List<String> getAllAuthors() {
        return books.stream()
                .map(IBook::getAuthor)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public IBook getCheapestBook() {
        return books.stream()
                .min(Comparator.comparingDouble(IBook::getPrice))
                .orElse(null);
    }
    @Override
    public String toString() {
        return String.format("Library{totalBooks=%d, totalValue=%.2f}",
                books.size(), getTotalValue());
    }
}