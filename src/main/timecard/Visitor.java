package timecard;

/**
 * Created by dnwiebe on 11/16/16.
 */
public interface Visitor<T> {
    void visit (T t);
}
