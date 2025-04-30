$("#camera_img").click(function() {
    $("#fileUploadInput").trigger("click");
});
function uploadFile(input) {
	var maxFileSize = 10 * 1024 * 1024;
	var file = input.files[0];
	var fileSize = file.size;
	if(fileSize > maxFileSize) {
		showAlert("10MB 미만의 파일만 첨부 가능합니다.");
		return;
	}
	
	var file = input.files[0];
	if(input.files && file) {
		var reader = new FileReader();
  	reader.readAsDataURL(file);

		var formData = new FormData();
		formData.append('file', file);
		showLoadingBar();
		jQuery.ajax({
			url:"file.do",
			type:"POST",
			dataType:"JSON",
			data:formData,
			beforeSend:function(xhr){
	            xhr.setRequestHeader("kind", "uploadFile");
	        },
			processData:false,
			contentType:false,
			success: function(data, textStatus, jqXHR) {
				hideLoadingBar();
				if(data.isConfirm == "Y") {
					var fileUrl = data.fileUrl;
					var fileName = data.fileName;
					$("#userPicture_img").attr("src", fileUrl);
					$("#pictureUrl_input").val(fileUrl);
				} else {
					showAlert("파일 업로드에 실패했습니다.", null);
				}
			}, error: function(jqXHR, textStatus, errorThrown) {
				hideLoadingBar();
				showAlert("파일 업로드에 실패했습니다.", null);
			}
		});
	}
}

function updateMyInformation() {
    let _userId = $("#userId_input").val();
    let _pictureUrl = $("#pictureUrl_input").val();
    let _nickName = $("#nickName_input").val();
    let _birthDay = $("#birthDay_input").val();
    let _sex = $("#sex_input").val();
    
	var param = {
		"kind":"updateMyInformation",
		"userId":_userId,
		"pictureUrl":_pictureUrl,
		"nickName":_nickName,
		"birthDay":_birthDay,
		"sex":_sex
	};
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			if(data.isConfirm == "Y") {
				alert("저장되었습니다.");
				location.reload();
			} else {
				alert("수정에 실패했습니다. 데이터를 확인해 주세요.");
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}