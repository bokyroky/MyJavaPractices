<%-- 
    Document   : registration
    Created on : 29-Jul-2015, 17:55:37
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registracija</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container myContainer">
            <div id="registration">
                <h1>Registracija</h1>
                <div class="form-wrapper-bd">
                    <fieldset>
                        <legend>
                            Registracija
                        </legend>
                        <form action="registration" method="POST">
                            <div class="form-group">
                                <label for="txtUserName">Username:</label> <input id="txtUsername" type="text" name="username" class="form-control"><br/>
                                <label for="txtPass1">Password:</label><input id="txtPass1" type="password" name="password" class="form-control"><br/>
                                <label for="txtPass2">Confirm password:</label><input id="txtPass2" type="password" name="passwordCheck" class="form-control"><br/>
                                <input type="submit"  value="Spremi podatke" class="form-control button">
                            </div>
                        </form>
                        <c:if test="${error!= null}">
                            <span style="color:red;font-weight:bold">Pogreška:<c:out value="${error}"/></span>
                        </c:if>    
                    </fieldset>
                </div>
            </div>
        </div>
    </body>
</html>
