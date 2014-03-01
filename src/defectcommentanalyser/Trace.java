/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectcommentanalyser;

import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author quan
 */
public class Trace implements Comparator<Trace>, Comparable<Trace> {

    private Integer runNumber;
    private String defectStatus;

    /**
     * @return the runNumber
     */
    public Integer getRunNumber() {
        return runNumber;
    }

    /**
     * @param runNumber the runNumber to set
     */
    public void setRunNumber(Integer runNumber) {
        this.runNumber = runNumber;
    }

    /**
     * @return the defectStatus
     */
    public String getDefectStatus() {
        return defectStatus;
    }

    /**
     * @param defectStatus the defectStatus to set
     */
    public void setDefectStatus(String defectStatus) {
        this.defectStatus = defectStatus;
    }

    @Override
    public String toString() {
        return "(run " + runNumber + ": " + defectStatus + ")";
    }

    @Override
    public int compare(Trace o1, Trace o2) {
        return o1.runNumber.compareTo(o2.runNumber);
    }

    @Override
    public int compareTo(Trace o) {
        return this.runNumber.compareTo(o.runNumber);
    }

    @Override
    public int hashCode() {
        return runNumber.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trace other = (Trace) obj;
        return Objects.equals(this.runNumber, other.runNumber);
    }
}
