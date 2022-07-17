/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author truong
 */
public class CheckData {

    public boolean checkName(String a) {
             
        if (a.length()< 10) {
            return false;
        }
        return true;
    }

    public boolean checkEmail(String e) {
        String parttern = "^\\w{3,}@\\w{2,}(\\.[a-zA-Z]{2,3}){1,2}$";
        return e.matches(parttern);
    }

    public boolean checkNumberPhone(String s) {
        String parttern = "0[932]\\d{8}";
        return s.matches(parttern);
    }

    public boolean checkMaSv(String ma) {
        String parttern = "^[PS]+[0-9]{5}$";
        return ma.matches(parttern);
    }

}
