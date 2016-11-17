package timecard.visitors;

import timecard.Task;
import timecard.Timespan;
import timecard.Visitor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TaskCollector implements Visitor<Timespan> {
    private Set<Task> tasks = new HashSet<> ();

    @Override
    public void visit (Timespan timespan) {
        tasks.add (timespan.getTask ());
    }

    public Set<Task> getTasks () {
        return tasks;
    }
}
