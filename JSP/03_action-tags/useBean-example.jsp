<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="person" class="model.Person" scope="page" />
<jsp:setProperty name="person" property="name" value="홍길동" />
<jsp:setProperty name="person" property="age" value="25" />

<html>
  <head>
    <title>useBean Example</title>
  </head>
<body>
  <h2>이름: <jsp:getProperty name="person" property="name" /></h2>
  <h2>나이: <jsp:getProperty name="person" property="age" /></h2>
</body>
</html>

