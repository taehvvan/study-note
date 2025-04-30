function checkLogin() {
	var idElement = $(".loginIdInput");
	var passwordElement = $(".loginPasswordInput");

	var _id = idElement.val();
	var _password = passwordElement.val();
	if(!_id) {
		showAlert("아이디를 입력해 주세요.", function() {
			idElement.focus();
		});
		return;
	}

	if(!_password) {
		showAlert("비밀번호를 입력해 주세요.", function() {
			passwordElement.focus();
		});
		return;
	}
	if(String(_password).length < 4) {
		showAlert("비밀번호를 4자리이상 입력해 주세요.", function() {
			passwordElement.focus();
		});
		return;
	}

	var param = {
		kind:"login",
		userId:_id,
		password:_password
	};
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			if(data.isConfirm == "Y") {
				location.replace("index.jsp");
			} else {
				showAlert("아이디 또는 비밀번호가 일치하지 않습니다.", function() {
					passwordElement.focus();
				});
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}




function checkJoin() {
	var _userId = $(".join_userIdInput").val();
	var _password = $(".join_passwordInput").val();
	var _retryPassword = $(".join_retryPasswordInput").val();
	var _name = $(".join_nameInput").val();
	
	if(String(_userId).length == 0) {
		showAlert("아이디를 입력해 주세요.", function() {
			$(".join_userIdInput").focus();
		});
		return;
	}
	
	if(String(_password).length < 4) {
		showAlert("비밀번호를 4자리 이상 입력해 주세요.", function() {
			$(".join_passwordInput").focus();
		});
		return;
	}
	
	if(String(_retryPassword) != String(_password)) {
		showAlert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해 주세요.", function() {
			$(".join_retryPasswordInput").focus();
		});
		return;
	}

	if(String(_name).length == 0) {
		showAlert("이름을 입력해 주세요.", function() {
			$(".join_nameInput").focus();
		});
		return;
	}
	
	join(_userId, _password, _name);
}

function join(_userId, _password, _name) {
	showLoadingBar();
	var param = {
		kind:"join",
		userId:_userId,
		password:_password,
		name:_name
	};
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				showAlert("회원가입이 완료되었습니다. 로그인 해주세요.", function() {
					closePopup();
					
					$(".loginIdInput").val(_userId);
					$(".loginPasswordInput").focus();
				});
			} else {
				if(data.isExistUser == "Y") {
					showAlert("중복된 회원이 존재하여, 등록에 실패했습니다.", null);
				} else {
					showAlert("회원 등록에 실패했습니다.", null);
				}
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

$(".joinText").click(function() {
	showTinyBackground();
	$("#joinPopup").css("display", "block");
	$(".join_userIdInput").val("");
	$(".join_passwordInput").val("");
	$(".join_retryPasswordInput").val("");
	$(".join_nameInput").val("");
	$(".join_userIdInput").focus();
});
