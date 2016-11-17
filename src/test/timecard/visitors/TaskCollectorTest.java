package timecard.visitors;

import org.junit.Test;
import timecard.Task;
import timecard.Timespan;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TaskCollectorTest {
    @Test
    public void collectsTasks () {
        Task oneTask = mock (Task.class);
        Task anotherTask = mock (Task.class);
        TaskCollector subject = new TaskCollector ();

        subject.visit (new Timespan (null, null, oneTask));
        subject.visit (new Timespan (null, null, anotherTask));
        subject.visit (new Timespan (null, null, oneTask));

        Set<Task> tasks = subject.getTasks ();
        assertTrue (tasks.contains (oneTask));
        assertTrue (tasks.contains (anotherTask));
        assertEquals (2, tasks.size ());
    }
}
