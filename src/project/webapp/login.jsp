<%@page language="java" pageEncoding="utf-8" %>
<%@ include file="include/begin-html.jsp" %>

<p>${message}</p>
<form class="form-horizontal" action="do?command=Login" method="POST">
    <fieldset>


        <!-- Form Name -->
        <legend>Войти</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Login">Login</label>
            <div class="col-md-4">
                <input id="Login" name="Login" type="text" placeholder="Login" class="form-control input-md"
                       value="bayernkraft.by">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Password">Password</label>
            <div class="col-md-4">
                <input id="Password" name="Password" type="password" placeholder="Password"
                       class="form-control input-md" value="bayernkraft">
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="button"></label>
            <div class="col-md-4">
                <button id="button" name="button" class="btn btn-success">Войти</button>
            </div>
        </div>

    </fieldset>
</form>

<%@ include file="include/end-html.jsp" %>