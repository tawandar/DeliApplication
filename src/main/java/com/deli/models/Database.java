/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deli.models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {

String database_name;
static Connection conn;


public void close_db(){
    try{
         conn.close();
    }catch(Exception ex){
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "DATABASE CONNECTION FAILED", ex);
       // LOGGER.info ("DATABASE CONNECTION FAILED \n " + ex.getMessage( ));
    }
}


public void connect_db(){ 
//0. REGISTER MYSQL JDBC DRIVER
   try {
       
       Class.forName("com.mysql.jdbc.Driver").newInstance();

       String url = "jdbc:mysql://127.0.0.1:3306/shop_db";
       String uName = "root";
          String uPass = "dread_woman";


       conn = DriverManager.getConnection( url , uName, uPass);
       Logger.getLogger(Database.class.getName()).log(Level.INFO,"Connected Successfully to online application");

   } 
   catch ( Exception err) {
       Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "DATABASE CONNECTION FAILED", err);
   }
}

public void update_db(String query){
   try {
       Statement stmt = conn.createStatement();
       stmt.executeUpdate(query);
       Logger.getLogger(Database.class.getName()).log(Level.INFO,"Update Querry Succesful to onlineapplication db.");
   } catch (SQLException ex) {
       Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQL EXCEPTION", ex);

   }
}

public boolean runQuery(String query){
   try {
      
       Statement stmt = conn.createStatement();
       stmt.executeUpdate(query);
      // LOGGER.info(" Query Run Successful.");
       conn.close();
       return true;
   } catch (SQLException ex) {
       Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      // LOGGER.info("Query Run Failed.");
       return false;
   }

}



public ResultSet select_db(String query){
   try {
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(query);
       Logger.getLogger(Database.class.getName()).log(Level.INFO,"Applicants Records successfully retrieved from onlineapplication db");
       return rs; 
   } catch (SQLException ex) {
       Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
       return null;
   }
}

 public void insert_db(String query){
        try {
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            Logger.getLogger(Database.class.getName()).log(Level.INFO,"Succesfully inserted applicant details into onlineapplication db");


      } catch (SQLException ex) {
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQL Exception", ex);

       }

    }


}