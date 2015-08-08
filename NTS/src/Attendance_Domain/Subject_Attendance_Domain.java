/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance_Domain;

import Attendance_Data_Access.Subject_Attendance_Data_Access;
import Coneection.DBConnector;
import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public final  class Subject_Attendance_Domain extends Attendance{
    private Subject_Domain.Subject subject ;
    private int LectureNumber;
    private Attendance_Data_Access.Subject_Attendance_Data_Access access;
    public Subject_Attendance_Domain( Date date,int batch,int level,Subject_Domain.Subject subject,int LectureNumber){
        this.date = date;
        this.batch = batch;
        this.level = level;
        this.LectureNumber = LectureNumber;
        this.subject = subject;
        
        
    }
    
    public void saveSubjectAttendanceToDatabase(int [][]table){
        this.table = table;
        getAccess().saveToDatabase(this.table,this.date,this.batch,this.level,LectureNumber,subject);
    }
    public int [][] getSubjectAttendance(){
        return getAccess().getSubjectAttendance(this.date,this.batch,this.level,this.LectureNumber,subject);
    }
    public void updateSubjectAttendance(int [][]table){
        this.table = table;
        getAccess().updateSubjectAttendance(this.table,date,this.batch,this.level,this.LectureNumber,this.subject);
    }
    
    /**
     * @return the subject
     */
    public Subject_Domain.Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject_Domain.Subject subject) {
        this.subject = subject;
    }

    /**
     * @return the LectureNumber
     */
    public int getLectureNumber() {
        return LectureNumber;
    }

    /**
     * @param LectureNumber the LectureNumber to set
     */
    public void setLectureNumber(int LectureNumber) {
        this.LectureNumber = LectureNumber;
    }

    /**
     * @return the access
     */
    public Attendance_Data_Access.Subject_Attendance_Data_Access getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(Attendance_Data_Access.Subject_Attendance_Data_Access access) {
        this.access = access;
    }
    
}
