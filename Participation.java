import java.text.SimpleDateFormat;
import java.util.Date;
//Data type used to hold all the informations required for a participation, like the speaker name and
//the time that the request has been sent.
public class Participation {

    private static SimpleDateFormat formatter = new SimpleDateFormat("[hh:mm:ss]");
    private Date   registrationTime;
    private String Speaker;

    public Participation(String SpeakerName, Date registrationTime) throws NullPointerException {
        if(SpeakerName.equals(null))
            throw new NullPointerException("Speaker name undefined.");

        this.registrationTime = registrationTime;
        this.Speaker = SpeakerName;
    }

    public String getSpeaker(){
        return this.Speaker;
    }
}
