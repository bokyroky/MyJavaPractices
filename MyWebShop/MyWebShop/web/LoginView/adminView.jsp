<%-- 
    Document   : adminView
    Created on : 02-Aug-2015, 20:10:44
    Author     : Box
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/my_style.css">
    <style>
        ul li{
            display:inline;
            margin-left: 15px;
        }
        .headerElementLeft{    
            margin-left: 16%;
        }
        .headerElementRight{
            margin-left: 40%;
        }
    </style>    
    <!-- Load jQuery from Google's CDN -->
    <!-- Load jQuery UI CSS  -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css" />

    <!-- Load jQuery JS -->
    <script src="http://code.jquery.com/jquery-2.1.4.js"></script>
    <!-- Load jQuery UI Main JS  -->
    <script src="http://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>


    <script>
        $(document).ready(function () {
            $("input[id^='txt']").datepicker({dateFormat: 'yy.mm.dd', maxDate: new Date(), changeMonth: true, changeYear: true
            });
        });


    </script>

</head>
<div class="page-header myHeader">
    <nav>
        <ul>
            <span class="headerElementLeft">
                <li><a href="http://localhost:8080/MyWebShop/pregledTransakcija">Pregled transakcija</a></li>
                <li><a href="http://localhost:8080/MyWebShop/admin">Pregled logiranja</a></li>    
            </span>

            <span class="headerElementRight">
                <li><a href="logout.jsp">Odjava</a></li>
            </span>
        </ul>
    </nav>
</div>










