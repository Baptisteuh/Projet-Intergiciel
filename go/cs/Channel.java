package go.cs;

import go.Direction;
import go.Observer;

public class Channel<T> implements go.Channel<T> {

    private String channelName;

    public Channel(String name) {
        channelName = name;
    }

    public void out(T v) {
        // TODO
    }
    
    public T in() {
        // TODO
        return null;
    }

    public String getName() {
        return channelName;
    }

    public void observe(Direction direction, Observer observer) {
        // TODO
    }
}
