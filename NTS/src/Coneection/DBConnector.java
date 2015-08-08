/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coneection;

/**
 *
 * @author Pasindu Tennage
 */
public class DBConnector {
    //this class is a singleton class that is used to connect to the data base
    //when implementing follow the singleton class structure
    public void connectToDatabase(){
        //establish the connection to the database
        
    }
    public void updateTable(String sqlStatement){
        //sends data to a table 
        //insert into tablename values = ___;
    }
    public void createNewTable(String sqlStatement){
        //create table tableName(name VARCHAR(20))
    }
    public String getQuerry(String sqlStatement){
        //select * from table ___
        return null;
    }
    public void deletetable(){
    
    }
    
}
