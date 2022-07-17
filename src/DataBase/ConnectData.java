/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author truong
 */
public class ConnectData {
    public static Connection ConnecttoSQL() throws  Exception{
         String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=STUDENTMANAGER;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String pass = "12345";
        Connection com = DriverManager.getConnection(dbURL, user, pass);
        // tra ve ket noi
        return com;
    }
    
}
