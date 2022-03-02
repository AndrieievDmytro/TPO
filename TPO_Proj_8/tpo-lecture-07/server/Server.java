package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import common.Common;

public final class Server {

    public static void main(String... args) {
        try {
            LocateRegistry.createRegistry(Common.RMI_PORT);
            MainRemoteObject mainRO = new MainRemoteObject();
            Naming.bind(Common.ECHO_URI, mainRO);
            Naming.bind(Common.ADD_URI, mainRO);
            System.out.println("Server has started");
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
    }
}