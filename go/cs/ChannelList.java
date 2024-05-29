package go.cs;

import go.Channel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChannelList extends Remote {
    void insert(String name) throws RemoteException;

    Object get(String name) throws RemoteException;;
}
