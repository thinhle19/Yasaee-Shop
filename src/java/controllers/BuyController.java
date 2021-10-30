/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.Cart;
import shopping.Product;
import shopping.ProductDAO;

/**
 *
 * @author letie
 */
public class BuyController extends HttpServlet {

    private static final String ERROR = "view-cart.jsp";
    private static final String SUCCESS = "ViewAllProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String username = request.getParameter("username");
            Float total = Float.parseFloat(request.getParameter("total"));
            boolean validQuantity = true;
            Date now = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("MM/dd/YYYY");
            Cart cart = (Cart) session.getAttribute("CART"); //lấy thông tin quantity từ cart
            List<Product> list = (List<Product>) ProductDAO.getAllProducts(); // lấy thông tin từ sql 
            for (Product buyProd : cart.getCart().values()) {
                for (Product prod : list) {
                    if (buyProd.getId().equals(prod.getId()) && buyProd.getQuantity() > prod.getQuantity()) {
                        validQuantity = false;
                    }
                }
            }
            if (validQuantity) {
                request.setAttribute("BUY_MESSAGE", "You have ordered succesfully!");
                for (Product buyProd : cart.getCart().values()) {
                    for (Product prod : list) {
                        if (buyProd.getId().equals(prod.getId())) {
                            ProductDAO.updateQty(prod.getId(), buyProd.getQuantity(), prod.getQuantity());
                        }
                    }
                }
                cart.getCart().clear();
                session.setAttribute("CART", cart);
                url = SUCCESS;
            } else {
                String message = "Not enough quantity in warehouse for the order";
                request.setAttribute("PRODUCT_QUANTITY_ERROR", message);
            }

        } catch (Exception e) {
            log("Error at BuyController" + e.toString());
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
