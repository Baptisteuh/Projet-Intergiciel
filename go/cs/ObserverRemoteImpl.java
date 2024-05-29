package go.cs;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObserverRemoteImpl extends UnicastRemoteObject implements ObserverRemote{


    public ObserverRemoteImpl() throws RemoteException {
    }

    @Override
    public void update() throws RemoteException {
        System.out.println("ObserverImpl.update");
    }
}
