<%-- 
    Document   : administracija
    Created on : 02-Aug-2015, 20:04:23
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Povijest logiranja</title>
    </head>
    <body>

        <%@include file="../../LoginView/adminView.jsp" %>

        <div class="container myContainer" >
            <h1>Administracija</h1>
            <div>
                <div>
                    <fieldset>
                        <legend>Pregled logiranja</legend>
                        <table class="table">
                            <tr>
                                <td><a href="http://localhost:8080/MyWebShop/admin?orderByParam=KorisnickoIme" >Korisničko ime</a></td>
                                <td><a href="http://localhost:8080/MyWebShop/admin?orderByParam=DatumVrijemeLogiranja">Datum i vrijeme</a></td>
                                <td>IP Adresa</td>
                            </tr>
                            <c:forEach items="${logList}" var="item">
                                <tr>
                                    <td>${item.username}</td>
                                    <td>${item.logDateTime}</td>
                                    <td>${item.userIPAdress}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </fieldset>
                </div>
            </div>
        </div>

    </body>
</html>
