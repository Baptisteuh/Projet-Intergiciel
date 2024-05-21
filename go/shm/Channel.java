package go.shm;

import go.Direction;
import go.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Channel<T> implements go.Channel<T> {

    private String channelName;

    public Semaphore verrou;
    public Semaphore blockOut;

    private T data;

    private List<Observer> inObservers;
    private List<Observer> outObservers;

    public Channel(String name) {
        channelName = name;
        verrou = new Semaphore(0, true);
        blockOut = new Semaphore(1, true);
        inObservers = new ArrayList<>();
        outObservers = new ArrayList<>();
    }

    public T in() {
        try {
            notifyObservers(Direction.In);
            verrou.acquire();
            blockOut.release();
            T d = data;
            data = null;
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void out(T v) {
        try {
            notifyObservers(Direction.Out);
            blockOut.acquire();
            data = v;
            verrou.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return channelName;
    }

    public void observe(Direction dir, Observer observer) {
        if (dir == Direction.In) {
            if (!inObservers.contains(observer)) inObservers.add(observer);
        } else {
            if (!outObservers.contains(observer)) outObservers.add(observer);
        }
    }

    private void notifyObservers(Direction dir) {
        List<Observer> observersToNotify = (dir == Direction.In) ? inObservers : outObservers;
        for (Observer observer : observersToNotify) {
            observer.update();
        }
        observersToNotify.clear();
    }
}
