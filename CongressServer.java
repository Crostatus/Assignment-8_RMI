import java.net.BindException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CongressServer {

    public static void main(String[] args) throws RemoteException {
        //main argument : (int) port => the port to locate the RMI registry
        int port = 9811;
        try{
            port = Integer.valueOf(args[0]);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("usage: java CongressServer port");
            System.exit(1);
        }
        CongressServiceImpl congressService = new CongressServiceImpl();
        CongressService stub = (CongressService) UnicastRemoteObject.exportObject(congressService, 0);

        try {
            LocateRegistry.createRegistry(port);
            Registry r = LocateRegistry.getRegistry(port);
            r.rebind("CONGRESS-SERVER", stub);
        }
        catch (UnknownHostException e){
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Server pronto.");

    }
}
