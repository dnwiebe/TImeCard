package timecard.visitors;

import timecard.Timespan;
import timecard.Visitor;

/**
 * Created by dnwiebe on 11/19/16.
 */
public class Valuator implements Visitor<Timespan> {
    private int value = 0;

    @Override
    public void visit (Timespan timespan) {
        int hourlyRate = timespan.getTask ().getHourlyRate ();
        int minutes = timespan.lengthInMinutes ();
        value += (hourlyRate * minutes) / 60;
    }

    public int getValue () {
        return value;
    };
}
