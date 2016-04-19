<%-- 
    Document   : pregledTransakcija
    Created on : 04-Aug-2015, 12:21:28
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pregled transakcija</title>   

    </head>
    <body>

        <%@include file="../../LoginView/adminView.jsp" %>

        <div class="container myContainer">
            <div>
                <h1>Administracija</h1>
                <div>
                    <fieldset>
                        <legend>Pregled transakcija</legend>
                        <table id="tblTransakcije" class="table">
                            <tr>
                                <th>Kupac:</th>
                            </tr>
                            <form id="formTransakcije" action="pregledTransakcija" method="POST">
                                <tr>
                                    <td colspan="2">

                                        <select id="ddlKupci" name="idKupac" class="btn dropdown-toggle selectpicker btn-default" onchange="this.form.submit()">
                                            <option value="-1"> -- Kupci -- </option>
                                            <c:forEach items="${kupci}" var="item">
                                                <option value="${item.value}">
                                                    ${item.text}
                                                </option>
                                            </c:forEach> 
                                        </select>
                                    </td>
                                </tr>             
                                <tr>                         
                                    <td>Od datuma: </td>
                                    <td><input id="txtStartDate" type="text" name="startDate"   class="form-control" /></td>                            
                                </tr>
                                <tr>
                                    <td>Do datuma: </td>
                                    <td><input id="txtEndDate" type="text" name="endDate" class="form-control" /></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="submit" value="Prikaži" onclick="document.getElementById('formTransakcije').submit();" class="btn-default"/>
                          
                                    </td>
                                </tr>
                                <c:if test="${dateError!=null}">
                                    <tr>
                                        <td colspan="2"><span style="color:red;text-decoration: underline">Neispravan unos datuma!</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">${dateError}</td>
                                    </tr>
                                </c:if>
                            </form>
                        </table>


                </div>
                <div>
                    <table class="table">
                        <c:if test="${racuni!=null && fn:length(racuni) gt 0}">
                            <tr>
                                <th>Broj računa</th>
                                <th>Datum izdavanja</th>
                                <th>Način plaćanja</th>
                                <th>Ukupna cijena</th>
                                <th>&nbsp;</th>
                            </tr>
                        </c:if>
                        <c:forEach items="${racuni}" var="item">
                            <tr>
                                <td>${item.brojRacuna}</td>
                                <td>${item.datumIzdavanja}</td>
                                <td>${item.nacinPlacanja}</td>
                                <td><fmt:formatNumber type="currency" currencySymbol="$" value="${item.ukupnaCijena}" /></td>
                                <td><a href="http://localhost:8080/MyWebShop/pregledTransakcija?idRacun=${item.idRacun}&idKupac=${item.kupacID}">
                                        Prikaži stavke</a></td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </div>
            <div>
                <table class="table">
                    <c:if test="${stavke!=null}">
                        <tr>
                            <th>Naziv</th>
                            <th>Količina</th>
                            <th>Cijena po komadu</th>
                            <th>Ukupna cijena</th>
                            <th>&nbsp;</th>
                        </tr>
                    </c:if>
                    <c:forEach items="${stavke}" var="item">
                        <tr>
                            <td>${item.nazivArtikla}</td>
                            <td>${item.kolicina}</td>
                            <td><fmt:formatNumber type="currency" currencySymbol="$" value="${item.cijenaPoKomadu}" /></td>
                            <td><fmt:formatNumber type="currency" currencySymbol="$" value="${item.ukupnaCijena}" /></td>             
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </fieldset>
</body>
</html>

<script type="text/javascript">
    this.ddlKupci.value = <c:out value="${racuni[0].kupacID}" />;
</script>

