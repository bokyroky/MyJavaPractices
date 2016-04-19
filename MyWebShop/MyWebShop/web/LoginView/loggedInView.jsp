<%-- 
    Document   : loggedInTemplate
    Created on : 30-Jul-2015, 12:02:41
    Author     : Box
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    ul li{
            display:inline;
            margin-left: 15px;
        }
    .headerElementLeft{    
        margin-left: 16%;
    }
    .headerElementRight{
        margin-left: 35%;
    }
</style>
<nav>
    <ul>
        <span class="headerElementLeft">
            <li><a href="home?idCategory=1">Početna</a></li>
            <li>
                <a href="http://localhost:8080/MyWebShop/kosarica?idArticle=pregled">Vaša košarica</a>
            </li>
            <li>
                <a href="http://localhost:8080/MyWebShop/povijestKupnje">Pregled povijesti kupnje</a> 
            </li>
        </span>
        <span class="headerElementRight">
            <li><a href="logout.jsp">Odjava</a></li>
        </span>



    </ul>
</nav>





