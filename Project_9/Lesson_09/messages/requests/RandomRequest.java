package messages.requests;

public class RandomRequest extends DefaultRequest {

    private final int min;
    private final int max;

    public RandomRequest(int senderId, int min, int max) {
        super(senderId);

        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
