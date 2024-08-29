<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 코어 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 포매팅 태그 -->
<%@ include file="../includes/header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- list.jsp 에서 /board/register 경로를 호출하면 get메서드가 실행이 되고 폼 박스가 나온다.  -->
<!-- 입력 완료를 누르면 VO객체가 만들어져서 /board/register 에 post 메서드가 실행 됨.  -->

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"> 게시글 수정 페이지 </h1>
	</div>	<!-- .col-lg-12 end -->
</div> <!-- .row end  -->

<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
      	<div class="panel-heading"> Board 수정 페이지임  ㅋ</div>
      	<div class="panel-body">
      	<!--  form 박스 만들고 submit 처리 -->
				<form role="form" action="/board/modify" method="post">
      			
      			<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
      			<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
      			<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
      			<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
      			
      			<div class="form-group">
      				<label>번호</label>
      				<input class="form-control" name="bno" value="<c:out value="${ board.bno }"/>"readonly="readonly" />
      			</div>	<!-- bno .form-group end  -->
      			
      			<div class="form-group">
      				<label>제목</label>
      				<input class="form-control" name="title" value="<c:out value="${ board.title }"/>" />
      			</div>	<!-- title .form-group end  -->
      		
      			<div class="form-group">
      				<label>내용</label>
      				<textarea class="form-control" rows="3" name="content"><c:out value="${ board.content }" /></textarea>
      			</div>	<!-- content .form-group end  -->
      			
      			<div class="form-group">
      				<label>작성자</label>
      				<input class="form-control" name="writer" value='<c:out value="${ board.writer }"/>' readonly="readonly" />
      			</div>	<!-- writer .form-group end  -->
      			
      			<div class="form-group">
      				<label>등록일</label>
      				<input class="form-control"  
      				value="<fmt:formatDate pattern="yyyy/MM/dd" value="${ board.regdate }"/>" readonly="readonly" />
      			</div>	<!-- regdate .form-group  end -->

      			<div class="form-group">
      				<label>수정일</label>
      				<input class="form-control"  
      				value="<fmt:formatDate pattern="yyyy/MM/dd" value="${ board.updateDate }"/>" readonly="readonly" />
      			</div>	<!-- updateDate .form-group  end -->
      			
      			<button type="submit" data-oper='modify' class="btn btn-primary">수정</button>
      			<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>		
      	    	<button type="submit" data-oper='list' class="btn btn-info">리스트</button>	
      	    	<!-- submit이 많은 경우에는 자바스크립트를 이용해서 분기처리를 해줘야한다. -->	
      		</form> <!--  form end -->
      		
      		</div> <!--  .panel-body end -->
        </div> <!--  .panel panel-default end -->         
   </div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->

<script type="text/javascript">
$(document).ready(function() {
	
	var formObj = $("form");	/* 상단 코드 중에 form 태그를 formObj로 관여 하겠다.  */
	
	$('button').on("click", function(e) {
		
		e.preventDefault();	/* button 기본 사용을 안하겠다!  sumit이 안된다. */	
		
		var operation = $(this).data("oper");	/* data-oper='modify', 'remove', 'list'  */
		
		console.log(operation);	/* 개발자 도구에 콘솔이 찍힘  */
		
		if(operation === 'remove'){
			formObj.attr("action", "/board/remove"); /* 삭제 컨트롤러를 요청한다.  */
			
		} else if(operation === 'list'){	/* data-oper='list'  */
			
			formObj.attr("action", "/board/list").attr("method", "get");
			
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			var typeTag = $("input[name='type']").clone();
			
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
			
		}
		
		formObj.submit();	/* data-oper='modify'  -> 28행이 실행됨 */
	});
});
</script>


</body>
</html>