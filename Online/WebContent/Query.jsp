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
<title>图书查询</title>
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
<script type="text/javascript">
function g(bookid){
    document.form.op.value="shopping";
    document.form.shopid.value=bookid;
    document.form.submit();
}
function order(){
	document.form.op.value="o";
	document.form.submit();
}
</script>
<body>
<input type="button" value="查看订单" onclick="order()">
<div class="box">
  <p style="font-size: 28px;font-weight: bold">图书查询</p>
  <form action = "QueryServlet" method = post name=form>
    <div>
      图书名称：<input type="text" name="bookName" id="name"><input type="submit" id="qName" name="qName" value="书名查询"><br>
    </div>
    <p id="namespan"></p>
    <div>
    图书书号：<input type="text" name="bookID" id="ID"><input type="submit" id="iName" name="iName" value="书号查询"><br>
    </div>
    <p id="idspan"></p>
    <div>
    作者：<input type="text" name="author" id="author"><input type="submit" id="aName" name="aName" value="作者查询"><br>
    </div>
    <p id="authorspan"></p>
    <div>
    出版时间：<input type="text" name="btime" id="btime"> - <input type="text" name="atime" id="atime"><input type="submit" id="tName" name="tName" value="出版时间查询"><br>
    </div>
    <p id="timespan"></p>
    <div>
    价格：<input type="text" name="dprice" id="dprice"> - <input type="text" name="uprice" id="uprice"><input type="submit" id="uName" name="uName" value="价格查询"><br>
    </div>
    <p id="pricespan"></p>
    <div>
    类别：<input type="text" name="bookClass" id="bclass"><input type="submit" id="cName" name="cName" value="类别查询"><br>
    </div>
    <p id="classrspan"></p>
	<input type="submit"  value="查看购物车" name="showShop"/>
	<p></p>
	<input type="hidden" name="op">
	<input type="hidden" name="shopid">
	<c:forEach  items="${bookList}" var="book">
    <tr>
       <td>id：${book.id}<br></td>
       <td>书名：${book.name}<br></td>
       <td>作者：${book.authorList}<br></td>
       <td>图书编号：${book.isbn}<br></td>
       <td>数量：${book.num}<br></td>
       <td>价格：${book.price}<br></td>
       <td>出版日期：${book.pubDate}<br></td>
       <td>类型：${book.category}<br></td>
       <td><p></p></td>
       <td><input type="button"  value="加入购物车" onclick="g('${book.id}')" /><br></td>
       <td><p></p></td>
    <tr>
	</c:forEach>
	</form>
</div>
</body>
</html>