<%-- 
    Document   : products
    Created on : 24-Jun-2015, 16:48:39
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proizvodi</title>
        <style>
            table tr td ul li{
                display:table;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container myContainer" >
            <jsp:include page="category">
                <jsp:param name="idCategory" value='${idCategory}'/>
            </jsp:include>
            <div>
                <table class="table">
                    <c:forEach items="${proizvodi}" var="item">
                        <tr>
                            <td style="padding: 5px"><img src="${item.slika}" width="60" height="60" alt="no slika"/>
                            </td>
                            <td style="padding: 5px">
                                <ul>
                                    <li><a href="product_details?idArticle=${item.idArtikal}">${item.naziv}</a></li>
                                    <li>${item.sifraArtikla}</li>
                                </ul>
                            </td>
                            <td>
                                <form name="kosarica_form" action="kosarica" method="POST">
                                    <input type="hidden" id="txtIDProizvod"  name="idArticle" value="${item.idArtikal}"/>
                                    <input type="image" src="css/images/cart_add.png" name="btnKosarica"  class="btn-link"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>



    </body>
</html>





