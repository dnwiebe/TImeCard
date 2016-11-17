package timecard.visitors;

import org.junit.Test;
import timecard.Task;
import timecard.Timespan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class OverlapDetectorTest {
    private static final SimpleDateFormat SDF = new SimpleDateFormat ("HH:mm");

    @Test
    public void nonOverlapIsDetected () throws Exception {
        OverlapDetector subject = new OverlapDetector (makeHour ("07:00", "x"));

        subject.visit (makeHour ("12:00", "x"));

        assertFalse (subject.overlaps ());
    }

    @Test
    public void adjacencyIsNotOverlap () throws Exception {
        OverlapDetector subject = new OverlapDetector (makeHour ("07:00", "x"));

        subject.visit (makeHour ("06:00", "x"));
        subject.visit (makeHour ("08:00", "x"));

        assertFalse (subject.overlaps ());
    }

    @Test
    public void beginningOverlapIsDetected () throws Exception {
        OverlapDetector subject = new OverlapDetector (makeHour ("07:00", "x"));

        subject.visit (makeHour ("06:30", "x"));

        assertTrue (subject.overlaps ());
    }

    @Test
    public void endingOverlapIsDetected () throws Exception {
        OverlapDetector subject = new OverlapDetector (makeHour ("07:00", "x"));

        subject.visit (makeHour ("07:30", "x"));

        assertTrue (subject.overlaps ());
    }

    private Timespan makeHour (String beginString, String taskName) {
        try {
            Task task = new Task (taskName, 1L);
            Date begin = SDF.parse (beginString);
            Date end = new Date (begin.getTime () + 3600000L);
            return new Timespan (begin, end, task);
        }
        catch (ParseException e) {
            throw new IllegalStateException (e);
        }
    }
}
