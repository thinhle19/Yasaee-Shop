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

/**
 *
 * @author letie
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String SIGN_UP = "SignUpController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String VIEW_CART = "view-cart.jsp";
    private static final String UPDATE_CART = "UpdateCartController";
    private static final String REMOVE_CART = "RemoveCartController";
    private static final String CHECKOUT_CART = "check-out.jsp";
    private static final String BUY_CART = "BuyController";
    private static final String SEARCH_PRODUCT = "SearchProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "Login":
                        url = LOGIN;
                        break;
                    case "Logout":
                        url = LOGOUT;
                        break;
                    case "SignUp":
                        url = SIGN_UP;
                        break;
                    case "Add to Cart":
                        url = ADD_TO_CART;
                        break;
                    case "View Cart":
                        url = VIEW_CART;
                        break;
                    case "SearchProduct":
                        url = SEARCH_PRODUCT;
                        break;
                    case "Modify":
                        url = UPDATE_CART;
                        break;
                    case "Remove":
                        url = REMOVE_CART;
                        break;
                    case "Check Out":
                        url = CHECKOUT_CART;
                        break;
                    case "Buy":
                        url = BUY_CART;
                        break;
                }
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
