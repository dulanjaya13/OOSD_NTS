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
public class Continous_Assignments extends ResultSheet{
    private int assignmentNumber;
    
    public Continous_Assignments(int number){
        this.assignmentNumber = number;
    }
    public void saveAssignmentResults() throws ClassNotFoundException, SQLException{
        getAccess().saveAssignmentResults(this.level,this.batch,this.assignmentNumber,this.subject,this.results);
    }
    public Continous_Assignments getAssignmentResultSheet(int level,int batch,int assignmentNumber) throws ClassNotFoundException, SQLException{
        return getAccess().getAssignmentResults(this.level,this.batch,this.assignmentNumber,this.subject);
    }
    public void updateAssignmentResults() throws ClassNotFoundException, SQLException{
        getAccess().updateAssignmentResults(this.level,this.batch,this.assignmentNumber,this.subject,this.results);
    }
    
}
