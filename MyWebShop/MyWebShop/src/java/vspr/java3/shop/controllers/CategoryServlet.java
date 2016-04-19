/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import vspr.java3.shop.data.DBAccessor;

/**
 *
 * @author Box
 */
public class CategoryServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        if (dba == null) {
            dba = new DBAccessor(kreirajDataSource());
            session.setAttribute("konekcija", dba);
        }

        request.setAttribute("kategorije", dba.getCategories_db());

        request.setAttribute("potkategorije", dba.getSubCategories_db(Integer.valueOf(request.getParameter("idCategory"))));
        RequestDispatcher dispatcher = request.getRequestDispatcher("categories.jsp");
        dispatcher.include(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static DataSource kreirajDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("MyWebShop");
        dataSource.setUser("sa");
        dataSource.setPassword("SQL");
        return dataSource;
    }

}
