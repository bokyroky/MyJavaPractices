/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vspr.java3.shop.data.DBAccessor;

/**
 *
 * @author Box
 */
@WebServlet(name = "ProductsServlet", urlPatterns = {"/products"})
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        if (dba != null) {
            String idSubParam = request.getParameter("idSubCategory");
            if("".equals(idSubParam))
                idSubParam = "1";
            int idSubCat = Integer.parseInt(idSubParam);
            session.setAttribute("idSubCategory",idSubParam);
            request.setAttribute("proizvodi", dba.getArticles_db(idSubCat));
            RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
