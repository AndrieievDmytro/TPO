package messages.requests;

public class DivisionRequest extends DefaultRequest {

    private final double numerator;
    private final double denominator;

    public DivisionRequest(int senderId, double numerator, double denominator) {
        super(senderId);

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double getNumerator() {
        return numerator;
    }

    public double getDenominator() {
        return denominator;
    }
}
