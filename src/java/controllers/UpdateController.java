/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;
import user.UserError;

/**
 *
 * @author ASUS
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "update.jsp";
    private static final String SUCCESS = "SearchController";
    private static final String LOGOUT = "LogoutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String name = request.getParameter("name");
            String roleID = request.getParameter("roleID");
            String address = request.getParameter("address");
            String status = request.getParameter("status");
            UserError userError = new UserError("", "", "", "", "", "");
            boolean check = true;
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (name.length() < 5) {
                userError.setNameError("Name must contains 5 or more characters");
                check = false;
            }
            if (address.length() > 15) {
                userError.setAddressError("Invalid address");
                check = false;
            }
            if (check) {
                UserDAO dao = new UserDAO();
                UserDTO user = new UserDTO(userID, name, "", roleID, address,status,"");
                boolean checkUpdate = dao.checkUpdate(user);
                if (checkUpdate) {
                    if (userID.equals(loginUser.getUserID())) {
                        url = LOGOUT;
                    } else {
                        url = SUCCESS;
                    }
                }

            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
