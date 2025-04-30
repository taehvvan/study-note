<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>


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
    
<%
String apple = request.getParameter("apple");
String peach = request.getParameter("peach");
String pear = request.getParameter("pear");
String kiwi = request.getParameter("kiwi");
%>
<h1>구입 확인</h1>
<form action="buyServlet" method="post">
    <table>
        <thead>
            <tr>
                <th>상품명</th>
                <th>개수</th>
            </tr>
        </thead>
        <tbody>
            <% if (Integer.parseInt(apple) > 0) { %>
            <tr>
                <td>사과</td>
                <td><%= apple %></td>
                <input type="hidden" name="apple" value="<%= apple %>">
            </tr>
            <% } %>
            <% if (Integer.parseInt(peach) > 0) { %>
            <tr>
                <td>복숭아</td>
                <td><%= peach %></td>
                <input type="hidden" name="peach" value="<%= peach %>">
            </tr>
            <% } %>
            <% if (Integer.parseInt(pear) > 0) { %>
            <tr>
                <td>배</td>
                <td><%= pear %></td>
                <input type="hidden" name="pear" value="<%= pear %>">
            </tr>
            <% } %>
            <% if (Integer.parseInt(kiwi) > 0) { %>
            <tr>
                <td>키위</td>
                <td><%= kiwi %></td>
                <input type="hidden" name="kiwi" value="<%= kiwi %>">
            </tr>
            <% } %>
        </tbody>
    </table>
    <button type="submit">구입 확정</button>
</form>
</body>
</html>
