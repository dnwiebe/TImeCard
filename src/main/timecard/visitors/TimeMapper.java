package timecard.visitors;

import timecard.Task;
import timecard.Timespan;
import timecard.Visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeMapper implements Visitor<Timespan> {
    private Map<Task, Integer> timeMap = new HashMap<> ();

    @Override
    public void visit (Timespan timespan) {
        Integer minutesSoFar = timeMap.get (timespan.getTask ());
        Integer minutesAfterThis;
        if (minutesSoFar == null) {
            minutesAfterThis = timespan.lengthInMinutes ();
        }
        else {
            minutesAfterThis = minutesSoFar + timespan.lengthInMinutes ();
        }
        timeMap.put (timespan.getTask (), minutesAfterThis);
    }

    public int minutesOnTask (Task task) {
        return timeMap.get (task);
    }
}
