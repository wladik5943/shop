<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ page import="by.teachmeskills.shop.api.User.UserResponse" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
    UserResponse user = (UserResponse) session.getAttribute("user");

%>
<html>
<head>
    <meta http-equiv="Content-Type" charset="UTF-8" content="text/html">
</head>
<title>Админ</title>

<h2 align="center">Добро пожаловать</h2>
<h2 align="center"><%= user.getName()%></h2>

<%if (user == null) {%>
<a href=http://localhost:8080//html//authorization//authorization.jsp />
    <input type="submit" value="авторизация" />
<% } %>

<form action="http://localhost:8080//butt" method="post">
    <input type="hidden" name="allGoods" value="1111">
        <input type="submit" value="каталог товаров" />
</form>

<form action="http://localhost:8080//butt" method="post">
    <input type="hidden" name="allUsers" value="1111">
    <input type="submit" value="список пользователей" />
</form>

<form action="http://localhost:8080//butt" method="post">
    <input type="hidden" name="addGood" value="1111">
    <input type="submit" value="добавление товара" />
</form>

</html>


