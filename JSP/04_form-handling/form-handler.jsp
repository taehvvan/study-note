<%@ page contentType="text/html; charset=UTF-8" %>
<html>
  <head>
    <title>Form Example</title>
  </head>
<body>
  <form method="post" action="form-handler.jsp">
      이름: <input type="text" name="name" />
      <input type="submit" value="전송" />
  </form>
  
  <%
      String name = request.getParameter("name");
      if (name != null && !name.trim().isEmpty()) {
  %>
      <h2>입력한 이름: <%= name %></h2>
  <% } %>
  </body>
</html>

