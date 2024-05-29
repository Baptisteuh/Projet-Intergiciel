package go.cs;

import go.Direction;
import go.Channel;
import go.Observer;

import java.rmi.RemoteException;
import java.util.*;

public class Selector implements go.Selector {

    private final Map<Channel, Direction> channelsList = new HashMap<>();
    private final List<Channel> availableChannels = new ArrayList<>();

    public Selector(Map<Channel, Direction> channels)  {
        for (Channel chan : channels.keySet()) {
            channelsList.put(chan, channels.get(chan));
            ObserverRemote observerRemote = null;
            try {
                observerRemote = new ObserverRemoteImpl();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            chan.observe(Direction.inverse(channels.get(chan)), new go.cs.Observer(observerRemote));
        }
    }

    public Channel select() {
        // TODO
        return null;
    }

}
