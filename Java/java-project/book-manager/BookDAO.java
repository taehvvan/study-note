import java.sql.*;
import java.util.*;

public class BookDAO {
    public void insertBook(Book book) {
        String sql = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.executeUpdate();
            System.out.println("도서가 등록되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Book(rs.getInt("id"), rs.getString("title"),
                        rs.getString("author"), rs.getString("isbn")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void searchBooks(String keyword) {
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(new Book(rs.getInt("id"), rs.getString("title"),
                        rs.getString("author"), rs.getString("isbn")));
            }
            if (!found) {
                System.out.println("검색 결과가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("도서가 삭제되었습니다.");
            } else {
                System.out.println("해당 ID의 도서가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
