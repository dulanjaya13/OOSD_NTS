/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance_Data_Access;

import Coneection.DBConnector;
import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public class Daily_Attendance_Data_Access extends Attendace_Data_Access{

    public Daily_Attendance_Data_Access(DBConnector connector) {
        super(connector);
        
    }

    public void saveToDatabase(int[][] table, Date date, int Batch, int level) {
        
    }

    public int[][] getDailyAttendance(Date date, int Batch, int level) {
        return null;
    }

    public void updateDailyAttendance(int[][] table, Date date, int Batch, int level) {
        
    }

    public int[][] getWarningReports(int Batch, int level) {
        return null;
    }

    public int[][] getAttendanceReports(int Batch, int level) {
        return null;
    }
   

    
    

    
    
}
