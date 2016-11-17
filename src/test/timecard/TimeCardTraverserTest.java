package timecard;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class TimeCardTraverserTest {

    @Test
    public void traverserWorks () {
        Timespan oneTimespan = mock (Timespan.class);
        Timespan anotherTimespan = mock (Timespan.class);
        TimeCard timeCard = TimeCard.builder ()
                .addTimespan (oneTimespan)
                .addTimespan (anotherTimespan)
                .build();
        Visitor<Timespan> visitor = mock(Visitor.class);
        TimeCardTraverser subject = new TimeCardTraverser (timeCard);

        subject.traverse (visitor);

        verify (visitor).visit (oneTimespan);
        verify (visitor).visit (anotherTimespan);
    }
}
