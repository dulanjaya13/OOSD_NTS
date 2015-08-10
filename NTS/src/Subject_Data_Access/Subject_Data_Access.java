/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject_Data_Access;

import Connection.DBConnector;
import Lecturer_Domain.Lecturer;
import Subject_Domain.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author Pasindu Tennage
 */
public class Subject_Data_Access {

    private DBConnector connector;

    public Subject_Data_Access(DBConnector connector) {
        this.connector = connector;
    }

    public void addANewSubject(String code, String name, int level) throws ClassNotFoundException, SQLException {
        //this string will 
        String sql;
        sql = "INSERT INTO subject VALUES ('" + code + "', '" + name + "', '" + level + "')";
        connector.updateTable(sql);
    }

    //this will be added after mapping
    public void editLecturerList(String code, LinkedList<Lecturer> lecturers) throws ClassNotFoundException, SQLException {

        String sql;
        for (int i = 0; i < lecturers.size(); i++) {
            sql = "INSERT INTO lecturer_has_subject VALUES ('" + lecturers.get(i).getID()+ "', '" + code + "')";
            connector.updateTable(sql);

        }
    }

    public void editSubjectInfo(String code, String name, int level) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "UPDATE subject SET subject_code='" + code + "',name='"
                + name + "',level='" + level
                + "' WHERE subject_code='" + code + "'";
        connector.updateTable(sql);
    }

    public Subject getSubject(String name) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "SELECT * FROM subject WHERE name LIKE '%" + name + "%'";

        //result set to get selected row of the database
        ResultSet rs = connector.getQuerry(sql);

        //create vector to add each field in the student table
        Vector v = new Vector();
        while (rs.next()) {
            v.add(rs.getString("subject_code"));
            v.add(rs.getString("name"));
            v.add(rs.getInt("level"));

        }
        //create new student object
        Subject subject = new Subject((String) v.get(0), (String) v.get(1), (int) v.get(2));

        return subject;
    }

    public Subject getSubject(int code) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "SELECT * FROM subject WHERE subject_code LIKE '%" + code + "%'";

        //result set to get selected row of the database
        ResultSet rs = connector.getQuerry(sql);

        //create vector to add each field in the student table
        Vector v = new Vector();
        while (rs.next()) {
            v.add(rs.getString("subject_code"));
            v.add(rs.getString("name"));
            v.add(rs.getInt("level"));

        }
        //create new student object
        Subject subject = new Subject((String) v.get(0), (String) v.get(1), (int) v.get(2));

        return subject;

    }

}
