<%--
  Created by IntelliJ IDEA.
  User: kostya
  Date: 9/23/2021
  Time: 3:16 AM
  To change this template use File | Settings | File Templates.
--%>

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
                            <li class="menu-item _active"><a class="menu-link" href="${pageContext.request.contextPath}/main/manager"><span class="svg-study"><svg
                                    xmlns="http://www.w3.org/2000/svg" viewBox="0 0 26 26">
                                                <path
                                                        d="M15.3 11c.6 0 1.1-.5 1.1-1.1V4c0-1 .8-1.8 1.8-1.9h3.9c1 0 1.8.8 1.8 1.9v4c0 .5-.2 1-.5 1.3-.3.4-.8.6-1.3.6h-1.5c-.5.1-.9.4-.9.9-.1.6.3 1.1.9 1.2h1.6c1 0 2-.5 2.8-1.2.6-.8 1-1.8 1-2.9V4c0-2.2-1.8-4-3.9-4h-3.8c-1 0-2.1.4-2.8 1.2-.8.7-1.2 1.8-1.2 2.8v5.8c0 .3.1.6.3.8.2.2.4.4.7.4zM22.1 14h-3.8c-2.2 0-4 1.8-3.9 4v4c0 2.2 1.7 4 3.9 4h3.8c1 0 2.1-.4 2.8-1.2.7-.7 1.1-1.8 1.1-2.8v-4c0-2.1-1.8-3.9-3.9-4zm1.8 8c0 .5-.2 1-.5 1.3-.3.3-.8.5-1.3.5h-3.9c-.5 0-.9-.2-1.3-.5-.3-.4-.5-.8-.5-1.3v-4c0-1 .8-1.8 1.8-1.9H22c.5 0 .9.2 1.3.5.3.4.5.8.5 1.3V22zM10.1 15c-.4.2-.6.7-.5 1.1v5.8c0 .5-.2 1-.5 1.3-.3.3-.8.5-1.3.5H3.9c-1 0-1.8-.8-1.8-1.9V18c0-1 .8-1.8 1.8-1.9h1.6c.4 0 .8-.2 1-.5.2-.4.2-.8 0-1.1-.2-.3-.6-.5-1-.5H3.9c-1 0-2.1.4-2.8 1.2C.4 16 0 17 0 18v4c0 2.2 1.8 4 3.9 4h3.8c2.2 0 4-1.8 3.9-4v-5.8c.1-.4-.1-.9-.4-1.1-.2-.3-.7-.3-1.1-.1zM3.9 12h3.8c1 0 2.1-.4 2.8-1.2.8-.8 1.2-1.8 1.2-2.8V4c0-2.2-1.7-4-3.9-4H3.9c-1 0-2.1.4-2.8 1.2C.4 1.9 0 3 0 4v4c0 1 .4 2 1.2 2.8.7.7 1.7 1.2 2.7 1.2zM2.1 4c0-.5.2-1 .5-1.3.4-.3.8-.5 1.3-.5h3.9c1 0 1.8.8 1.8 1.9V8c0 .5-.2 1-.5 1.3-.3.3-.8.6-1.3.6H3.9c-.5 0-.9-.2-1.3-.6-.3-.4-.5-.8-.5-1.3V4z"></path>
                                            </svg></span>Просмотр заявок<br>на бронирование</a>
                            </li>
                        </ul>
                    </div>
                    <form action="${pageContext.request.contextPath}/logout">
                        <button id="logoutBtn" type="submit" class="button--outline">Выйти</button>
                    </form>
                </div>
                <div class="block__main">
                    <div class="header-block">
                        <p class="sub-title">Рады видеть вас на работе :)</p>
                        <p class="header-title"><span id="greeting">${user.getLogin()}</span> 👋</p>
                    </div>
                    <form id="formClient" action="#" class="form-client">
                        <div class="block__type-occupation">
                            <p class="title">Просмотрите список заявок от клиентов:</p>
                            <div class="block-types">

                                <c:forEach items="${orders}" var="room">

                                </c:forEach>

                                <%--<div>
                                    <nav aria-label="Navigation for orders">
                                        <ul class="pagination">

                                            <c:if test="${currentPage != 1}">
                                                <li class="page-item"><a class="page-link"
                                                                         href="room-pagination?currentPage=${currentPage-1}">Previous</a>
                                                </li>
                                            </c:if>

                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li class="page-item active"><a class="page-link">
                                                                ${i} <span class="sr-only">(current)</span></a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="page-item"><a class="page-link"
                                                                                 href="room-pagination?currentPage=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${currentPage lt noOfPages}">
                                                <li class="page-item"><a class="page-link"
                                                                         href="room-pagination?currentPage=${currentPage+1}">Next</a>
                                                </li>
                                            </c:if>

                                        </ul>
                                    </nav>
                                </div>--%>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</main>

</body>

</html>