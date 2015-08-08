/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject_Data_Access;

import Coneection.DBConnector;
import Lecturer_Domain.Lecturer;
import Subject_Domain.Subject;
import java.util.LinkedList;

/**
 *
 * @author Pasindu Tennage
 */
public class Subject_Data_Access {
    private DBConnector connector;
    public Subject_Data_Access(DBConnector connector){
        this.connector = connector;
    }

    public void addANewSubject(String code, String name, int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editLecturerList(String code, LinkedList<Lecturer> lecturers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editSubjectInfo(String code, String name, int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Lecturer getSubject(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Subject getSubject(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
