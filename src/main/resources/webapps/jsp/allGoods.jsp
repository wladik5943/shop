
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<body>
<div>
    <form method="post" action="http://localhost:8080//shop//disp">
        <input type="submit" value="Все товары">
        <input type="hidden" name="allGoods">
    </form>
    <br>
    <table>
        <c:forEach var="good" items="${goods}">
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
    </table>
</div>