<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 조회</title>
</head>
<%
//java 실행부
	//DB에 접근하려면 어떤 객체가 필요할까
	UserDao dao=new UserDao();
	List<UserDto>lists=dao.getAllUser();//회원목록 가져오기
%>
<body>
<h1>회원 조회 결과</h1>
<table border="1">
	<tr>
		<th>아이디</th><th>이름</th><th>가입일</th><th>수정</th><th>삭제</th>
	</tr>
	<%
	for(int i=0;i<lists.size();i++){
		UserDto dto=lists.get(i);
	%>
	<tr>
		<td><%=dto.getUserID()%></td>
		<td><%=dto.getName()%></td>
		<td><%=dto.getmDate()%></td>
		<td><a href="userUpdateForm.jsp?userId=<%=dto.getUserID()%>">수정</a></td>
<%-- 		<td><a href="userDelete.jsp?userId=<%=dto.getUserID()%>">삭제</a></td> --%>
		<td><a href="#" onclick="deleteUser('<%=dto.getUserID()%>')">삭제</a></td>
	</tr>
	<%
	}
  %>
  <tr>
  	<td colspan="5">
  		<a href="index.jsp">초기화면</a>
  	</td>
  </tr>
</table>
<script type="text/javascript">
	//자바스크립트에서 삭제여부 확인후에 요청진행한다.
	function deleteUser(){
		if(confirm("정말 삭제하겠습니까?")){
			location.href="userDelete.jsp?userId="+userId;
		}
	}
</script>
</body>
</html>