/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student_Domain;

import Lecturer_Data_Access.Lecturer_Data_Access;
import Student_Data_Access.Student_Data_Access;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Pasindu Tennage
 */
public class Student  {

    
    private	int id ; //(Primary Key)
    private	String name;
    private	Date dob ; 
    private	int batch;
    private	String address;
    private	String nic;
    private	int phone;
    private	Date  date;
    private	String guadian1Name;
    private	int guadian1Telephone;
    private	String guadian1Address;
    private	String guadian2Name;
    private	int guadian2Telephone;
    private	String guadian2Address;
    private	boolean isHostel ;
    private     int  level;
    private	String picture;//URL of the picture
    private     Student_Data_Access access;
    /**
     * @return the id
     */
    
    //Constructor for student
    public Student(int id, String name, Date dob, int batch, String address, String nic, int phone, Date date, String guadian1Name, int guadian1Telephone, String guadian1Address, String guadian2Name, int guadian2Telephone, String guadian2Address, boolean isHostel, int level, String picture) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.batch = batch;
        this.address = address;
        this.nic = nic;
        this.phone = phone;
        this.date = date;
        this.guadian1Name = guadian1Name;
        this.guadian1Telephone = guadian1Telephone;
        this.guadian1Address = guadian1Address;
        this.guadian2Name = guadian2Name;
        this.guadian2Telephone = guadian2Telephone;
        this.guadian2Address = guadian2Address;
        this.isHostel = isHostel;
        this.level = level;
        this.picture = picture;
    }
    //Another constructor for Student
    public Student(){
                       
    }
    
    public void createNewStudent() throws ClassNotFoundException, SQLException{
        getAccess().createNewStudent(id,name,dob,batch,address,nic,phone,date,guadian1Name,guadian1Telephone,guadian1Address,guadian2Name,guadian2Telephone,guadian2Address,isHostel,level,picture );
    }
    public static Object[][] getStudentList(int year){
        return Student_Data_Access.getStudentList(year);
    }
    public static boolean isStudentExists(int id){
        return Student_Data_Access.isStudentExists(id);
    }
    public static boolean isStudentExists(String name){
        return Student_Data_Access.isStudentExists(name);
    }
    
    
    public Student getProfile(String name,int batch,int level) throws ClassNotFoundException, SQLException{
        return getAccess().getProfile(name,batch,level);
    }
    public Student getProfile(int id) throws ClassNotFoundException, SQLException{
        return getAccess().getProfile(id);
    }
    public Student getProfile(String name) throws ClassNotFoundException, SQLException{
        return getAccess().getProfile(name);
    }
    public void updateStudent() throws ClassNotFoundException, SQLException{
        getAccess().updateStudent(id,name,dob,batch,address,nic,phone,date,guadian1Name,guadian1Telephone,guadian1Address,guadian2Name,guadian2Telephone,guadian2Address,isHostel,level,picture);
    }
    public int[][] getDailyAttendace(){
        return getAccess().getDailyAttendance(id);
    }
    
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
     * @return the batch
     */
    public int getBatch() {
        return batch;
    }

    /**
     * @param Batch the batch to set
     */
    public void setBatch(int Batch) {
        this.batch = Batch;
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
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * @param Phone the phone to set
     */
    public void setPhone(int Phone) {
        this.phone = Phone;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the guadian1Name
     */
    public String getGuadian1Name() {
        return guadian1Name;
    }

    /**
     * @param Guadian1Name the guadian1Name to set
     */
    public void setGuadian1Name(String Guadian1Name) {
        this.guadian1Name = Guadian1Name;
    }

    /**
     * @return the guadian1Telephone
     */
    public int getGuadian1Telephone() {
        return guadian1Telephone;
    }

    /**
     * @param Guadian1Telephone the guadian1Telephone to set
     */
    public void setGuadian1Telephone(int Guadian1Telephone) {
        this.guadian1Telephone = Guadian1Telephone;
    }

    /**
     * @return the guadian1Address
     */
    public String getGuadian1Address() {
        return guadian1Address;
    }

    /**
     * @param Guadian1Address the guadian1Address to set
     */
    public void setGuadian1Address(String Guadian1Address) {
        this.guadian1Address = Guadian1Address;
    }

    /**
     * @return the guadian2Name
     */
    public String getGuadian2Name() {
        return guadian2Name;
    }

    /**
     * @param Guadian2Name the guadian2Name to set
     */
    public void setGuadian2Name(String Guadian2Name) {
        this.guadian2Name = Guadian2Name;
    }

    /**
     * @return the guadian2Telephone
     */
    public int getGuadian2Telephone() {
        return guadian2Telephone;
    }

    /**
     * @param Guadian2Telephone the guadian2Telephone to set
     */
    public void setGuadian2Telephone(int Guadian2Telephone) {
        this.guadian2Telephone = Guadian2Telephone;
    }

    /**
     * @return the guadian2Address
     */
    public String getGuadian2Address() {
        return guadian2Address;
    }

    /**
     * @param Guadian2Address the guadian2Address to set
     */
    public void setGuadian2Address(String Guadian2Address) {
        this.guadian2Address = Guadian2Address;
    }

    /**
     * @return the isHostel
     */
    public boolean isIsHostel() {
        return isHostel;
    }

    /**
     * @param isHostel the isHostel to set
     */
    public void setIsHostel(boolean isHostel) {
        this.isHostel = isHostel;
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
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }  

    /**
     * @return the access
     */
    public Student_Data_Access getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(Student_Data_Access access) {
        this.access = access;
    }

    
}
