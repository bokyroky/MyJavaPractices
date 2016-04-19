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

/**
 *
 * @author Box
 */
public class AdminServlet extends HttpServlet {

    private static boolean orderUserDirection=false;
    private static boolean orderDateDirection = false;

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderBy = request.getParameter("orderByParam");
        orderBy=(orderBy!=null)?orderBy:"KorisničkoIme";
        String sort="";
        if(orderBy.equals("KorisnickoIme")){
            changeUserOrderDirection();
            sort =(orderUserDirection)?"ASC":"DESC";
        }
        else{
            changeDateOrderDirection();
            sort =(orderDateDirection)?"ASC":"DESC";
        }
        
        
        HttpSession session = request.getSession();
         DBAccessor dba = (DBAccessor) session.getAttribute("konekcija");
        request.setAttribute("logList", dba.getLogList_db(orderBy,sort));
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Admin/administracija.jsp");
        rd.forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private static void changeUserOrderDirection(){
        orderUserDirection=!orderUserDirection;
    }
    private static void changeDateOrderDirection(){
        orderDateDirection=!orderDateDirection;
    }

}
