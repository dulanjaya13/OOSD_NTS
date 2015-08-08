/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lecturer_Domain;

import Lecturer_Data_Access.Lecturer_Data_Access;
import Subject_Domain.Subject;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Pasindu Tennage
 */
public final class Lecturer {

    
    private     int id ; //(Primary Key)
    private	String name;
    private	String picture;
    private	String nic;
    private	String address;
    private	Date dob;
    private Lecturer_Data_Access access;
    private LinkedList<Subject_Domain.Subject> subjects ; 
    
    public Lecturer(int id, String name, String picture, String nic, String address, Date dob) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        subjects = new LinkedList<Subject>();
    }
    public Lecturer(){
        
        subjects = new LinkedList<Subject>();
    }
    
    public void addSubject(Subject_Domain.Subject subject){
        this.subjects.add(subject);
    }
    public void addANewLecturer() throws ClassNotFoundException, SQLException{
        getAccess().addANewLecturer(this.id,this.name,this.picture,this.nic,this.address,this.dob);
    }
    public void editSubjectList(){
        getAccess().editSubjectList(this.id,this.subjects);
    }
    public void editLecturerProfile() throws ClassNotFoundException, SQLException{
        getAccess().editLecturerProfile(this.id,this.name,this.picture,this.nic,this.address,this.dob);
    }
    public Lecturer getLecturerProfile(int ID) throws ClassNotFoundException, SQLException{
        return getAccess().getLecturerProfile(ID);
    }
    public Lecturer getLecturerProfile(String Name) throws SQLException, ClassNotFoundException{
        return getAccess().getLecturerProfile(Name);
    }
    

    /**
     * @return the id
     */
    public int getID() {
        return id;
    }

    /**
     * @param ID the id to set
     */
    public void setID(int ID) {
        this.id = ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param Name the name to set
     */
    public void setName(String Name) {
        this.name = Name;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param Picture the picture to set
     */
    public void setPicture(String Picture) {
        this.picture = Picture;
    }

    /**
     * @return the nic
     */
    public String getNIC() {
        return nic;
    }

    /**
     * @param NIC the nic to set
     */
    public void setNIC(String NIC) {
        this.nic = NIC;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param Address the address to set
     */
    public void setAddress(String Address) {
        this.address = Address;
    }

    /**
     * @return the dob
     */
    public Date getDOB() {
        return dob;
    }

    /**
     * @param DOB the dob to set
     */
    public void setDOB(Date DOB) {
        this.dob = DOB;
    }

    /**
     * @return the subjects
     */
    public LinkedList<Subject_Domain.Subject> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(LinkedList<Subject_Domain.Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     * @return the access
     */
    public Lecturer_Data_Access getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(Lecturer_Data_Access access) {
        this.access = access;
    }
    
    

    
}
