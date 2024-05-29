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
    private Map<String, Object> channels = new HashMap<>();

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
    public Object get(String name)  {
        Object c = channels.get(name);
        return c;
    }
}
