<%@page contentType="text/html; charset=utf-8" language="java"
import="com.toeic.util.StringUtil"
import="com.toeic.util.DateUtil"
import="java.io.BufferedWriter"
import="java.io.FileWriter"
import="java.io.IOException"
import="java.io.Writer"
%>

<%
String _userId = StringUtil.replaceNullForServlet((String)session.getAttribute("userId"));
String _page = StringUtil.replaceNull(request.getParameter("page"));
String _pageUrl = "page/login.jsp";

String _nowDate = DateUtil.getTodayDate("yyyyMMdd_HHmmss");
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>

<title>toeic</title>

<meta charset="utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

<link href="https://fonts.googleapis.com/css2?family=Hi+Melody&display=swap" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="css/jquery.ui.min.css" />
<link rel="stylesheet" type="text/css" href="css/font.css?v=3" />
<link rel="stylesheet" type="text/css" href="css/reset.css?v=5" />
<link rel="stylesheet" type="text/css" media="all" href="css/module.css?v=<%= _nowDate%>" />
<link rel="stylesheet" type="text/css" media="all" href="css/popup.css?v=<%= _nowDate%>" />

<link rel="shortcut icon" href="/favicon.ico">

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/module.js?v=<%= _nowDate%>"></script>

<style>
.ui-datepicker{font-size:12px;width:180px}
.ui-datepicker select{-moz-appearance:none;-webkit-appearance:none;appearance:none;text-align-last:center}
.ui-datepicker select.ui-datepicker-year{width:40px;font-size:10px}
.ui-datepicker select.ui-datepicker-month{width:30px;font-size:10px;margin-left:10px}
</style>
</head>

<body>
<div class="imagePopupArea">
</div>
<div class="entireArea">
	<jsp:include page="<%=_pageUrl %>">
		<jsp:param name="nowDate" value="<%= _nowDate %>"></jsp:param>
   	</jsp:include>
</div>
<%@include file="page/popup.jsp" %>

<script>
$(function() {
	$(".searchDateInput").datepicker();
});
$.datepicker.setDefaults({
	dateFormat: 'yy-mm-dd',
	showMonthAfterYear: true,
	yearSuffix: '/',
	changeYear: true,
	changeMonth: true
});
</script>
<script type="text/javascript" src="js/moduleAfter.js?v=<%= _nowDate%>"></script>
</body>
</html>