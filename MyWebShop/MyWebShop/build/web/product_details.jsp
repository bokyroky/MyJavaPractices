<%-- 
    Document   : proizvod_detalji
    Created on : 25-Jun-2015, 11:49:39
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalji proizvoda</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container myContainer" >
            <jsp:include page="category">
                <jsp:param name="idCategory" value='${idCategory}'/>
            </jsp:include>
            <div>
                <h3>
                    <label  style="display:inline-block;vertical-align: middle">
                        ${proizvod.naziv}
                    </label>
                </h3>
                <div id="articlePicture">
                    <img src="${proizvod.slika}" width="300" height="300" alt="no slika"/>
                </div>
                <div style="margin-top:2em;padding:5px">
                    <form name="kosarica_form" action="kosarica" method="POST">
                        <input type="hidden" id="txtIDProizvod"  name="idArticle" value="${proizvod.idArtikal}"/>
                        <input type="submit" value="U košaricu" name="btnKosarica" class="btn-success" />
                    </form>
                </div>
                <h3>
                    <label class=" label label-info">
                        Informacije o proizvodu
                    </label>  
                </h3>
                <div style="margin-top:1em">
                    <table class="table">
                        <tr>
                            <td>Šifra: </td>
                            <td>${proizvod.sifraArtikla}</td>
                        </tr>
                        <tr>
                            <td>Naziv: </td>
                            <td>${proizvod.naziv}</td>                
                        </tr>
                        <tr>
                            <td>Cijena: </td>
                            <td><fmt:formatNumber type="currency" currencySymbol="$" value="${proizvod.cijenaBezPDV}" /></td>
                        </tr>
                        <tr>
                            <td>Opis: </td>
                            <td>${proizvod.opis}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
