<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="addBook" class="javaee.controller.AddServlet" scope="request">
	<jsp:setProperty property="*" name="info" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书添加</title>
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
  <p style="font-size: 28px;font-weight: bold">图书添加</p>
  <form action = "AddServlet" method = post>
    <div>
      图书名称：<input type="text" name="bookName" id="name"><br>
    </div>
    <p id="namespan"></p>
    <div>
    图书书号：<input type="text" name="bookID" id="ID"><br>
    </div>
    <p id="idspan"></p>
    <div>
    作者：<input type="text" name="author" id="author"><br>
    </div>
    <p id="authorspan"></p>
    <div>
    出版时间：<input type="text" name="Pubtime" id="time"><br>
    </div>
    <p id="timespan"></p>
    <div>
    价格：<input type="text" name="price" id="price"><br>
    </div>
    <p id="pricespan"></p>
    <div>
    类别：<input type="text" name="bookClass" id="bclass"><br>
    </div>
    <p id="classrspan"></p>
    <div>
    数量：<input type="text" name="num" id="num"><br>
    </div>
    <p id="numspan"></p>
    <div>
    <input type="submit" id="continueAdd" name="continueAdd" value="继续添加">
    </div>
    <div>
    <input type="submit" id="showBookList" name="showBookList" value="查看已添加列表">
    </div>
    <div>
    <input type="submit" id="AddOver" name="AddOver" value="退出添加">
    </div>
  </form>
  </div>
</body>
</html>