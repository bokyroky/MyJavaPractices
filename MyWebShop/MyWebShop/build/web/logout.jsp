<%-- 
    Document   : logout
    Created on : 28-Jun-2015, 11:15:49
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
        <%
            session.setAttribute("userCredentials", null);          
        %>
        <%@include file="login.jsp" %>
        
   
