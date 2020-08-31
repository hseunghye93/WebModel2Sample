<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<!-- <head> -->
<%@ include file="/common/head.jsp"%>

<body>
<div class="wrapper">

<!-- header -->
<%@ include file="/common/header.jsp"%>
	
	<!-- Content -->
	<div class="row">
		
		<!-- 좌측 내용 -->
		<div class="leftcolumn">
			<div class="card">
			
				<h2 style="float: left;margin: 0;">샘플 게시판 목록</h2>
				<button class="btn_sav" onclick="location.href='/sample/sampleCreate.sa'">글쓰기</button>
				
				<!-- 게시판 목록 -->
				<table class="list_table">
					<thead>
						<tr>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList}" var="list" varStatus="status">
							<tr boardId="${list.num}">
								<td>${list.title}</td>
								<td>${list.writer}</td>
								<td>${list.regDate}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty boardList}">
							<tr><td colspan="3">등록된 게시물이 없습니다.</td></tr>
						</c:if>
					</tbody>
				</table>
				<!-- // 게시판 목록 -->
				
				<!-- 페이징 -->
				<div class="center">
					<div class="pagination">
						<a href="sampleList.sa?curPage=${requestScope.page.start-1}">&laquo;</a>
						<c:forEach begin="${requestScope.page.start}" end="${requestScope.page.last}" var="i">
							<c:choose>
								<c:when test="${i eq requestScope.page.curPage}">
									<a class="active" href="<%=application.getContextPath()%>/sample/sampleList.sa?curPage=${i}">${i}</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/sample/sampleList.sa'/>?curPage=${i}">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<a href="sampleList.sa?curPage=${requestScope.page.last+1}">&raquo;</a>
					</div>
				</div>
				<!-- // 페이징 -->
			</div>
		</div>
		<!-- // 좌측 내용 -->
		
		<!-- 우측 내용 -->
		<div class="rightcolumn">
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