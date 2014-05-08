<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>내용</td>
		<td>작성일자</td>
		<td>작성자</td>
		<td>수정일자</td>
		<td>수정자</td>
		<td>공개여부</td>
		<td></td>
	</tr>
<%

	// request, response, out
 
	List<Map<String,Object>> dogBoardList = (List<Map<String,Object>>) request.getAttribute("dogBoardList");
	
	for (int i = 0; i < dogBoardList.size(); i++) {
		Map<String,Object> boardMap = dogBoardList.get(i);
	}
	for (Map<String,Object> boardMap : dogBoardList) {
		out.print("<tr>");
		out.print("<td><a href='/readOneServlet?id="+boardMap.get("id")+"'>" + boardMap.get("id") + "</a></td>");
		out.print("<td>" + boardMap.get("title") + "</td>");
		out.print("<td>" + boardMap.get("content") + "</td>");
		out.print("<td>" + boardMap.get("write_date") + "</td>");
		out.print("<td>" + boardMap.get("writer") + "</td>");
		out.print("<td>" + boardMap.get("edit_date") + "</td>");
		out.print("<td>" + boardMap.get("editer") + "</td>");
		out.print("<td>" + boardMap.get("isopen") + "</td>");
		out.print("<td><a href='/deleteServlet?id="+boardMap.get("id")+"'>삭제</a></td>");
		out.print("</tr>");
	}
%>
</table>
<br />
<a href="./dogBoardWrite.jsp">글쓰기</a>
</body>
</html>