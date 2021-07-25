<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="placeOrder" class="javaee.controller.QueryServlet" scope="request">
	<jsp:setProperty property="*" name="info" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下单</title>
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
  <p style="font-size: 28px;font-weight: bold">订单信息</p>
  <form action = "QueryServlet" method = post>
    <div>
      配送地址：<input type="text" name="address" id="add"><br>
    </div>
    <p id="namespan"></p>
    <div>
    配送时间：<input type="text" name="time" id="time"><br>
    </div>
    <p id="idspan"></p>
    <div>
    收件人姓名：<input type="text" name="receiver" id="receiver"><br>
    </div>
    <p id="authorspan"></p>
    <div>
    电话：<input type="text" name="phone" id="phone"><br>
    </div>
    <p id="timespan"></p>
    <div>
    <input type="submit" id="sure" name="buy" value="确认下单">
    </div>
  </form>
  </div>
</body>
</html>