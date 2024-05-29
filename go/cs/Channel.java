package go.cs;

import go.Direction;
import go.Observer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Channel<T> implements go.Channel<T> {

    private ChannelRemote<T> channel;

    public Channel(ChannelRemote channelRemote) {
        channel = channelRemote;
    }

    public void out(T v) {
        try {
            channel.out(v);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    
    public T in() {
        try {
            return channel.in();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        try {
            return channel.getName();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void observe(Direction direction, Observer observer) {
        try {
            channel.observe(direction, observer);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean waiting() {
        try {
            return channel.waiting();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
