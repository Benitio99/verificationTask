package cm;

import java.math.BigDecimal;

public class StudentCalculator implements Calculator {

    // STUDENT: 25% reduction on any amount above 5.50

    @Override
    public BigDecimal calculate(BigDecimal preCalculation) {
        BigDecimal result = preCalculation;
        if (result.compareTo(new BigDecimal(5.5)) == 1) {
            result = result.subtract(new BigDecimal(5.5));
            result = result.multiply(new BigDecimal(0.75));
            result = result.add(new BigDecimal(5.5));
        }
        return result;

    }

}
