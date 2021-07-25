<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="addBook" class="javaee.controller.AddServlet" scope="request">
	<jsp:setProperty property="*" name="info" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已添加图书列表</title>
</head>
<body>
<form action = "AddServlet" method = post>
<c:forEach  items="${bookList}" var="book">
    <tr>
       <td>书名：${book.name}<br></td>
       <td>作者：${book.authorList}<br></td>
       <td>图书编号：${book.isbn}<br></td>
       <td>数量：${book.num}<br></td>
       <td>价格：${book.price}<br></td>
       <td>出版日期：${book.pubDate}<br></td>
       <td>类型：${book.category}<br></td>
       <td><p></p></td>
       <td><input type="button"  value="修改" onclick="g('${book.id}')" /><br></td>
       <td><p></p></td>
    <tr>
</c:forEach>
</form>
</body>
</html>