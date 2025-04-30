<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String username = (String) session.getAttribute("username");

    if (username == null) {
        // 세션에 username이 없으면 로그인 페이지로 리다이렉트
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <style>
    	header {
            position: fixed;
            top: 0;
            right: 0;
            background-color: #f4f4f4;
            padding: 10px 20px;
            border-bottom-left-radius: 10px;
            text-align: right;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        header p {
            margin: 0;
            font-size: 16px;
            color: #333;
        }
        header a {
            margin-left: 10px;
            text-decoration: none;
            color: #007bff;
            font-size: 14px;
        }
        header a:hover {
            text-decoration: underline;
        }
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        h1 {
            font-size: 36px;
            margin-top: 50px;
        }
        p {
            font-size: 18px;
            color: #333;
        }
        .button-container {
            display: inline-block;
            margin-top: 30px;
        }
        .button-container button {
            display: block;
            width: 300px;
            padding: 15px;
            margin: 10px 0;
            font-size: 18px;
            background-color: #fff;
            border: 2px solid #333;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .button-container button:hover {
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>
	<header>
        <p><%= username %>님, 환영합니다.</p>
        <a href="myPage.jsp">마이페이지</a>
        <a href="logout">로그아웃</a>
    </header>
    
    <p>물건 구입하기</p>
    <form action="checkProduct.jsp" method="POST">
	    <table border="1" width="550" height="300" align="center">
	    	<tr height="20%">
	    		<th width="50%">상품명</th>
	    		<th width="30%">가격</th>
	    		<th width="20%">개수(1~10개)</th>
	    	</tr>
	    	<tr align="center">
	    		<th width="50%">사과</th>
	    		<th width="30%">2000원</th>
	    		<th width="20%"><input type="number" name="apple" value="1" min="1" max="10"></th>
	    	</tr>
	    	<tr align="center">
	    		<th width="50%">복숭아</th>
	    		<th width="30%">3000원</th>
	    		<th width="20%"><input type="number" name="peach" value="1" min="1" max="10"></th>
	    	</tr>
	    	<tr align="center">
	    		<th width="50%">배</th>
	    		<th width="30%">4000원</th>
	    		<th width="20%"><input type="number" name="pear" value="1" min="1" max="10"></th>
	    	</tr>
	    	<tr align="center">
	    		<th width="50%">키위</th>
	    		<th width="30%">5000원</th>
	    		<th width="20%"><input type="number" name="kiwi" value="1" min="1" max="10"></th>
	    	</tr>
	    	<tr align="center">
	    		<th colspan="3"><input type="submit" value="구입"></th>
	    	</tr>
	    
	    </table>
    </form>
</body>
</html>
