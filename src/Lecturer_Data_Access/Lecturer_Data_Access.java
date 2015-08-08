/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lecturer_Data_Access;

import Coneection.DBConnector;
import Lecturer_Domain.Lecturer;
import Subject_Domain.Subject;
import java.util.LinkedList;

/**
 *
 * @author Pasindu Tennage
 */
public class Lecturer_Data_Access {
    private DBConnector connector;
    public Lecturer_Data_Access(DBConnector connector){
        this.connector = connector;
    }

    public void addANewLecturer(int id, String name, String picture, String nic, String address, String dob) {
        
    }

    public void editSubjectList(int id, LinkedList<Subject> subjects) {
        
    }

    public void editLecturerProfile(int id, String name, String picture, String nic, String address, String dob) {
        
    }

    public Lecturer getLecturerProfile(int id) {
        return null;
    }

    public Lecturer getLecturerProfile(String Name) {
        return null;
    }
    
}
