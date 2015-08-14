/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject_Domain;

import java.sql.SQLException;

/**
 *
 * @author Pasindu Tennage
 */
public class End_Semester_Exam extends ResultSheet{
    
    public End_Semester_Exam(){
        
    }
    public void saveEndSemesterResult() throws ClassNotFoundException, SQLException{
        getAccess().saveEndResults(this.level,this.batch,this.subject,this.results);
    }
    public End_Semester_Exam getEndSemesterResultSheet(int level,int batch) throws SQLException, ClassNotFoundException{
        return getAccess().getEndSemesterResultSheet(this.level,this.batch,this.subject);
    }
    public void updateEndSemesterResults() throws ClassNotFoundException, SQLException{
        getAccess().updateEndSemesterResults(this.level,this.batch,this.subject,this.results);
    }
    
}
