package provider.handlers;

import messages.requests.AdditionRequest;
import messages.responses.ArithmeticResponse;

public class AdditionRequestHandler implements IRequestHandler<AdditionRequest, ArithmeticResponse> {

    @Override
    public ArithmeticResponse handle(AdditionRequest request) {
        return new ArithmeticResponse(request.getNumber1() + request.getNumber2());
    }
}
