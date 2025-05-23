public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setIsbn(isbn);
    }

    public Book(int id, String title, String author, String isbn) {
        this(title, author, isbn);
        this.id = id;
    }

    public String toString() {
        return "[ID: " + id + "] " + getTitle() + " / " + getAuthor() + " / ISBN: " + getIsbn();
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
