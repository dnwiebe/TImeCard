package timecard;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeCard {

    private String name;
    private long id;
    private List<Timespan> timespans;

    private TimeCard () {
        this.name = null;
        this.id = -1L;
        this.timespans = new LinkedList<> ();
    }

    public static Builder builder () {
        return new Builder ();
    }

    public static Builder builder (TimeCard original) {
        return new Builder (original);
    }

    public String getName () {
        return name;
    }

    public long getId () {
        return id;
    }

    public List<Timespan> getTimespans () {
        return timespans;
    }

    public static class Builder {
        TimeCard timeCard = new TimeCard ();

        private Builder () {}

        private Builder (TimeCard original) {
            timeCard.name = original.name;
            timeCard.id = original.id;
            timeCard.timespans.addAll (original.timespans);
        }

        public Builder name (String name) {
            timeCard.name = name;
            return this;
        }

        public Builder id (long id) {
            timeCard.id = id;
            return this;
        }

        public Builder addTimespan (Timespan timespan) {
            timeCard.timespans.add(timespan);
            return this;
        }

        public TimeCard build () {
            return timeCard;
        }
    }
}
