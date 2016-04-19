<%-- 
    Document   : paypalSubmitForm
    Created on : 17-Aug-2015, 22:00:03
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <script>
            window.onload = function () {
                document.forms['paypalForm'].submit();

            };
        </script>
    </head>
    <body>
        <form id="paypalForm" name="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="POST">
            <input type="hidden" name="cmd" value="_xclick" />
            <input type="hidden" name="business" value="bodrakul-facilitator@inet.hr" />
            <input type="hidden" name="item_name" value="${item_name}" />         
            <input type="hidden" name="amount" value="${amount}" />
            <input type="hidden" name="no_shipping" value="0"/>
            <input type="hidden" name="no_note" value="1"/>
            <input type="hidden" name="currency_code" value="USD"/>
            <input type="hidden" name="lc" value="AU"/>
            <input type="hidden" name="return" value="http://localhost:8080/MyWebShop/potvrda_narudzbe.jsp"/>
            <input type="hidden" name="bn" value="PP-BuyNowBF"/>
        </form>
    </body>
</html>
