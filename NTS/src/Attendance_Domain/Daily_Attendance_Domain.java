/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance_Domain;

import Attendance_Data_Access.Daily_Attendance_Data_Access;
import Coneection.DBConnector;
import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public final class Daily_Attendance_Domain extends Attendance{
    private Daily_Attendance_Data_Access access;
    public Daily_Attendance_Domain( Date date,int Batch,int level ){
        this.date = date;
        this.batch = Batch;
        this.level = level;
        
    }
    public void saveDailyAttendanceToDatabase(int [][]table ){
        this.table = table;
        getAccess().saveToDatabase(this.table,this.date,this.batch,this.level);
    }
    public int [][] getDailyAttendance(){
        return getAccess().getDailyAttendance(this.date,this.batch,this.level);
    }
    public void updateDailyAttendance(int [][]table){
        this.table = table;
        getAccess().updateDailyAttendance(this.table,date,this.batch,this.level);
    }
    public int [][] getWarningReport(){
        return getAccess().getWarningReports(this.batch,this.level);
    } 
    public int[][] getAttendanceReports(){
        return getAccess().getAttendanceReports(this.batch,this.level);
    }

    /**
     * @return the access
     */
    public Daily_Attendance_Data_Access getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(Daily_Attendance_Data_Access access) {
        this.access = access;
    }
    
    
}
