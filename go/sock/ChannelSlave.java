package go.sock;

import go.Channel;
import go.Direction;
import go.Observer;

public class ChannelSlave<T> implements Channel {

    public ChannelSlave() {

    }

    @Override
    public void out(Object v) {

    }

    @Override
    public Object in() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void observe(Direction direction, Observer observer) {

    }
}
