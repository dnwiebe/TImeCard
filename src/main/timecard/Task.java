package timecard;

/**
 * Created by dnwiebe on 11/16/16.
 */
public class Task {
    private int hourlyRate;
    private String name;

    public Task (String name, int hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    public String getName () {
        return name;
    }

    public int getHourlyRate () {
        return hourlyRate;
    }

    @Override
    public boolean equals (Object o) {
        if (o == this) {return true;}
        if (o == null) {return false;}
        if (!(o instanceof Task)) {return false;}
        Task that = (Task)o;
        if (!that.name.equals (this.name)) {return false;}
        if (that.hourlyRate != this.hourlyRate) {return false;}
        return true;
    }

    @Override
    public int hashCode () {
        return (int)(this.name.hashCode () * 1000 + this.hourlyRate);
    }
}
