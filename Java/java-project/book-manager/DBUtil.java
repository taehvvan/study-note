import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bookdb";
        String user = "root"; // MySQL 계정
        String password = "1234"; // MySQL 비밀번호
        return DriverManager.getConnection(url, user, password);
    }
}
