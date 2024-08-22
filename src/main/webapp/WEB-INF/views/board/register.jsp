<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 코어 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 포매팅 태그 -->   
<%@ include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록 페이지</title>
</head>
<body>
<!-- list.jsp 에서 /borad/register 경로를 호출하면 get 메서드가 실행이 되고 폼 박스가 나옴 -->
<!-- 입력 완료를 누르면 VO객체가 만들어져서 /board/register 에 post 메서드가 실행이 된다. -->

<div class="row">
   <div class="col-lg-12">
      <h1 class="page-header">게시글 등록 페이지</h1>
   </div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->
   
<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
      	<div class="panel-heading"> Board Register</div>
      	<div class="panel-body">
      		<!--  form 박스 만들고 submit 처리 -->
      		<form role="form" action="/board/register" method="post">
      		      		
      			<div class="form-group">
      				<label>제목</label>
      				<input class="form-control" name="title">
      			</div>	<!--  title .form-group end -->
      			
      			<div class="form-group">
      				<label>내용</label>
      				<textarea class="form-control" rows="3" name="content" ></textarea>      				
      			</div> <!-- content .form-group end -->	
      			
      			<div class="form-group">
      				<label>작성자</label>
      				<input class="form-control" name="writer">
      			</div>	<!--  writer .form-group end -->
      					
      			<button type="submit" class="btn btn-default">저장</button>
      			<button type="reset" class="btn btn-default">초기화</button>		
      	    			
      		</form> <!--  form end -->
      	
      
      		</div> <!--  .panel-body end -->
        </div> <!--  .panel panel-default end -->         
   </div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->



</body>
</html>