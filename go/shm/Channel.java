package go.shm;

import go.Direction;
import go.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Channel<T> implements go.Channel<T> {

    private String channelName;

    private Semaphore verrou;
    private Semaphore blockOut;

    private List<Observer> observers;
    private Direction direction;

    private T data;

    public Channel(String name) {
        channelName = name;
        verrou = new Semaphore(0, true);
        blockOut = new Semaphore(1, true);
        observers = new ArrayList<>();
    }

    public T in() {
        try {
            verrou.acquire();
            blockOut.release();
            if (direction == Direction.In) updateObservers();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void out(T v) {
        try {
            blockOut.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        data = v;
        verrou.release();
        if (direction == Direction.Out) updateObservers();
    }

    public String getName() {
        return channelName;
    }

    public void observe(Direction dir, Observer observer) {
        direction = dir;
        observers.add(observer);
    }

    public void updateObservers() {
        for (Observer obs : observers) {
            // obs.update();
            ((Selector) obs).channelsList.put(this, Direction.inverse(direction));
        }
        observers.clear();
    }
}
