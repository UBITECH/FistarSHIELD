package eu.ubitech.fistar.shield.servlet;

import eu.ubitech.fistar.other.Util;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author smantzouratos
 */
public class assignRole extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getUserPrincipal().getName();
        request.setAttribute("username", username);

        String userRole = Util.getUserRole(username);
        request.setAttribute("userRole", userRole);

        if (userRole.equalsIgnoreCase("user")) {

            response.sendRedirect("createPseudonym");

        } else if (userRole.equalsIgnoreCase("admin")) {
            
            String userDN = request.getParameter("userDN");
            String idm = request.getParameter("idm");
            
            System.out.println("ID: " + userDN);
            System.out.println("IDM: " + idm);   
            
            boolean roleAdded = Util.assignIDMRoleToUser(idm, userDN);
            
            String m = "ERROR";
            
            if (roleAdded) {
                m = "ROK";
            }
            
            response.sendRedirect("users?m=" + m);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
