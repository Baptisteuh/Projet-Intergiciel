package go.sock;

import go.Channel;
import go.Direction;
import go.Selector;

import java.util.Map;
import java.util.Set;

public class Factory implements go.Factory {

    @Override
    public <T> Channel<T> newChannel(String name) {
        if (name.equals("existant")) {
            return new ChannelMaster<>(name);
        }
        return new ChannelSlave<>();
    }

    @Override
    public Selector newSelector(Map<Channel, Direction> channels) {
        return null;
    }

    @Override
    public Selector newSelector(Set<Channel> channels, Direction direction) {
        return null;
    }
}
