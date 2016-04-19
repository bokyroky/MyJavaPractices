<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Povijest kupnje</title>

    </head>
    <body>


        <%@include file="../../header.jsp" %>

        <div  class="container myContainer">
            <h1>Pregled povijesti kupnje</h1>
            <div class="form-group">
                <form name="racuni_form" action="povijestKupnje" method="POST" >
                    <select id="ddlRacuni" name="idRacun" onchange="this.form.submit()" class="btn dropdown-toggle selectpicker btn-default">
                        <option value="-1"> -- izaberite račun -- </option>
                        <c:forEach items="${racuni}" var="item">
                            <option  value="${item.idRacun}">
                                ${item.brojRacuna}
                            </option>
                        </c:forEach>
                    </select>
                </form>
            </div>
            <div>
                
                <table class="table">
                    <c:if test="${racunDetalji!=null}">
                        <tr>
                            <th colspan="4"><h3>Račun</h3></th>
                        </tr>
                        <tr>
                            <th>Broj računa</th>
                            <th>Datum izdavanja</th>
                            <th>Način plaćanja</th>
                            <th>Ukupna cijena</th>
                        </tr>
                    </c:if>

                    <tr>
                        <td>${racunDetalji.brojRacuna}</td>
                        <td>${racunDetalji.datumIzdavanja}</td>
                        <td>${racunDetalji.nacinPlacanja}</td>
                        <td><fmt:formatNumber type="currency" currencySymbol="$" value="${racunDetalji.ukupnaCijena}" /></td>
                    </tr>
                </table>
            </div>
            <div>
                
                <table class="table">
                    <c:if test="${stavke!=null}">
                        <tr>
                            <th colspan="4"><h3>Stavke</h3></th>
                        </tr>
                        <tr>
                            <th>Artikal</th>
                            <th>Količina</th>
                            <th>Cijena po komadu</th>
                            <th>Ukupna cijena</th>
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


    </body>
</html>
<script type="text/javascript">
    this.ddlRacuni.value = ${racunDetalji.idRacun};
</script>


