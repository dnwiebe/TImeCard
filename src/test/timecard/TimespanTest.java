package timecard;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimespanTest {

    private static final SimpleDateFormat SDF = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

    @Test
    public void constructorAndAccessors () throws Exception {
        Date begin = SDF.parse ("2016-3-4 15:00:00");
        Date end = SDF.parse ("2016-3-4 17:00:00");
        Task task = mock (Task.class);

        Timespan timespan = new Timespan (begin, end, task);

        assertSame (begin, timespan.getBegin ());
        assertSame (end, timespan.getEnd ());
        assertSame (task, timespan.getTask ());
    }

    @Test
    public void canComputeLengthInMinutes () throws Exception {
        Date begin = SDF.parse ("2016-3-4 15:00:00");
        Date end = SDF.parse ("2016-3-4 17:00:00");
        Timespan subject = new Timespan (begin, end, null);

        int result = subject.lengthInMinutes ();

        assertEquals (120, result);
    }

    @Test
    public void containsItsBeginPoint () throws Exception {
        Date begin = SDF.parse ("2016-3-4 15:00:00");
        Date end = SDF.parse ("2016-3-4 17:00:00");
        Timespan subject = new Timespan (begin, end, null);

        boolean result = subject.contains (begin);

        assertTrue (result);
    }

    @Test
    public void doesNotContainItsEndPoint () throws Exception {
        Date begin = SDF.parse ("2016-3-4 15:00:00");
        Date end = SDF.parse ("2016-3-4 17:00:00");
        Timespan subject = new Timespan (begin, end, null);

        boolean result = subject.contains (end);

        assertFalse (result);
    }

    @Test
    public void containsInternalPoint () throws Exception {
        Date begin = SDF.parse ("2016-3-4 15:00:00");
        Date end = SDF.parse ("2016-3-4 17:00:00");
        Date internal = SDF.parse ("2016-3-4 16:00:00");
        Timespan subject = new Timespan (begin, end, null);

        boolean result = subject.contains (internal);

        assertTrue (result);
    }

    @Test
    public void doesNotContainExternalPoint () throws Exception {
        Date begin = SDF.parse ("2016-3-4 15:00:00");
        Date end = SDF.parse ("2016-3-4 17:00:00");
        Date external = SDF.parse ("2016-3-4 20:00:00");
        Timespan subject = new Timespan (begin, end, null);

        boolean result = subject.contains (external);

        assertFalse (result);
    }
}
