package common.requests;

import java.io.Serializable;
import java.math.BigInteger;

public final class AddRequest implements Serializable{
    public final BigInteger number1;
    public final BigInteger number2;

    public AddRequest(BigInteger number1, BigInteger number2) {
        this.number1 = number1;
        this.number2 = number2;
    }
}
