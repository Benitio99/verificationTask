package main;
//package src;

public class Period implements Comparable<Period> {

    private int startHour;
    private int endHour;

    public Period(int startHour, int endHour) {
        if (!checkValidStartHour(startHour)) {
            throw new IllegalArgumentException(startHour + " is not a valid starting hour in the 24 hour format.");
        } else if (!checkValidEndHour(endHour)) {
            throw new IllegalArgumentException(endHour + " is not a valid ending Hour in the 24 hour format.");
        } else if (!checkPositveDuration(startHour, endHour)) {
            throw new IllegalArgumentException(startHour + " must be before " + endHour + ".");
        }

        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    private Boolean checkValidStartHour(int hour) {
        Boolean result;

        result = hour >= 0 && hour <= 23 ? true : false;

        return result;
    }

    private Boolean checkValidEndHour(int hour) {
        Boolean result;

        result = hour >= 1 && hour <= 24 ? true : false;

        return result;
    }

    private Boolean checkPositveDuration(int startHour, int endHour) {

        Boolean result;

        result = endHour - startHour >= 1 ? true : false;

        return result;
    }

    public int duration() {
        int difference;

        difference = getEndHour() - getStartHour();

        return difference;
    }

    @Override
    public int compareTo(Period comparison) {

        int comparedStartTime = ((Period) comparison).getStartHour();

        return getStartHour() - comparedStartTime;
    }

    public Boolean overlaps(Period p) {
        Boolean overlaps;

        overlaps = (p.getEndHour() > getStartHour() && p.getEndHour() <= getEndHour()) ? true : false;

        return overlaps;
    }

}
