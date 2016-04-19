<%-- 
    Document   : home
    Created on : 24-Jun-2015, 10:22:10
    Author     : Box
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Početna</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div  class="container myContainer">
            <jsp:include page="category">
                <jsp:param name="idCategory" value='${idCategory}'/>
            </jsp:include>
        </div>
    </body>
</html>
