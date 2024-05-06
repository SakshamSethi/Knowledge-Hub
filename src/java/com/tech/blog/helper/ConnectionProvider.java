
package com.tech.blog.helper;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionProvider {
    private static Connection con;
    
    public static Connection getConnection()
    {
        try {
            if(con==null)
            {
                Class.forName("com.mysql.jdbc.Driver"); //Loading the Driver Class
            
            //create a connection
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knowledgehub","root","saksham123");
            }
            
        } 
        
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
