/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incognitocoders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

public  static Connection getConnection() throws SQLException {
    
    Connection conn=null;    
    
    try{
        conn =  DriverManager.getConnection("jdbc:mysql://localhost/incognitocoders","root","");

        if(conn != null){
            System.out.println("Connected Successfully");

        }
        else{
            System.out.println("Failed to Connect");
        }
    }
    catch(Exception e){
    
        System.err.println(e);
        
    }
    return conn;
    }
    
}