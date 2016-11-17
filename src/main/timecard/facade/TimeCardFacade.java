package timecard.facade;

import timecard.Task;
import timecard.TimeCard;
import timecard.TimeCardTraverser;
import timecard.Timespan;
import timecard.visitors.OverlapDetector;
import timecard.visitors.TaskCollector;
import timecard.visitors.TimeMapper;

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
        TaskCollector collector = new TaskCollector ();
        new TimeCardTraverser (timeCard).traverse (collector);
        return collector.getTasks ();
    }

    public int getMinutesForTask (Task task) {
        TimeMapper mapper = new TimeMapper ();
        new TimeCardTraverser (timeCard).traverse (mapper);
        return mapper.minutesOnTask (task);
    }
}
