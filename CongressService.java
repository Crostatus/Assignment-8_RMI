import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CongressService extends Remote {
    //Request to register a speaker in the specified day, session and slot number.
    boolean registerSpeaker(String SpeakerName, int day, int sessionNumber, int participationSlot) throws RemoteException;
    //Request to register a speaker in a specified day and session, without any slot number preference.
    boolean registerSpeakerOnAnyParticipationSlot(String SpeakerName, int day, int sessionNumber) throws RemoteException;
    //Request to register a speaker in a specified day, without any session (and slot) preference.
    boolean registerSpeakerOnAnySession(String SpeakerName, int day) throws RemoteException;
    //Get the entire congress schedule. The notation "SV" means "Empty slot".
    String getSchedule() throws RemoteException;
}
