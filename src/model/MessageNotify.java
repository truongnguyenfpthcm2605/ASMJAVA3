/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author truong
 */
public class MessageNotify   {
    public int QuestionMessage(Component com,String a){
         int choose = JOptionPane.showConfirmDialog(com, a, "Notify", JOptionPane.YES_NO_OPTION);
        
         return choose;
    }
}
