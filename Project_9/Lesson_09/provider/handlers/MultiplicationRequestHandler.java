package provider.handlers;

import messages.requests.MultiplicationRequest;
import messages.responses.ArithmeticResponse;

public class MultiplicationRequestHandler implements IRequestHandler<MultiplicationRequest, ArithmeticResponse> {

    @Override
    public ArithmeticResponse handle(MultiplicationRequest request) {
        return new ArithmeticResponse(request.getNumber1() * request.getNumber2());
    }
}
