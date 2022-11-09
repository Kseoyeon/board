<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
</head>

<body>
	<article>
		<div class="container" role="main">
			<h2>board Content</h2>
			<div class="bg-white rounded shadow-sm">
				<div class="board_title"><c:out value="${boardContent.title}"/></div>
				<div class="board_info_box">
					<span class="board_author"><c:out value="${boardContent.reg_id}"/>,</span><span class="board_date"><c:out value="${boardContent.reg_dt}"/></span>
				</div>
				<div class="board_content">${boardContent.content}</div>
				<div class="board_tag">TAG : <c:out value="${boardContent.tag}"/></div>
			</div>
			<div style="margin-top : 20px">
				<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
			
			<!-- Reply Form {s} -->
			<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
				<form:form name="form" id = "form" role = "form" modelAttribute = "reply" method = "post">
				<form:hidden path="bid" id = "bid"/>
				<div class="row">
					<div class="col-sm-10">
						<form:textarea path="content" id="content" class="form-control" style="word-break: break-all" rows="3" placeholder="댓글을 입력해 주세요"/>
					</div>
					<div class="col-sm-2">
						<form:input path="reg_id" class="form-control" id="reg_id" placeholder="댓글 작성자"/>
						<button type="button" class="btn btn-sm btn-primary" id="btnReplySave" style="width:100%; margin-top: 10px">저 장</button>
					</div>
				</div>
				</form:form>
			</div>
			<!-- Reply Form {e} -->
			
			<!-- Reply List {s} -->
			<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
				<h6 class="border-bottom pb-2 mb-0">Reply list</h6>
				<div id = "replyList"></div>
			</div>
			<!-- Reply List {e} -->
		</div>
	</article>
	<script type="text/javascript">
		$(document).on('click', '#btnList', function(){
			location.href = "${pageContext.request.contextPath}/board/getBoardList";
		});
		
		$(document).on('click', '#btnUpdate', function(){
			var url = "${pageContext.request.contextPath}/board/editForm";
			url = url + "?bid=" + ${boardContent.bid};
			url = url + "&mode=edit";
			
			location.href = url;
		});
		
		//삭제 버튼 클릭 이벤트
		$(document).on('click', '#btnDelete', function(){
	    var url = "${pageContext.request.contextPath}/board/deleteBoard";
	    url = url + "?bid=" + ${boardContent.bid};
			location.href = url;
		});
		
		//댓글
		function showReplyList(){
			var url = "${pageContext.request.contextPath}/restBoard/getReqlyList";
			var paramData = {"bid" : "${boardContent.bid}"};
			$.ajax({
				type: 'POST', //HTTP방식
				url: url, //서비스 주소
				data: paramData, //서비스 처리에 필요한 인자값
				dataType: 'json', //return 받을 데이터 타입
				success: function(result) { //서비스 성공 시 처리 할 내용
					var htmls = "";
					if(result.length < 1){
						htmls.push("등록된 댓글이 없습니다.");
					} else {
						$(result).each(function(){
							htmls += '<div class="media text-muted pt-3" id="rid' + this.rid + '">';
							htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
							htmls += '<title>Placeholder</title>';
							htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
							htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
							htmls += '</svg>';
							htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
							htmls += '<span class="d-block">';
							htmls += '<strong class="text-gray-dark">' + this.reg_id + '</strong>';
							htmls += '<span style="padding-left: 7px; font-size: 9pt">';
							htmls += '<a href="javascript:void(0)" onclick="fn_editReply(' + this.rid + ', \'' + this.reg_id + '\', \'' + this.content + '\' )" style="padding-right:5px">수정</a>';
							htmls += '<a href="javascript:void(0)" onclick="fn_deleteReply(' + this.rid + ')" >삭제</a>';
							htmls += '</span>';
							htmls += '</span>';
							htmls += this.content;
							htmls += '</p>';
							htmls += '</div>';
						});	//each end
					}
					$("#replyList").html(htmls);
				} //Ajax success end
			}); //Ajax end
		}
		
		$(document).ready(function(){
			showReplyList();
		});
		
		//댓글 저장 버튼 클릭 이벤트
		$(document).on('click', '#btnReplySave', function(){
			var replyContent = $('#content').val();
			var replyReg_id = $('#reg_id').val();
			
			var paramData = JSON.stringify({"content" : replyContent
						, "reg_id" : replyReg_id
						, "bid" : '${boardContent.bid}'
			});
			
			var headers = {"content-Type" : "application/json"
					, "X-HTTP-Method-Override" : "POST"};
			
			$.ajax({
				url : "${saveReplyURL}"
				, headers : headers
				, data : paramData
				, type : 'POST'
				, dataType : 'text'
				, success : function(result){
					showReplyList();
					
					$('#content').val('');
					$('#reg_id').val('');
				}
				, error : function(error){
					console.log("에러 : " + error);
				}
			});
		});
		
		//댓글 수정 폼 이벤트 추가
		function fn_editReply(rid, reg_id, content){
			var htmls = "";
			htmls += '<div class="media text-muted pt-3" id="rid' + rid + '">';
			htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
			htmls += '<title>Placeholder</title>';
			htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
			htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
			htmls += '</svg>';
			htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
			htmls += '<span class="d-block">';
			htmls += '<strong class="text-gray-dark">' + reg_id + '</strong>';
			htmls += '<span style="padding-left: 7px; font-size: 9pt">';
			htmls += '<a href="javascript:void(0)" onclick="fn_updateReply(' + rid + ', \'' + reg_id + '\')" style="padding-right:5px">저장</a>';
			htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';
			htmls += '</span>';
			htmls += '</span>';
			htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
			htmls += content;
			htmls += '</textarea>';
			
			htmls += '</p>';
			htmls += '</div>';
			
			$('#rid' + rid).replaceWith(htmls);
			$('#rid' + rid + ' #editContent').focus();
		}
		
		//댓글 수정 내용 저장 이벤트
		function fn_updateReply(rid, reg_id){
			var replyEditContent = $('#editContent').val();
			var paramData = JSON.stringify({"content": replyEditContent
				, "rid" : rid
			});
			
			var headers = {"Content-Type" : "application/json"
				, "X-HTTP-Method=Override" : "POST"};
			
			$.ajax({
				url : "${updateReplyURL}"
				, headers : headers
				, data : paramData
				, type : 'POST'
				, dataType : 'text'
				, success : function(result){
					console.log(result);
					showReplyList();
				}
				, error : function(error){
					console.log("에러 : " + error);
				}
			});
		}
		
		//댓글 삭제 이벤트
		function fn_deleteReply(rid){
			var paramData = {"rid" : rid};
			
			$.ajax({
				url : "${deleteReplyURL}"
				, data : paramData
				, type : 'POST'
				, dataType : 'text'
				, success : function(result){
					showReplyList();
				}
				, error : function(error){
					console.log("에러 : " + error);
				}
			});
		}
	</script>
</body>
</html>