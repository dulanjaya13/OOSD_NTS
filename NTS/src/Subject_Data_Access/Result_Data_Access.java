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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pasindu Tennage
 */
public class Result_Data_Access {

    private DBConnector connector;

    public Result_Data_Access(DBConnector connector) {
        this.connector = connector;
    }

    public void saveAssignmentResults(int level, int batch, int assignmentNumber, Subject subject, int[][] results) throws ClassNotFoundException, SQLException {
        //to make the table name according to given parameters
        String tableName = "level" + level + "_batch" + batch + "_" + subject.getCode() + "_assignment_" + assignmentNumber + "_marks";
        //this will create final sql statement for execution
        String sql;
        sql = "CREATE TABLE " + tableName + " (student_id int NOT NULL, result int NOT NULL, PRIMARY KEY (student_id));";

        this.connector.createNewTable(sql);
        for (int i = 0; i < results.length; i++) {
            sql = "INSERT INTO " + tableName + " VALUES (" + results[i][0] + " , " + results[i][1] + ")";
            connector.updateTable(sql);
        }

    }

    public Continous_Assignments getAssignmentResults(int level, int batch, int assignmentNumber, Subject subject) throws ClassNotFoundException, SQLException {
        //table name that we require
        String tableName = "level" + level + "_batch" + batch + "_" + subject.getCode() + "_assignment_" + assignmentNumber + "_marks";
        //create sql statememt
        String sql;
        sql = "SELECT * FROM " + tableName;
        ResultSet rs = connector.getQuerry(sql);
        //this iwll be used to store the datbase as an array
        int[][] list = null;
        int row = 0;
        //this iwll get the row count
        while (rs.next()) {
            row++;
        }
        //set the cursor of the result set to the beginning
        rs.beforeFirst();
        list = new int[row][2];
        //assign the table values to a 2D Array
        int i = 0;
        while (rs.next()) {
            list[i][0] = rs.getInt("student_id");
            list[i][1] = rs.getInt("result");
            i++;
        }
        Continous_Assignments ca = new Continous_Assignments(assignmentNumber);
        ca.setBatch(batch);
        ca.setLevel(level);
        ca.setSubject(subject);
        ca.setResults(list);
        return ca;
    }

    public void updateAssignmentResults(int level, int batch, int assignmentNumber, Subject subject, int[][] results) throws ClassNotFoundException, SQLException {
        //make table name
        String tableName = "level" + level + "_batch" + batch + "_" + subject.getCode() + "_assignment_" + assignmentNumber + "_marks";
        String sql;

        for (int i = 0; i < results.length; i++) {
            sql = "UPDATE " + tableName + " SET student_id='" + results[i][0] + "',result='" + results[i][1] + "' WHERE student_id='" + results[i][0] + "'";
            connector.updateTable(sql);
        }
    }

    //public void saveResults(int level, int batch, Subject subject, int[][] results) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public void saveEndResults(int level, int batch, Subject subject, int[][] results) throws ClassNotFoundException, SQLException {
        //to make the table name according to given parameters
        String tableName = "level" + level + "_batch" + batch + "_" + subject.getCode() + "_EndExam_marks";
        //this will create final sql statement for execution
        String sql;
        sql = "CREATE TABLE " + tableName + " (student_id int NOT NULL, result int NOT NULL, PRIMARY KEY (student_id));";

        this.connector.createNewTable(sql);
        for (int i = 0; i < results.length; i++) {
            sql = "INSERT INTO " + tableName + " VALUES (" + results[i][0] + " , " + results[i][1] + ")";
            connector.updateTable(sql);
        }
    }

    public End_Semester_Exam getEndSemesterResultSheet(int level, int batch, Subject subject) throws SQLException, ClassNotFoundException {
        //table name that we require
        String tableName = "level" + level + "_batch" + batch + "_" + subject.getCode() + "_EndExam_marks";
        //create sql statememt
        String sql;
        sql = "SELECT * FROM " + tableName;
        ResultSet rs = connector.getQuerry(sql);
        //this iwll be used to store the datbase as an array
        int[][] list = null;
        int row = 0;
        //this iwll get the row count
        while (rs.next()) {
            row++;
        }
        //set the cursor of the result set to the beginning
        rs.beforeFirst();
        list = new int[row][2];
        //assign the table values to a 2D Array
        int i = 0;
        while (rs.next()) {
            list[i][0] = rs.getInt("student_id");
            list[i][1] = rs.getInt("result");
            i++;
        }
        End_Semester_Exam ee = new End_Semester_Exam();
        ee.setBatch(batch);
        ee.setLevel(level);
        ee.setSubject(subject);
        ee.setResults(list);
        return ee;
    }

    public void updateEndSemesterResults(int level, int batch, Subject subject, int[][] results) throws ClassNotFoundException, SQLException {
        //make table name
        String tableName = "level" + level + "_batch" + batch + "_" + subject.getCode() + "_EndExam_marks";
        String sql;

        for (int i = 0; i < results.length; i++) {
            sql = "UPDATE " + tableName + " SET student_id='" + results[i][0] + "',result='" + results[i][1] + "' WHERE student_id='" + results[i][0] + "'";
            connector.updateTable(sql);
        }
    }

}
