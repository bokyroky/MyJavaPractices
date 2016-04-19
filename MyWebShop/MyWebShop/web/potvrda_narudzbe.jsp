<%-- 
    Document   : potvrdaNarudzbe
    Created on : 31-Jul-2015, 17:24:28
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Potvrda narudzbe</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container myContainer">
            <jsp:include page="category">
                <jsp:param name="idCategory" value='${idCategory}'/>
            </jsp:include>

            <h1>Potvrda!</h1>
            <p>
                Vaša narudžba je uspješno zaprimljena!           
            </p>
        </div>
    </body>
</html>
