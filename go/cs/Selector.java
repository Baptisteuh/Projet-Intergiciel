package go.cs;

import go.Direction;
import go.Channel;
import go.shm.ObserverImpl;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Selector implements go.Selector {

    private final Semaphore semaphore = new Semaphore(0, true);

    private final Map<Channel, Direction> channelsList = new HashMap<>();
    private final List<Channel> availableChannels = new ArrayList<>();
    private final Map<Channel, Observer> observers = new HashMap<>();

    public Selector(Map<Channel, Direction> channels) {

        for (Channel chan : channels.keySet()) {
            go.cs.Channel c = (go.cs.Channel) chan;

            channelsList.put(chan, channels.get(chan));
            ObserverRemote remoteObserver;
            try {
                remoteObserver = new ObserverRemoteImpl(chan, availableChannels, semaphore);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            observers.put(c, new Observer(remoteObserver));

            c.observe(Direction.inverse(channels.get(c)), observers.get(c));

            if (c.waiting()) {
                observers.get(c).update();
            }
        }
    }

    public Channel select() {
        /*
        // Affichage
        System.out.println("============");
        for (Channel chan : availableChannels) {
            System.out.println(chan.getName());
        }
         */

        // Traitement
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Channel chan = availableChannels.get(0);
        availableChannels.remove(0);
        chan.observe(Direction.inverse(channelsList.get(chan)), observers.get(chan));

        return chan;
    }

}
