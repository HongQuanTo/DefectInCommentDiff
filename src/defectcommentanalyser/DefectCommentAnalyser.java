/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectcommentanalyser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author quan
 */
public class DefectCommentAnalyser {

    /**
     * @param args the command ljne arguments
     */
    private HashSet<Defect> defects;

    public DefectCommentAnalyser() {
        defects = new HashSet<Defect>();
    }

    public boolean analyseComment(Comment comment) throws InvalidTraceException, InvalidDefectStatusException {

        boolean success = false;
        Trace trace = new Trace();
        try {
            trace = comment.getTrace();
        } catch (InvalidTraceException ex) {
            System.out.println(ex.toString());
            return success;
        }

        HashSet<Integer> defectIDs = comment.getDefectIDs();
        Defect defect;
        for (Integer defectID : defectIDs) {
            // Check if bug is already added  // if yes, add trace to bug; creat bug and and current trace to bug. 
            defect = new Defect(defectID);
            if (defects.contains(new Defect(defectID))) {
                // System.out.println("Encountered existed defect has ID = " + defectID);
                // Iterate over defects in defect set to find the defect with the same ID, then add trace to that defect.
                Iterator<Defect> iterator = defects.iterator();
                for (int i = 0; i < defects.size(); i++) {
                    defect = iterator.next();
                    // System.out.println("Iterate on defect " + defect.getDefectID());
                    if (defect.getDefectID().equals(defectID)) {
                        defect.addTrace(trace);
                        // System.out.println("Catch defect" + defect.getDefectID());
                        break;
                    }
                }
            } else {
                try {
                    defect.addTrace(trace);
                } catch (InvalidDefectStatusException ex) {
                    System.out.println(ex.toString());
                    return success;
                }
                defects.add(defect);
            }
        }
        success = true;
        return success;
    }

    public String printAllDefectTraces() {
        String printString = new String();
        ArrayList<Defect> sortedDefects = new ArrayList<Defect>();
        for (Defect defect : defects) {
            sortedDefects.add(defect);
        }
        Collections.sort(sortedDefects);
        for (Defect defect : sortedDefects) {
            printString += defect.printTrace();
        }
        printString += "END OF ANALYSIS.\n";
        return printString;
    }

    public void analyseRun(ArrayList<Run> runs) {
        for (int i = 0; i < runs.size(); i++) {
            ArrayList<Comment> commentLines;
            commentLines = runs.get(i).getCommentsLines();
            // System.out.println("commentLines.size() " + commentLines.size());
            for (int j = 0; j < commentLines.size(); j++) {
                try {
                    // System.out.println("Enter try");
                    analyseComment(commentLines.get(j));
                } catch (InvalidTraceException ex) {
                    System.out.print(ex.toString());
                    // Logger.getLogger(DefectCommentAnalyser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidDefectStatusException ex) {
                    System.out.print(ex.toString());
                    // Logger.getLogger(DefectCommentAnalyser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void main(String[] args) {

        DefectCommentAnalyser analyser = new DefectCommentAnalyser();
        ArrayList<Run> runs = new ArrayList<Run>();

        String cmt1 = "Open defect 11111, 22222, 33333, 44444. ";
        String cmt2 = "Reopen defect 11111, 22222. \n Close defect 33333. \n Open defect 55555";
        String cmt3 = "Open defect 22222. \n Close defect 44444 as dup. \n Close defect 11111 as Deferred.";
        String cmt4 = "Close defect 22222 as Notabug. \n Close defect 55555";

        // runs.add(new Run().readComment(cmt4));
        String[] runCommentString = new String[4];
        runCommentString[0] = cmt1;
        runCommentString[1] = cmt2;
        runCommentString[2] = cmt3;
        runCommentString[3] = cmt4;
        Run aRun;
        for (int i = 0; i < 4; i++) {
            aRun = new Run(i);
            aRun.readComment(runCommentString[i]);
            runs.add(aRun);
        }
        analyser.analyseRun(runs);        
        System.out.print(analyser.printAllDefectTraces());
    }

}
