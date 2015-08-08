/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject_Domain;

import Lecturer_Domain.Lecturer;
import java.util.LinkedList;

/**
 *
 * @author Pasindu Tennage
 */
public class Subject {
    private String 	code ; //(Primary Key)
    private String      name;
    private int 	level;
    private LinkedList<Lecturer_Domain.Lecturer> lecturers ;
    private Subject_Data_Access.Subject_Data_Access access;
    
    public Subject(){
        lecturers = new LinkedList<Lecturer>();
    }
    
    public void addANewSubject(){
        getAccess().addANewSubject(code,name,level);
    }
    public void editLecturerList(){
        getAccess().editLecturerList(code,lecturers);
    }
    public void editSubjectInfo(){
        getAccess().editSubjectInfo(code,name,level);
    }
    public Subject getSubject(int code){
        return getAccess().getSubject(code);
    }
    public Lecturer getSubject(String name){
        return getAccess().getSubject(name);
    }
    

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param Code the code to set
     */
    public void setCode(String Code) {
        this.code = Code;
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
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param Level the level to set
     */
    public void setLevel(int Level) {
        this.level = Level;
    }

    /**
     * @return the lecturers
     */
    public LinkedList<Lecturer_Domain.Lecturer> getLecturers() {
        return lecturers;
    }

    /**
     * @param lecturers the lecturers to set
     */
    public void setLecturers(LinkedList<Lecturer_Domain.Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public void addLecturer(Lecturer_Domain.Lecturer lecturer){
        this.lecturers.add(lecturer);
    }

    /**
     * @return the access
     */
    public Subject_Data_Access.Subject_Data_Access getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(Subject_Data_Access.Subject_Data_Access access) {
        this.access = access;
    }
}
