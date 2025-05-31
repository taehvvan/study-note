<%@ page contentType="text/html; charset=UTF-8" %>
<html>
  <head>
    <title>로그인</title>
  </head>
<body>
  <form method="post" action="LoginServlet">
      아이디: <input type="text" name="userId" /><br/>
      비밀번호: <input type="password" name="password" /><br/>
      <input type="submit" value="로그인" />
  </form>
</body>
</html>

