package timecard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeCardTest {
    @Test
    public void canMakeTimeCardWithTimespans () {
        Timespan oneTimespan = mock(Timespan.class);
        Timespan anotherTimespan = mock(Timespan.class);

        TimeCard result = TimeCard.builder ()
                .name ("Billy")
                .id (1234L)
                .addTimespan(oneTimespan)
                .addTimespan(anotherTimespan)
                .build ();

        assertEquals ("Billy", result.getName ());
        assertEquals (1234L, result.getId ());
        assertSame (oneTimespan, result.getTimespans ().get (0));
        assertSame (anotherTimespan, result.getTimespans ().get (1));
    }

    @Test
    public void canBuildFromExistingTimecard () {
        Timespan oneTimespan = mock(Timespan.class);
        Timespan anotherTimespan = mock(Timespan.class);
        TimeCard initial = TimeCard.builder ()
                .name ("Billy")
                .id (1234L)
                .addTimespan (oneTimespan)
                .build ();

        TimeCard result = TimeCard.builder (initial)
                .addTimespan (anotherTimespan)
                .build ();

        assertEquals (1, initial.getTimespans ().size ());
        assertEquals ("Billy", result.getName ());
        assertEquals (1234L, result.getId ());
        assertSame (oneTimespan, result.getTimespans ().get (0));
        assertSame (anotherTimespan, result.getTimespans ().get (1));
    }
}
