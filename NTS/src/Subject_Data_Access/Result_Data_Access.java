/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject_Data_Access;

import Connection.DBConnector;
import Subject_Domain.Continous_Assignments;
import Subject_Domain.End_Semester_Exam;
import Subject_Domain.Subject;

/**
 *
 * @author Pasindu Tennage
 */
public class Result_Data_Access {
    private DBConnector connector;
    public Result_Data_Access(DBConnector connector){
        this.connector = connector;
    }
    public void saveAssignmentResults(int level, int batch, int AssignmentNumber, Subject subject, int[][] results) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Continous_Assignments getAssignmentResults(int level, int batch, int assignmentNumber, Subject subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateAssignmentResults(int level, int batch, int assignmentNumber, Subject subject, int[][] results) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveResults(int level, int batch, Subject subject, int[][] results) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveEndResults(int level, int batch, Subject subject, int[][] results) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public End_Semester_Exam getEndSemesterResultSheet(int level, int batch, Subject subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateEndSemesterResults(int level, int batch, Subject subject, int[][] results) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
