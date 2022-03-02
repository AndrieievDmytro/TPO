package client;

import java.math.BigInteger;
import java.rmi.Naming;

import common.Common;
import common.IAddition;
import common.IEcho;
import common.requests.AddRequest;
import common.requests.EchoRequest;
import common.responses.AddResponse;
import common.responses.EchoResponse;

public final class Client {

    public static void main(String... args) {
        try {
            IEcho echo = (IEcho) Naming.lookup(Common.ECHO_URI);
            EchoRequest echoRequest = new EchoRequest("Hello");
            EchoResponse echoResponse = echo.echo(echoRequest);
            System.out.println(echoResponse.message);

            IAddition addition = (IAddition) Naming.lookup(Common.ADD_URI);
            BigInteger number1 = new BigInteger("150");
            BigInteger number2 = new BigInteger("350");
            AddRequest addRequest = new AddRequest(number1, number2);
            AddResponse addResponse = addition.add(addRequest);
            System.out.printf("Sum: %d\n", addResponse.Sum);
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
    }
}