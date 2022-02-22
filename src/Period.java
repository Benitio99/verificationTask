package src;

import java.math.BigDecimal;

public class Period implements Comparable<Period> {

    int startHour;
    int endhour;

    public Period(int startHour, int endhour) {
        try {
            try {
                checkValidHour(startHour);
            } catch (IllegalArgumentException error) {
                System.out.println(startHour + " is not a valid hour in the 24 hour format.");
            }
            try {
                checkValidHour(startHour);
            } catch (IllegalArgumentException error) {
                System.out.println(startHour + " is not a valid hour in the 24 hour format.");

            }
            checkPositveDuration();
        } catch (IllegalArgumentException error) {
            System.out.println(startHour + " must be before " + endhour + ".");
        }
        this.startHour = startHour;
        this.endhour = endhour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndhour() {
        return endhour;
    }

    public void setEndhour(int endhour) {
        this.endhour = endhour;
    }

    private Boolean checkValidHour(int hour) {
        Boolean result;

        result = hour >= 0 && hour <= 24 ? true : false;

        return result;
    }

    private Boolean checkPositveDuration() {
        Boolean result;

        result = (getEndhour() - getStartHour()) > 0 ? true : false;

        return result;

    }

    public BigDecimal duration() {
        BigDecimal difference = BigDecimal.ZERO;

        difference = BigDecimal.valueOf(getEndhour() - getStartHour());

        return difference;
    }

    @Override
    public int compareTo(Period comparison) {

        int comparedStartTime = ((Period) comparison).getStartHour();

        return getStartHour() - comparedStartTime;
    }

    public Boolean overlaps(Period p) {
        Boolean overlaps = false;
        return overlaps;
    }

}
