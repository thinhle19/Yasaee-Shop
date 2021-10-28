/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserDAO;
import user.UserDTO;
import user.UserError;
import utils.Encryption;

/**
 *
 * @author ASUS
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError("","","","","","");
        try {
            String userID = request.getParameter("userID");
            String name = request.getParameter("name");
            String roleID = request.getParameter("roleID");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm");
            Date now = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("MM/dd/YYYY");
            boolean check = true;
            if (userID.length() > 10 || userID.length() < 2) {
                userError.setUserIDError("User ID must be from 2 to 10");
                check = false;
            }
            if (name.length() > 20 || name.length() < 5) {
                userError.setNameError("Full name must be from 5 to 20");
                check = false;
            }
            if (address.length() > 20) {
                userError.setAddressError("Invalid address");
                check = false;
            }
            if (!password.equals(confirmPassword)) {
                userError.setConfirmPasswordError("Password not match");
                check = false;
            }
            if (check) {
                UserDAO dao = new UserDAO();
                boolean checkDuplicate = dao.checkDuplicate(userID);
                if (checkDuplicate) {
                    userError.setUserIDError("Duplicate User ID : " + userID);
                    request.setAttribute("USER_ERROR", userError);
                } else {
                    String pw = Encryption.getMD5(password);
                    UserDTO user = new UserDTO(userID, name, pw, roleID, address,"active",dt.format(now));
                    boolean checkInsert = dao.insertUser(user);
                    if (checkInsert) {
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
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
