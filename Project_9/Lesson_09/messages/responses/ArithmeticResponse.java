package messages.responses;

import java.io.Serializable;

public class ArithmeticResponse implements Serializable {
    
    private double result;

    public ArithmeticResponse() {
    }

    public ArithmeticResponse(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }
}
