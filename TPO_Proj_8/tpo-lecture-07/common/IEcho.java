package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.requests.EchoRequest;
import common.responses.EchoResponse;

public interface IEcho extends Remote {
    EchoResponse echo(EchoRequest request) throws RemoteException;
}