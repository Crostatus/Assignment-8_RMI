import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;


public class CongressClient {

    public static void main(String[] args) throws Exception {
        //main argument : (int) port => the port to locate the RMI registry
        int port = 9811;
        try{
            port = Integer.valueOf(args[0]);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("usage: java CongressClient port");
            System.exit(1);
        }

        CongressService serverObj = null;
        Remote remoteObj;
        try {
            Registry r = LocateRegistry.getRegistry(port);
            remoteObj = r.lookup("CONGRESS-SERVER");
            serverObj = (CongressService) remoteObj;
        }
        catch (UnknownHostException e){
            e.printStackTrace();
            System.exit(1);
        }
        catch (RemoteException e){
            System.out.println("Connection failed.");
            System.exit(1);
        }
        int i;
        int day;
        int session;
        int participationSlot;
        int speakerId = 1000;
        Random randomGen = new Random();
        for(i = 0; i < 40; i++){
            day = randomGen.nextInt(3) + 1;
            session = randomGen.nextInt(12) + 1;
            participationSlot = randomGen.nextInt(5) + 1;
            serverObj.registerSpeaker("Speaker " + speakerId, day, session, participationSlot);
            speakerId++;
        }
        for(i = 0; i < 40; i++){
            day = randomGen.nextInt(3) + 1;
            session = randomGen.nextInt(12) + 1;
            serverObj.registerSpeakerOnAnyParticipationSlot("Speaker " + speakerId, day, session);
            speakerId++;
        }
        for(i = 0; i < 40; i++){
            day = randomGen.nextInt(3) + 1;
            serverObj.registerSpeakerOnAnySession("Speaker " + speakerId, day);
            speakerId++;
        }
        String congressSchedule = serverObj.getSchedule();
        System.out.println(congressSchedule);
    }

}
