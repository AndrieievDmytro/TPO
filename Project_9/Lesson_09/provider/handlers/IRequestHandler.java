package provider.handlers;

import java.io.Serializable;

import messages.requests.DefaultRequest;

public interface IRequestHandler<TRequest extends DefaultRequest, TResponse extends Serializable> {
    TResponse handle(TRequest request);
}
