package Part6.Library;

/**
 * Class Book - Đại diện cho một quyển sách
 *
 * Mô tả:
 * - Class này đại diện cho một quyển sách trong thư viện
 * - Cần implements interface IBook
 *
 * Thuộc tính:
 * - title: String - Tên sách
 * - author: String - Tác giả
 * - year: int - Năm xuất bản
 * - price: double - Giá sách
 *
 * Yêu cầu:
 * 1. Tạo constructor với đầy đủ 4 tham số
 * 2. Implement tất cả methods từ interface IBook
 * 3. Override toString() để in thông tin sách theo format:
 *    "Book[title='<title>', author='<author>', year=<year>, price=<price>]"
 */
class Book implements IBook {

    // 1. Thuộc tính (private để đảm bảo encapsulation)
    private String title;
    private String author;
    private int year;
    private double price;

    // 2. Constructor đầy đủ tham số
    public Book(String title, String author, int year, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    // 3. Implement các methods từ interface IBook

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getPrice() {
        return price;
    }

    // 4. Override toString()

    @Override
    public String toString() {
        return String.format(
                "Book[title='%s', author='%s', year=%d, price=%.2f]",
                title, author, year, price
        );
    }
}