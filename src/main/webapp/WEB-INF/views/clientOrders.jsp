
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Страница клиента</title>
    <meta name="description" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
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

<body class="page__client">
<ul class="lang-list">
    <li class="lang-flag"><a href="?lang=ru&${pageContext.request.queryString}" title="RU"><img class="lang-pic" src="${pageContext.request.contextPath}
    /images/russian.jpg"/></a></li>
    <li class="lang-flag"><a href="?lang=en&${pageContext.request.queryString}" title="ENG"><img class="lang-pic" src="${pageContext.request.contextPath}
    /images/england.png"/></a></li>
</ul>
<main class="content-wrapper">
    <section class="section section__client">
        <div class="container">
            <div class="section__wrapper">
                <div class="block__left">
                    <div class="user__block">
                        <div class="image-wrap">
                            <img src="${pageContext.request.contextPath}/images/user_01.png" alt="">
                        </div>
                        <div class="user-info">
                            <p id="profileName" class="user-name">${user.getLogin()}</p>
                            <p id="profileEmail" class="user-email">${user.getEmail()}</p>
                        </div>
                        <div class="user-action">
                            <button type="button" class="user-button"><span class="svg-arrow"><svg
                                    xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16">
                                            <path
                                                    d="M5 15c-.3 0-.5-.1-.7-.3-.4-.4-.4-1 0-1.4L9.6 8 4.3 2.7c-.4-.4-.4-1 0-1.4s1-.4 1.4 0l6 6c.4.4.4 1 0 1.4l-6 6c-.2.2-.4.3-.7.3z"></path>
                                        </svg></span>
                            </button>
                        </div>
                    </div>
                    <div class="user-menu">
                        <ul class="menu-list">
                            <li class="menu-item"><a class="menu-link" href="${pageContext.request.contextPath}/client-room-pagination"><span class="svg-study"><svg
                                    xmlns="http://www.w3.org/2000/svg" viewBox="0 0 26 26">
                                                <path
                                                        d="M15.3 11c.6 0 1.1-.5 1.1-1.1V4c0-1 .8-1.8 1.8-1.9h3.9c1 0 1.8.8 1.8 1.9v4c0 .5-.2 1-.5 1.3-.3.4-.8.6-1.3.6h-1.5c-.5.1-.9.4-.9.9-.1.6.3 1.1.9 1.2h1.6c1 0 2-.5 2.8-1.2.6-.8 1-1.8 1-2.9V4c0-2.2-1.8-4-3.9-4h-3.8c-1 0-2.1.4-2.8 1.2-.8.7-1.2 1.8-1.2 2.8v5.8c0 .3.1.6.3.8.2.2.4.4.7.4zM22.1 14h-3.8c-2.2 0-4 1.8-3.9 4v4c0 2.2 1.7 4 3.9 4h3.8c1 0 2.1-.4 2.8-1.2.7-.7 1.1-1.8 1.1-2.8v-4c0-2.1-1.8-3.9-3.9-4zm1.8 8c0 .5-.2 1-.5 1.3-.3.3-.8.5-1.3.5h-3.9c-.5 0-.9-.2-1.3-.5-.3-.4-.5-.8-.5-1.3v-4c0-1 .8-1.8 1.8-1.9H22c.5 0 .9.2 1.3.5.3.4.5.8.5 1.3V22zM10.1 15c-.4.2-.6.7-.5 1.1v5.8c0 .5-.2 1-.5 1.3-.3.3-.8.5-1.3.5H3.9c-1 0-1.8-.8-1.8-1.9V18c0-1 .8-1.8 1.8-1.9h1.6c.4 0 .8-.2 1-.5.2-.4.2-.8 0-1.1-.2-.3-.6-.5-1-.5H3.9c-1 0-2.1.4-2.8 1.2C.4 16 0 17 0 18v4c0 2.2 1.8 4 3.9 4h3.8c2.2 0 4-1.8 3.9-4v-5.8c.1-.4-.1-.9-.4-1.1-.2-.3-.7-.3-1.1-.1zM3.9 12h3.8c1 0 2.1-.4 2.8-1.2.8-.8 1.2-1.8 1.2-2.8V4c0-2.2-1.7-4-3.9-4H3.9c-1 0-2.1.4-2.8 1.2C.4 1.9 0 3 0 4v4c0 1 .4 2 1.2 2.8.7.7 1.7 1.2 2.7 1.2zM2.1 4c0-.5.2-1 .5-1.3.4-.3.8-.5 1.3-.5h3.9c1 0 1.8.8 1.8 1.9V8c0 .5-.2 1-.5 1.3-.3.3-.8.6-1.3.6H3.9c-.5 0-.9-.2-1.3-.6-.3-.4-.5-.8-.5-1.3V4z"></path>
                                            </svg></span>Забронировать номер</a>
                            </li>
                            <li class="menu-item _active"><a class="menu-link" href="${pageContext.request.contextPath}/client-order-list"><span
                                    class="svg-saved"><svg xmlns="http://www.w3.org/2000/svg"
                                                           viewBox="0 0 32 32">
                                                <path
                                                        d="M20.7 4h-8.2C9.1 4 7 6.4 7 10.2V26c0 1.1.9 2 1.9 2 .3 0 .6-.1.9-.2l6.7-3.7 7.8 3.8c.2.1.4.1.7 0 .2-.1.4-.3.5-.5.1-.4 0-.8-.4-1.1l-7.7-3.7c-.5-.3-1.1-.3-1.6 0L9 26.2h-.2c-.1 0-.1-.1-.1-.2V10.2c0-2.8 1.4-4.4 3.8-4.4h8.2c3.2 0 3.6 2.6 3.6 4.2v.7h-8.9c-.2 0-.4.1-.6.3-.2.2-.2.4-.2.6 0 .5.4.8.8.8h8.8v9.3c0 .5.4.9.8.9.5 0 .9-.4.9-.8V10c.1-3.7-1.9-6-5.2-6z"></path>
                                            </svg></span>Мои заявки</a>
                            </li>
                            <li class="menu-item"><a class="menu-link" href="${pageContext.request.contextPath}/client-invoice-list"><span class="svg-profile"><svg
                                    xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                                                <path
                                                        d="M16 16.9c-3.8 0-6.9-3.1-6.9-6.9s3.1-6.9 6.9-6.9c3.8 0 6.9 3.1 6.9 6.9s-3.1 6.9-6.9 6.9zm0-12c-2.8 0-5.1 2.3-5.1 5.1s2.3 5.1 5.1 5.1 5.1-2.3 5.1-5.1-2.3-5.1-5.1-5.1zM16 28.9c-.6 0-1.1 0-1.7-.1-.3 0-.7 0-1.1-.1-.9-.1-1.9-.2-2.8-.5-2-.4-3.4-1.3-3.9-2.6-.2-.5-.3-1.1-.3-1.6 0-.6.1-1.1.3-1.6.8-1.7 2.8-2.4 3.9-2.6 1-.2 1.9-.4 2.9-.5 1.8-.2 3.6-.2 5.4 0 1 .1 1.9.2 2.9.5 2.1.5 3.4 1.4 3.9 2.6.4 1 .4 2.2 0 3.3-.6 1.3-1.9 2.2-4 2.6-.9.2-1.9.4-2.9.5-.8 0-1.7.1-2.6.1zm-1.7-1.8c1.4.1 2.8.1 4.2-.1.9-.1 1.8-.2 2.7-.4 1.1-.2 2.4-.7 2.8-1.6.3-.6.3-1.3 0-1.9-.4-.9-1.7-1.4-2.8-1.6-.9-.2-1.8-.3-2.7-.4-1.7-.2-3.4-.2-5.1 0-.9.1-1.8.2-2.7.4-.6.1-2.2.5-2.7 1.6-.1.3-.2.6-.2.9 0 .3.1.7.2 1 .4.9 1.7 1.4 2.8 1.6.9.2 1.8.4 2.7.4.3.1.6.1.8.1z"></path>
                                            </svg></span>Счета к оплате</a>
                            </li>
                        </ul>
                    </div>
                    <form action="${pageContext.request.contextPath}/logout">
                        <button id="logoutBtn" type="submit" class="button--outline">Выйти</button>
                    </form>
                </div>
                <div class="block__main">
                    <div class="header-block">
                        <p class="sub-title">Привет,</p>
                        <p class="header-title"><span id="greeting">${user.getLogin()}</span> 👋</p>
                    </div>
                    <form id="formClient" action="${pageContext.request.contextPath}/client-order-list" method="get" class="form-client">
                        <div class="block__type-occupation">
                            <p class="title">Ваши заказы на бронирование номера:</p>
                            <div class="block-types">

                                <table class="about__table">
                                    <thead>
                                    <tr class="about__table-tr">
                                        <th class="about__table-td">ID заказа</th>
                                        <th class="about__table-td">Гостиничный номер</th>
                                        <th class="about__table-td">Количество дней</th>
                                        <th class="about__table-td">Количество гостей</th>
                                        <th class="about__table-td">Сумма к оплате</th>
                                        <th class="about__table-td">Статус</th>
                                        <th class="about__table-td">Подтвердить / Отменить</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${orders}" var="order">
                                    <tr class="about__table-tr">
                                        <th class="about__table-td">${order.getId()}</th>
                                        <th class="about__table-td">${order.getRoomId}</th>
                                        <th class="about__table-td">${order.getDaysNumber()}</th>
                                        <th class="about__table-td">${order.getPeoplesCount()}</th>
                                        <th class="about__table-td">${order.getSum()}</th>
                                        <th class="about__table-td">${order.getStatusRus()}</th>
                                        <th class="about__table-td">
                                            <c:if test="${order.inConfirmation()}">
                                                <p>
                                                    <a href="${pageContext.request.contextPath}/client-invoice-payment?order=${order.getId()}&room=${order.getRoomId()}&sum=${order.getSum()}">подтвердить</a> /
                                                    <a href="${pageContext.request.contextPath}/client-invoice-refusal?order=${order.getId()}&room=${order.getRoomId()}">отменить</a>
                                                </p>
                                            </c:if>
                                        </th>
                                    </tr>
                                    </tbody>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="button-wrapper">
                                <button type="submit" class="button">Обновить</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </section>
</main>
