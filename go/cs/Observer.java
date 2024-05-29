package go.cs;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Observer implements go.Observer {

    private ObserverRemote observerRemote;
    public Observer(ObserverRemote observerRemote) {
            this.observerRemote = observerRemote;
    }

    @Override
    public void update() {
        System.out.println("Observer.update");
        try {
            observerRemote.update();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
