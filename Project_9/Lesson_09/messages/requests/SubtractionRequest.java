package messages.requests;

public class SubtractionRequest extends DefaultRequest {

    private final double number1;
    private final double number2;

    public SubtractionRequest(int senderId, double number1, double number2) {
        super(senderId);

        this.number1 = number1;
        this.number2 = number2;
    }

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }
}
