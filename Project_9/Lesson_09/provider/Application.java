package provider;

import common.Common;
import common.exceptions.CustomException;
import messages.requests.AdditionRequest;
import messages.requests.DivisionRequest;
import messages.requests.MultiplicationRequest;
import messages.requests.RandomRequest;
import messages.requests.SubtractionRequest;
import provider.handlers.AdditionRequestHandler;
import provider.handlers.DivisionRequestHandler;
import provider.handlers.HandlerProvider;
import provider.handlers.MultiplicationRequestHandler;
import provider.handlers.RandomRequestHandler;
import provider.handlers.SubtractionRequestHandler;

public class Application {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %5$s %n");
        try {
            HandlerProvider.bind(RandomRequest.class, RandomRequestHandler.class);
            HandlerProvider.bind(AdditionRequest.class, AdditionRequestHandler.class);
            HandlerProvider.bind(SubtractionRequest.class, SubtractionRequestHandler.class);
            HandlerProvider.bind(MultiplicationRequest.class, MultiplicationRequestHandler.class);
            HandlerProvider.bind(DivisionRequest.class, DivisionRequestHandler.class);

            for (int i = 0; i < Common.PROVIDER_POOL_SIZE; i++) {
                new Thread(new DefaultProvider()).start();
            }
        } catch (CustomException e) {
            e.printStackTrace();
        }

    }
}