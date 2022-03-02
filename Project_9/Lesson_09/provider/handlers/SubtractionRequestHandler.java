package provider.handlers;

import messages.requests.SubtractionRequest;
import messages.responses.ArithmeticResponse;

public class SubtractionRequestHandler implements IRequestHandler<SubtractionRequest, ArithmeticResponse> {

    @Override
    public ArithmeticResponse handle(SubtractionRequest request) {
        return new ArithmeticResponse(request.getNumber1() - request.getNumber2());
    }
}
