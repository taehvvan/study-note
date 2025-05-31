<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.User" %>
<html>
  <head>
    <title>회원 목록</title>
  </head>
<body>
  <h2>회원 목록</h2>
  <table border="1">
      <tr><th>ID</th><th>이름</th><th>이메일</th></tr>
  <%
      List<User> list = (List<User>) request.getAttribute("userList");
      for (User u : list) {
  %>
      <tr>
          <td><%= u.getId() %></td>
          <td><%= u.getName() %></td>
          <td><%= u.getEmail() %></td>
      </tr>
  <% } %>
  </table>
</body>
</html>

