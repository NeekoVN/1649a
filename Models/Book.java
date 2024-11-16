package Models;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Integer quantity;

    public Book(Integer id, String title, String author, Integer quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Book ID: " + id + "\nTitle: " + title + "\nAuthor: " + author + "\nQuantity: " + quantity;
    }
}
