/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectcommentanalyser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author quan
 */
public class Defect implements Comparable<Defect>, Comparator<Defect> {

    private Integer defectID;
    private HashSet<Trace> defectTrace;

    /**
     * @return the defectID
     */
    public Integer getDefectID() {
        return defectID;
    }

    /**
     * @param defectID the defectID to set
     */
    public void setDefectID(Integer defectID) {
        this.defectID = defectID;
    }

    /**
     * @return the defectTrace
     */
    public HashSet<Trace> getDefectTrace() {
        return defectTrace;
    }

    public Defect() {

    }

    public Defect(Integer defectID) {
        this.defectID = defectID;
        defectTrace = new HashSet<Trace>();
    }

    public void addTrace(Trace trace) throws InvalidDefectStatusException {
        InvalidDefectStatusException defectStatusException = new InvalidDefectStatusException(trace, defectID);
        // Check if a trace is already contain in the defect.
        if (defectTrace.contains(trace)) {
            throw defectStatusException;
        } else {
            defectTrace.add(trace);
        }
    }

    public String printTrace() {
        String result = new String();
        ArrayList<Trace> sortedDefectTrace = new ArrayList<Trace>();
        for (Trace trace : defectTrace) {
            sortedDefectTrace.add(trace);
        }
        Collections.sort(sortedDefectTrace);
        result += "Defect " + defectID + ": ";
        for (int i = 0; i < sortedDefectTrace.size(); i++) {
            result = result  + sortedDefectTrace.get(i).toString() + "; ";
        }
        
        // result.charAt(result.length()-1)= "a".charAt(Integer.parseInt(""));
        result += "Done.\n";
        return result;
    }

    @Override
    public int compareTo(Defect otherDefect) {
        return this.defectID.compareTo(otherDefect.defectID);
    }

    @Override
    public int compare(Defect o1, Defect o2) {
        return o1.defectID.compareTo(o2.defectID);
    }
    @Override 
    public int hashCode(){
        return defectID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Defect other = (Defect) obj;
        return Objects.equals(this.defectID, other.defectID);
    }
}
