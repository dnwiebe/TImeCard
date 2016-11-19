package timecard.facade;

import org.junit.Test;
import timecard.Task;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeCardFacadeTest {
    private static final SimpleDateFormat SDF = new SimpleDateFormat ("HH:mm");

    @Test
    public void addingOverlappingTimespansThrowsException () throws Exception {
        TimeCardFacade subject = new TimeCardFacade ("Billy", 1234L);
        Task task = new Task ("Sweeping the floor", 100);
        subject.addTime (SDF.parse ("08:00"), 120, task);

        try {
            subject.addTime (SDF.parse ("9:30"), 30, task);
            fail ();
        }
        catch (IllegalArgumentException e) {
            assertEquals ("Timespan overlap", e.getMessage ());
        }
    }

    @Test
    public void canGetCollectionOfTasks () throws Exception {
        TimeCardFacade subject = new TimeCardFacade ("Billy", 1234L);
        Task oneTask = mock (Task.class);
        Task anotherTask = mock (Task.class);
        subject.addTime (SDF.parse ("08:00"), 120, oneTask);
        subject.addTime (SDF.parse ("10:00"), 60, oneTask);
        subject.addTime (SDF.parse ("14:00"), 45, anotherTask);

        Set<Task> result = subject.getTasks ();

        Set<Task> expected = new HashSet<> ();
        expected.add (oneTask);
        expected.add (anotherTask);
        assertEquals (expected, result);
    }

    @Test
    public void canGetMinutesForTask () throws Exception {
        TimeCardFacade subject = new TimeCardFacade ("Billy", 1234L);
        Task oneTask = mock (Task.class);
        Task anotherTask = mock (Task.class);
        subject.addTime (SDF.parse ("08:00"), 120, oneTask);
        subject.addTime (SDF.parse ("10:00"), 60, oneTask);
        subject.addTime (SDF.parse ("14:00"), 45, anotherTask);

        int oneTaskMinutes = subject.getMinutesForTask (oneTask);
        int anotherTaskMinutes = subject.getMinutesForTask (anotherTask);

        assertEquals (180, oneTaskMinutes);
        assertEquals (45, anotherTaskMinutes);
    }

    @Test
    public void canGetValueOfTimeCard () throws Exception {
        TimeCardFacade subject = new TimeCardFacade ("Billy", 1234L);
        Task sweeping = new Task ("Sweeping Floors", 5);
        Task wardening = new Task ("Wardening", 5000);
        subject.addTime (SDF.parse ("08:00"), 60 * 8, sweeping);
        subject.addTime (SDF.parse ("10:00"), 60 * 2, wardening);
        subject.addTime (SDF.parse ("2:00"), 60 * 2, wardening);

        int result = subject.getValue ();

        assertEquals (20040, result);
    }
}
