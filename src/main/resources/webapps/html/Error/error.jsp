

<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%--<style><%@include file="error.css"%></style>--%>

<%--&lt;%&ndash;<style><%@include file="nicepage.css"%></style>&ndash;%&gt;--%>
<%--    <style><%@include file="jquery.js"%></style>--%>
<%--    <style><%@include file="nicepage.js"%></style>--%>

<html style="font-size: 16px;" lang="ru"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Ошибка">
    <meta name="description" content="">
    <title>Ошибка</title>
    <link rel="stylesheet" href="nicepage.css" media="screen">
    <link rel="stylesheet" href="error.css" media="screen">
    <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 6.5.3, nicepage.com">
<%--    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">--%>
<%--    <link id="u-page-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Ubuntu:300,300i,400,400i,500,500i,700,700i">--%>



    <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "Organization",
        "name": ""
    }</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="Главная">
    <meta property="og:type" content="website">
    <meta data-intl-tel-input-cdn-path="intlTelInput/"></head>
<body data-path-to-root="./" data-include-products="false" class="u-body u-xl-mode" data-lang="ru">
<section class="u-clearfix u-section-1" id="sec-2966">
    <div class="u-clearfix u-sheet u-sheet-1">
        <img class="u-image u-image-contain u-image-default u-preserve-proportions u-image-1" src="images/pngtree-red-error-icon-image_2248553-removebg-preview.png" alt="" data-image-width="500" data-image-height="500">
        <h1 class="u-align-left u-custom-font u-font-ubuntu u-text u-text-palette-2-base u-text-1">Ошибка<span style="font-weight: 400;"></span>
        </h1>
        <h4 class="u-align-center u-custom-font u-font-ubuntu u-text u-text-default u-text-palette-2-dark-2 u-text-2"><c:out value="${exp}"/></h4>
        <form method="post" action="http://localhost:8080//butt">
            <input type="submit" value="на главную">
            <input type="hidden" name="master">
        </form>
    </div>
</section>


</body></html>