<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ page import="by.teachmeskills.shop.api.User.UserResponse" %>
<%@ page import="by.teachmeskills.shop.entity.Good" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
    UserResponse user = (UserResponse) session.getAttribute("user");

%>
<%
    Collection<Good> basket = (LinkedList)session.getAttribute("basket");
    request.setAttribute("basket",basket);
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
    <input type="hidden" name="allGoods">
        <input type="submit" value="каталог товаров" />
</form>

<form action="http://localhost:8080//butt" method="post">
    <input type="hidden" name="allUsers">
    <input type="submit" value="список пользователей" />
</form>

<form action="http://localhost:8080//butt" method="post">
    <input type="hidden" name="addGood">
    <input type="submit" value="добавление товара" />
</form>

<form action="http://localhost:8080//butt" method="post">
    <input type="hidden" name="packedOrder">
    <input type="submit" value="сборка заказа" />
</form>

<%if (user != null) {%>
<form action="http://localhost:8080//butt" method="post">
    <input type="hidden" name="myOrders">
    <input type="submit" value="мои заказы" />
</form>

<form action="http://localhost:8080//disp" method="post">
    <input type="hidden" name="logout">
    <input type="submit" value="выход" />
</form>
<% } %>

<%if (user != null) {%>
<h3>ваша корзина:</h3>
<table>
    <c:choose>
        <c:when test="${basket == null}">
            <h4>ваша корзина пуста</h4>
        </c:when>
        <c:otherwise>
            <c:forEach var="good" items="${basket}">
                <c:choose>
                    <c:when test="${good.stock}">
                        <div>
                            <tr>
                                <td>Name: ${good.name}</td>
                            </tr>
                            <tr>
                                <td>Code: ${good.code} </td>
                            </tr>
                            <tr>
                                <td>Subtype: ${good.subtype} </td>
                            </tr>
                            <tr>
                                <td>Price: ${good.price} </td>
                            </tr>
                            <tr>
                                <td>Quantity: ${good.quantity} </td>
                            </tr>

                            <tr>
                                <td>
                                    <form method="post" action="http://localhost:8080//disp">
                                        <input type="hidden" name="deleteGoodFromBasket" value="${good.id}" />
                                        <input type="submit" value="удалить из корзины" /><br><br>
                                    </form></td>
                            </tr>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <tr>
                                <td>Name: ${good.name}</td>
                            </tr>
                            <tr>
                                <td>Недостаточно товара</td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="post" action="http://localhost:8080//disp">
                                        <input type="hidden" name="deleteGoodFromBasket" value="${good.id}" />
                                        <input type="submit" value="удалить из корзины" /><br><br>
                                    </form></td>
                            </tr>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <form method="post" action="http://localhost:8080//disp">
                <input type="submit" value="оформить заказ">
                <input type="hidden" name="placeOrder">
            </form>
        </c:otherwise>
    </c:choose>
</table>
<% } %>

</html>


