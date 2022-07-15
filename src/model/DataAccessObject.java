/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truong
 */
public class DataAccessObject {

    public List<Users> list = new ArrayList<>();

    public DataAccessObject() {
        list.add(new Users("truong", "12345", "sv"));
        list.add(new Users("nam", "12345", "sv"));
        list.add(new Users("trung", "12345", "sv"));
        list.add(new Users("quoc", "12345", "gv"));
        list.add(new Users("nghia", "12345", "gv"));
    }

    public boolean checkLogin(String user, String pass) {
        for (Users users : list) {
            if (users.getUser().equals(user) && pass.equals(pass)) {
                return true;
            }
        }
        return false;
    }

}
