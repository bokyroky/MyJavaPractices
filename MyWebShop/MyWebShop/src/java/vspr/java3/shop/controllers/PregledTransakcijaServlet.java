/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vspr.java3.shop.data.DBAccessor;

/**
 *
 * @author Box
 */
public class PregledTransakcijaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        prikaziKupce(request,dba);
        
        String idKupac = request.getParameter("idKupac");
        String idRacun = request.getParameter("idRacun");
        if(idKupac != null && idRacun != null){
            prikaziRacune(idKupac,request,session,dba);
            prikaziStavkeZaRacun(idRacun,request,dba);
        } 
       
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Admin/pregledTransakcija.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        prikaziKupce(request,dba);
        prikaziRacune(null,request,session,dba);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Admin/pregledTransakcija.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private java.sql.Date convertToDate(String dateString, HttpServletRequest request) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            java.util.Date date = df.parse(dateString);

            return new java.sql.Date(date.getTime());
        } catch (IllegalArgumentException ex) {

            request.setAttribute("dateError", "Neispravan unos datuma!");
            return null;
        } catch (Exception ex) {
            request.setAttribute("dateError",ex.getLocalizedMessage());
            return null;
        }
    }


    private void addDatesToSession(String start, String end, HttpSession session) {
        session.setAttribute("startDate", start);
        session.setAttribute("endDate", end);
    }

    private void prikaziKupce(HttpServletRequest request, DBAccessor dba) {
        request.setAttribute("kupci", dba.getKupci_db());
    }

    private void prikaziRacune(String idKupacParam,HttpServletRequest request, HttpSession session,DBAccessor dba) {
        String idKupac =(idKupacParam!=null)?idKupacParam:request.getParameter("idKupac");
        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");
        if(start==null && end==null){
            start = session.getAttribute("startDate").toString();
            end = session.getAttribute("endDate").toString();
        }
        addDatesToSession(start,end,session);
        java.sql.Date startDate = (!"".equals(start)) ? convertToDate(start, request) : null;
        java.sql.Date endDate = (!"".equals(end)) ? convertToDate(end, request) : null;
        request.setAttribute("racuni", dba.getRacuniForKupacWithFilter_db(Integer.parseInt(idKupac), startDate, endDate));
    }

    private void prikaziStavkeZaRacun(String idRacun, HttpServletRequest request, DBAccessor dba) {
        request.setAttribute("stavke", dba.getStavkeForRacun_db(Integer.parseInt(idRacun)));
    }

}
