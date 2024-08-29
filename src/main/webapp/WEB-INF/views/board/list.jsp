<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL 코어 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- JSTL 포매팅 태그 -->
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">MBC 아카데미 게시판 리스트</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->


<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				BoardController List
				<button id="regBtn" type="button" class="btn btn-xs pull-right">새 게시물 등록</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="boardlist">
						<!-- 객체를 반복적으로 돌린다(list 객체) -->
						<tr>
							<td><c:out value="${ boardlist.bno }" /></td>
							<!-- 1열 -->
							<td><a
								href='/board/get?bno=<c:out value="${ boardlist.bno }" /> '><c:out
										value="${ boardlist.title }" /></a></td>
							<!-- 2열  -->
							<td><c:out value="${ boardlist.writer }" /></td>
							<!-- 3열  -->
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ boardlist.regdate }" /></td>
							<!-- 4열 -->
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ boardlist.updateDate }" /></td>
							<!-- 4열 -->
						</tr>
					</c:forEach>
				</table>
				
				<div class='row'>
					<div class="col-lg-12">
					
						<form id='seachForm' action="/board/list" method='get'>
							<select name='type'>
								<option value=""
								<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>선택♥</option>
									<option value="T"<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
									<option value="C"<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
									<option value="W"<c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
									<option value="TC"<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목 or 내용</option>
									<option value="TW"<c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목 or 작성자</option>
									<option value="TWC"<c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목 or 내용 or 작성자</option>								
							</select>
							<input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>' />
							<input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum}"/>' />
							<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}"/>' />
							<button class='btn btn-default'>검색</button>
						</form>					
					</div>
				</div>
			</div>
			<!-- /.panel-body end -->
		</div>
		<!-- "panel panel-default end -->
	</div>
	<!--  col-lg-12 end -->
</div>
<!-- row end-->

<div class="row">
	<div class="col-lg-12">

		<div class='pull-right'>
			<ul class="pagination">

				<c:if test="${pageMaker.prev}">
					<li class="paginate_button previous"><a href="#">Previous</a>
					</li>
				</c:if>

				<c:forEach var="num" begin="${pageMaker.startPage}"
					end="${pageMaker.endPage}">
					<li
						class='paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} '><a
						href="${num}">${num}</a></li>
				</c:forEach>

				<c:if test="${pageMaker.next}">
					<li class="paginate_button next"><a href="#">Next</a></li>
				</c:if>

			</ul>
		</div>

		<form id='actionForm' action="/board/list" method='get'>
			<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
			<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
			<input type='hidden' name='type' value="${pageMaker.cri.type}"/>
			<input type='hidden' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'>
		</form>

		

	</div>

</div>

<!-- Modal 추가 -> 요즘 트렌트는 alert 대신 modal을 쓴다 ! -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">알립니다.</h4>
			</div>
			<div class="modal-body">처리가 완료 되었습니다.</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary">저장</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script type="text/javascript">
	/* 자바 스크립트   */
	$(document).ready(
			function() { // 문서의 준비단계에서 실행

				var result = '<c:out value="${result}"/>'; /* controller에서 flashAttribute를 통해 1회용으로 넘어오는 값  */

				checkModal(result); //아래에 있는 function checkModal(result) 실행 코드

				history.replaceState({}, null, null);

				function checkModal(result) {// bno를 받음

					if (result === '' || history.state) {// result값이 null 이면? 
						return;
					}

					if (parseInt(result) > 0) {// 게시물의 번호가 0보다 크다면?
						$(".modal-boby").html(
								"게시글" + parseInt(result) + "번이 등록 되었습니다.");
						// class .modal-body 에 값을 변경하는 코드
					}

					$("#myModal").modal("show"); // 변경된 모달창을 띄운다.
					// #MyMOdal 52행 근처에 id="myModal"
				}

				$("#regBtn").on("click", function() {

					self.location = "/board/register";
				});

				var actionForm = $("#actionForm");

				$(".paginate_button a").on("click", function(e) {

							e.preventDefault();

							console.log('click');

							actionForm.find("input[name='pageNum']").val(
									$(this).attr("href"));
							actionForm.submit();
						});
				
				$(".move").on("click", function(e) {
					
					e.preventDefault();
					actionForm.append("<input type='hidden' name='bno' value='"+
				$(this).attr("href")+"'>");
					actionForm.attr("action", "/board/get");
					actionForm.submit();
				});
				
				
				var searchForm = $("#seachForm");
				
				$("#searchForm button").on("click", function(e) {
					
					if(!searchForm.find("option:selected").val()) {
						alert("검색종류를 선택하시오!");
						return false;
					}
					
					if(!searchForm.find("input[name='keyword']").val()) {
						alert("키워드를 선택하시오!");
						return false;
					}
					
					searchForm.find("input[name='pageNum']").val("1");
					e.preventDefault();
					
					searchForm.submit();
					
				});	
												
			});
</script>

<%@include file="../includes/footer.jsp"%>