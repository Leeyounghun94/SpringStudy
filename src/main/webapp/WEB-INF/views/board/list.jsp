<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 코어 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 포매팅 태그 -->   
<%@ include file="../includes/header.jsp" %>
   <!-- <div id="page-wrapper"> header.jsp 에 위치함 -->
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
                            <button id="regBtn" type="Button" class="btn btn-xs pull-right">새 게시물 등록</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                
                                <c:forEach items="${list}" var="boardlist"> <!-- 객체를 반복적으로 돌린다(list 객체) -->
                                <tr> <!-- 1행 추가 -->
                                   <td><c:out value="${boardlist.bno}" /></td> <!-- 1열 -->
                                   <td>
                                  		 <a href='/board/get?bno=<c:out value="${ boardlist.bno }" /> '>
                                   			<c:out value="${boardlist.title}" />
                                   			</a>
                                   </td> <!-- 2열 -->		
                                   <td><c:out value="${boardlist.writer}" /></td> <!-- 3열 -->
                                   <td><fmt:formatDate pattern="yyyy-MM-dd" value="${boardlist.regdate}"/></td> <!-- 4열 -->
                                   <td><fmt:formatDate pattern="yyyy-MM-dd" value="${boardlist.updateDate}"/></td> <!-- 5열 -->
                                </tr>   
                                </c:forEach>                               
                                </table>
                                
	                                <!-- Modal 추가 -> 요즘 트렌트는 alert 대신 modal을 쓴다 ! -->
	                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	                                <div class="modal-dialog">
	                                    <div class="modal-content">
	                                        <div class="modal-header">
	                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                                            <h4 class="modal-title" id="myModalLabel">알립니다.</h4>
	                                        </div>
	                                        <div class="modal-body">
	                                        	처리가 완료 되었습니다.
	                                        </div>
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
                                
                                
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
           
<script type="text/javascript"> /* 자바 스크립트 코드임을 명시 */
   $(document).ready(function(){ /* 문서의 준비단계에서 실행 */
	   
	   var result = '<c:out value="${result}"/>';	/* controller에서 flashAttribute를 통해 1회용으로 넘어오는 값  */
	   /* console.log(result);
	   alert(result);  alert = 경고창이기때문에 백엔드에선 정지되기 때문에 현재는 잘 쓰이지 않아서 모달창을 많이 쓴다. */
	   
	   checkModal(result);	/* 아래쪽에 function 실행 코드  */
	   
	   histoy.replaceState({},null,null);	/* 뒤로가기 버튼 동작했을때 도배 방지 대한 값 -> histoy 초기화 */
	   
	   function checkModal(result){	/* bno를 받음  */
		   
		   if(result === '' || history.state ) { /* result값이 null이거나 histoy.State가 true이면? (자바스크립트에서는 같다 연산자 : ===) */
			   return;
		   }
		   
		   if (parseInt(result) > 0) {/* 게시물의 번호가 0보다 크면?  */
			   $(".modal-body").html("게시글" + parseInt(result) + "번이 등록되었습니다.");
		   		/* class .modal-body 에 값을 변경하는 코드  */
		   }
		   
		   $("#myModal").modal("show");	/* 변경된 모달창을 띄운다.  */
		   /* #myModal 52행 근처에 id="myModal"  */	
	   }
	   
      $("#regBtn").on("click" , function(){  /* 22행 근처 #regBtn=button id -> 클릭 동작(기능) */
         self.location="/board/register";  /* 현재 문서를 /board/register로 이동 */
      });
   });
</script> <!-- 08.22 오늘의 핵심 키 포인트  -->

            
<%@ include file="../includes/footer.jsp" %>

    