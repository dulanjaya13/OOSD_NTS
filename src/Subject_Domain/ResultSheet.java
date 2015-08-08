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
public abstract class ResultSheet
{
    protected Subject subject ;
    protected int batch ; 
    protected  int level ;
    protected int[][]results;
    private Subject_Data_Access.Result_Data_Access access;

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * @return the batch
     */
    public int getBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(int batch) {
        this.batch = batch;
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
     * @return the results
     */
    public int[][] getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(int[][] results) {
        this.results = results;
    }

    /**
     * @return the access
     */
    public Subject_Data_Access.Result_Data_Access getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(Subject_Data_Access.Result_Data_Access access) {
        this.access = access;
    }
}
