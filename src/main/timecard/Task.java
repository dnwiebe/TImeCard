package timecard;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class Task {
    private String name;
    private long id;

    public Task (String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public long getId () {
        return id;
    }

    @Override
    public boolean equals (Object o) {
        if (o == this) {return true;}
        if (o == null) {return false;}
        if (!(o instanceof Task)) {return false;}
        Task that = (Task)o;
        if (!that.name.equals (this.name)) {return false;}
        if (that.id != this.id) {return false;}
        return true;
    }

    @Override
    public int hashCode () {
        return (int)(this.name.hashCode () * 1000 + this.id);
    }
}
