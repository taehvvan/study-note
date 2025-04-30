package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")		// "/register" URL로 요청을 받음
public class RegisterServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";	// DB URL
    private static final String DB_USER = "test";								// DB 사용자 이름
    private static final String DB_PASSWORD = "password12!";					// DB 비밀번호

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// 클라이언트로부터 전달받은 파라미터(아이디와 비밀번호) 추출
    	String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (
        	// 데이터베이스 연결 생성
        	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	// 사용자 정보를 추가하기 위한 SQL문
        	PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")
            ){
        	Class.forName("com.mysql.cj.jdbc.Driver");
            
            insertStatement.setString(1, username);		// 아이디 설정
            insertStatement.setString(2, password);		// 비밀번호 설정
            insertStatement.executeUpdate();			// 데이터베이스에 정보 삽입

            response.sendRedirect("login.jsp"); // 성공 시 로그인 화면으로 이동
            

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
