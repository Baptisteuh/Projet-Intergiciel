package go.cs;

import go.Direction;
import go.shm.Channel;

import java.net.MalformedURLException;
import java.rmi.Naming;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Factory implements go.Factory {

    /** Création ou accès à un canal existant.
     * Côté serveur, le canal est créé au premier appel avec un nom donné ;
     * les appels suivants avec le même nom donneront accès au même canal.
     */
    private ChannelList channels;

    public Factory() {
        try {
            channels = (ChannelList)Naming.lookup("rmi://localhost:1099/ChannelList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> go.Channel<T> newChannel(String name) {
        // TODO
        try {
            channels.insert(name);
            System.out. println("name");
            go.cs.Channel<T> c = new go.cs.Channel<T>((ChannelRemote) channels.get(name));
            return c;
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /** Spécifie quels sont les canaux écoutés et la direction pour chacun. */
    public go.Selector newSelector(Map<go.Channel, Direction> channels) {
        // TODO
        return null;
    }

    /** Spécifie quels sont les canaux écoutés et la même direction pour tous. */
    public go.Selector newSelector(Set<go.Channel> channels, Direction direction) {
        return newSelector(channels
                           .stream() 
                           .collect(Collectors.toMap(Function.identity(), e -> direction)));
    }

}

