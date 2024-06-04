package go.cs;

import go.Channel;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The ChannelList interface extends the Remote interface and provides methods for inserting a channel
 * by name and getting a channel by name.
 * ChannelList is a list of ChannelRemote objects.
 *
 */
public interface ChannelList extends Remote {
    /**
     * Inserts a channel with the given name into the list.
     *
     * @param name the name of the channel to insert
     * @throws RemoteException if a remote communication problem occurs
     */
    void insert(String name) throws RemoteException;

    /**
     * Returns the channel with the given name from the list.
     *
     * @param name the name of the channel to get
     * @return the channel with the given name
     * @throws RemoteException if a remote communication problem occurs
     */
    Object get(String name) throws RemoteException;
}