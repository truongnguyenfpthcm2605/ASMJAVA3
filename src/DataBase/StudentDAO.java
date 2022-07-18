/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truong
 */
public class StudentDAO {

    public boolean Insert(Student sv) throws Exception {
        String sql = "insert into STUDENTS(MASV,HOTEN,EMAIL,SODT,GIOITINH,DIACHI,HINH) values (?,?,?,?,?,?,?)";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, sv.getID());
            pstmt.setString(2, sv.getName());
            pstmt.setString(3, sv.getEmail());
            pstmt.setString(4, sv.getPhone());
            pstmt.setBoolean(5, sv.isGender());
            pstmt.setString(6, sv.getAddress());
            pstmt.setString(7, sv.getImg());
            return pstmt.executeUpdate() > 0;
        }

    }

    public boolean Update(Student sv) throws Exception {
        String sql = "update STUDENTS set HOTEN = ?,EMAIL = ?,SODT = ?,GIOITINH =?,DIACHI = ?,HINH = ? where MASV = ? ";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(7, sv.getID());
            pstmt.setString(1, sv.getName());
            pstmt.setString(2, sv.getEmail());
            pstmt.setString(3, sv.getPhone());
            pstmt.setBoolean(4, sv.isGender());
            pstmt.setString(5, sv.getAddress());
            pstmt.setString(6, sv.getImg());
            return pstmt.executeUpdate() > 0;
        }

    }

    public boolean Delete(String id) throws Exception {
        String sql = "Delete from STUDENTS where MASV = ? ";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        }

    }

    public Student findStudent(String id) throws Exception {
        String sql = "Select * from STUDENTS where MASV = ? ";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, id);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Student sv = CreateStudent(rs);
                    return sv;
                }
            }

        }
        return null;
    }

    private Student CreateStudent(final ResultSet rs) throws SQLException {
        Student sv = new Student();
        sv.setID(rs.getString(1));
        sv.setName(rs.getString(2));
        sv.setEmail(rs.getString(3));
        sv.setPhone(rs.getString(4));
        sv.setGender(rs.getBoolean(5));
        sv.setAddress(rs.getString(6));
        sv.setImg(rs.getString(7));
        return sv;
    }

    public List<Student> getAllStudnet() throws Exception {
        String query = "select * from  students";
        try (
                 Connection con = ConnectData.ConnecttoSQL();  PreparedStatement pstmt = con.prepareStatement(query);) {

            try ( ResultSet rs = pstmt.executeQuery()) {
                List<Student> list = new ArrayList<>();

                while (rs.next()) {
                    Student sv = CreateStudent(rs);
                    list.add(sv);
                }
                return list;
            }
        }
    }

    public StudentDAO() {

    }

}
