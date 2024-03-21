<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" charset="UTF-8" content="text/html">
</head>
<title>Главная</title>

<h2 align="center">Добро пожаловать</h2>
<h2 align="center"><c:out value="${username}"/></h2>
<c:if test="${username == null}">
<a href=http://localhost:8080//html//authorization//authorization.jsp />
<input type="submit" value="авторизация" />
    </c:if>
<a href=http://localhost:8080//jsp//allGoods.jsp />
<input type="submit" value="каталог товаров" />



</html>