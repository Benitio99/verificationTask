package cm;

import java.math.BigDecimal;

public class StaffStrategy implements CalculatorStrategy {

    // STAFF: maximum payable is 16.00 per day
    @Override
    public BigDecimal getFinalCalculation(BigDecimal preCalculation) {
        BigDecimal result = preCalculation;
        if (result.compareTo(new BigDecimal(16)) == 1) {
            result = new BigDecimal(16);
        }
        return result;

    }

}
