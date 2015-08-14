/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance_Domain;

import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public abstract class Attendance {
    
    protected Date date;
    protected int batch ;
    protected int level ;
    protected int table[][]; // index  : present/absent denorted ny 1/0   
    
    
    public  int [][] getAttendanceSheet(){
        return this.table;
    }
    public  void setAttendanceSheet(int table [][]){
        
        this.table = table ; 
    }
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the batch
     */
    public int getBatch() {
        return batch;
    }

    /**
     * @param Batch the batch to set
     */
    public void setBatch(int Batch) {
        this.batch = Batch;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
}
