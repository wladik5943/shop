
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<body>
<form method="post" action="http://localhost:8080//butt">
    <input type="submit" value="на главную">
    <input type="hidden" name="master">
</form>
<div>
    <form method="post" action="http://localhost:8080//disp">
        <input type="submit" value="Все товары">
        <input type="hidden" name="allGoods">
    </form>
    <br>
    <table>
        <c:forEach var="good" items="${goods}">
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
                <td>Quantity: ${good.quantity} <br></td>
            </tr>
                <tr>
            <td>
                <form method="post" action="http://localhost:8080//disp">
                    <input type="hidden" name="basket" value="${good.id}" />
                    <input type="number" name="count" placeholder="количество">
                    <input type="submit" value="добавить в корзину" /><br><br>

                </form></td>
                </tr>

            </div>
        </c:forEach>
    </table>
</div>