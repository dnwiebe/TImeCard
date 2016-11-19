package timecard.facade;

import timecard.*;
import timecard.visitors.OverlapDetector;
import timecard.visitors.TaskCollector;
import timecard.visitors.TimeMapper;
import timecard.visitors.Valuator;

import java.util.Date;
import java.util.Set;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeCardFacade {
    private TimeCard timeCard;

    public TimeCardFacade (String name, long id) {
        this.timeCard = TimeCard.builder ().name (name).id (id).build ();
    }

    public void addTime (Date begin, int minutes, Task task) {
        Date end = new Date (begin.getTime () + (minutes * 60000L));
        Timespan timespan = new Timespan (begin, end, task);
        OverlapDetector detector = new OverlapDetector (timespan);
        new TimeCardTraverser (timeCard).traverse (detector);
        if (detector.overlaps ()) {
            throw new IllegalArgumentException ("Timespan overlap");
        }
        timeCard = TimeCard.builder (timeCard).addTimespan (timespan).build ();
    }

    public Set<Task> getTasks () {
        return traverse (new TaskCollector ()).getTasks ();
    }

    public int getMinutesForTask (Task task) {
        return traverse (new TimeMapper ()).minutesOnTask (task);
    }

    public int getValue () {
        return traverse (new Valuator ()).getValue ();
    }

    private <T extends Visitor<Timespan>> T traverse (T visitor) {
        new TimeCardTraverser (timeCard).traverse (visitor);
        return visitor;
    }
}
