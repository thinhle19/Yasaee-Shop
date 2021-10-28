/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import user.UserDTO;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class BookDAO {

    public List<Books> getBookList() throws SQLException {

        List<Books> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT *"
                        + " FROM tblProducts "
                        + " WHERE status= 'active' AND quantity > 0 ";
                stm = conn.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookId = rs.getString("productID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    Float price = rs.getFloat("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    list.add((new Books(bookId, name, quantity, price, description, image)));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean insertOrder(String userID, float total, String date) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "INSERT INTO tblOrder(userID,date,total) "
                        + " VALUES (?,?,?)  ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setFloat(3, total);
                stm.setString(2, date);
                check = stm.executeUpdate() > 0;
            }

        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String selectOrderID(Order order) throws SQLException {
        String result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT orderID FROM tblOrder "
                        + " WHERE userID =? AND date = ? AND total=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, order.getUserID());
                stm.setString(2, order.getDate());
                stm.setFloat(3, order.getTotal());
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = rs.getString("orderID");
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblOrderDetail(orderID,productID,quantity,price) "
                        + " VALUES (?,?,?,?)  ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, orderDetail.getOrderID());
                stm.setString(2, orderDetail.getProductID());
                stm.setInt(3, orderDetail.getQuantity());
                stm.setDouble(4, orderDetail.getPrice());
                check = stm.executeUpdate() > 0;
            }

        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateProduct(String bookID, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblProducts "
                        + " SET quantity = ? "
                        + " WHERE productId = ?  ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, bookID);
                check = stm.executeUpdate() > 0;
            }

        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<Books> searchBook(String search) throws SQLException {
        List<Books> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productID, name , price,quantity,categoryID,status ,description ,image"
                        + " FROM tblProducts "
                        + " WHERE name like ? ";
                stm = conn.prepareCall(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookId = rs.getString("productID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    Float price = rs.getFloat("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    list.add((new Books(bookId, name, quantity, price, description, image)));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static boolean send(Cart cart, String userEmail) {
        String email;
        Boolean check = false;
        String name = "Library System";
        email = userEmail;
        String subject = "Library System Code";

        final String username = "ltt01092001@gmail.com";//your email id
        final String password = "Dpccdv19?";// your password

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); //set nguoi nhan 
            int count = 0;
            //set text 
            int total = 0;
            for (Books book : cart.getCart().values()) {
                total += book.getQuantity() * book.getPrice();
            }
            StringBuffer body = new StringBuffer("<html>");
            body.append("<head>");
            body.append("</head>");
            body.append("<body>");
            body.append("<div class=\"container\">"
                    + "     <div class=\"card\">"
                    + "         <p style=\"color: green\">Your order has been already submited !</p>"
                    + "         <p style=\"color: #333\">List of book: </p>"
                    + "         <table>"
                    + "             <thead>"
                    + "                 <tr style=\"text-align: center\">"
                    + "                     <th>NO</th>"
                    + "                     <th>ID</th>"
                    + "                     <th>Name</th>"
                    + "                     <th>Quantity</th>"
                    + "                     <th>Price</th>"
                    + "                 </tr>"
                    + "             </thead>"
                    + "             <tbody>");
            for (Books book : cart.getCart().values()) {
                String bookID = book.getBookId();
                String Bookname = book.getBookName();
                int quantity = book.getQuantity();
                double price = book.getPrice();
                body.append("<tr style=\"text-align: center\">"
                        + "     <td>" + (++count) + "</td>"
                        + "     <td>" + bookID + "</td>"
                        + "     <td>" + Bookname + "</td>"
                        + "     <td>" + quantity + "</td>"
                        + "     <td>" + price + "</td>");
                body.append("</tr>");
            }
            body.append("</tbody>");
            body.append("</table>");
            body.append("Total: " + total + " VND");
            body.append(" </div>");
            body.append("</div>");
            body.append("</body>");
            body.append("</html>");
            message.setContent(body.toString(), "text/html");
            message.setSubject("Library System Recipt");
            Transport.send(message);
            check = true;

        } catch (Exception e) {
        }

        return check;
    }

    public static boolean send2(Cart cart, String userEmail) {
        boolean test = false;

        String toEmail = userEmail;
        //String fromEmail = "youremail@myself.com";
        // String password = "youremailpassword";
        final String fromEmail = "ltt0109@gmail.com";//your email id
        final String password = "";// your password

        try {

            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.mail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            //set email subject
            mess.setSubject("User Email Verification");

            //set message text
            mess.setText("Registered successfully.Please verify your account using this code: ");
            //send the message
            Transport.send(mess);

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}
