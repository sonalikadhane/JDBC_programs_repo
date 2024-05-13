package jdbc_programs;

import java.sql.*;
import java.util.*;

class BookstoreDAO {
    private Connection connection;

    public BookstoreDAO() {
        try {
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/BookStoreDB";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        try {
            // Prepare SQL statement
            String sql = "INSERT INTO Books (book_name, book_category, book_price, book_quantity, author_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getCategory());
            statement.setDouble(3, book.getPrice());
            statement.setInt(4, book.getQuantity());
            statement.setInt(5, book.getAuthorId());
            // Execute SQL statement
            statement.executeUpdate();
            System.out.println("Book added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAuthor(Author author) {
        try {
            // Prepare SQL statement
            String sql = "INSERT INTO Authors (author_name, author_gmail, author_phoneno) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author.getAuthorName());
            statement.setString(2, author.getGmail());
            statement.setString(3, author.getPhoneNumber());
            // Execute SQL statement
            statement.executeUpdate();
            System.out.println("Author added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Book {
   public String bookName;
   public String category;
  public double price;
    public int quantity;
    public int authorId;

    // Getters and setters
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}

class Author {
   public String authorName;
   public String gmail;
  public String phoneNumber;

    // Getters and setters
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

public class OnlineBookStores {
    public static void main(String[] args) {
        // Initialize BookstoreDAO object
        BookstoreDAO dao = new BookstoreDAO();
        Scanner sc = new Scanner(System.in);

        // Add a book
        Book book = new Book();
        System.out.print("Enter book name: ");
        book.setBookName(sc.nextLine());
        System.out.print("Enter book category: ");
        book.setCategory(sc.nextLine());
        System.out.print("Enter book price: ");
        book.setPrice(Double.parseDouble(sc.nextLine()));
        System.out.print("Enter book quantity: ");
        book.setQuantity(Integer.parseInt(sc.nextLine()));

        // Add an author
        Author author = new Author();
        System.out.print("Enter author name: ");
        author.setAuthorName(sc.nextLine());
        System.out.print("Enter author Gmail: ");
        author.setGmail(sc.nextLine());
        System.out.print("Enter author phone number: ");
        author.setPhoneNumber(sc.nextLine());

        // Add the author to the database
        dao.addAuthor(author);
        // Get the author's ID
        // You would typically query the database for the author ID based on the added
        // author's information
        // For simplicity, assuming it's 1
        int authorId = 1;
        // Set the author ID to the book
        book.setAuthorId(authorId);
        // Add the book to the database
        dao.addBook(book);
    }
}