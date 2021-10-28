/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author letie
 */
public class UserDAO {

    public static User checkLogin(String username, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT * "
                        + " FROM [user] "
                        + " WHERE username = ? AND password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String roleID = rs.getString("role_id");
                    String id = rs.getString("id");
                    user = new User(id, roleID, name, "", "", "", username, password, "");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
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
        return user;
    }

    public static boolean isContainUser(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT id "
                        + " FROM [user]"
                        + " WHERE username = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return check;
    }

    public static boolean addUser(User user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "insert into [user]"
                        + "(id, role_id, [name], email, [address], phone_num, username, [password], avatar_url) "
                        + " values(?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getId());
                stm.setString(2, user.getRoleId());
                stm.setString(3, user.getName());
                stm.setString(4, user.getEmail());
                stm.setString(5, user.getAddress());
                stm.setString(6, user.getPhoneNum());
                stm.setString(7, user.getUsername());
                stm.setString(8, user.getPassword());
                stm.setString(9, user.getAvatarUrl());
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

//    public boolean insertUserGoogle(GooglePojo user) throws SQLException {
//        boolean check = false;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        Date now = new Date();
//        SimpleDateFormat dt = new SimpleDateFormat("MM/dd/YYYY");
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = "INSERT INTO tblUsers(name,userID,roleID,address,password,status,createDate) "
//                        + "VALUES (?,?,?,?,?,?,?) ";
//                stm = conn.prepareStatement(sql);
//                stm.setString(1, user.getEmail());
//                stm.setString(2, user.getId());
//                stm.setString(3, "US");
//                stm.setString(4, "");
//                stm.setString(5, "");
//                stm.setString(6, "active");
//                stm.setString(7, dt.format(now));
//                check = stm.executeUpdate() > 0;
//            }
//        } catch (Exception e) {
//            e.toString();
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return check;
//    }
//    public UserDTO checkLoginGoogle(String userID) throws SQLException {
//        UserDTO user = null;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                String sql = "SELECT roleID ,name,createDate"
//                        + " FROM tblUsers "
//                        + " WHERE userID =? AND status= 'active' ";
//                stm = conn.prepareStatement(sql);
//                stm.setString(1, userID);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    String name = rs.getString("name");
//                    String roleID = rs.getString("roleID");
//                    user = new UserDTO(userID, name, "", roleID, "", "", "");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//        return user;
//    }
}
