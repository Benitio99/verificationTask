package src;

import java.sql.Time;

public class Stay {
    Time entryDateTime;
    Time exitDateTime;
    double charge;

    public Stay(Time entryDateTime, Time exitDateTime, double charge) {
        this.entryDateTime = entryDateTime;
        this.exitDateTime = exitDateTime;
        this.charge = charge;
    }

    public Time getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(Time entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public Time getExitDateTime() {
        return exitDateTime;
    }

    public void setExitDateTime(Time exitDateTime) {
        this.exitDateTime = exitDateTime;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

}
