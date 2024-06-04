package go.cs;

import go.Channel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelListImpl extends UnicastRemoteObject implements ChannelList {
    //TODO: Change Object type
    private Map<String, ChannelRemote> channels = new HashMap<>();

    public ChannelListImpl() throws RemoteException {
    }

    @Override
    public void insert(String name)  {
        if (!channels.containsKey(name)) {
            ChannelRemoteImpl<?> c = null;
            try {
                c = new ChannelRemoteImpl<>(name);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            channels.put(name, c);
        }
    }

    @Override
    public ChannelRemote get(String name)  {
        return channels.get(name);
    }
}
