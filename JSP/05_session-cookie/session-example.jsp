<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String user = request.getParameter("user");
    if (user != null) {
        session.setAttribute("username", user);
    }
%>
<html>
  <head>
    <title>Session Example</title>
  </head>
<body>
  <form method="get">
      사용자 이름: <input type="text" name="user" />
      <input type="submit" value="저장" />
  </form>
  
  <%
      String username = (String) session.getAttribute("username");
      if (username != null) {
  %>
      <h2>세션에 저장된 사용자: <%= username %></h2>
  <% } %>
</body>
</html>

