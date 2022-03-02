package provider.handlers;

import messages.requests.RandomRequest;
import messages.responses.RandomResponse;

public class RandomRequestHandler implements IRequestHandler<RandomRequest, RandomResponse> {

    @Override
    public RandomResponse handle(RandomRequest request) {
        int min = request.getMin();
        int max = request.getMax();
        int result = (int) (Math.random() * (max - min)) + min;
        return new RandomResponse(result);
    }
}
