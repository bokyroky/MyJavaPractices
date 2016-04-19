/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.HttpMethod;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import sun.net.www.http.HttpClient;
import vspr.java3.shop.data.DBAccessor;
import vspr.java3.shop.model.Korisnik;
import vspr.java3.shop.model.UserLog;

/**
 *
 * @author Box
 */
public class UserAuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String userType = request.getParameter("hfUserType");
        boolean isAdmin = ("Admin".equals(userType));
        if (userIsValid(user, pass, request, isAdmin)) {
            Korisnik korisnik = new Korisnik();
            korisnik.setUsername(user);
            korisnik.setPassword(pass);
            HttpSession session = request.getSession();
            session.setAttribute("userCredentials", korisnik);

            createLoginEvidence(korisnik, request, session);
            if (isAdmin) {
                try {

                    response.sendRedirect("admin");
                } catch (Exception ex) {
                    Logger.getLogger(UserAuthServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("potvrda.jsp");
            }

        } else {

            response.sendRedirect("login.jsp?error" + userType + "='Korisnik nije registriran!'");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean userIsValid(String user, String pass, HttpServletRequest request, boolean isAdmin) {
        HttpSession session = request.getSession();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(user);
        korisnik.setPassword(pass);
        int userValidation = dba.validateUserCredentials_db(korisnik, isAdmin);
        boolean valid = (userValidation != 0);
        return valid;
    }

    private void createLoginEvidence(Korisnik korisnik, HttpServletRequest request, HttpSession session) {
        String ipAdresa = request.getRemoteAddr();
        String username = ((Korisnik) session.getAttribute("userCredentials")).getUsername();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        UserLog log = new UserLog();
        log.setUsername(username);
        log.setUserIPAdress(ipAdresa);

        dba.addNewLog_db(log);
    }

}
