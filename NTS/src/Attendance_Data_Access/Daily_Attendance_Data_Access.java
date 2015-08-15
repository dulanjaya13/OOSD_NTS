/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance_Data_Access;

import Connection.DBConnector;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public class Daily_Attendance_Data_Access extends Attendace_Data_Access {

    public Daily_Attendance_Data_Access(DBConnector connector) {
        super(connector);

    }

    public void saveToDatabase(int[][] table, Date date, int batch, int level) throws ClassNotFoundException, SQLException {
        //to make the table name according to given parameters
        String tableName = "level" + level + "_batch" + batch + "_daily_attendance";
        //this will create final sql statement for execution
        String sql;
        Format formatter = new SimpleDateFormat("yyyy_MM_dd");
        String s = formatter.format(date);
        sql = "CREATE TABLE " + tableName + " (student_id int NOT NULL, " + s + " int NOT NULL, PRIMARY KEY (student_id));";

        this.connector.createNewTable(sql);
        for (int i = 0; i < table.length; i++) {
            sql = "INSERT INTO " + tableName + " VALUES (" + table[i][0] + " , " + table[i][1] + ")";
            connector.updateTable(sql);
        }
    }

    public int[][] getDailyAttendance(Date date, int batch, int level) throws SQLException, ClassNotFoundException {
        //table name that we require
        String tableName = "level" + level + "_batch" + batch + "_daily_attendance";
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
        Format formatter = new SimpleDateFormat("yyyy_MM_dd");
        String s = formatter.format(date);
        while (rs.next()) {
            list[i][0] = rs.getInt("student_id");
            list[i][1] = rs.getInt(s);
            i++;
        }
        return list;
    }

    public void updateDailyAttendance(int[][] table, Date date, int batch, int level) throws ClassNotFoundException, SQLException {
        //make table name
        String tableName = "level" + level + "_batch" + batch + "_daily_attendance";
        String sql;

        Format formatter = new SimpleDateFormat("yyyy_MM_dd");
        String s = formatter.format(date);

        //String sql2 = "IF COL_LENGTH( "+tableName+" , L" + LectureNumber + "_" + s+ " ) IS NULL BEGIN ALTER TABLE "+tableName+" ADD [L" + LectureNumber + "_" + s+"] INT END";
        try {
            for (int i = 0; i < table.length; i++) {
                //connector.updateTable(sql2);
                sql = "UPDATE " + tableName + " SET student_id=" + table[i][0] + " , " + s + "=" + table[i][1] + " WHERE student_id=" + table[i][0];
                connector.updateTable(sql);
            }
            //will catch if there is no such column and we need to create a new column in our table
        } catch (MySQLSyntaxErrorException m) {
            sql = "ALTER TABLE " + tableName + " ADD " + s + " int not null";
            connector.updateTable(sql);
            for (int i = 0; i < table.length; i++) {
                //connector.updateTable(sql2);
                sql = "UPDATE " + tableName + " SET student_id=" + table[i][0] + "," + s + "=" + table[i][1] + " WHERE student_id=" + table[i][0];
                connector.updateTable(sql);
            }
        }
    }

    public int[][] getWarningReports(int batch, int level) throws SQLException, ClassNotFoundException {
//        //table name that we require
//        String tableName = "level" + level + "_batch" + batch + "_daily_attendance";
//        //create sql statememt
//        String sql;
//        sql = "SELECT * FROM " + tableName;
//        ResultSet rs = connector.getQuerry(sql);
//        //this iwll be used to store the datbase as an array
//        int[][] list = null;
//        int row = 0;
//        //this iwll get the row count
//        while (rs.next()) {
//            row++;
//        }
//        //set the cursor of the result set to the beginning
//        rs.beforeFirst();
//
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
//        list = new int[row][columnsNumber];
//        //assign the table values to a 2D Array
//        int i = 0;
//        //Format formatter = new SimpleDateFormat("yyyy_MM_dd");
//        //String s = formatter.format(date);
//        
//        while (rs.next()) {
//            for (int j=0; j<columnsNumber ;j++){
//                list[i][j] = rs.getInt(j+1);
//            }
//            
//            //list[i][1] = rs.getInt(s);
//            i++;
//        }
//        return list;
        return null;
    }

    public int[][] getAttendanceReports(int batch, int level) throws SQLException, ClassNotFoundException {
//        //table name that we require
//        String tableName = "level" + level + "_batch" + batch + "_daily_attendance";
//        //create sql statememt
//        String sql;
//        sql = "SELECT * FROM " + tableName;
//        ResultSet rs = connector.getQuerry(sql);
//        //this iwll be used to store the datbase as an array
//        int[][] list = null;
//        int row = 0;
//        //this iwll get the row count
//        while (rs.next()) {
//            row++;
//        }
//        //set the cursor of the result set to the beginning
//        rs.beforeFirst();
//
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
//        list = new int[row][columnsNumber];
//        //assign the table values to a 2D Array
//        int i = 0;
//        //Format formatter = new SimpleDateFormat("yyyy_MM_dd");
//        //String s = formatter.format(date);
//        
//        while (rs.next()) {
//            for (int j=0; j<columnsNumber ;j++){
//                list[i][j] = rs.getInt(j+1);
//            }
//            
//            //list[i][1] = rs.getInt(s);
//            i++;
//        }
//        return list;
        return null;
    }

}
