package Part1.BuildingLibrary;

public class Library {
    private String name;
    private Book[] books;
    private int bookCount;     // Biến đếm số lượng sách hiện có

    /**
     * Constructor để khởi tạo một thư viện.
     * @param name Tên của thư viện.
     * @param capacity Sức chứa tối đa của thư viện (kích thước của mảng books).
     */
    public Library(String name, int capacity){
        this.name=name;
        this.books= new Book[capacity];
        this.bookCount=0;       // Ban đầu chưa có sách nào
    }

    public void addBook(Book book){
        if(bookCount < books.length){
            books[bookCount] = book;
            bookCount++;
            System.out.println("Added book: "+book.getTitle());
        }
        System.out.println("Library is full. Cannot add the book: "+book.getTitle());

    }

    public void displayBooks(){
        System.out.println("\n--- Books in " + this.name + " ---");
        for(int i=0; i < bookCount -1 ; i++){
            System.out.println(books[i]);
        }
        if(bookCount == 0) System.out.println("The library is empty.");

        System.out.println("------------------------");
    }


    public Book findBookByTitle(String title){
        for (Book bk : books){
            if(bk.getTitle().equalsIgnoreCase(title)) {
                return bk;
            }

        }
        return null;
    }


}
