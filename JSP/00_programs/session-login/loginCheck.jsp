<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");

    if ("admin".equals(id) && "1234".equals(pw)) {
        session.setAttribute("loginUser", id);
        out.println("<h2>" + id + "님 로그인 성공</h2>");
        out.println("<a href='logout.jsp'>로그아웃</a>");
    } else {
        out.println("<h2>로그인 실패</h2>");
    }
%>
