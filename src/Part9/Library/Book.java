package Part9.Library;

/**
 * CLASS: Book
 * MÔ TẢ: Class đại diện cho một cuốn sách trong thư viện
 *
 * THUỘC TÍNH:
 * - title: String - Tên sách
 * - author: String - Tác giả
 * - year: int - Năm xuất bản
 * - price: double - Giá sách
 * - category: String - Thể loại (Fiction, Science, History, Technology, etc.)
 *
 * YÊU CẦU:
 * 1. Implement interface IBook
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Override toString() để trả về chuỗi theo format:
 *    "Book{title='...', author='...', year=..., price=..., category='...'}"
 */
public class Book implements IBook {
    private String title;
    private String author;
    private int year;
    private double price;
    private String category;

    public Book(String title, String author, int year, double price, String category) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + String.format("%.2f", price) +
                ", category='" + category + '\'' +
                '}';
    }
}
