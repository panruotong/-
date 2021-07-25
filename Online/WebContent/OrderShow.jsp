                                                                                                                   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="order" class="javaee.controller.OrderServlet" scope="request">
	<jsp:setProperty property="*" name="info" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单显示</title>
</head>
<body>
<form action = "OrderServlet" method = post>
<c:forEach  items="${orderList}" var="record">
    <tr>
       <td>订单号：${record.id}<br></td>
       <td>配送地址：${record.address}<br></td>
       <td>配送时间：${record.time}<br></td>
       <td>收件人姓名：${record.revName}<br></td>
       <td>收件人电话：${record.phone}<br></td>
       <c:forEach  items="${record.bookList}" var="book">
       		<td>书名：${book.name}<br></td>
       		<td>作者：${book.authorList}<br></td>
       		<td>图书编号：${book.isbn}<br></td>
       		<td>价格：${book.price}<br></td>
       </c:forEach>
       <td><p></p></td>
    <tr>
</c:forEach>
</form>
</body>
</html>