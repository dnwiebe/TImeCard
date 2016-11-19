package timecard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TaskTest {

    @Test
    public void constructorAndAccessors () {
        Task task = new Task ("name", 100);

        assertEquals ("name", task.getName ());
        assertEquals (100, task.getHourlyRate ());
    }

    @Test
    public void equalsWorks () {
        Task a = new Task ("name", 100);
        Task b = new Task ("name", 100);
        Task c = new Task ("mame", 100);
        Task d = new Task ("name", 101);

        assertFalse (a.equals (null));
        assertFalse (a.equals ("blah"));
        assertTrue (a.equals (a));
        assertTrue (a.equals (b));
        assertFalse (a.equals (c));
        assertFalse (a.equals (d));
    }

    @Test
    public void hashCodeWorks () {
        Task a = new Task ("name", 100);
        Task b = new Task ("name", 100);
        Task c = new Task ("mame", 100);
        Task d = new Task ("name", 101);

        assertTrue (a.hashCode () == a.hashCode ());
        assertTrue (a.hashCode () == b.hashCode ());
        assertFalse (a.hashCode () == c.hashCode ());
        assertFalse (a.hashCode () == d.hashCode ());
    }
}
