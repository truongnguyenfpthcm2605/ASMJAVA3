/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import model.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
