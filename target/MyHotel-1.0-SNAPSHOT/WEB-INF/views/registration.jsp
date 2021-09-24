<%--
  Created by IntelliJ IDEA.
  User: kostya
  Date: 9/16/2021
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <meta name="description" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <style>
        body {
            margin: 0;
            font-size: 16px;
            font-family: 'Montserrat', sans-serif;
        }

        img {
            max-width: 100%
        }

        a {
            text-decoration: none;
        }

        h1,
        h2,
        h3,
        h4,
        h5,
        h6,
        figure,
        p {
            padding: 0;
            margin: 0;
        }

        html {
            box-sizing: border-box;
        }

        *,
        *:before,
        *:after {
            box-sizing: inherit;
        }
    </style>
</head>

<body class="page__login">
<ul class="lang-list">
    <li class="lang-flag"><a href="?lang=ru&${pageContext.request.queryString}" title="RU"><img class="lang-pic" src="${pageContext.request.contextPath}
    /images/russian.jpg"/></a></li>
    <li class="lang-flag"><a href="?lang=en&${pageContext.request.queryString}" title="ENG"><img class="lang-pic" src="${pageContext.request.contextPath}
    /images/england.png"/></a></li>
</ul>
<main class="content-wrapper">
    <section class="section section__login">
        <div class="container">
            <div class="section__wrapper">
                <div class="block__right">
                    <div id="slider" class="block__box">
                        <div class="box-header">
                            <p class="header-title">Отель</p>
                            <p class="header-logo">Hotel!</p>
                            <figure class="image-wrap">
                                <img src="${pageContext.request.contextPath}/images/hotel.jpg" alt="" class="image">
                            </figure>
                        </div>
                    </div>
                </div>
                <div class="block__form">
                    <div class="block__box">
                        <div id="regBlock" class="block__login" style="display: flex;">
                            <div class="login-wrapper">
                                <div class="block__header">
                                    <form id="prevForm" action="welcome-page" method="get">
                                        <button id="from3to2Svg" type="submit" class="button-prev">
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16">
                                                <path
                                                        d="M11 15c-.3 0-.5-.1-.7-.3l-6-6c-.4-.4-.4-1 0-1.4l6-6c.4-.4 1-.4 1.4 0s.4 1 0 1.4L6.4 8l5.3 5.3c.4.4.4 1 0 1.4-.2.2-.4.3-.7.3z"></path>
                                            </svg>
                                        </button>
                                    </form>
                                    <p class="header-title">Зарегистрируйся сейчас!</p>
                                    <p class="header-desc">Предоставьте нам информацию ниже <br /> для создания
                                        аккаунта.</p>
                                </div>
                                <form action="${pageContext.request.contextPath}/reg-logic" class="form-login" id="registration" method="post">
                                    <div class="input-wrap">
                                        <label class="input-label" for="name">Логин</label>
                                        <p class="input-field">
                                                <span class="svg-email">
                                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                                                        <path
                                                                d="M10.8 28C6.5 28 3 24.5 3 20.2v-8.4C3 7.5 6.5 4 10.8 4h11.3c4.3 0 7.8 3.5 7.8 7.8v1.9c0 .5-.4 1-1 .9-.5 0-1-.4-1-.9v-1.9c0-3.2-2.7-5.9-5.9-5.9H10.8c-3.2 0-5.9 2.6-5.9 5.9v8.4c0 3.2 2.7 5.9 5.9 5.9h11.3c3.2 0 5.9-2.6 5.9-5.9.1-.5.5-.9 1.1-.8.4.1.8.4.8.8 0 4.3-3.5 7.8-7.8 7.8H10.8zm3.1-10.7l-5.6-4.4c-.4-.3-.5-.9-.1-1.3.3-.4.9-.5 1.3-.2l5.6 4.4c.7.5 1.7.5 2.4 0l5.5-4.4c.4-.3 1-.3 1.3.1.3.4.3 1-.1 1.3l-5.6 4.4c-1.3 1.2-3.3 1.2-4.7.1z"></path>
                                                    </svg>
                                                </span>
                                            <input class="input-box" type="text" placeholder="Логин(максимум 30 символов)" maxlength="30"
                                                   name="login" id="name" autocomplete="off">
                                        </p>
                                    </div>

                                    <div class="input-wrap">
                                        <label class="input-label" for="email">Электронная почта</label>
                                        <p class="input-field">
                                                <span class="svg-email">
                                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                                                        <path
                                                                d="M10.8 28C6.5 28 3 24.5 3 20.2v-8.4C3 7.5 6.5 4 10.8 4h11.3c4.3 0 7.8 3.5 7.8 7.8v1.9c0 .5-.4 1-1 .9-.5 0-1-.4-1-.9v-1.9c0-3.2-2.7-5.9-5.9-5.9H10.8c-3.2 0-5.9 2.6-5.9 5.9v8.4c0 3.2 2.7 5.9 5.9 5.9h11.3c3.2 0 5.9-2.6 5.9-5.9.1-.5.5-.9 1.1-.8.4.1.8.4.8.8 0 4.3-3.5 7.8-7.8 7.8H10.8zm3.1-10.7l-5.6-4.4c-.4-.3-.5-.9-.1-1.3.3-.4.9-.5 1.3-.2l5.6 4.4c.7.5 1.7.5 2.4 0l5.5-4.4c.4-.3 1-.3 1.3.1.3.4.3 1-.1 1.3l-5.6 4.4c-1.3 1.2-3.3 1.2-4.7.1z"></path>
                                                    </svg>
                                                </span>
                                            <input class="input-box" type="email" placeholder="Email" name="email"
                                                   id="email" autocomplete="off">
                                        </p>
                                    </div>

                                    <div class="input-wrap">
                                        <label class="input-label" for="password">Пароль</label>
                                        <p class="input-field">
                                                <span class="svg-pass">
                                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                                                        <path
                                                                d="M13.2 20.8c-1.6-1.6-1.6-4.1 0-5.7s4.1-1.6 5.7 0c1.6 1.6 1.6 4.1 0 5.7-.8.8-1.8 1.2-2.9 1.2-1.1 0-2.1-.4-2.8-1.2zM20.9 7H19V5.7c0-1-.8-1.7-1.7-1.7h-2.7c-1 0-1.7.8-1.7 1.7V7h-1.8V5.7c0-2 1.6-3.6 3.6-3.6h2.7c2 0 3.6 1.6 3.6 3.6V7z"></path>
                                                        <path
                                                                d="M27.2 16.7c.5 0 .9-.5.8-.9v-1.9C28 9.5 24.9 6 21 6H11c-3.8 0-7 3.5-7 7.8v8.4c0 4.3 3.1 7.8 7 7.8h10c3.8 0 7-3.5 7-7.8 0-.5-.4-.9-.9-.9s-.9.4-.9.9c0 3.2-2.4 5.8-5.3 5.9H11c-2.9 0-5.2-2.6-5.3-5.9v-8.4c0-3.2 2.4-5.8 5.3-5.9h10c2.9 0 5.2 2.6 5.3 5.9v1.9c0 .2.1.5.2.7.2.2.4.3.7.3z"></path>
                                                    </svg>
                                                </span>
                                            <input class="input-box" type="password" placeholder="Password"
                                                   name="password" id="password">
                                        </p>
                                    </div>

                                    <div class="input-wrap">
                                        <label class="input-label" for="password_next">Пароль (повторно)</label>
                                        <p class="input-field">
                                                <span class="svg-pass">
                                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                                                        <path
                                                                d="M13.2 20.8c-1.6-1.6-1.6-4.1 0-5.7s4.1-1.6 5.7 0c1.6 1.6 1.6 4.1 0 5.7-.8.8-1.8 1.2-2.9 1.2-1.1 0-2.1-.4-2.8-1.2zM20.9 7H19V5.7c0-1-.8-1.7-1.7-1.7h-2.7c-1 0-1.7.8-1.7 1.7V7h-1.8V5.7c0-2 1.6-3.6 3.6-3.6h2.7c2 0 3.6 1.6 3.6 3.6V7z"></path>
                                                        <path
                                                                d="M27.2 16.7c.5 0 .9-.5.8-.9v-1.9C28 9.5 24.9 6 21 6H11c-3.8 0-7 3.5-7 7.8v8.4c0 4.3 3.1 7.8 7 7.8h10c3.8 0 7-3.5 7-7.8 0-.5-.4-.9-.9-.9s-.9.4-.9.9c0 3.2-2.4 5.8-5.3 5.9H11c-2.9 0-5.2-2.6-5.3-5.9v-8.4c0-3.2 2.4-5.8 5.3-5.9h10c2.9 0 5.2 2.6 5.3 5.9v1.9c0 .2.1.5.2.7.2.2.4.3.7.3z"></path>
                                                    </svg>
                                                </span>
                                            <input class="input-box" type="password" placeholder="Password"
                                                   name="password2" id="password_next">
                                        </p>
                                    </div>
                                    <p style="color: red;">${errorString}</p>
                                    <p style="color: green;">${regConfirmation}</p>
                                </form>
                                <div class="pagination-bullets _step2">
                                    <button id="from2to1" type="button" class="pagination-bullet"></button>
                                    <button type="button" class="pagination-bullet"></button>
                                </div>
                                <div class="button-wrapper">
                                    <button id="createAccount" type="submit" class="button" form="registration">
                                        Создать аккаунт
                                        <span class="svg-arrow-right">
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16">
                                                <path
                                                        d="M5 15c-.3 0-.5-.1-.7-.3-.4-.4-.4-1 0-1.4L9.6 8 4.3 2.7c-.4-.4-.4-1 0-1.4s1-.4 1.4 0l6 6c.4.4.4 1 0 1.4l-6 6c-.2.2-.4.3-.7.3z"></path>
                                            </svg>
                                        </span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
</body>

</html>