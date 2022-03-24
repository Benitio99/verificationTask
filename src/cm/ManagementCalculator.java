package cm;

import java.math.BigDecimal;

public class ManagementCalculator implements Calculator {

    // MANAGEMENT: minimum payable is 4.00
    @Override
    public BigDecimal calculate(BigDecimal preCalculation) {
        BigDecimal result = preCalculation;
        if (result.compareTo(new BigDecimal(4)) == -1) {
            result = new BigDecimal(4);
        }
        return result;

    }

}
