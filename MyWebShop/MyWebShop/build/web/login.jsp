<%-- 
    Document   : login
    Created on : 27-Jun-2015, 18:06:17
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Prijava</title>
</head>
<%@include file="header.jsp" %>

<div class="container myContainer" > 
    <jsp:include page="category">
        <jsp:param name="idCategory" value='${idCategory}'/>
    </jsp:include>  
    <div class="form-wrapper-bd left">
        <div class="form-group">
            <fieldset>
                <legend>Prijava</legend>
                <form action="auth" method="POST">
                    <label for="txtUsername"> Korisničko ime:</label> <input type="text" id="txtUsername" name="username" class="form-control"><br>
                    <label for="txtUserPass">Lozinka:</label> <input type="password" id="txtUserPass" name="password" class="form-control" >
                    <input type="hidden"  name="hfUserType" value="Customer"/>
                    <input type="submit" value="Prijavi se" class="form-control button">
                </form>
                <div>
                    <c:if test="${param.errorCustomer!= null}">
                        <p>Pogreška:<c:out value="${param.errorCustomer}"/></p>

                    </c:if>    
                </div>
            </fieldset>      
        </div>
    </div>
    <div class="form-wrapper-bd right">
        <div class="form-group">
            <fieldset>
                <legend>Administracija</legend>
                <form action="auth" method="POST">
                    <label for="txtAdminName"> Korisničko ime:</label> <input type="text" id="txtAdminName" name="username" class="form-control"><br>
                    <label for="txtAdminPass">Lozinka:</label> <input type="password" id="txtAdminPass" name="password" class="form-control">
                    <input type="hidden" name="hfUserType" value="Admin"/>
                    <input type="submit" value="Prijavi se kao administrator" class="form-control button">
                </form>
                <div>
                    <c:if test="${param.errorAdmin!= null}">
                        <p>Pogreška:<c:out value="${param.errorAdmin}"/></p>
                    </c:if>    
                </div>
            </fieldset>  

        </div>

    </div>

</div>





