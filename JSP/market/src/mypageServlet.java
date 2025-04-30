package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mypageServlet")
public class mypageServlet extends HttpServlet {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/test";	// DB URL
    private static final String DB_USER = "test";								// 사용자이름
    private static final String DB_PASSWORD = "password12!";					// 비밀번호
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 데이터 리스트 초기화
        List<String[]> purchaseList = new ArrayList<>();

        // SQL 쿼리로 데이터 가져오기
        String query = "SELECT item_name, SUM(quantity) AS total_quantity " +
                       "FROM purchases WHERE user_id = ? GROUP BY item_name";

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            // 데이터 결과를 List에 추가
            while (rs.next()) {
                String itemName = rs.getString("item_name");
                int totalQuantity = rs.getInt("total_quantity");
                purchaseList.add(new String[]{itemName, String.valueOf(totalQuantity)});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 데이터 JSP로 전달
        request.setAttribute("purchaseList", purchaseList);
        request.getRequestDispatcher("myPage.jsp").forward(request, response);
    }
}

