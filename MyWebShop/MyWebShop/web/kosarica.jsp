<%-- 
    Document   : kosarica
    Created on : 25-Jun-2015, 16:12:40
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib uri="/WEB-INF/tlds/taglib.tld" prefix="ml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kosarica</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container myContainer">           
            <jsp:include page="category">
                <jsp:param name="idCategory" value='${idCategory}'/>
            </jsp:include>          
            <div>
                <h2>Vaša košarica</h2>
                <c:choose>
                    <c:when test="${kosarica==null || fn:length(kosarica) == 0}">
                        <h3>Košarica prazna :(</h3>
                    </c:when>
                    <c:otherwise>
                        <form name="kosarica_form" action="kosarica" method="POST">
                            <table class="table">
                                <c:forEach items="${kosarica}" var="item">
                                    <tr>
                                        <td>${item.naziv}</td>
                                        <td>${item.cijenaPoKomaduView}</td>
                                        <td><input type="text" name="${item.idArtikal}" value="${item.kolicina}" style="width:30px" /></td>                       
                                        <td>${item.ukupnaCijenaView}</td>
                                        <td><a href="kosarica?idArticle=${item.idArtikal}">Obriši</a></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td><span style="font-weight: bold;font-size: 17px">Ukupna cijena</span></td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td><ml:KosaricaHandler kosarica="${kosarica}"/></td>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr style="border-top:none">
                                    <td><a href="products?idSubCategory=${idSubCategory}" class="btn btn-default">Natrag na kupnju</a></td>
                                    <td>&nbsp;</td>
                                    <td><input type="submit" value="Osvježi košaricu" class="btn btn-default" name="btnOsvjezi" />
                                        </form>
                                    </td>
                                    <td>
                                        <a href="kosarica?idArticle=-1" class="btn btn-default">Isprazni košaricu</a>
                                    </td>
                                    <td>
                                        <form action="payment.jsp" method="POST">
                                            <input type="submit" value="Završi narudžbu"  name="narudzba" style="padding:8px" class="btn-primary" />
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </c:otherwise>
                    </c:choose>
            </div>   
        </div>

    </body>
</html>
