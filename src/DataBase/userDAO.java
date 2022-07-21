/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import model.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTextField;

/**
 *
 * @author truong
 */
public class userDAO {

    public Users checkLogin(String user, String pass) throws Exception {
        String sql = "select username,password ,role from users where username = ? and password= ? ";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            try ( ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    Users us = new Users();
                    us.setUser(user);
                    us.setPass(pass);
                    us.setRole(rs.getString("role"));
                    return us;
                }
            }

        }
        return null;
    }

    public boolean Insert(Users us) throws Exception {
        String sql = "insert into USERS (USERNAME,PASSWORD,ROLE,MASV) values (?,?,?,?)";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, us.getUser());
            pstmt.setString(2, us.getPass());
            pstmt.setString(3, us.getRole());
            pstmt.setString(4, us.getMasv());
            return pstmt.executeUpdate() > 0;
        }
    }

    public Users FindbyIDUser(String id) throws Exception {
        String sql = "select * from Users where USERNAME = ?";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, id);
            try ( ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    Users us = new Users();
                    us.setUser(rs.getString(1));
                    us.setPass(rs.getString(2));
                    us.setRole(rs.getString(3));
                    us.setMasv(rs.getString(4));
                    return us;
                }

            }

        }
        return null;
    }

    public boolean Update(Users us) throws Exception {
        String sql = " update USERS set PASSWORD = ? where USERNAME = ?";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, us.getPass());
            pstmt.setString(2, us.getUser());
            return pstmt.executeUpdate() > 0;
        }
    }
}
