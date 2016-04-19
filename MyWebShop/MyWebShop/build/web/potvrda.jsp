<%-- 
    Document   : potvrda
    Created on : 29-Jul-2015, 17:00:27
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Potvrda</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container myContainer">           
            <jsp:include page="category">
                <jsp:param name="idCategory" value='${idCategory}'/>
            </jsp:include>          
            <h1>Potvrda!</h1>
            <div>
                <p>Prijavljeni ste kao korisnik ${userCredentials.username}!</p>
            </div>
            <div>
                <a href="products?idSubCategory=${idSubCategory}">< natrag na kupnju</a>
            </div>
        </div>
    </body>
</html>
