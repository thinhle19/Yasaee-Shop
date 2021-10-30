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
import shopping.Cart;
import shopping.Product;
import user.User;

/**
 *
 * @author letie
 */
public class AddToCartController extends HttpServlet {

    private static final String SUCCESS = "shopping.jsp";
    private static final String ERROR = "error.jsp";
    private static final String LOGOUT = "LogoutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("CURRENT_USER");
            if (user == null) {
                url = LOGOUT;
            } else {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String imageUrl = request.getParameter("image_url");
                int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
                float price = Float.parseFloat(request.getParameter("price"));

                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                }
                Product prod = new Product(id, "", name, imageUrl, price, orderQuantity);
                cart.add(prod);
                session.setAttribute("CART", cart);
                String message = "You added " + orderQuantity + " " + name + " succesfully!";
                request.setAttribute("SHOPPING_MESSAGE", message);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at AddToCartController" + e.toString());
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
