/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectcommentanalyser;

/**
 *
 * @author quan
 */
public class InvalidDefectStatusException extends Exception {

    private Trace trace;
    private String defectID;

    @Override
    public String toString() {
        return "Invalid Defect Status Encountered: There are more than 1 status for defect " + defectID + " in run" + trace.getRunNumber();
    }

    public InvalidDefectStatusException() {
        trace = new Trace();
        defectID = "<unknown>";
    }

    public InvalidDefectStatusException(Trace trace, Integer defectID) {
        this.trace = trace;
        this.defectID = defectID.toString();
    }

}
