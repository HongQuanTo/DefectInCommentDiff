/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectcommentanalyser;

import java.util.HashSet;

/**
 *
 * @author quan
 */
public class Comment {

    private final String[] defectStatus;
    private Integer runNumber;
    private String commnetLine;

    public Comment() {
        defectStatus = new String[6];
        defectStatus[0] = "notabug";
        defectStatus[1] = "dup";
        defectStatus[2] = "defer";
        defectStatus[3] = "reopen";
        defectStatus[4] = "open";
        defectStatus[5] = "close";
    }

    public Comment(Integer runNumber, String commentLine) {
        this.runNumber = runNumber;
        this.commnetLine = commentLine;
        defectStatus = new String[6];
        defectStatus[0] = "notabug";
        defectStatus[1] = "dup";
        defectStatus[2] = "defer";
        defectStatus[3] = "reopen";
        defectStatus[4] = "open";
        defectStatus[5] = "close";
    }

    public Trace getTrace() throws InvalidTraceException {
        /**
         * @param commentLine the comment itself in string form
         * @param runNumber the run number that provides the comment line
         * @return Integer(1) if comment is in valid form (content only one type
         * of defect status), otherwise number of status recognized.
         */
        InvalidTraceException invalidTraceEx;
        Integer countStatus = 0;
        String status = "";     // variable to save extracted status.
        String statusOfComment = commnetLine.toLowerCase();     // lowercase comment String.
        Trace currentTrace = new Trace(); 
        
        currentTrace.setRunNumber(runNumber); 
        // Get the type of bug in the comment: Notabug, Dup, Defer, Reopen, Open, Close
        // Loop all defects status, catch comment that have status.
        for (int i = 0; i < defectStatus.length; i++) {
            
            if (statusOfComment.contains(defectStatus[i])) {
                // remove all status from the comment for next status check for invalid comment.
                // Therefore, at the second row, if any status still exist throw InvalidTraceException 
                statusOfComment = statusOfComment.replaceAll(defectStatus[i], "");
                // System.out.println("Extract " + "\"" + defectStatus[i] + "\", remain: " + statusOfComment);
                
                // if comment has type of bug as Notabug, Dup, Defer, remove the close status and append status with "close as "
                if (i < 3) {
                    statusOfComment = statusOfComment.replaceAll(defectStatus[5], "");
                    status += "close as ";
                }
                status += defectStatus[i]; // jump to next status
                countStatus++;
            }
        }
        // Check if comment is valid (contain unique defect status) 
        if (countStatus > 1) {
            invalidTraceEx = new InvalidTraceException(commnetLine);
            throw invalidTraceEx;
        }
        // Build Trace tag for all bug: use comment.runNumber for trace.runNumber, type of bug for statusOfComment.
        currentTrace.setDefectStatus(status);
        return currentTrace;
    }

    public HashSet<Integer> getDefectIDs() {
        /**
         * @param commentLine the comment line to capture
         * @return a HashSet of defect IDs (Note: hard coded for defect ID: a
         * number greater than 9999
         */
        String[] commentWords;
        HashSet<Integer> defectIDs = new HashSet<Integer>();
        Integer aNumber;
        commentWords = commnetLine.split("\\D|\\s+");
        for (String result1 : commentWords) {
            if (!result1.isEmpty()) {
                try {
                    aNumber = Integer.parseInt(result1.toString());
                    if (aNumber.compareTo(Integer.parseInt("9999")) > 0) {
                        defectIDs.add(aNumber);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Can not parse -->|" + result1 + "|<--");
                    System.out.println(e.toString());
                }
            }
        }
        return defectIDs;
    }

}
