<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="queryBook" class="javaee.controller.QueryServlet" scope="request">
	<jsp:setProperty property="*" name="info" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
</head>
<style>
    .box{
      margin: 100px auto;
      text-align: center;
    }
    #btn{
      width: 100px;
      padding: 6px;
    }
  </style>
<body>
<div class="box">
  <p style="font-size: 28px;font-weight: bold">购物车</p>
</div>
<div class="box">
<form action = "QueryServlet" method = post>
<input type="submit"  value="下单" name="sure"/><span></span>
</form>
<c:forEach  items="${shopList}" var="book">
    <tr>
       <td>书名：${book.name}<br></td>
       <td>作者：${book.authorList}<br></td>
       <td>图书编号：${book.isbn}<br></td>
       <td>数量：${book.num}<br></td>
       <td>价格：${book.price}<br></td>
       <td>出版日期：${book.pubDate}<br></td>
       <td>类型：${book.category}<br></td>
       <td><p></p></td>
    <tr>
</c:forEach>
</div>
</body>
</html>