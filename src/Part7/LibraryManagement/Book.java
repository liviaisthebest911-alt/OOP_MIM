package Part7.LibraryManagement;

import java.time.Year;

/**
 * CLASS 1: Book - Đại diện cho một cuốn sách
 *
 * THUỘC TÍNH:
 * - isbn: String - Mã ISBN của sách (duy nhất)
 * - title: String - Tên sách
 * - author: String - Tác giả
 * - publicationYear: int - Năm xuất bản
 * - isBorrowed: boolean - Trạng thái đã mượn hay chưa
 * - borrowedBy: String - ID của thành viên đang mượn (null nếu chưa mượn)
 *
 * YÊU CẦU:
 * 1. Implement các interface: Borrowable, Displayable, Validatable
 * 2. Constructor nhận đầy đủ tham số (trừ isBorrowed và borrowedBy - mặc định false và null)
 * 3. Implement phương thức borrow():
 *    - Kiểm tra nếu đã mượn thì throw BookAlreadyBorrowedException
 *    - Nếu chưa mượn thì đánh dấu isBorrowed = true và lưu borrowedBy
 * 4. Implement phương thức returnItem():
 *    - Đặt isBorrowed = false và borrowedBy = null
 * 5. Implement phương thức isBorrowed(): trả về trạng thái mượn
 * 6. Implement phương thức getDisplayInfo():
 *    - Trả về chuỗi với format: "ISBN: [isbn] | Title: [title] | Author: [author] | Year: [year] | Status: [Available/Borrowed by memberID]"
 * 7. Implement phương thức isValid():
 *    - Kiểm tra isbn không null và không rỗng
 *    - Kiểm tra title không null và không rỗng
 *    - Kiểm tra author không null và không rỗng
 *    - Kiểm tra publicationYear > 0 và <= năm hiện tại
 * 8. Override toString() trả về getDisplayInfo()
 * 9. Tạo các getter cho tất cả thuộc tính
 */
class Book implements Borrowable, Displayable, Validatable {
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private boolean isBorrowed;
    private String borrowedBy;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isBorrowed = false;
        this.borrowedBy = null;
    }

    @Override
    public void borrow(String memberId) throws BookAlreadyBorrowedException {
        if(isBorrowed) throw new  BookAlreadyBorrowedException("Sach da duoc muon");

        isBorrowed=true;
        this.borrowedBy=borrowedBy;
    }

    @Override
    public void returnItem() {
        this.isBorrowed = false;
        this.borrowedBy = null;

    }

    @Override
    public boolean isBorrowed() {
        return isBorrowed;
    }

    @Override
    public String getDisplayInfo() {
        String status = isBorrowed ? "Borrowed by " + borrowedBy : "Available";
        return "ISBN: "+isbn+" | Title: "+title+" | Author: "+author+" | Year: "+publicationYear+" | Status: "+status;
    }

    @Override
    public boolean isValid() {
        int currentYear = Year.now().getValue();
        return isbn != null && !isbn.isEmpty()
                && title != null && !title.isEmpty()
                && author != null && !author.isEmpty()
                && publicationYear > 0 && publicationYear <= currentYear;
    }

    @Override
    public String toString() {
        return getDisplayInfo();
    }


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }
}