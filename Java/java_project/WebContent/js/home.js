


function getQuestionArray(_page) {
	nowPage = _page;
	var _viewCount = 3;
	
	var param = {
		kind:"getQuestionArray",
		page:_page,
		viewCount:_viewCount
	};
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			if(data.isConfirm == "Y") {
				totalCount = data.totalCount;
				$(".papaerCount").text((_page + 1) + " / " + parseInt(totalCount / 3));
				
				questionArray = new Array();
				questionArray = data.questionArray;
				
				$(".startArea").css("display", "none");
				$(".examArea").css("display", "block");
				
				drawQuestion();
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function saveExam(_questionNo, _pick, _index) {
	var param = {
		kind:"saveExam",
		questionNo:_questionNo,
		pick:_pick
	};
	
	showLoadingBar();
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				var strPick = "";
				if(_pick == 1) {
					strPick = "a";
				} else if(_pick == 2) {
					strPick = "b";
				} else if(_pick == 3) {
					strPick = "c";
				} else if(_pick == 4) {
					strPick = "d";
				}
				
				$(".row_"+_index).find(".rowItem").removeClass("pick");
				$(".row_"+_index).find(".rowItem_"+strPick).addClass("pick");
				
				if($(".pick").length == questionArray.length) {
					$(".nextButton").removeClass("nextButtonOn").addClass("nextButtonOn");
				}
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function drawQuestion() {
	$(".nextButton").removeClass("nextButtonOn");
	
	var pickCount = 0;
	$(".paperContentArea").empty();
	var size = questionArray.length;
	for(var i=0; i<size; i++) {
		var item = questionArray[i];
		var displayNumber = item.displayNumber;
		var title = item.title;
		var content = JSON.parse(item.content);
		
		var pick = item.pick;
		if(pick > 0) {
			pickCount++;
		}
		
		var a = content[0].name;
		var b = content[1].name;
		var c = content[2].name;
		var d = content[3].name;
		var rowText = "<div class=\"row row_"+i+"\"><p class=\"rowTitle\">"+displayNumber+"."+title+"</p><p class=\"rowItem rowItem_a rowItem_"+i+(pick==1?" pick":"")+"\">(A) "+a+"</p><p class=\"rowItem rowItem_b rowItem_"+i+(pick==2?" pick":"")+"\">(B) "+b+"</p><p class=\"rowItem rowItem_c rowItem_"+i+(pick==3?" pick":"")+"\">(C) "+c+"</p><p class=\"rowItem rowItem_d rowItem_"+i+(pick==4?" pick":"")+"\">(D) "+d+"</p></div>";
		$(".paperContentArea").append(rowText);
	}
	
	if(pickCount == size) {
		$(".nextButton").removeClass("nextButtonOn").addClass("nextButtonOn");
	}
}

$(document).on("click", ".rowItem", function() {
	var selfElement = $(this);
	var pickIndex = -1;
	if(selfElement.hasClass("rowItem_a")) {
		pickIndex = 1;
	} else if(selfElement.hasClass("rowItem_b")) {
		pickIndex = 2;
	} else if(selfElement.hasClass("rowItem_c")) {
		pickIndex = 3;
	} else if(selfElement.hasClass("rowItem_d")) {
		pickIndex = 4;
	}
	
	var index = -1;
	var questionArraySize = questionArray.length;
	for(var i=0; i<questionArraySize; i++) {
		if(selfElement.hasClass("rowItem_"+i)) {
			index = i;
			break;
		}
	}
	
	if(index != -1) {
		var item = questionArray[index];
		var _questionNo = item.no;
		var _pick = pickIndex;
		saveExam(_questionNo, _pick, index);
	}
});

function onClickPrev() {
	nowPage--;
	if(nowPage == -1) {
		$(".startArea").css("display", "block");
		$(".examArea").css("display", "none");
		$(".resultArea").css("display", "none");
		return;
	}
	
	getQuestionArray(nowPage);
}

function onClickNext() {
	if(!$(".nextButton").hasClass("nextButtonOn")) {
		return;
	}
	
	nowPage++;
	var endPage = parseInt(totalCount / 3);
	if(nowPage == endPage) {
		showConfirm("제출하시겠습니까?", function() {
			checkResult();
		});
		return;
	}
	
	getQuestionArray(nowPage);
}


function resetExam() {
	var param = {
		kind:"resetExam",
	};
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			if(data.isConfirm == "Y") {
				getQuestionArray(0);
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function getExamCount() {
	var param = {
		kind:"getExamCount",
	};
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			if(data.isConfirm == "Y") {
				var _examCount = data.examCount;
				var _questionCount = data.questionCount;
				
				nowPage = parseInt(_examCount / 3);
				getQuestionArray(nowPage);
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function checkResult() {
	var param = {
		kind:"checkResult",
	};
	jQuery.ajax({
		type:"POST",
		url:("app.do"),
		dataType:"JSON",
		data:param,
		timeout:20000,
		success:function(data) {
			if(data.isConfirm == "Y") {
				questionArray = new Array();
				questionArray = data.questionArray;
				drawResult();
				
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function drawResult() {
	$(".startArea").css("display", "none");
	$(".examArea").css("display", "none");
	$(".resultArea").css("display", "block");
	
	$(".resultBoxArea").empty();
	
	var headerText = "<div class=\"box headerBox\"><h5>No.</h5><p class=\"pick\">선택</p><p class=\"answer\">정답</p></div>";
	$(".resultBoxArea").append(headerText);
	$(".resultBoxArea").append(headerText);
	$(".resultBoxArea").append(headerText);
	
	var size = questionArray.length;
	for(var i=0; i<size; i++) {
		var item = questionArray[i];
		var displayNumber = item.displayNumber;
		
		var strPick = "";
		var pick = item.pick;
		if(pick == 1) {
			strPick = "A";
		} else if(pick == 2) {
			strPick = "B";
		} else if(pick == 3) {
			strPick = "C";
		} else if(pick == 4) {
			strPick = "D";
		}
		
		var strAnswer = "";
		var answer = item.answer;
		if(answer == 1) {
			strAnswer = "A";
		} else if(answer == 2) {
			strAnswer = "B";
		} else if(answer == 3) {
			strAnswer = "C";
		} else if(answer == 4) {
			strAnswer = "D";
		}
		
		
		var rowText = "<div class=\"box\"><h5>"+displayNumber+".</h5><p class=\"pick\">"+strPick+"</p><p class=\"answer "+(pick==answer?"correct":"incorrect")+"\">"+strAnswer+"</p></div>";
		$(".resultBoxArea").append(rowText);
	}
}

function onClickHome() {
	location.reload();
}

function checkContinue() {
	showConfirm("이어서 진행하시겠습니까?", function() {
		getExamCount();
	});
}

function checkReset() {
	showConfirm("새로 진행하시겠습니까?", function() {
		resetExam();
	});
}