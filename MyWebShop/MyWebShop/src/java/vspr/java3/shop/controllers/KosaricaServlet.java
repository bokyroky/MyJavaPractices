/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vspr.java3.shop.data.DBAccessor;
import vspr.java3.shop.model.Artikal;
import vspr.java3.shop.model.view.ArtikalView;

/**
 *
 * @author Box
 */
public class KosaricaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idArtikal = request.getParameter("idArticle");
        switch (idArtikal) {
            case "pregled":
                break;
            case "-1":
                isprazniKosaricu(request);
                break;
            default:
                obrisiArtikal(Integer.parseInt(idArtikal), request);
                break;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("kosarica.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idArtikalParam = request.getParameter("idArticle");
        if (idArtikalParam != null) {
            dodajNoviArtikalUKosaricu(idArtikalParam, session);

        } else {
            osvjeziKosaricu(request, session);

        }
        response.sendRedirect("kosarica.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private ArtikalView getArtikalViewObject(Artikal artikal) {
        ArtikalView artikalView = new ArtikalView();
        artikalView.setCijenaBezPDV(artikal.getCijenaBezPDV());
        artikalView.setIdArtikal(artikal.getIdArtikal());
        artikalView.setNaziv(artikal.getNaziv());
        artikalView.setOpis(artikal.getOpis());
        artikalView.setSifraArtikla(artikal.getSifraArtikla());
        return artikalView;
    }

    private int getKolicina(HttpServletRequest request, Artikal artikal) {
        int kolicina = 1;
        String kolicinaParam = request.getParameter(String.valueOf(artikal.getIdArtikal()));
        if (kolicinaParam != null) {
            kolicina = Integer.parseInt(kolicinaParam);
        }
        return kolicina;
    }

    private BigDecimal calculateUkupnaCijena(ArtikalView artikalView) {
        BigDecimal c = artikalView.getCijenaBezPDV();
        BigDecimal ukupnaCijena = new BigDecimal(0);
        for (int i = 0; i < artikalView.getKolicina(); i++) {
            ukupnaCijena = ukupnaCijena.add(c);
        }
        return ukupnaCijena;
    }

    private void osvjeziKosaricu(HttpServletRequest request, HttpSession session) {
        List<ArtikalView> artikli = (List<ArtikalView>) session.getAttribute("kosarica");
        for (ArtikalView a : artikli) {
            String kolicina = request.getParameter(String.valueOf(a.getIdArtikal()));
            short kolicinaShort = tryParseShort(kolicina);
            if (kolicinaShort == -1) {
                return;
            }
            a.setKolicina(kolicinaShort);
            a.setUkupnaCijena(calculateUkupnaCijena(a));
        }
        session.setAttribute("kosarica", artikli);
    }

    private void dodajNoviArtikalUKosaricu(String idArtikalParam, HttpSession session) {
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        if (dba != null) {
            int idArticle = Integer.parseInt(idArtikalParam);
            Artikal artikal = dba.getArticle_db(idArticle);
            ArtikalView artikalView = getArtikalViewObject(artikal);
            if (session.getAttribute("kosarica") == null) {
                List<ArtikalView> artikli = new LinkedList<>();

                artikalView.setKolicina((short) 1);
                artikalView.setUkupnaCijena(calculateUkupnaCijena(artikalView));
                artikli.add(artikalView);
                session.setAttribute("kosarica", artikli);
            } else {
                List<ArtikalView> artikli = (LinkedList<ArtikalView>) session.getAttribute("kosarica");
                if (artikalVecDodanUKosaricu(artikli, artikalView)) {

                    session.setAttribute("idSubCategorySession", artikal.getPotKategorijaID());
                    session.setAttribute("kosarica", artikli);

                } else {
                    artikalView.setKolicina((short) 1);
                    artikalView.setUkupnaCijena(calculateUkupnaCijena(artikalView));
                    artikli.add(artikalView);
                    session.setAttribute("idSubCategorySession", artikal.getPotKategorijaID());
                    session.setAttribute("kosarica", artikli);
                }

            }

        }

    }

    private void obrisiArtikal(int idArtikal, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ArtikalView> artikli = (List<ArtikalView>) session.getAttribute("kosarica");
        List<ArtikalView> helperList = new LinkedList<>();
        for (ArtikalView a : artikli) {
            helperList.add(a);
        }
        for (ArtikalView a : helperList) {
            if (idArtikal == a.getIdArtikal()) {
                artikli.remove(a);
            }
        }
        session.setAttribute("kosarica", artikli);

    }

    private void isprazniKosaricu(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ArtikalView> artikli = (List<ArtikalView>) session.getAttribute("kosarica");
        artikli.clear();
        session.setAttribute("kosarica", artikli);
    }

    private boolean artikalVecDodanUKosaricu(List<ArtikalView> artikli, ArtikalView artikalView) {
        for (ArtikalView a : artikli) {
            if (artikalView.getIdArtikal() == a.getIdArtikal()) {
                a.setKolicina((short) (a.getKolicina() + 1));
                a.setUkupnaCijena(calculateUkupnaCijena(a));
                return true;
            }
        }
        return false;
    }

    private short tryParseShort(String kolicina) {
        try {
            short novaKolicina = Short.parseShort(kolicina);
            return novaKolicina;
        } catch (NumberFormatException ex) {
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
}
