<html>
<head>
<meta charset="utf-8">
<link href="style.css" rel="stylesheet">
</head>
<body>
<?php
	$gender = $_POST["gender"];
	$email = $_POST["email"];
	
?>
	<ul>
		<li>성별 : <?= $gender?></li>
		<li>이메일 : <?= $email?></li>
	</ul>
</body>
</html>
