<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="register" class="javaee.controller.RegServlet" scope="request">
	<jsp:setProperty property="*" name="info" />
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
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
function jump(){
	 window.location.href="login.jsp";
	}
</script>
<body>
<div class="box">
  <p style="font-size: 28px;font-weight: bold">用户注册</p>
  <form action = "RegServlet" method = post>
    <div>
      用户名：<input type="text" name="userName" id="name"><br>
    </div>
    <p id="namespan"></p>
    <div>
    密  码：<input type="password" name="password" id="password"><br>
    </div>
    <p id="passwordspan"></p>
    <div>
    <input type="submit" id="btn" value="注册">
    <input type="button" id="btn" value="登录" onclick="jump()">
    </div>
  </form>
  </div>
</body>
</html>