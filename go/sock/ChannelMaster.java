package go.sock;

import go.Channel;
import go.Direction;
import go.Observer;

public class ChannelMaster<T> implements Channel<T> {

    private final go.shm.Channel<T> channel;

    public ChannelMaster(String name) {
        channel = new go.shm.Channel<>(name);
    }

    @Override
    public void out(T v) {
        channel.out(v);
    }

    @Override
    public T in() {
        return channel.in();
    }

    @Override
    public String getName() {
        return channel.getName();
    }

    @Override
    public void observe(Direction direction, Observer observer) {

    }
}
