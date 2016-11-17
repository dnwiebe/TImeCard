package timecard;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeCardTraverser {
    private TimeCard timeCard;

    public TimeCardTraverser (TimeCard timeCard) {
        this.timeCard = timeCard;
    }

    public void traverse (Visitor<Timespan> visitor) {
        timeCard.getTimespans ().forEach (visitor::visit);
    }
}
