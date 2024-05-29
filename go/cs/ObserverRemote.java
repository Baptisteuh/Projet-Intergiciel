package go.cs;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverRemote extends Remote {
    void update() throws RemoteException;
}
