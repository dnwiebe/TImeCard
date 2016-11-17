package timecard;

import java.util.Date;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class Timespan {
    private Date begin;
    private Date end;
    private Task task;

    public Timespan (Date begin, Date end, Task task) {
        this.begin = begin;
        this.end = end;
        this.task = task;
    }

    public Date getBegin () {
        return begin;
    }

    public Date getEnd () {
        return end;
    }

    public Task getTask () {
        return task;
    }

    public boolean contains (Date time) {
        return (!time.before (begin) && time.before (end));
    }

    public int lengthInMinutes () {
        long milliseconds = end.getTime () - begin.getTime ();
        return (int)(milliseconds / 60000);
    }
}
