<%@page contentType="text/html; charset=utf-8" language="java"
import="com.toeic.util.DateUtil"
%>

<%
String _nowDateTemp = DateUtil.getTodayDate("yyyyMMdd_HHmmss");
%>
<link rel="stylesheet" type="text/css" media="all" href="css/login.css?v=<%= _nowDateTemp%>">
<div class="loginArea">
	<div class="loginBox">
		<h1>테스트 로그인창</h1>
		<input type="text" class="loginIdInput" maxlength="20" placeholder="User ID" onkeydown="if(event.keyCode==13) {checkLogin(); return true;}"/>
		<input type="password" class="loginPasswordInput" maxlength="100" placeholder="PSWD" onkeydown="if(event.keyCode==13) {checkLogin(); return true;}" />
		<button class="blueButton loginButton" onclick="checkLogin()">LOGIN</button>
	</div>
	<p class="joinText">아이디가 없으신가요?</p>
</div>


<div id="joinPopup" class="popupArea joinPopup">
	<h1>간편 회원가입</h1>
	
	<input type="text" class="joinInput join_userIdInput" placeholder="User ID" maxlength="100" />
	<input type="password" class="joinInput join_passwordInput" placeholder="비밀번호 입력" maxlength="20" />
	<input type="password" class="joinInput join_retryPasswordInput" placeholder="비밀번호 재입력" maxlength="20" onkeydown="if(event.keyCode==13) {checkJoin(); return true;}"/>
	<input type="text" class="joinInput join_nameInput" placeholder="이름 입력" maxlength="20" />
	
	<div class="popupButtonArea">
		<button class="grayButton popupButton popupCancelButton" onclick="closePopup()">취소</button>
		<button class="blueButton popupButton popupConfirmButton" onclick="checkJoin()">가입</button>
	</div>
</div>

<script type="text/javascript" src="js/login.js?v=<%= _nowDateTemp%>"></script>