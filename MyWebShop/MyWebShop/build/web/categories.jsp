<%-- 
    Document   : header
    Created on : 23-Jun-2015, 19:47:01
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="categories">
    <div style="margin-bottom: 10px;margin-left:15px;">
        <label class="label label-info" style="font-size:20px">Kategorije</label>
    </div>
    <ul class="nav nav-tabs">
        <c:forEach items='${kategorije}' var="item">
            <c:choose>
                <c:when test="${idCategory==item.idKategorija}">                   
                        <li >
                            <a href="home?idCategory=${item.idKategorija}" style="background-color: lightyellow;font-weight: bold;text-decoration: underline;">${item.naziv}</a>
                        </li>
                </c:when>
                <c:otherwise>                   
                        <li>
                            <a href="home?idCategory=${item.idKategorija}">${item.naziv}</a>
                        </li>                   
                </c:otherwise>
            </c:choose>

        </c:forEach>           
    </ul>
    <ul class="nav nav-tabs">
        <c:forEach items='${potkategorije}' var="item">
            <c:choose>
                <c:when test="${idSubCategory==item.idPotKategorija}">
                    <li>
                        <a href="products?idSubCategory=${item.idPotKategorija}" style="background-color: lightyellow;font-weight: bold;text-decoration: underline">${item.naziv}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="products?idSubCategory=${item.idPotKategorija}">${item.naziv}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>  










