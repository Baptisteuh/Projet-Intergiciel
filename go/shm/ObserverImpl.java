package go.shm;

import go.Observer;

import java.util.List;
import java.util.concurrent.Semaphore;

public class ObserverImpl implements Observer {

    go.Channel chan;
    List<go.Channel> channelList;
    Semaphore sem;

    public ObserverImpl(go.Channel channel, List<go.Channel> channels, Semaphore semaphore) {
        chan = channel;
        channelList = channels;
        sem = semaphore;
    }

    @Override
    public void update() {
        // System.out.println("Ajout du chan " + chan.getName());
        channelList.add(chan);
        sem.release();
    }
}
