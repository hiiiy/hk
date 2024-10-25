<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
   List<UserDto> list=(List<UserDto>)request.getAttribute("list");
%>
<body>
<div id="container">
   <div class="main">
      <div class="lead">
         <span><%=ldto.getId()%>[<%=ldto.getRole()%>]님이 로그인 함</span> |
         <span><a href="userController.jsp?command=getAllUserList">회원전체조회</a></span> |
         <span><a href="userController.jsp?command=getUserList">회원정보[등급]수정</a></span> |
         <span><a href="userController.jsp?command=logout">로그아웃</a></span>
      </div>
      <div class="contents">
         <h1>관리자 페이지</h1>
         <h2>회원전체목록</h2>
         <div id="userAllList">
            <table class="table">
               <tr>
                  <th>회원번호</th>
                  <th>아이디</th>
                  <th>이름</th>
                  <th>주소</th>
                  <th>이메일</th>
                  <th>회원등급</th>
                  <th>탈퇴여부</th>
                  <th>가입일</th>
               </tr>
               <%
                  for(int i=0;i<list.size();i++){
                     UserDto dto = list.get(i);
                  %>
                  <tr>
                     <td><%=dto.getSeq()%></td>
                     <td><%=dto.getId()%></td>
                     <td><%=dto.getName()%></td>
                     <td><%=dto.getAddress()%></td>
                     <td><%=dto.getEmail()%></td>
                     <td><%=dto.getRole()%></td>
                     <td><%=dto.getEnabled()%></td>
                     <td><%=dto.getRegDate()%></td>
                  </tr>
                  <%
                  } 
                  %>
            </table>
         </div>
      </div>
   </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>