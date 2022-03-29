package cm;

import java.math.BigDecimal;

public interface CalculatorStrategy {
    BigDecimal getFinalCalculation(BigDecimal preCalculation);
}
