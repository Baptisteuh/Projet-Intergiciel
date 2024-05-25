package go.cs;

import go.Direction;
import go.Channel;

import java.util.*;

public class Selector implements go.Selector {

    private final Map<Channel, Direction> channelsList = new HashMap<>();
    private final List<Channel> availableChannels = new ArrayList<>();

    public Channel select() {
        // TODO
        return null;
    }

}
