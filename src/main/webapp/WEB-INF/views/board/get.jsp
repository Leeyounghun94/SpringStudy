<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 코어 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 포매팅 태그 -->   
<%@ include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 등록 페이지</title>
</head>
<body>
<!-- list.jsp 에서 /borad/register 경로를 호출하면 get 메서드가 실행이 되고 폼 박스가 나옴 -->
<!-- 입력 완료를 누르면 VO객체가 만들어져서 /board/register 에 post 메서드가 실행이 된다. -->

<div class="row">
   <div class="col-lg-12">
      <h1 class="page-header">게시판 글 상세 보기 페이지</h1>
   </div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->
   
<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
      	<div class="panel-heading"> Board Read Page</div>
      	<div class="panel-body">
      		<!--  form 박스 만들고 submit 처리 -->
      		<!-- <form role="form" action="/board/register" method="post"> -->
      		      
      		     <div class="form-group">
      		     	<label>번호</label>
      		     	<input class="form-control" name="bno" value="<c:out value="${ board.bno }"/>" readonly="readonly" />
      		     </div> 
      		     
      		      		
      			<div class="form-group">
      				<label>제목</label>
      				<input class="form-control" name="title" value="<c:out value="${ board.title }"/>" readonly="readonly" />
      			</div>	<!--  title .form-group end -->
      			
      			<div class="form-group">
      				<label>내용</label>
      				<textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value= "${ board.content }" />
      				</textarea>
      			</div> <!-- content .form-group end -->	
      			
      			<div class="form-group">
      				<label>작성자</label>
      				<input class="form-control" name="writer" value='<c:out value="${ board.writer }"/>' readonly="readonly" />
      			</div>	<!--  writer .form-group end -->	<!-- 등록일, 수정일 넣을려면 이것처럼 똑같이 하면 됨 -->
      					
      			<button data-oper='modify' class="btn btn-primary">수정</button>
      			<%-- onclick="location.href='/board/modify?bno=<c:out value="${ board.bno }"/>'" --%>
      			<!-- 링크 -> /board/modify?bno=게시물번호 -->
      			
      			<!-- <button data-oper='delete' class="btn btn-danger">삭제</button> -->		
      	    	<button data-oper='list' class="btn btn-info">리스트</button>
      	    	<!-- onclick="location.href='/board/list'" -->
      	    	
      	    	<!-- 수정 버튼 클릭 시 Bno를 가지고 가도록 설정 -->
      	    	<form id='operForm' action="/board/modify" method="get">
      	    		<input type="hidden" id='bno' name='bno' value='<c:out value="${board.bno}"/>'/>     	    	
      	    	</form>	
      			<!-- </form> --> <!--  form end -->
      	
      
      		</div> <!--  .panel-body end -->
        </div> <!--  .panel panel-default end -->         
   </div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->

<script type="text/javascript">
	$(document).ready(function(){
			
		var operForm = $("#operForm");	/* 56행 <form id='openForm' action="/board/modify" method="get">  */
		
		$("button[data-oper='modify']").on("click", function(e){
			
			operForm.attr("action", "/board/modify").submit();
			
		});
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list")	/* input에 있는 bno를 삭제 */
			operForm.submit();
			
		});
	
	});

</script>

</body>
</html>