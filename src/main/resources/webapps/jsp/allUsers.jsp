
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<body>
<div>
    <form method="post" action="http://localhost:8080//disp">
        <input type="submit" value="Все пользователи">
        <input type="hidden" name="allUsers">
    </form>
    <br>
    <table>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>Name: ${user.name}</td>
            </tr>
            <tr>
                <td>Surname: ${user.surname} </td>
            </tr>
            <tr>
                <td>Login: ${user.login} </td>
            </tr>
            <tr>
                <td>Password: ${user.password} </td>
            </tr>
            <tr>
                <td>Role: ${user.role} <br><br></td>
            </tr>
        </c:forEach>
    </table>
</div>