/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vspr.java3.shop.data.DBAccessor;
import vspr.java3.shop.model.Korisnik;
import vspr.java3.shop.model.view.ArtikalView;
import vspr.java3.shop.tags.KosaricaHandler;

/**
 *
 * @author Box
 */
public class PayPalPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Korisnik k = (Korisnik) session.getAttribute("userCredentials");
        if (k != null) {
            List<ArtikalView> kosarica = (List<ArtikalView>) session.getAttribute("kosarica");
            DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
            BigDecimal ukupnaVrijednost = getUkupnaVrijednostKosarice(kosarica);
            dba.insertKosarica_db(kosarica, dba.getKorisnikID_db(k.getUsername(), k.getPassword()), ukupnaVrijednost, "PayPal");
            setAttributeValues(request, ukupnaVrijednost, kosarica);

            session.setAttribute("kosarica", null);
            RequestDispatcher rd = request.getRequestDispatcher("paypalSubmit.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void setAttributeValues(HttpServletRequest request, BigDecimal ukupnaVrijednost, List<ArtikalView> kosarica) {
        request.setAttribute("amount", ukupnaVrijednost.setScale(2, RoundingMode.CEILING));
        StringBuilder sb = new StringBuilder();
        for (ArtikalView a : kosarica) {
            sb.append(String.format("%s ", a.getNaziv().split("\\,")[0]));
            sb.append(String.format("%d\\x ", a.getKolicina()));
            sb.append("\n");
        }
        request.setAttribute("item_name", sb.toString());
    }

    private BigDecimal getUkupnaVrijednostKosarice(List<ArtikalView> kosarica) {
        KosaricaHandler kh = new KosaricaHandler();
        return kh.izracunajUkupnuCijenuKosarice(kosarica);
    }

}
