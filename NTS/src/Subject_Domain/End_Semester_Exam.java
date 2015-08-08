/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject_Domain;

/**
 *
 * @author Pasindu Tennage
 */
public class End_Semester_Exam extends ResultSheet{
    
    public End_Semester_Exam(){
        
    }
    public void saveEndSemesterResult(){
        getAccess().saveEndResults(this.level,this.batch,this.subject,this.results);
    }
    public End_Semester_Exam getEndSemesterResultSheet(int level,int batch){
        return getAccess().getEndSemesterResultSheet(this.level,this.batch,this.subject);
    }
    public void updateEndSemesterResults(){
        getAccess().updateEndSemesterResults(this.level,this.batch,this.subject,this.results);
    }
    
}
