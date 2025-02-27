import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Book {
    private String title;
    private List<String> authors;

    public Book(String title, List<String> authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int numOfAuthors() {
        return authors.size();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                '}';
    }

    public static void main(String[] args) {
        List<Book> books = List.of(
                new Book("Book A", List.of("Author 1", "Author 2")),
                new Book("Book B", List.of("Author 3")),
                new Book("Book C", List.of("Author 2", "Author 4"))
        );

        List<String> authors = books.stream().map(Book::getAuthors).flatMap(List::stream).collect(Collectors.toSet()).stream().toList();
        System.out.println(authors);

        Map<Integer, List<String>> booksByNumAuthors = books.stream().collect(Collectors.groupingBy(
                Book::numOfAuthors,
                Collectors.mapping(Book::getTitle, Collectors.toList())
        ));

        System.out.println(booksByNumAuthors);

        String mostCommonAuthor = books.stream().map(Book::getAuthors).flatMap(List::stream).collect(Collectors.groupingBy(
                        author -> author,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse(null);
    }
}
