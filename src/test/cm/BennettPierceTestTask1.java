package test.cm;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.Before;
import org.junit.BeforeClass;

import main.CarParkKind;
import main.Period;
import main.Rate;

@TestInstance(Lifecycle.PER_CLASS)
class BennettPierceTestTask1 {

    /**
     *
     */

    private static final IllegalArgumentException ILLEGAL_ARGUMENT_EXCEPTION = new IllegalArgumentException();

    static int numberPeriodTests = 21;
    static int[] periodTestStartHours = { -6000, -1, 0, 1, 12, 13, 24, 25, 5, 5, 0, 1, 12, 18, 18, 18, 0, 0, 24, 13,
            0 };
    static int[] periodTestEndHours = { 5, 5, 5, 7, 17, 20, 24, 5, -6000, -1, 0, 3, 17, 23, 24, 25, 1, 0, 24, 12, 24 };
    static Object[] periodTestExpectedResults = { ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 5, 6, 5, 7,
            ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION,
            ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 2, 5, 5, 6, ILLEGAL_ARGUMENT_EXCEPTION, 1,
            ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 24 };

    static int numberRateTests = 30;
    static int[][] reducedStartHours = { { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 },
            { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 },
            { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 }, { 7, 9, 11 },
            { 7, 9, 11 }, { 7, 9, 11 }, { 2, 4, 6 }, {}, { 8, 10 }, { 10, 8 }, { 7, 9, 11 }, { 2, 4, 6 }, {}, { 5 },
            { 1 } };
    static int[][] reducedEndHours = { { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 },
            { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 },
            { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 }, { 8, 10, 12 },
            { 8, 10, 12 }, { 8, 10, 12 }, { 3, 5, 7 }, {}, { 15, 20 }, { 20, 15 }, { 8, 10, 12 }, { 3, 5, 7 }, {},
            { 10 }, { 10 } };

    static int[][] normalStartHours = { { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 },
            { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 },
            { 1, 3, 5 }, { 1, 3, 5 }, { 1, 8, 10 }, {}, { 1, 2 }, { 3, 1 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 },
            { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, { 1, 3, 5 }, {}, { 1 }, { 5 } };
    static int[][] normalEndHours = { { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 },
            { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 },
            { 2, 4, 6 }, { 2, 4, 6 }, { 4, 9, 11 }, {}, { 6, 7 }, { 5, 4 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 },
            { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 }, {}, { 10 }, { 10 } };

    static CarParkKind[] kinds = { CarParkKind.STAFF, CarParkKind.STUDENT, CarParkKind.MANAGEMENT,
            CarParkKind.VISITOR };

    static int[] normalRates = { -5000, -1, 0, 7, 5000, 1, 1, 1, 1, 1, 1, 0, 3000, 2000, 10, 10, 10, 10, 10, 10, 10, 10,
            10, 10, 10, 10, 10, 10, 10, 10 };
    static int[] reducedRates = { 1, 1, 1, 1, 1, -5000, -1, 0, 7, 5000, 0, -1, 2000, 3000, 8, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 5, 5, 5, 5 };

    static int[] periodToTestStartHours = { 2, 0, 6, 1, 5, 2, 0, 6, 1, 5, 2, 0, 6, 1, 5, 2, 0, 6, 1, 5, 2, 0, 6, 1, 5,
            2, 0, 6, 1, 5 };
    static int[] periodToTestEndHours = { 8, 12, 10, 19, 12, 8, 12, 10, 19, 12, 8, 12, 10, 19, 12, 8, 12, 10, 19, 12, 8,
            12, 10, 19, 12, 8, 12, 10, 19, 12 };

    static ArrayList<Period> reducedPeriods = new ArrayList<Period>();
    static ArrayList<Period> normalPeriods = new ArrayList<Period>();

    static ArrayList<Rate> data = new ArrayList<Rate>(numberRateTests);
    static ArrayList<Period> periodsToTest = new ArrayList<Period>(numberRateTests);
    static Object[] rateTestExpectedResults = { ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION,
            ILLEGAL_ARGUMENT_EXCEPTION, 24, 5003, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 0,
            ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 2, ILLEGAL_ARGUMENT_EXCEPTION, 6000,
            ILLEGAL_ARGUMENT_EXCEPTION, 34, 25, 65, 10, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 25, 45,
            0, ILLEGAL_ARGUMENT_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION, 25, 45, 0, ILLEGAL_ARGUMENT_EXCEPTION,
            ILLEGAL_ARGUMENT_EXCEPTION };

    /**
     * Combining of the Raw Test Data into Rate Objects for testing
     */
    /*
     * @BeforeEach
     * void setup() {
     * 
     * for (int i = 0; i < numberRateTests; i++) {
     * 
     * }
     * 
     * }
     */
    @Test
    void periodTest() {
        System.out.println("\nTesting Period Class...");
        for (int i = 0; i < numberPeriodTests; i++) {
            int index = i;
            System.out.print("\nTest : " + (index + 1));
            Object result = periodTestExpectedResults[index];
            System.out.print(" - " + periodTestStartHours[index] + ", " + periodTestEndHours[index]);
            try {
                Period p = new Period(periodTestStartHours[index], periodTestEndHours[index]);
                assertEquals(result, p.duration());
                System.out.print(", duration : " + result);
            } catch (IllegalArgumentException error) {
                IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    new Period(periodTestStartHours[index], periodTestEndHours[index]);
                }, "IllegalArgumentException was excepted.");
                System.out.print(" - error : " + thrown.getMessage());
            }

        }
    }

    @Test
    void rateTest() {
        System.out.println("\nTesting Rate Class...");
        // int index;
        BigDecimal rate;
        for (int i = 0; i < numberRateTests; i++) {
            int index = i;
            Rate rObject;

            System.out.print("\nTest : " + (index + 1));
            Object result = rateTestExpectedResults[index];

            try {
                // Data Setup
                periodsToTest.add(i, new Period(periodToTestStartHours[i], periodToTestStartHours[i]));

                for (int k = 0; k < reducedStartHours[i].length; k++) {
                    reducedPeriods.add(i, new Period(reducedStartHours[i][k], reducedEndHours[i][k]));
                    normalPeriods.add(i, new Period(normalStartHours[i][k], normalEndHours[i][k]));
                }
                System.out.print(
                        " - " + periodsToTest.get(index).getStartHour() + ", " + periodsToTest.get(index).getEndHour());
                rObject = new Rate((kinds[index % 4]), new BigDecimal(normalRates[index]),
                        new BigDecimal(reducedRates[index]),
                        reducedPeriods, normalPeriods);
                rate = rObject.calculate(periodsToTest.get(index));
                assertEquals(result, rate);
                System.out.print(", rate : " + result);
            } catch (IllegalArgumentException error) {
                IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    new Rate((kinds[index % 4]), new BigDecimal(normalRates[index]),
                            new BigDecimal(reducedRates[index]),
                            reducedPeriods, normalPeriods).calculate(periodsToTest.get(index));
                    ;
                }, "IllegalArgumentException was excepted.");
                System.out.print(" - error : " + thrown.getMessage());
            }

        }
        /*
         * System.out.println("\nTesting calculate method of Rate Class...");
         * // IllegalArgumentException e = new IllegalArgumentException();
         * for (int i = 0; i < numberRateTests; i++) {
         * assertEquals(ILLEGAL_ARGUMENT_EXCEPTION, data.get(i));
         * }
         */
    }
}