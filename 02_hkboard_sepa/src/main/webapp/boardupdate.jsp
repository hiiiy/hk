<%@page import="com.hk.dtos.HkDto"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String sseq=request.getParameter("seq");
	int seq=Integer.parseInt(sseq);
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	HkDao dao=new HkDao();
	boolean isS=dao.updateBoard(new HkDto(seq,title,content));
	if(isS){
		%>
		<script type="text/javascript">
			alert("글을 수정합니다.");
			location.href="boarddetail.jsp?seq=<%=seq%>";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("글 수정 실패");
			location.href="boardipdate.jsp?seq=<%=seq%>";
		</script>
		<%
	}
%>
</body>
</html>