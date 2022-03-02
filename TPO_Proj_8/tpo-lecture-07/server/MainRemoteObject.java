package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IAddition;
import common.IEcho;
import common.requests.AddRequest;
import common.requests.EchoRequest;
import common.responses.AddResponse;
import common.responses.EchoResponse;

public final class MainRemoteObject extends UnicastRemoteObject implements IAddition, IEcho {

    protected MainRemoteObject() throws RemoteException {
        super();
    }

    @Override
    public EchoResponse echo(EchoRequest request) throws RemoteException {
        return new EchoResponse(request.message);
    }

    @Override
    public AddResponse add(AddRequest request) throws RemoteException {
        return new AddResponse(request.number1, request.number2);
    }
}
