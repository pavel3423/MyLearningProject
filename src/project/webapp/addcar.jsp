<%@page language="java" pageEncoding="utf-8" %>
<%@ include file="include/begin-html.jsp" %>
<p>${message}</p>
<form class="form-horizontal" action="do?command=addCar" method="POST">
    <fieldset>

        <!-- Form Name -->
        <legend>Добавить автомобиль</legend>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Brand">Марка</label>
            <div class="col-md-4">
                <select id="Brand" name="Brand" class="form-control">
                    <c:forEach items="${brands}" var="brand">
                        <option value="${brand.id}"
                                <c:if test="${brand.id==car.brandID}">
                                    selected
                                </c:if>
                        >${brand.brand}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Model">Модель</label>
            <div class="col-md-4">
                <input id="Model" name="Model" type="text" placeholder="Модель" class="form-control input-md">
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="CarClass">Класс</label>
            <div class="col-md-4">
                <select id="CarClass" name="CarClass" class="form-control">
                    <c:forEach items="${bodies}" var="body">
                        <option value="${body.id}">${body.body}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Price">Цена</label>
            <div class="col-md-4">
                <input id="Price" name="Price" type="text" placeholder="Цена" class="form-control input-md">

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Year">Год выпуска</label>
            <div class="col-md-4">
                <select id="Year" name="Year" class="form-control">
                    <c:forEach items="${years}" var="year">
                        <option value="${year.year}"
                                <c:if test="${year.year==car.year}">
                                    selected
                                </c:if>
                        >${year.year}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="CarButton"></label>
            <div class="col-md-4">
                <button id="CarButton" name="CarButton" class="btn btn-primary">Добавить автомобиль</button>
            </div>
        </div>

    </fieldset>
</form>

<%@ include file="include/end-html.jsp" %>