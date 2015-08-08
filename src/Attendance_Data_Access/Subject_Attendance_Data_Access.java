/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance_Data_Access;

import Coneection.DBConnector;
import Subject_Domain.Subject;
import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public class Subject_Attendance_Data_Access extends Attendace_Data_Access{

    public Subject_Attendance_Data_Access(DBConnector connector) {
        super(connector);
    }

    public void saveToDatabase(int[][] table, Date date, int Batch, int level, int LectureNumber, Subject subject) {
        
    }

    public int[][] getSubjectAttendance(Date date, int Batch, int level, int LectureNumber, Subject subject) {
        return null;
    }

    public void updateSubjectAttendance(int[][] table, Date date, int Batch, int level, int LectureNumber, Subject subject) {
        
    }
    
}
