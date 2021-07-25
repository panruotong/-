<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书</title>
</head>
<style>
	.box{
            margin: 100px auto;
            /*text-align: center;*/
            width: 600px;
        }
	.bbox{
            padding: 10px;
        }
</style>
<body>
<div class="box">
    <h1>查询所有已经添加的各种书籍的信息</h1>
    <c:forEach var="item" items="#{booksList.bookList}">
        <br/>
        <li>
            图书名称：
            <h:outputLabel value="#{item.name}"/>
            <br/>
            图书书号：
            <h:outputLabel value="#{item.num}"/>
            <br/>
            作者：
            <h:outputLabel value="#{item.authorList}">
                <!--<f:converter converterId="BookAuthorsConverter"/>-->
            </h:outputLabel>
            <br/>
            出版时间：
            <h:outputLabel value="#{item.pubDate}">
                <f:convertDateTime pattern="yyyy年MM月" timeZone="GMT+8"/>
            </h:outputLabel>
            <br/>
            价格:
            <h:outputLabel value="#{item.price}"/>
            <br/>
            分类：
            <h:outputLabel value="#{registerForm.getCategory(item.category)}"/>
            <h:outputLabel value="#{registerForm.getSubCategoryName(item.category,item.subCategory)}"/>
            <br/>
        </li>

    </c:forEach>
    <h2><a href="javascript:void(0);" onclick="jumpToIndex()">点击此处立即返回</a></h2>
</div>
</body>
</html>