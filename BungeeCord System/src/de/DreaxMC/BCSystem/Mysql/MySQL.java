package de.DreaxMC.BCSystem.Mysql;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class MySQL {
public static Connection con;
       
        public static void connect(){
               
                try {
                        con = DriverManager.getConnection("jdbc:mysql://" + MySQLDataBase.MySQL_host + ":3306/"+MySQLDataBase.MySQL_db,MySQLDataBase.MySQL_user,MySQLDataBase.MySQL_pass);
                        System.out.println("MySQL Connected");
                        //Verbindung Hergestellt!
                       
                } catch (SQLException e) {
                }
               
        }
       
        public static void close(){
               
                if(con != null){
                        try {
                                con.close();
                        } catch (SQLException e) {
                        }
                }
               
        }
       
        public static void Update(String qry){
               
               
                try {
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(qry);
                } catch (SQLException e) {
                }
               
        }
       
        public static ResultSet Query(String qry){
                ResultSet rs = null;
               
                try {
                        Statement stmt = con.createStatement();
                        rs = stmt.executeQuery(qry);
                } catch (SQLException e) {
                }
               
                return rs;
        }
       
}