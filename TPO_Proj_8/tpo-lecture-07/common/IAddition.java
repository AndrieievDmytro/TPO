package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.requests.AddRequest;
import common.responses.AddResponse;

public interface IAddition extends Remote {
    AddResponse add(AddRequest request) throws RemoteException;
}
