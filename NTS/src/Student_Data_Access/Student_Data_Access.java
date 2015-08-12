/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student_Data_Access;

import Connection.DBConnector;
import Student_Domain.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Pasindu Tennage
 */
public class Student_Data_Access {

    public static boolean isStudentExists(int id) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean isStudentExists(String name) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private DBConnector connector;

    public Student_Data_Access(DBConnector connector) {
        this.connector = connector;
    }
    public static Object [][] getStudentList(int year){
        return null;
    }
    public void createNewStudent(int ID, String Name, Date DOB, int Batch, String Address, String NIC, int Phone, Date date, String Guadian1Name, int Guadian1Telephone, String Guadian1Address, String Guadian2Name, int Guadian2Telephone, String Guadian2Address, boolean hostel, int level, String Picture) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "INSERT INTO student VALUES ('" + ID + "', '" + Name + "', '" + DOB + "','"
                + Batch + "', '" + Address + "', '" + NIC + "','" + Phone + "', '" + date + "', '"
                + Guadian1Name + "','" + Guadian1Telephone + "', '" + Guadian1Address + "', '"
                + Guadian2Name + "','" + Guadian2Telephone + "', '" + Guadian2Address + "', '"
                + ((hostel) ? 1 : 0) + "' , '" + level + "','" + Picture + "')";
        connector.updateTable(sql);
    }

    public Student getProfile(String name, int batch, int level) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = "SELECT * FROM student WHERE '" + "'name LIKE '%" + name + "%'" + "'batch LIKE '%" + batch + "%'"
                + "'level LIKE '%" + level + "%'";

        //result set to get selected row of the database
        ResultSet rs = connector.getQuerry(sql);

        //create vector to add each field in the student table
        Vector v = new Vector();
        while (rs.next()) {
            v.add(rs.getInt("student_id"));
            v.add(rs.getString("name"));
            v.add(rs.getDate("date_of_birth"));
            v.add(rs.getInt("batch"));
            v.add(rs.getString("address"));
            v.add(rs.getString("nic"));
            v.add(rs.getInt("phone_number"));
            v.add(rs.getDate("registration_date"));
            v.add(rs.getString("guardian_one_name"));
            v.add(rs.getInt("guardian_one_telephone"));
            v.add(rs.getString("guardian_one_address"));
            v.add(rs.getString("guardian_two_name"));
            v.add(rs.getInt("guardian_two_telephone"));
            v.add(rs.getString("guardian_two_address"));
            v.add(rs.getBoolean("hostel_student"));
            v.add(rs.getInt("level"));
            v.add(rs.getString("picture"));
        }
        //create new student object
        Student student = new Student((int) v.get(0), (String) v.get(1), (Date) v.get(2), (int) v.get(3),
                (String) v.get(4), (String) v.get(5), (int) v.get(6), (Date) v.get(7), (String) v.get(8), (int) v.get(9), (String) v.get(10),
                (String) v.get(11), (int) v.get(12), (String) v.get(13), (Boolean) v.get(14), (int) v.get(15), (String) v.get(16));

        return student;
    }

    public Student getProfile(int id) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = "SELECT * FROM student WHERE '" + "'student_id LIKE '%" + id + "%'";

        //result set to get selected row of the database
        ResultSet rs = connector.getQuerry(sql);

        //create vector to add each field in the student table
        Vector v = new Vector();
        while (rs.next()) {
            v.add(rs.getInt("student_id"));
            v.add(rs.getString("name"));
            v.add(rs.getDate("date_of_birth"));
            v.add(rs.getInt("batch"));
            v.add(rs.getString("address"));
            v.add(rs.getString("nic"));
            v.add(rs.getInt("phone_number"));
            v.add(rs.getDate("registration_date"));
            v.add(rs.getString("guardian_one_name"));
            v.add(rs.getInt("guardian_one_telephone"));
            v.add(rs.getString("guardian_one_address"));
            v.add(rs.getString("guardian_two_name"));
            v.add(rs.getInt("guardian_two_telephone"));
            v.add(rs.getString("guardian_two_address"));
            v.add(rs.getBoolean("hostel_student"));
            v.add(rs.getInt("level"));
            v.add(rs.getString("picture"));
        }
        //create new student object
        Student student = new Student((int) v.get(0), (String) v.get(1), (Date) v.get(2), (int) v.get(3),
                (String) v.get(4), (String) v.get(5), (int) v.get(6), (Date) v.get(7), (String) v.get(8), (int) v.get(9), (String) v.get(10),
                (String) v.get(11), (int) v.get(12), (String) v.get(13), (Boolean) v.get(14), (int) v.get(15), (String) v.get(16));

        return student;
    }

    public void updateStudent(int id, String name, Date dob, int batch, String address, String nic, int phone, Date date, String guadian1Name, int guadian1Telephone, String guadian1Address, String guadian2Name, int guadian2Telephone, String guadian2Address, boolean hostel, int level, String picture) throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String sql;
        sql = "UPDATE student SET student_id='" + id + "',name='" + name
                + "',date_of_birth='" + dob + "',batch='" + batch + "',address='"
                + address + "',nic='" + nic + "',phone_number='"
                + phone + "',registration_date='" + date + "',guardian_one_name='"
                + guadian1Name + "',guardian_one_telephone='" + guadian1Telephone + "',guardian_one_address='"
                + guadian1Address + "',guardian_two_name='" + guadian2Name+ "',guardian_two_telephone='"
                +guadian2Telephone+ "',guardian_two_address='" + guadian2Address + "',hostel_student='"
                + hostel+ "',level='" + level+ "',picture='" + picture
                + "' WHERE student_id='" + id + "'";

        connector.updateTable(sql);
    }
    public int[][] getDailyAttendance(int id) {
        return null;
    }

    

}
