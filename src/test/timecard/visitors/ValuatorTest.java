package timecard.visitors;

import org.junit.Test;
import timecard.Task;
import timecard.Timespan;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

/**
 * Created by dnwiebe on 11/19/16.
 */
public class ValuatorTest {
    private static final SimpleDateFormat SDF = new SimpleDateFormat ("HH:mm");

    @Test
    public void collectsTasks () throws Exception {
        Task oneTask = new Task ("one", 150);
        Task anotherTask = new Task ("another", 200);
        Valuator subject = new Valuator ();

        subject.visit (new Timespan (SDF.parse ("08:00"), SDF.parse ("09:00"), oneTask));
        subject.visit (new Timespan (SDF.parse ("09:00"), SDF.parse ("11:00"), anotherTask));
        subject.visit (new Timespan (SDF.parse ("12:00"), SDF.parse ("17:00"), oneTask));

        assertEquals (1300, subject.getValue ());
    }
}
