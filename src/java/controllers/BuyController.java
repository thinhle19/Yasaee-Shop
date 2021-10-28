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
import shopping.Books;
import shopping.Cart;
import shopping.Order;
import shopping.OrderDetail;
import shopping.BookDAO;

/**
 *
 * @author ASUS
 */
public class BuyController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
//    private static final String SUCCESS = "RemoveCartController";
    private static final String SUCCESS = "ShowBooksController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String userID = request.getParameter("userID");
            Float total = Float.parseFloat(request.getParameter("total"));
            String userEmail = request.getParameter("userEmail");
            boolean checkQuantity = true;
            Date now = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("MM/dd/YYYY");
            Cart cart = (Cart) session.getAttribute("CART"); //lấy thông tin quantity từ cart
            List<Books> book = (List<Books>) session.getAttribute("BOOK_LIST"); // lấy thông tin từ sql 
            for (Books test : cart.getCart().values()) {
                for (Books check : book) {
                    if (check.getBookId().equals(test.getBookId()) && check.getQuantity() < test.getQuantity()) {
                        checkQuantity = false;
                    }
                }
            }
            if (checkQuantity) {
                BookDAO dao = new BookDAO();
                Order order = new Order("", userID, dt.format(now), total);
                boolean check = false;
                check = dao.insertOrder(userID, total, dt.format(now));
                if (check) {
                    String orderID = dao.selectOrderID(order);

                    for (Books book1 : cart.getCart().values()) { //cart
                        OrderDetail orderDetail = new OrderDetail(orderID, book1.getBookId(), book1.getQuantity(), book1.getPrice());
                        dao.insertOrderDetail(orderDetail);
                        for (Books tmp : book) {
                            if (tmp.getBookId().equals(book1.getBookId())) {
                                dao.updateProduct(book1.getBookId(), tmp.getQuantity() - book1.getQuantity());
                                break;
                            }
                        }
                    }

                    if (BookDAO.send(cart, userEmail)) {
                        session.setAttribute("CLEAR_CART", "clear cart");
//                        session.setAttribute("BUY_MESSAGE", "You have order succesfully");
                        request.setAttribute("BUY_MESSAGE", "You have order succesfully");

                        cart.getCart().clear();
                        session.setAttribute("CART", cart);
                        url = SUCCESS;
                    } else {
//                        session.setAttribute("BUY_MESSAGE", "There a error in your order");
                        request.setAttribute("BUY_MESSAGE", "There a error in your order");
                        
                    }
                }
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
