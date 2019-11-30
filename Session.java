import java.text.SimpleDateFormat;
import java.util.Date;
//A Session object implements an ordered set of participations.
public class Session {

    private static SimpleDateFormat formatter = new SimpleDateFormat("[hh:mm:ss]");
    private Participation[] sessionSchedule;
    private int maxParticipations;
    private Date lastUpdate;

    public Session(int maxParticipations) throws IllegalArgumentException{
        if(maxParticipations < 1)
            throw new IllegalArgumentException("At least 1 partecipation per session required.");

        this.sessionSchedule = new Participation[maxParticipations];
        this.maxParticipations = maxParticipations;
    }

    //Register a participation, given the speaker's name and his requested slot.
    public boolean registerParticipation(String speakerName, int partecipationNumber) throws NullPointerException, IllegalArgumentException{
        if(speakerName.equals(null))
            throw new NullPointerException("Speaker name undefined.");

        int participationIndex = partecipationNumber - 1;
        if(participationIndex < 0 || participationIndex >= maxParticipations)
            throw new IllegalArgumentException("Invalid partecipation number.");

        if(!(sessionSchedule[participationIndex] == null))
            return false;

        this.lastUpdate = new Date();
        sessionSchedule[participationIndex] = new Participation(speakerName, lastUpdate);
        return true;
    }

    //Register a participation, given the speaker's name, on the first free slot found in increasing order.
    public int[] registerParticipationOnFirstFreeSlot(String speakerName){
        int i = 1;
        int[] results = new int[2];
        boolean slotFound = false;
        while(i <= maxParticipations && !slotFound){
            slotFound = registerParticipation(speakerName, i);
            i++;
        }
        if(slotFound) {
            results[0] = 1;
            results[1] = i - 1;
            return results;
        }
        else{
            results[0] = 0;
            results[1] = -1;
            return results;
        }
    }
    @Override
    public String toString(){
        String msg = "|";
        int i;
        for(i = 0; i < maxParticipations; i++) {
            try {
                msg += String.format("\"%s\" ", sessionSchedule[i].getSpeaker());
                if(i != maxParticipations - 1)
                    msg += " ---- ";
            } catch (NullPointerException e) {
                msg += " SV ";
                if(i != maxParticipations - 1)
                    msg += " ---- ";
            }
        }
        if(lastUpdate == null)
            msg += "|\n";
        else
            msg += String.format("|          (ultimo aggiornamento: %s)\n", formatter.format(lastUpdate));

        return msg;
    }

}
