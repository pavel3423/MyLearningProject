<%@page language="java" pageEncoding="utf-8" %>
<%@ include file="include/begin-html.jsp" %>


<p>${message}</p>

<form class="form-horizontal" action="do?command=Signup" method="POST">
    <fieldset>

        <!-- Form Name -->
        <legend>Регистрация</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Login">Login</label>
            <div class="col-md-4">
                <input id="Login" name="Login" type="text" placeholder="Login" class="form-control input-md">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Email">Email</label>
            <div class="col-md-4">
                <input id="Email" name="Email" type="text" placeholder="Email" class="form-control input-md">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Password">Password</label>
            <div class="col-md-4">
                <input id="Password" name="Password" type="password" placeholder="Password"
                       class="form-control input-md">

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="City">Город</label>
            <div class="col-md-4">
                <select id="City" name="City" class="form-control">
                    <c:forEach items="${cities}" var="city">
                        <option value="${city.id}">${city.city}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Address">Адрес</label>
            <div class="col-md-4">
                <input id="Address" name="Address" type="text" placeholder="Address" class="form-control input-md">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="PhoneNumber">Номер телефона</label>
            <div class="col-md-4">
                <input id="PhoneNumber" name="PhoneNumber" type="text" placeholder="PhoneNumber"
                       class="form-control input-md">

            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-primary">Зарегистрироваться</button>
            </div>
        </div>

    </fieldset>
</form>

<%@ include file="include/end-html.jsp" %>


