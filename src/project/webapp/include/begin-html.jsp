<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" pageEncoding="utf-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Автомобильный каталог</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
                aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <a class="navbar-brand" href="do?command=Index">Каталог</a>
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

                <c:if test="${user!=null}">
                    <c:if test="${user.rolesID==2}">
                        <li class="nav-item">
                            <a class="nav-link" href="do?command=AddCar">Добавить автомобиль</a>
                        </li>
                    </c:if>

                    <c:if test="${user.rolesID==1}">
                        <li class="nav-item">
                            <a class="nav-link" href="do?command=AllCarsUser">Автомобили пользователей</a>
                        </li>
                    </c:if>
                    <c:if test="${user.rolesID==2}">
                        <li class="nav-item">
                            <a class="nav-link" href="do?command=AllCarsUser">Автомобили пользователя</a>
                        </li>
                    </c:if>

                </c:if>

                <li class="nav-item">
                    <a class="nav-link" href="do?command=DBReset">Сброс базы данных</a>
                </li>
            </ul>
            <div align="right">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0" align="right">
                    <c:if test="${user==null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="do?command=Login">Войти</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="do?command=SignUp">Зарегистрироваться</a>
                        </li>
                    </c:if>
                    <c:if test="${user!=null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="do?command=Profile">${user.login}</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="do?command=Logout">Выйти</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>