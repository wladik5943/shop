
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="by.teachmeskills.shop.enums.OrderStatus" %>

<html>
<body>
<form method="post" action="http://localhost:8080//butt">
    <input type="submit" value="на главную">
    <input type="hidden" name="master">
</form>
<div>
    <form method="post" action="http://localhost:8080//disp">
        <input type="submit" value="Мои заказы">
        <input type="hidden" name="myOrders">
    </form>
    <br>
    <table>
        <c:forEach var="order" items="${userOrders}">

            <div>
                <tr>
                    <td>Id: ${order.id}</td>
                </tr>
                <tr>
                    <td>Status: ${order.status} <br></td>
                </tr>
                <tr>
                    <td>состав заказа:</td>
                </tr>
                <tr>
                    <c:forEach var="good" items="${order.basket}">
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
                                <td>Quantity: ${good.quantity} <br><br></td>
                            </tr>
                    </c:forEach>
                </tr>
                <tr>
                <td><c:if test="${order.status == OrderStatus.PROCESSED}">
                    <form method="post" action="http://localhost:8080//disp">
                        <input type="submit" value="Отменить заказ">
                        <input type="hidden" name="cancelOrder" value="${order.id}">
                    </form>
                </c:if></td>
                </tr>
            </div>
        </c:forEach>
    </table>
</div>