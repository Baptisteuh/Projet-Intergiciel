package go.cs;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

/**
 * Implantation d'un serveur hébergeant des canaux.
 *
 */
public class ServerImpl {

    public static void main(String[] args) throws Exception {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (ExportException var2) {
            System.out.println("A registry is already running, proceeding...");
        }

        ChannelListImpl var1 = new ChannelListImpl();
        Naming.rebind("rmi://localhost:1099/ChannelList", var1);
        System.out.println("Le systeme est pret.");
        // Créer l'objet distant en utilisant ChannelRemoteImpl
//        ChannelRemote channelRemote = new ChannelRemoteImpl(channel);

        // Enregistrer l'objet avec un nom dans le registre
//        Naming.rebind("rmi://localhost/ChannelRemote", channelRemote);
    }

}
