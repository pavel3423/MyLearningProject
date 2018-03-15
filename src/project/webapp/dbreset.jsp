<%@page language="java" pageEncoding="utf-8" %>
<%@ include file="include/begin-html.jsp" %>
<p>${message}</p>
<form class="form-horizontal" action="do?command=DBReset" method="POST">
    <fieldset>
        <div class="form-group">
            <label class="col-md-4 control-label" for="button"></label>
            <div class="col-md-8">
                <button id="button" name="button" class="btn btn-danger">Сбросить базу</button>
            </div>
        </div>
    </fieldset>
</form>

<%@ include file="include/end-html.jsp" %>