/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student_Data_Access;

import Coneection.DBConnector;
import Student_Domain.Student;
import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public class Student_Data_Access {
    private DBConnector connector;
    public Student_Data_Access(DBConnector connector){
        this.connector = connector;
    }

    public void createNewStudent(int ID, String Name, String DOB, int Batch, String Address, String NIC, int Phone, Date date, String Guadian1Name, int Guadian1Telephone, String Guadian1Address, String Guadian2Name, int Guadian2Telephone, String Guadian2Address, boolean hostel,int level, String Picture) {
        
    }

    public Student getProfile(String name, int batch, int level) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    public Student getProfile(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    public void updateStudent(int id, String name, String dob, int batch, String address, String nic, int phone, Date date, String guadian1Name, int guadian1Telephone, String guadian1Address, String guadian2Name, int guadian2Telephone, String guadian2Address, boolean hostel, int level, String picture) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[][] getDailyAttendance(int id) {
        return null;
    }
    
}
