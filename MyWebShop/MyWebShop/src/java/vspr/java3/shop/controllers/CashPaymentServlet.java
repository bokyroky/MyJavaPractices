/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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
public class CashPaymentServlet extends HttpServlet {

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
            dba.insertKosarica_db(kosarica,
                    dba.getKorisnikID_db(k.getUsername(),
                            k.getPassword()),
                                getUkupnaVrijednostKosarice(kosarica),
                                "Gotovina");
            session.setAttribute("kosarica", null);
            response.sendRedirect("potvrda_narudzbe.jsp");
        } else {
            response.sendRedirect("login.jsp");

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private BigDecimal getUkupnaVrijednostKosarice(List<ArtikalView> kosarica) {
        KosaricaHandler kh = new KosaricaHandler();
        return kh.izracunajUkupnuCijenuKosarice(kosarica);
    }

}
