package timecard.visitors;

import timecard.Timespan;
import timecard.Visitor;

import java.util.Date;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class OverlapDetector implements Visitor<Timespan> {
    private Timespan timespan;
    private boolean overlaps;

    public OverlapDetector (Timespan timespan) {
        this.timespan = timespan;
        this.overlaps = false;
    }

    @Override
    public void visit (Timespan candidate) {
        Date endButOne = new Date (candidate.getEnd ().getTime () - 1);
        if (timespan.contains (endButOne)) {overlaps = true;}
        if (timespan.contains (candidate.getBegin ())) {overlaps = true;}
    }

    public boolean overlaps () {
        return overlaps;
    }
}
