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
import shopping.Books;
import shopping.Cart;
import user.UserDTO;

/**
 *
 * @author ASUS
 */
public class AddToCartController extends HttpServlet {

    private static final String SUCCESS = "ShowBooksController";
    private static final String ERROR = "error.jsp";
    private static final String LOGOUT = "LogoutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean check = true;
            HttpSession session = request.getSession(true);
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                url = LOGOUT;
                check = false;
            }
            if (check) {
                String bookID = request.getParameter("bookId");
                String bookName = request.getParameter("bookName");
                String description = request.getParameter("description");
                String image = request.getParameter("image");
                int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
                float price2 = Float.parseFloat(request.getParameter("price"));
                Books book = new Books(bookID, bookName, orderQuantity, price2, description, image);
                Cart cart = (Cart) session.getAttribute("CART"); //get truoc de xem co null hay khong
                if (cart == null) {
                    cart = new Cart();
                }
                cart.add(book);
                session.setAttribute("CART", cart);
                String message = "You just choose " + orderQuantity + " " + bookName + " succesfully!";
                request.setAttribute("SHOPPING_MESSAGE", message);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at AddToCartController" + e.toString());
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
