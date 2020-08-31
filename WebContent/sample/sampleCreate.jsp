<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<!-- <head> -->
<%@ include file="/common/head.jsp"%>
<script src="/ckeditor/ckeditor.js"></script>
<script>
window.onload = function(){
	CKEDITOR.replace( 'editor1' , {
		customConfig	: '/ckeditor/config.js'
	});
}; 

function actionLogin(){
	if(validation()){
		if (confirm("로그인을 하시겠습니까?")) {
			$.ajax({
				url : "<c:url value='/sampleJson/actionLogin.sj'/>",
				data : {
					"id"	: $("#id").val(), 
					"pw"	: $("#pw").val()
				},
				type : "POST",
				dataType : "json",
				success: function(data,response,status) {
					if(data.result == "SUCCESS"){
						alert("로그인 성공");
						location.reload(true);
					}else{
						alert("로그인 실패");
					}
				},
				error:function(request,status,error){
					alert("관리자에게 문의를 요함");
					console.log(request);
					console.log(status);
					console.log(error);
				}
			});
		}
	}
}

function actionLogout(){
	if (confirm("로그아웃을 하시겠습니까?")) {
		$.ajax({
			url : "<c:url value='/sampleJson/actionLogOut.sj'/>",
			type : "POST",
			dataType : "json",
			success: function(data,response,status) {
				if(data.result == "SUCCESS"){
					alert("로그아웃 성공");
					location.reload(true);
				}else{
					alert("로그아웃 실패");
				}
			},
			error:function(request,status,error){
				alert("관리자에게 문의를 요함");
			}
		});
	}
}

function validation(){
	if ("" == $("#id").val()) {
		alert("아이디를 입력하세요.");
		$("#id").focus();
		return false;
	}
	if ("" == $("#pw").val()) {
		alert("비밀번호를 입력하세요.");
		$("#pw").focus();
		return false;
	}
	return true;
}
</script>
<body>
<div class="wrapper">

<!-- header -->
<%@ include file="/common/header.jsp"%>
	
	<!-- Content -->
	<div class="row">
		
		<!-- 좌측 내용 -->
		<div class="leftcolumn">
			<div class="card">
			
				<h2 style="float: left;margin: 0;">샘플 게시판 등록</h2>
				<button class="btn_sav" onclick="location.href='/sample/sampleCreate.sa'">글쓰기</button>
				
		
				<!-- 게시판 등록 폼 -->
				<form id="createFrm" name="createFrm" method="post" enctype="multipart/form-data">
				
					<label for="fname">First Name</label>
					<input type="text" id="fname" name="firstname" placeholder="Your name..">
					<label for="lname">Last Name</label>
					<input type="text" id="lname" name="lastname" placeholder="Your last name..">
					<label for="country">Country</label> <select id="country" name="country">
						<option value="australia">Australia</option>
						<option value="canada">Canada</option>
						<option value="usa">USA</option>
					</select>
					
					
					<label for="subject">Subject</label>
					<textarea name="editor1" id="editor1"></textarea>

					<input type="submit" value="Submit">
				</form>
				
				<!-- // 게시판 등록 폼 -->
						
			</div>
		</div>
		<!-- // 좌측 내용 -->
		
		<!-- 우측 내용 -->
		<div class="rightcolumn">
			<div class="card" id="loginArea">
				<c:if test="${loginDto != null}">
					<h3>회원정보</h3>
					<input type="button" value="로그아웃" onclick="actionLogout();">
					<label for="">ID</label>
					<input type="text" id="" name="" value="${loginDto.getId()}" disabled="disabled">
					<label for="">이름</label>
					<input type="text" id="" name="" value="${loginDto.getName()}" disabled="disabled">
				</c:if>
				<c:if test="${loginDto == null}">
					<form id="loginFrm" id="loginFrm" method="post" >
						<h3>로그인</h3>
						<input type="button" value="로그인" onclick="actionLogin();">
						<label for="id">ID</label>
						<input type="text" id="id" name="id">
						<label for="pw">pw</label>
						<input type="password" id="pw" name="pw">
					</form>
				</c:if>
			</div>
			<div class="card">
				<h2>About Me</h2>
				<div class="fakeimg" style="height:100px;">Image</div>
				<p>Some text about me in culpa qui officia deserunt mollit anim..</p>
			</div>
			<div class="card">
				<h3>Follow Me</h3>
				<p>Some text..</p>
			</div>
		</div>
		<!-- // 우측 내용 -->
		
	</div>
	<!-- // Content -->

<!--  footer -->
<%@ include file="/common/footer.jsp"%>

</div>
</body>
</html>