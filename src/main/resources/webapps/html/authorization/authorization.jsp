
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.shop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html style="font-size: 16px;" lang="ru"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="Вход">
    <meta name="description" content="">
    <title>Авторизация</title>
    <link rel="stylesheet" href="nicepage.css" media="screen">
    <link rel="stylesheet" href="Authorization.css" media="screen">
    <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 6.5.3, nicepage.com">
    <meta name="referrer" content="origin">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    <link id="u-page-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Ubuntu:300,300i,400,400i,500,500i,700,700i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">




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
<section class="u-clearfix u-section-1" id="sec-45e5">
    <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-border-6 u-border-palette-4-dark-1 u-radius u-shape u-shape-round u-shape-1"></div>
    </div>
</section>
<section class="u-clearfix u-section-2" id="sec-0573">
    <div class="u-clearfix u-sheet u-sheet-1">
        <h2 class="u-custom-font u-font-ubuntu u-text u-text-default u-text-1">Вход</h2>
        <div class="u-form u-form-1">
            <form method="post" action="http://localhost:8080//shop//disp" class="u-clearfix u-form-spacing-10 u-inner-form"  name="form" style="padding: 10px;">
                <div class="u-form-group u-form-name">
                    <label for="name-3f8a" class="u-custom-font u-font-open-sans u-label u-text-palette-2-dark-3 u-label-1">Логин</label>
                    <input type="text" placeholder="Введите Ваш логин" id="name-3f8a" name="login" class="u-input u-input-rectangle u-radius u-text-palette-5-dark-3 u-input-1" required="">
                </div>
                <div class="u-form-email u-form-group">
                    <label for="email-3f8a" class="u-custom-font u-font-open-sans u-label u-text-palette-2-dark-3 u-label-2">Пароль</label>
                    <input type="text" placeholder="Введите ваш пароль" id="email-3f8a" name="password" class="u-input u-input-rectangle u-radius u-text-palette-5-dark-3 u-input-2" required="">
                </div>
                <div class="u-align-right u-form-group u-form-submit">

                    <br>
                    <br>

                    <a href="#" class="u-border-2 u-border-grey-75 u-btn u-btn-round u-btn-submit u-button-style u-hover-palette-1-dark-1 u-palette-4-base u-radius u-btn-1">Войти</a>
                    <input type="submit" value="submit" class="u-form-control-hidden">
                </div>
                <div class="u-form-send-message u-form-send-success"> Спасибо! Ваше сообщение отправлено. </div>
                <div class="u-form-send-error u-form-send-message"> Отправка не удалась. Пожалуйста, исправьте ошибки и попробуйте еще раз. </div>
                <input type="hidden" value="" name="recaptchaResponse">
                <input type="hidden" name="authentication" value="authentication">
            </form>
        </div>
        <br>
        <a href="http://localhost:8080//shop//html//registration//register.html" class="u-border-none u-btn u-btn-round u-button-style u-custom-color-1  u-radius u-btn-2">Регистрация </a>
        <br>
    </div>
</section>




</body></html>