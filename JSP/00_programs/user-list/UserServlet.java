import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            UserDAO dao = new UserDAO();
            List<User> list = dao.getAllUsers();
            request.setAttribute("userList", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

