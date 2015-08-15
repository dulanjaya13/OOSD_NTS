/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lecturer_Data_Access;

import Connection.DBConnector;
import Lecturer_Domain.Lecturer;
import Student_Domain.Student;
import Subject_Data_Access.Subject_Data_Access;
import Subject_Domain.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author Pasindu Tennage
 */
public class Lecturer_Data_Access {

    private DBConnector connector;

    public Lecturer_Data_Access(DBConnector connector) {
        this.connector = connector;
    }

    public void addANewLecturer(int id, String name, String picture, String nic, String address, Date dob) throws ClassNotFoundException, SQLException {
        String sql;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        sql = "INSERT INTO lecturer VALUES ('" + id + "', '" + name + "', '" + picture + "','"
                + nic + "', '" + address + "', '" + sdf.format(dob) + "')";
        connector.updateTable(sql);
    }

    public void editSubjectList(int id, LinkedList<Subject> subjects) throws ClassNotFoundException, SQLException {

        String sql;
        for (int i = 0; i < subjects.size(); i++) {
            sql = "INSERT INTO lecturer_has_subject VALUES ('" + id + "', '" + subjects.get(i).getCode() + "')";
            connector.updateTable(sql);

        }

    }

    public void editLecturerProfile(int id, String name, String picture, String nic, String address, Date dob) throws ClassNotFoundException, SQLException {
        String sql;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sql = "UPDATE lecturer SET lecturer_id='" + id + "',name='"
                + name + "',picture='" + picture + "',nic='" + nic + "',address='" + address
                + "',date_of_birth='" + sdf.format(dob)
                + "' WHERE lecturer_id='" + id + "'";
        connector.updateTable(sql);
    }

    public Lecturer getLecturerProfile(int id) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "SELECT * FROM lecturer WHERE lecturer_id LIKE '%" + id + "%'";

        //result set to get selected row of the database
        ResultSet rs = connector.getQuerry(sql);

        //create vector to add each field in the student table
        Vector v = new Vector();
        while (rs.next()) {
            v.add(rs.getInt("lecturer_id"));
            v.add(rs.getString("name"));
            v.add(rs.getString("picture"));
            v.add(rs.getString("nic"));
            v.add(rs.getString("address"));
            v.add(rs.getDate("date_of_birth"));

        }
        //create new student object
        Lecturer lecturer = new Lecturer((int) v.get(0), (String) v.get(1), (String) v.get(2), (String) v.get(3),
                (String) v.get(4), (Date) v.get(5));
        
        //this is to get the subject list from the bridge class
        sql = "SELECT * FROM lecturer_has_subject WHERE lecturer_id LIKE '%" + id + "%'";
        //result set to get selected row of the database
        ResultSet rs2 = connector.getQuerry(sql);
        LinkedList<Subject> list = new LinkedList<Subject>();
        DBConnector db = new DBConnector();
        Subject_Data_Access sda= new Subject_Data_Access(db);
        
        while (rs2.next()) {
            
            String code = rs2.getString(2);
            list.add(sda.getSubjectByCode(code));
        }
        lecturer.setSubjects(list);        
        
        return lecturer;
    }

    public Lecturer getLecturerProfile(String Name) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM lecturer WHERE name LIKE '%" + Name + "%'";

        //result set to get selected row of the database
        ResultSet rs = connector.getQuerry(sql);
        
        //this wil store the Id of the lecturer - to find the subject list
        int lecturerId=0;
        
        //create vector to add each field in the student table
        Vector v = new Vector();
        while (rs.next()) {
            lecturerId = rs.getInt("lecturer_id");
            v.add(lecturerId);
            v.add(rs.getString("name"));
            v.add(rs.getString("picture"));
            v.add(rs.getString("nic"));
            v.add(rs.getString("address"));
            v.add(rs.getDate("date_of_birth"));

        }
        //create new student object
        Lecturer lecturer = new Lecturer((int) v.get(0), (String) v.get(1), (String) v.get(2), (String) v.get(3),
                (String) v.get(4), (Date) v.get(5));
        
        
        
        //this is to get the subject list from the bridge class
        sql = "SELECT * FROM lecturer_has_subject WHERE lecturer_id LIKE '%" + lecturerId + "%'";
        //result set to get selected row of the database
        ResultSet rs2 = connector.getQuerry(sql);
        LinkedList<Subject> list = new LinkedList<Subject>();
        DBConnector db = new DBConnector();
        Subject_Data_Access sda= new Subject_Data_Access(db);
        
        while (rs2.next()) {
            
            String code = rs2.getString(2);
            list.add(sda.getSubjectByCode(code));
        }
        lecturer.setSubjects(list);

        return lecturer;
    }

}
