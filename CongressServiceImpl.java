import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;

public class CongressServiceImpl extends RemoteServer implements CongressService {

    Congress congressObj;

    public CongressServiceImpl () throws RemoteException {
        congressObj = new Congress(3, 12, 5);
    }

    @Override
    public boolean registerSpeaker(String SpeakerName, int day, int sessionNumber, int participationSlot) throws RemoteException {
        return congressObj.registerSpeaker(SpeakerName, day, sessionNumber, participationSlot);
    }

    @Override
    public boolean registerSpeakerOnAnyParticipationSlot(String SpeakerName, int day, int sessionNumber) throws RemoteException {
        return congressObj.registerSpeaker(SpeakerName, day, sessionNumber);
    }

    @Override
    public boolean registerSpeakerOnAnySession(String SpeakerName, int day) throws RemoteException {
        return congressObj.registerSpeaker(SpeakerName, day);
    }

    public String getSchedule() {
        return congressObj.toString();
    }

}