<%-- 
    Document   : annonymusTemplate
    Created on : 30-Jul-2015, 12:00:46
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
        margin-left: 44%;
    }
</style>
<nav>
    <ul>
        <span class="headerElementLeft">
            <li><a href="home?idCategory=1">Početna</a></li>
            <li>
                <a href="http://localhost:8080/MyWebShop/kosarica?idArticle=pregled">Vaša košarica</a>
            </li>
        </span>
        <span class="headerElementRight">
            <li><a href="login.jsp">Prijava</a></li>
            <li><a href="registration.jsp">Registracija</a></li>
        </span>
    </ul>
</nav>









