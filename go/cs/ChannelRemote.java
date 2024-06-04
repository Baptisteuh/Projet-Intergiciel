package go.cs;

import go.Direction;
import go.Observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The ChannelRemote interface extends the Remote interface and provides methods for sending and receiving values.
 * It also provides methods for observing the go.shm.channel and checking if it is waiting.
 * ChannelRemote encapsulates the remote communication with a go.shm.channel.
 *
 * @param <T> the type of values that can be sent and received on this channel
 */
public interface ChannelRemote<T> extends Remote {

    /**
     * Sends a value on go.shm.channel.
     *
     * @param v the value to send
     * @throws RemoteException if a remote communication problem occurs
     */
    void out(T v) throws RemoteException;

    /**
     * Receives a value from go.shm.channel.
     *
     * @return the received value
     * @throws RemoteException if a remote communication problem occurs
     */
    T in() throws RemoteException;

    /**
     * Returns the name of go.shm.channel.
     *
     * @return the name of go.shm.channel
     * @throws RemoteException if a remote communication problem occurs
     */
    String getName() throws RemoteException;

    /**
     * Sets an observer for go.shm.channel.
     *
     * @param direction the direction to observe
     * @param observer the observer to set
     * @throws RemoteException if a remote communication problem occurs
     */
    void observe(Direction direction, Observer observer) throws RemoteException;

    /**
     * Checks if go.shm.channel is waiting.
     *
     * @return true if go.shm.channel is waiting, false otherwise
     * @throws RemoteException if a remote communication problem occurs
     */
    boolean waiting() throws RemoteException;
}