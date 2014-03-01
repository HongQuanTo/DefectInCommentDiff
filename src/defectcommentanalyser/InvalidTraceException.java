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
public class InvalidTraceException extends Exception {

    private String commentLine;

    @Override
    public String toString() {
        return "Invalid Trace encounted: There are more than 1 defect status type in a comment.\nComment: " + commentLine;
    }

    public InvalidTraceException() {
        commentLine = "";
    }

    public InvalidTraceException(String commentLine) {
        this.commentLine = commentLine;
    }
}
