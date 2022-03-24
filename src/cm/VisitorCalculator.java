package cm;

import java.math.BigDecimal;

public class VisitorCalculator implements Calculator {

    // VISITOR: first 10.00 is free, 50% reduction above that
    // e.g total calculated cost was 15.00, after reduction only 2.50 needs to be
    // paid (i.e. 50% of (15.00-8.00))
    @Override
    public BigDecimal calculate(BigDecimal preCalculation) {
        BigDecimal result = preCalculation;
        if (result.compareTo(new BigDecimal(10)) == 1) {
            result = result.subtract(new BigDecimal(10));
            result = result.divide(new BigDecimal(2));
        }
        return result;

    }

}
