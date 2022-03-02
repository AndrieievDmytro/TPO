package provider.handlers;

import messages.requests.DivisionRequest;
import messages.responses.ArithmeticResponse;

public class DivisionRequestHandler implements IRequestHandler<DivisionRequest, ArithmeticResponse> {

    @Override
    public ArithmeticResponse handle(DivisionRequest request) {
        if(request.getDenominator() == 0) {
            return new ArithmeticResponse(Double.NaN);
        }

        return new ArithmeticResponse(request.getNumerator() / request.getDenominator());
    }
}
