/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Grade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truong
 */
public class GradeDAO {

    public boolean Insert(Grade gd) throws Exception {
        String sql = " insert into GRADE(MASV,TOAN,NGUVAN,TIENGANH) values (?,?,?,?)";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, gd.getIDStudent());
            pstmt.setFloat(2, gd.getMark());
            pstmt.setFloat(3, gd.getIdentifiers());
            pstmt.setFloat(4, gd.getEnglish());
            return pstmt.executeUpdate() > 0;
        }

    }

    public boolean Update(Grade gd) throws Exception {
        String sql = " update GRADE set TOAN =? ,NGUVAN = ? ,TIENGANH = ? where MASV = ?";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(4, gd.getIDStudent());
            pstmt.setFloat(1, gd.getMark());
            pstmt.setFloat(2, gd.getIdentifiers());
            pstmt.setFloat(3, gd.getEnglish());
            return pstmt.executeUpdate() > 0;
        }

    }

    public boolean DeleteByID(String id) throws Exception {
        String sql = "delete from GRADE where MASV = ?";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        }

    }

    public Grade FindbyIDGrade(String id) throws Exception {
        String sql = "select * from GRADE where Masv = ?";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, id);

            try ( ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    Grade gd = InitGrade(rs);
                    return gd;
                }
            }
        }
        return null;

    }

    private Grade InitGrade(final ResultSet rs) throws SQLException {
        Grade gd = new Grade();
        gd.setId(rs.getInt(5));
        gd.setIDStudent(rs.getString(1));
        gd.setMark(rs.getFloat(2));
        gd.setIdentifiers(rs.getFloat(3));
        gd.setEnglish(rs.getFloat(4));
        return gd;
    }
    public List<Grade> getAllGrade() throws Exception {
        String sql = "select * from GRADE";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            try ( ResultSet rs = pstmt.executeQuery();) {
                List<Grade> list = new ArrayList<>();
                while (rs.next()) {
                    Grade gd = InitGrade(rs);
                    list.add(gd);
                }
                return list;
            }
            
        }

    }
     public List<Grade> getTop3Grade(int top) throws Exception {
        String sql = "select top "+  top + " * ,((toan+nguvan+tienganh)/3) as tb from GRADE order by tb desc";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            try ( ResultSet rs = pstmt.executeQuery();) {
                List<Grade> list = new ArrayList<>();
                while (rs.next()) {
                    Grade gd = InitGrade(rs);
                    list.add(gd);
                }
                return list;
            }
            
        }

    }
}
