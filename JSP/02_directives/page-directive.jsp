<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<html>
  <head>
    <title>Page Directive</title>
  </head>
<body>
  <%
      Date now = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  %>
  <h2>현재 시간: <%= sdf.format(now) %></h2>
</body>
</html>

