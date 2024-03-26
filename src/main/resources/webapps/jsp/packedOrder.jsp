<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<body>
<c:if test="${text != null}">
<h4>${text}</h4>
</c:if>
<form method="post" action="http://localhost:8080//butt">
    <input type="submit" value="на главную">
    <input type="hidden" name="master">
</form>
<div>
    <div>
        <form method="post" action="http://localhost:8080//disp">
            <input type="submit" value="Заказы">
            <input type="hidden" name="processedOrders">
        </form>
        <br>
        <table>
            <c:if test="${processedOrders != null}">
            <h3>Заказы для сборки:</h3>
            </c:if>
            <c:forEach var="order" items="${processedOrders}">
                <div>
                    <tr>
                        <td>Id: ${order.id}</td>
                    </tr>
                    <tr>
                        <td>Покупатель: ${order.user.name} ${order.user.surname} </td>
                    </tr>
                    <tr>
                        <td>количество позиций: ${order.products} </td>
                    </tr>
                    <td>
                        <form method="post" action="http://localhost:8080//disp">
                            <input type="hidden" name="showOrder" value="${order.id}"/>
                            <input type="submit" value="подробнее"/><br><br>

                        </form>
                    </td>
                    </tr>

                </div>
            </c:forEach>
        </table>
    </div>
    <table>
        <c:if test="${basketOrder != null}">
            <h3>Заказ: ${order.id}</h3>
               <h4> Покупатель: ${order.user.name} ${order.user.surname}</h4>

    <c:forEach var="good" items="${basketOrder}">
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
                <td>Quantity: ${good.quantity} <br><br></td>
            </tr>
        </div>
    </c:forEach>
        <form method="post" action="http://localhost:8080//disp">
            <input type="submit" value="Заказ собран">
            <input type="hidden" name="OrderPack" value="${order.id}">
        </form>
        </c:if>
    </table>

</div>