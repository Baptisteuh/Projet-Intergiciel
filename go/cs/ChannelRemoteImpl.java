package go.cs;

import go.Direction;
import go.Observer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChannelRemoteImpl<T> extends UnicastRemoteObject implements ChannelRemote<T>{
    private final go.shm.Channel<T> channel;
    public ChannelRemoteImpl(String name) throws RemoteException {
        super();
        channel = new go.shm.Channel<>(name);
    }

    public void out(T v) {
        channel.out(v);
    }

    public T in() {
        return channel.in();
    }

    public String getName() {
        return channel.getName();
    }

    public void observe(Direction direction, Observer observer) {
        channel.observe(direction, observer);
    }

    public boolean waiting() {
        return channel.waiting();
    }
}
