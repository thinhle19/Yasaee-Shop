/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.GooglePojo;
import user.UserDAO;
import user.UserDTO;
import user.UserError;
import utils.GoogleUtils;

/**
 *
 * @author ASUS
 */
public class LoginGoogleController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginGoogleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        UserError userError = new UserError("", "", "", "", "", "");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.html");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            request.setAttribute("id", googlePojo.getId());
            request.setAttribute("email", googlePojo.getEmail());
            UserDAO dao = new UserDAO();
            try {
                HttpSession session = request.getSession();
                UserDTO user = dao.checkLoginGoogle(googlePojo.getId());
                if (user == null) {
                    boolean checkDuplicate = dao.checkDuplicate(googlePojo.getId());
                    if (checkDuplicate) {
                        userError.setUserIDError("Duplicate User ID :" + googlePojo.getId());
                        request.setAttribute("USER_ERROR", userError);
                    } else {
                        boolean check = dao.insertUserGoogle(googlePojo);
                        if (check) {
                            session.setAttribute("LOGIN_USER", user);
                            RequestDispatcher dis = request.getRequestDispatcher("user.jsp");
                            dis.forward(request, response);
                        }
                    }
                } else {
                    session.setAttribute("LOGIN_USER", user);
                    RequestDispatcher dis = request.getRequestDispatcher("user.jsp");
                    dis.forward(request, response);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginGoogleController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
