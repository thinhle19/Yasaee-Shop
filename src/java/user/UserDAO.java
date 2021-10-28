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
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT name, roleID ,address ,createDate"
                        + " FROM tblUsers "
                        + " WHERE userID =? AND password=? AND status= 'active'";
                
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, name, "", roleID, "", "", "");
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

        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, name , roleID ,address ,status,createDate"
                        + " FROM tblUsers "
                        + " WHERE name like ? ";
                stm = conn.prepareCall(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    String password = "****";
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    String date = rs.getString("createDate");

                    list.add(new UserDTO(userID, name, password, roleID, address, status, date));
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

    public boolean deleteUser(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblUsers "
                        + "SET status=? "
                        + "WHERE userID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "inactive");
                stm.setString(2, userID);
                result = stm.executeUpdate() > 0;
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

        return result;
    }

    public boolean checkUpdate(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblUsers "
                        + "SET name=? , roleID=? , address=? ,status=? "
                        + "WHERE userID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getName());
                ps.setString(2, user.getRoleID());
                ps.setString(3, user.getAddress());
                ps.setString(4, user.getStatus());
                ps.setString(5, user.getUserID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
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

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID "
                        + "FROM tblUsers "
                        + "WHERE userID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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

        return check;
    }

    public boolean insertUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUsers(name,userID,roleID,address,password,status,createDate) "
                        + "VALUES (?,?,?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getName());
                stm.setString(2, user.getUserID());
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getAddress());
                stm.setString(5, user.getPassword());
                stm.setString(6, user.getStatus());
                stm.setString(7, user.getDate());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.toString();
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

    public boolean insertUserGoogle(GooglePojo user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        Date now = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("MM/dd/YYYY");
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUsers(name,userID,roleID,address,password,status,createDate) "
                        + "VALUES (?,?,?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getEmail());
                stm.setString(2, user.getId());
                stm.setString(3, "US");
                stm.setString(4, "");
                stm.setString(5, "");
                stm.setString(6, "active");
                stm.setString(7, dt.format(now));
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.toString();
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

    public UserDTO checkLoginGoogle(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT roleID ,name,createDate"
                        + " FROM tblUsers "
                        + " WHERE userID =? AND status= 'active' ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, name, "", roleID, "", "", "");
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

        return user;
    }
}
