<%-- 
    Document   : payment
    Created on : 30-Jul-2015, 19:30:14
    Author     : Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Placanje</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div  class="container myContainer">
            <div id="payment">
                <fieldset class="scheduler-border">
                    <legend>Odaberite način plaćanja</legend>
                    <div style="margin-bottom: 20px">
                        <form action="cash" method="POST">
                            <input type="submit" value="Gotovina" class="btn-default pay" />
                        </form>
                    </div>
                    <div>
                        <form action="paypal" method="POST">

                            <input type="submit" value="PayPal" class="btn-default pay" />
                        </form>
                    </div>
                </fieldset>
            </div>
        </div>
    </body>
</html>
