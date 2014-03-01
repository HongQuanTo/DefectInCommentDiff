/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectcommentanalyser;

import java.util.ArrayList;

/**
 *
 * @author quan
 */
public class Run {

    private Integer runID;
    private ArrayList<String> comments;

    public Run(Integer runID, ArrayList<String> comments) {
        this.runID = runID;
        this.comments = comments;
    }

    public Run(Integer runID) {
        this.runID = runID;
        comments = new ArrayList<String>();
    }
     public Run(){
     
     }
    /**
     * @return the runID
     */
    public Integer getRunID() {
        return runID;
    }

    /**
     * @param runID the runID to set
     */
    public void setRunID(Integer runID) {
        this.runID = runID;
    }

    /**
     * @return the commentLines
     */
    public ArrayList<String> getComments() {
        return comments;
    }
    public ArrayList<Comment> getCommentsLines() {
        ArrayList<Comment> commentLines = new ArrayList<Comment>();
        Comment aComment;
        for (int i = 0; i < comments.size(); i++) {
            aComment = new Comment(runID, comments.get(i));
            commentLines.add(aComment);
        }
        return commentLines;
    }

    public Integer readComment(String inputText) {
        String[] commnetLines = inputText.split("\n");
        for (int i = 0; i < commnetLines.length; i++) {
            if (!commnetLines[i].isEmpty()) {
                comments.add(commnetLines[i]);
            }
        }
        return comments.size();
    }
}
