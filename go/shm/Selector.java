package go.shm;

import go.Direction;
import go.Channel;
import go.Observer;

import java.util.Map;
import java.util.Set;

public class Selector implements go.Selector, Observer {

    protected Map<Channel, Direction> channelsList;

    public Selector(Map<Channel, Direction> channels) {
        channelsList = channels;
        for (Channel c : channelsList.keySet()) {
            c.observe(channelsList.get(c), this);
        }
    }

    public Channel select() {
        for (Channel c : channelsList.keySet()) {
            if (channelsList.get(c) == Direction.In) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void update() {
    }
}
