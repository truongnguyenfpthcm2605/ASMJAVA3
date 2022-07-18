/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author truong
 */
public class Grade {
    private int id;
    private String IDStudent;
    private float math;
    private float Identifiers;
    private float english;

    public Grade(int id, String IDStudent, float math, float Identifiers, float english) {
        this.id = id;
        this.IDStudent = IDStudent;
        this.math = math;
        this.Identifiers = Identifiers;
        this.english = english;
    }

    public Grade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIDStudent() {
        return IDStudent;
    }

    public void setIDStudent(String IDStudent) {
        this.IDStudent = IDStudent;
    }

    public float getMark() {
        return math;
    }

    public void setMark(float mark) {
        this.math = mark;
    }

    public float getIdentifiers() {
        return Identifiers;
    }

    public void setIdentifiers(float Identifiers) {
        this.Identifiers = Identifiers;
    }

    public float getEnglish() {
        return english;
    }

    public void setEnglish(float english) {
        this.english = english;
    }
    
    
}
