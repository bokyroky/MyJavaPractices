/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vspr.java3.shop.data.DBAccessor;
import vspr.java3.shop.model.Korisnik;
import vspr.java3.shop.model.Racun;

/**
 *
 * @author Box
 */
public class PovijestKupnjeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Korisnik k = (Korisnik) session.getAttribute("userCredentials");
        if (k != null) {
            DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
            prikaziRacune(dba, request, session);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Prijavljeni/povijest.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        prikaziRacune(dba, request, session);
        prikaziDetaljeIzabranogRacuna(dba, request, session);
        prikaziStavkeIzabranogRacuna(dba, request, session);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Prijavljeni/povijest.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void prikaziRacune(DBAccessor dba, HttpServletRequest request, HttpSession session) {
        Korisnik k = (Korisnik) session.getAttribute("userCredentials");
        request.setAttribute(
                "racuni",
                dba.getRacuniForKupac_db(dba.getKorisnikID_db(k.getUsername(), k.getPassword())
                )
        );
    }

    private void prikaziDetaljeIzabranogRacuna(DBAccessor dba, HttpServletRequest request, HttpSession session) {
        String idRacun = request.getParameter("idRacun");
        if (!"-1".equals(idRacun)) {
            Racun r = dba.getRacunDetalji_db(Integer.parseInt(idRacun));
            request.setAttribute("racunDetalji", r);
        }

    }

    private void prikaziStavkeIzabranogRacuna(DBAccessor dba, HttpServletRequest request, HttpSession session) {

        String idRacun = request.getParameter("idRacun");
        if (!"-1".equals(idRacun)) {
            request.setAttribute("stavke", dba.getStavkeForRacun_db(Integer.parseInt(idRacun)));
        }

    }

}
