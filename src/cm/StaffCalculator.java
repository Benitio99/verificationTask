package cm;

import java.math.BigDecimal;

public class StaffCalculator implements Calculator {

    // STAFF: maximum payable is 16.00 per day
    @Override
    public BigDecimal calculate(BigDecimal preCalculation) {
        BigDecimal result = preCalculation;
        if (result.compareTo(new BigDecimal(16)) == 1) {
            result = new BigDecimal(16);
        }
        return result;

    }

}
