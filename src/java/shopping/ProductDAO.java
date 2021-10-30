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
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author letie
 */
public class ProductDAO {

    public static List<Product> getAllProducts() throws SQLException {
        List<Product> prodList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + " FROM product";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                String name, cateId, id, imageUrl;
                double price;
                int quantity;
                while (rs.next()) {
                    name = rs.getString("name");
                    cateId = rs.getString("category_id");
                    price = Double.parseDouble(rs.getString("price"));
                    quantity = Integer.parseInt(rs.getString("quantity"));
                    id = rs.getString("id");
                    imageUrl = rs.getString("image_url");
                    prodList.add(new Product(id, cateId, name, imageUrl, price, quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return prodList;
    }

    public static boolean updateQty(String id, int buyQty, int oldQty) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            String sql = " UPDATE product "
                    + " SET quantity = ?"
                    + " WHERE id= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(oldQty - buyQty));
            ps.setString(2, id);
            check = ps.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static List<Product> searchProducts(String searchStr) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + " FROM product"
                        + " WHERE name LIKE ?";
                ps = conn.prepareCall(sql);
                ps.setString(1, "%" + searchStr + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String imageUrl = rs.getString("image_url");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new Product(id, "", name, imageUrl, price, quantity));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
