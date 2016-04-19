/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vspr.java3.shop.data.DBAccessor;
import vspr.java3.shop.model.Korisnik;

/**
 *
 * @author Box
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("passwordCheck");
        if (!password.equals("") && (password.equals(password2) && !username.equals(""))) {
            Korisnik korisnik = new Korisnik();
            korisnik.setUsername(username);
            korisnik.setPassword(password);
            addNewUser(korisnik, request, response);
            HttpSession session =request.getSession();
            session.setAttribute("userCredentials",korisnik);
            RequestDispatcher rd = request.getRequestDispatcher("potvrda.jsp");
            rd.forward(request, response);
        } else {
            
           request.setAttribute("error", "Nisu uneseni svi podaci ili se lozinke ne podudaraju!");
            RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addNewUser(Korisnik korisnik, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        dba.insertUser_db(korisnik);
    }

}
