package common.responses;

import java.io.Serializable;
import java.math.BigInteger;

public class AddResponse implements Serializable {
    private final static BigInteger sum(BigInteger number1, BigInteger number2)
    {
        if(number1 == null || number2 == null)
        {
            return null;
        }

        return number1.add(number2);
    }

    public final BigInteger Sum;

    public AddResponse(BigInteger number1, BigInteger number2) {
        Sum = sum(number1, number2);
    }
}
