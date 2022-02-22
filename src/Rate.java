package src;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Rate {

    CarParkKind kind;
    BigDecimal hourlyNormalRate;
    BigDecimal hourlyReducedRate;
    ArrayList<Period> reduced;
    ArrayList<Period> normal;

    public Rate(CarParkKind kind, BigDecimal hourlyNormalRate, BigDecimal hourlyReducedRate,
            ArrayList<Period> reducedPeriods, ArrayList<Period> normalPeriods) {
        this.kind = kind;

        try {
            normalRateGreaterOrEqualToReduced(hourlyNormalRate, hourlyReducedRate);
            try {
                greaterThanZero(hourlyNormalRate.intValue());
            } catch (IllegalArgumentException error) {
                System.out.println(hourlyNormalRate + " must be greater than zero.");
            }
            try {
                greaterThanZero(hourlyReducedRate.intValue());
            } catch (IllegalArgumentException error) {
                System.out.println(hourlyReducedRate + " must be greater than zero.");

            }
            this.hourlyNormalRate = hourlyNormalRate;
            this.hourlyReducedRate = hourlyReducedRate;
        } catch (IllegalArgumentException error) {
            System.out.println(hourlyNormalRate + " must be greater or equal to " + hourlyReducedRate + ".");
        }
        try {
            try {
                checkforOverlappingPeriods(reducedPeriods);
            } catch (IllegalArgumentException error) {
                System.out.println("Periods in a list must not overlap" + reducedPeriods);
            }
            try {
                checkforOverlappingPeriods(normalPeriods);
            } catch (IllegalArgumentException error) {
                System.out.println("Periods in a list must not overlap." + normalPeriods);

            }
            checkPeriodListsOverlapping(reducedPeriods, normalPeriods);

        } catch (IllegalArgumentException error) {
            System.out.println("Periods in the reduced and normatl rates must not overlap. " + normalPeriods + " : "
                    + reducedPeriods);

        }

        this.reduced = reducedPeriods;
        this.normal = normalPeriods;
    }

    public CarParkKind getKind() {
        return kind;
    }

    public void setKind(CarParkKind kind) {
        this.kind = kind;
    }

    public BigDecimal getHourlyNormalRate() {
        return hourlyNormalRate;
    }

    public void setHourlyNormalRate(BigDecimal hourlyNormalRate) {
        this.hourlyNormalRate = hourlyNormalRate;
    }

    public BigDecimal getHourlyReducedRate() {
        return hourlyReducedRate;
    }

    public void setHourlyReducedRate(BigDecimal hourlyReducedRate) {
        this.hourlyReducedRate = hourlyReducedRate;
    }

    public ArrayList<Period> getReduced() {
        return reduced;
    }

    public void setReduced(ArrayList<Period> reduced) {
        this.reduced = reduced;
    }

    public ArrayList<Period> getNormal() {
        return normal;
    }

    public void setNormal(ArrayList<Period> normal) {
        this.normal = normal;
    }

    /**
     * 
     * @param rate  The list the period is to be added to
     * @param start The start time of the period
     * @param end   The end time of the period
     */
    public void addPeriod(String rate, int start, int end) {
        Period period = new Period(start, end);
        ArrayList<Period> list = null;

        if (rate.compareTo("normal") == 0) {
            list = getNormal();
        } else if (rate.compareTo("reduced") == 0) {
            list = getReduced();
        }

        try {
            if (list.get(list.size() - 1).getEndhour() < start) {
                throw new IllegalArgumentException();
            } else {
                list.add(period);
            }
        } catch (IllegalArgumentException error) {
            System.out.println("Periods in a list must not overlap. " + list.get(list.size() - 1).getEndhour()
                    + " overlaps with " + period.getStartHour());
        }
        sortPeriods(list);
    }

    /**
     * 
     * @param number
     * @return
     */
    // Checks if an int is greater than or equal to 0
    private Boolean greaterThanZero(int number) {

        Boolean greaterThan;

        greaterThan = number >= 0 ? true : false;
        if (greaterThan) {
            return greaterThan;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // Checks that the normalRate is greater or equal to the reducedRate
    private Boolean normalRateGreaterOrEqualToReduced(BigDecimal normal, BigDecimal reduced) {

        Boolean result;
        int compareResult;

        compareResult = getHourlyNormalRate().compareTo(getHourlyReducedRate());
        result = compareResult == 1 || compareResult == 0 ? true : false;

        return result;
    }

    private Boolean checkPeriodListsOverlapping(ArrayList<Period> listOne, ArrayList<Period> listTwo) {

        Boolean result = true;

        int i = 0;
        int k = 0;
        try {
            while (i < listOne.size() && k < listTwo.size()) {
                if (listOne.get(i).getStartHour() < listTwo.get(k).getStartHour()) {
                    if (listOne.get(i).getEndhour() > listTwo.get(k).getStartHour()) {
                        throw new IllegalArgumentException();
                    }
                    i++;
                } else if (listOne.get(i).getStartHour() > listTwo.get(k).getStartHour()) {
                    if (listTwo.get(k).getEndhour() > listOne.get(i).getStartHour()) {
                        throw new IllegalArgumentException();
                    }
                    k++;
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException error) {
            System.out.println(listOne.get(i) + " and " + listTwo.get(k) + " start at the same time.");
        }

        return result;
    }

    private Boolean checkforOverlappingPeriods(ArrayList<Period> list) {

        Boolean result;

        result = false;
        for (int i = 1; i < list.size(); i++) {
            if (checkForOverlapInTwoPeriods(list.get(i - 1).getEndhour(), list.get(i).getStartHour())) {
                return true;
            }
        }
        return result;
    }

    private Boolean checkForOverlapInTwoPeriods(int x, int y) {
        if (x > y) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Periods sorted by Period.startHour of the Period
     */
    private ArrayList<Period> sortPeriods(ArrayList<Period> list) {

        Collections.sort(list);

        return list;
    }

    public BigDecimal calculate(Period periodStay) {

        BigDecimal rate = new BigDecimal("0");

        for (int i = 0; i < getNormal().size(); i++) {
            rate.add(getHourlyNormalRate().multiply(getNormal().get(i).duration()));
        }

        return rate;
    }

}