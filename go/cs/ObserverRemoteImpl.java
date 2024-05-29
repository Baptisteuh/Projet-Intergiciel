package go.cs;

import go.Channel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ObserverRemoteImpl extends UnicastRemoteObject implements ObserverRemote{


    go.Observer observer;
    public ObserverRemoteImpl(go.Channel channel, List<Channel> channels, Semaphore semaphore) throws RemoteException {
        observer = new go.shm.ObserverImpl(channel, channels, semaphore);
    }

    @Override
    public void update() throws RemoteException {
        System.out.println("ObserverImpl.update");
        observer.update();
    }
}
