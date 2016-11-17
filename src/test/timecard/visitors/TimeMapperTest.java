package timecard.visitors;

import org.junit.Test;
import timecard.Task;
import timecard.Timespan;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeMapperTest {

    private static final SimpleDateFormat SDF = new SimpleDateFormat ("HH:mm");

    @Test
    public void mapsTimeAccordingToTask () throws Exception {
        Task oneTask = new Task ("one", 12L);
        Task anotherTask = new Task ("another", 13L);
        TimeMapper subject = new TimeMapper ();

        subject.visit (new Timespan (SDF.parse ("10:00"), SDF.parse ("11:00"), oneTask));
        subject.visit (new Timespan (SDF.parse ("16:00"), SDF.parse ("17:30"), oneTask));
        subject.visit (new Timespan (SDF.parse ("12:00"), SDF.parse ("13:00"), anotherTask));

        assertEquals (150, subject.minutesOnTask (oneTask));
        assertEquals (60, subject.minutesOnTask (anotherTask));
    }
}
