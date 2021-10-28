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
 * @author ASUS
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH = "SearchController";
    private static final String DELETE = "DeleteController";
    private static final String UPDATE_PAGE = "update.jsp";
    private static final String CONFIRM_UPDATE = "UpdateController";
    private static final String CREATE = "CreateController";
    private static final String SHOW_BOOK_LIST = "ShowBooksController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String UPDATE_CART = "UpdateCartController";
    private static final String REMOVE_CART = "RemoveCartController";
    private static final String CHECKOUT_CART = "checkOut.jsp";
    private static final String BUY_CART = "BuyController";
    private static final String SEARCH_BOOK = "SearchBooksController";

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
                    case "Search":
                        url = SEARCH;
                        break;
                    case "Logout":
                        url = LOGOUT;
                        break;
                    case "Delete":
                        url = DELETE;
                        break;
                    case "Update":
                        url = UPDATE_PAGE;
                        break;
                    case "Confirm Update":
                        url = CONFIRM_UPDATE;
                        break;
                    case "Show Book List":
                        url = SHOW_BOOK_LIST;
                        break;
                    case "Create":
                        url = CREATE;
                        break;
                    case "Add to Cart":
                        url = ADD_TO_CART;
                        break;
                    case "View Cart":
                        url = VIEW_CART;
                        break;
                    case "SearchBook":
                        url = SEARCH_BOOK;
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
