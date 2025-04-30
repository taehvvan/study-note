<%@page import="com.toeic.api.app.AppInformationManager"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html; charset=utf-8" language="java"
import="com.toeic.util.StringUtil"
import="com.toeic.util.DateUtil"
import="java.io.BufferedWriter"
import="java.io.FileWriter"
import="java.io.IOException"
import="java.io.Writer"
%>

<%
//카카오톡에 로그인되어 저장된 개인 아이디는 test1이라고 가정함(참고로 암호화는 생략)
String _userId = "test1";

JSONObject _jsonObject = AppInformationManager.getInstance().getMyInformation(_userId);
String _pictureUrl = _jsonObject.getString("pictureUrl");
String _nickName = _jsonObject.getString("nickName");
String _birthDay = _jsonObject.getString("birthDay");
String _sex = _jsonObject.getString("sex");

String _displayPictureUrl = "images/ico_profile_empty.png";
if(_pictureUrl != null && _pictureUrl.length() > 0) {
	_displayPictureUrl = _pictureUrl;
}

String _nowDate = DateUtil.getTodayDate("yyyyMMdd_HHmmss");
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>

<title>카카오톡 내정보 비슷하게 따라해보기</title>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

<link rel="stylesheet" type="text/css" media="all" href="css/jquery.ui.min.css" />
<link rel="stylesheet" type="text/css" href="css/reset.css?v=1" />
<link rel="stylesheet" type="text/css" href="css/my.css?v=<%= _nowDate%>" />
<link rel="stylesheet" type="text/css" media="all" href="css/module.css?v=<%= _nowDate%>" />
<link rel="stylesheet" type="text/css" media="all" href="css/popup.css?v=<%= _nowDate%>" />

<link rel="shortcut icon" href="images/favicon.ico">

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/module.js?v=<%= _nowDate%>"></script>

</head>

<body>
<div class="box">
	<div class="topArea">
		<h1>내 정보 관리</h1>
		<div class="pictureBackground">
			<img id="userPicture_img" src="<%= _displayPictureUrl %>" />
			<div class="cameraBackground">
				<img id="camera_img" src="images/ico_camera.png" />
			</div>
		</div>		
	</div>
	<div class="contentArea">
		<div class="row">
			<h1>닉네임</h1>
			<input id="nickName_input" value="<%= _nickName %>" maxlength="10" />
		</div>
		<div class="row">
			<h1>생일</h1>
			<input id="birthDay_input" value="<%= _birthDay %>" maxlength="10" />
		</div>
		<div class="row">
			<h1>성별</h1>
			<input id="sex_input" value="<%= _sex %>" maxlength="8" />
		</div>
		
		<button class="commonButton orangeButton" onclick="updateMyInformation();">저장</button>
	</div>
</div>
<script type="text/javascript" src="js/my.js"></script>

<input id="userId_input" type="hidden" value="<%= _userId %>" />
<input id="pictureUrl_input" type="hidden" value="<%= _pictureUrl %>" />
<input id="fileUploadInput" type="file" class="fileUploadInput" onchange="uploadFile(this)" accept="image/*" />
</body>
</html>