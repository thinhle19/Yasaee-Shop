/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.User;
import user.UserDAO;
import user.UserError;

/**
 *
 * @author letie
 */
public class SignUpController extends HttpServlet {

    private static final String ERROR = "sign-up.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phoneNum = request.getParameter("phone_num");
            
            //validation 
            boolean isValidInfo = true;
            UserError userError = new UserError();
            if (username.length() < 2 || username.length() > 10) {
                userError.setUsername("Username length must be from 2 - 10 characters");
                isValidInfo = false;
            }
            if (name.length() < 5 || name.length() > 50) {
                userError.setName("Full name length must be from 5 - 50 characters");
                isValidInfo = false;
            }
            if (password.length() < 1 || password.length() > 50) {
                userError.setPassword("Password length must be from 1 - 50 characters");
                isValidInfo = false;
            }
            if (!confirm.equals(password)) {
                userError.setConfirmPassword("Not matched password");
                isValidInfo = false;
            }
            boolean sqlCheck = UserDAO.isContainUser(username);
            if (sqlCheck) {
                userError.setUsername("This username was took by another");
                isValidInfo = false;
            }
            if (isValidInfo) {
                boolean checkInsert = UserDAO.addUser(new User(new Date().toString(), "CU", name, address, email, phoneNum, username, password, ""));
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("SignUp Controller " + e.toString());
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
