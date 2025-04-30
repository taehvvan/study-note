package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/buyServlet")
public class buyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";	// DB URL
    private static final String DB_USER = "test";								// 사용자이름
    private static final String DB_PASSWORD = "password12!";					// 비밀번호

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 파라미터 값 가져오기
        String apple = request.getParameter("apple");
        String peach = request.getParameter("peach");
        String pear = request.getParameter("pear");
        String kiwi = request.getParameter("kiwi");
        
        System.out.println("username: " + username);
        System.out.println("Apple: " + apple + ", Peach: " + peach + ", Pear: " + pear + ", Kiwi: " + kiwi);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
        	Class.forName("com.mysql.cj.jdbc.Driver");
            String insertQuery = "INSERT INTO purchases (username, item_name, quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);

            if (Integer.parseInt(apple) > 0) {
                pstmt.setString(1, username);
                pstmt.setString(2, "사과");
                pstmt.setInt(3, Integer.parseInt(apple));
                pstmt.addBatch();
            }
            if (Integer.parseInt(peach) > 0) {
                pstmt.setString(1, username);
                pstmt.setString(2, "복숭아");
                pstmt.setInt(3, Integer.parseInt(peach));
                pstmt.addBatch();
            }
            if (Integer.parseInt(pear) > 0) {
                pstmt.setString(1, username);
                pstmt.setString(2, "배");
                pstmt.setInt(3, Integer.parseInt(pear));
                pstmt.addBatch();
            }
            if (Integer.parseInt(kiwi) > 0) {
                pstmt.setString(1, username);
                pstmt.setString(2, "키위");
                pstmt.setInt(3, Integer.parseInt(kiwi));
                pstmt.addBatch();
            }

            pstmt.executeBatch(); // 배치 처리로 데이터 삽입
            response.sendRedirect("success.jsp"); // 성공 후 리디렉션
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // 에러 발생 시 리디렉션
        }
    }
}
