<%-- 
    Document   : header
    Created on : 28-Jun-2015, 11:19:32
    Author     : Box
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/my_style.css">

    <style>
        ul li{
            display:inline;
            margin-left: 15px;
        }
    </style>
</head>
<div class="page-header myHeader">   
    <c:choose>
        <c:when test="${sessionScope.userCredentials==null}">
            <%@include file="LoginView/annonymusView.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="LoginView/loggedInView.jsp" %>
        </c:otherwise>
    </c:choose>
</div>




