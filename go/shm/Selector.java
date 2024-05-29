package go.shm;

import go.Direction;
import go.Channel;
import go.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Selector implements go.Selector {

    private final Semaphore semaphore = new Semaphore(0, true);

    private final Map<Channel, Direction> channelsList = new HashMap<>();
    private final List<Channel> availableChannels = new ArrayList<>();

    public Selector(Map<Channel, Direction> channels) {

        for (Channel chan : channels.keySet()) {
            go.shm.Channel c = (go.shm.Channel) chan;

            if (c.waiting()) {
                availableChannels.add(chan);
                semaphore.release();
            }

            channelsList.put(chan, channels.get(chan));
            chan.observe(Direction.inverse(channels.get(chan)), new Observer() {
                @Override
                public void update() {
                    // System.out.println("Ajout du chan " + chan.getName());
                    availableChannels.add(chan);
                    semaphore.release();
                }
            });
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
        chan.observe(Direction.inverse(channelsList.get(chan)), new Observer() {
            @Override
            public void update() {
                availableChannels.add(chan);
                semaphore.release();
            }
        });
        return chan;
    }
}
