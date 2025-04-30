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
    
    int applePrice = 2000;
    int peachPrice = 3000;
    int pearPrice = 4000;
    int kiwiPrice = 5000;

    // 총 금액 계산
    int total = (Integer.parseInt(apple) * applePrice) +
                (Integer.parseInt(peach) * peachPrice) +
                (Integer.parseInt(pear) * pearPrice) +
                (Integer.parseInt(kiwi) * kiwiPrice);
%>
<h1>구입 확인</h1>
<form action="buyServlet" method="post">
    <table border="1" align="center" width="500">
        <thead>
            <tr>
                <th>상품명</th>
                <th>가격</th>
                <th>개수</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="50%">사과</td>
                <td width="30%">2000원</td>
                <td width="20%"><%= apple %></td>
                <input type="hidden" name="apple" value="<%= apple %>">
            </tr>
            <tr>
                <td width="50%">복숭아</td>
                <td width="30%">3000원</td>
                <td width="20%"><%= peach %></td>
                <input type="hidden" name="peach" value="<%= peach %>">
            </tr>
            <tr>
                <td width="50%">배</td>
                <td width="30%">4000원</td>
                <td width="20%"><%= pear %></td>
                <input type="hidden" name="pear" value="<%= pear %>">
            </tr>
            <tr>
                <td width="50%">키위</td>
                <td width="30%">5000원</td>
                <td width="20%"><%= kiwi %></td>
                <input type="hidden" name="kiwi" value="<%= kiwi %>">
            </tr>
            <tr>
                <td width="50%">총 금액</td>
                <td colspan="2"><%=total %>원</td>
            </tr>
        </tbody>
    </table>
    <button type="submit">구입 확정</button>
</form>
</body>
</html>
