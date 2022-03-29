package cm;

import java.math.BigDecimal;

public class ManagementStrategy implements CalculatorStrategy {

    // MANAGEMENT: minimum payable is 4.00
    @Override
    public BigDecimal getFinalCalculation(BigDecimal preCalculation) {
        BigDecimal result = preCalculation;
        if (result.compareTo(new BigDecimal(4)) == -1) {
            result = new BigDecimal(4);
        }
        return result;

    }

}
