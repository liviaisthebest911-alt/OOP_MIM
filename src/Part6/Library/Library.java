package Part6.Library;



import java.util.*;
import java.util.stream.Collectors;

public class Library implements ILibrary {
    private List<IBook> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(IBook book) {
        if (book != null) {
            books.add(book);
        }
    }

    @Override
    public void removeBook(String title) {
        if (title == null) return;

        books.removeIf(b -> title.equalsIgnoreCase(b.getTitle()));
    }

    @Override
    public IBook findBookByTitle(String title) {
        if (title == null) return null;

        return books.stream()
                .filter(b -> title.equalsIgnoreCase(b.getTitle()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<IBook> findBooksByAuthor(String author) {
        if (author == null) return List.of();

        return books.stream()
                .filter(b -> author.equalsIgnoreCase(b.getAuthor()))
                .toList();
    }

    @Override
    public List<IBook> getAllBooks() {
        return books;
    }

    @Override
    public int getTotalBooks() {
        return books.size();
    }

    @Override
    public double getTotalValue() {
        return books.stream()
                .mapToDouble(b -> b.getPrice())
                .sum();
    }

    @Override
    public List<IBook> sortBooksByPrice() {
        return books.stream()
                .sorted(Comparator.comparingDouble(b -> b.getPrice()))
                .toList();
    }

    @Override
    public String toString() {
        return books.stream()
                .map(b -> b.toString())
                .collect(Collectors.joining("\n"));
    }
}