//A Congress object implements an ordered set of days.
public class Congress {

    private Day[] congressSchedule;
    private int   congressLength;
    private int   sessionsPerDay;
    private int   maxParticipationsPerSession;

    public Congress(int congressDaysLength, int sessionsPerDay, int maxParticipationsPerSession) throws IllegalArgumentException{
        if(congressDaysLength < 1 || sessionsPerDay < 1 || maxParticipationsPerSession < 1)
            throw new IllegalArgumentException("Invalid arguments: they must all be > 1");

        this.congressLength = congressDaysLength;
        this.sessionsPerDay = sessionsPerDay;
        this.maxParticipationsPerSession = maxParticipationsPerSession;
        this.congressSchedule = new Day[congressDaysLength];

        int i;
        for(i = 0; i < congressDaysLength; i ++){
            congressSchedule[i] = new Day(sessionsPerDay, maxParticipationsPerSession);
        }
        System.out.print("---- Congress instance created ----\nDays: " + congressDaysLength + "\nSessions per day: " + sessionsPerDay + "\nParticipations slot per session: " + maxParticipationsPerSession);
        System.out.println("\n-----------------------------------");
    }
    //Request to register a speaker in the specified day, session and slot number.
    public boolean registerSpeaker(String speakerName, int day, int sessionNumber, int participationNumber) throws IllegalArgumentException{
        if(day < 1 || day > congressLength)
            throw new IllegalArgumentException("Invalid day.");
        if(sessionNumber < 1 || sessionNumber > sessionsPerDay)
            throw new IllegalArgumentException("Invalid session number.");
        if(participationNumber < 1 || participationNumber > maxParticipationsPerSession)
            throw new IllegalArgumentException("Invalid participation slot.");

        int dayIndex = day - 1;
        Session registerSessionRequest = congressSchedule[dayIndex].getSession(sessionNumber);
        boolean success = registerSessionRequest.registerParticipation(speakerName, participationNumber);
        if(success){
            System.out.println("[UPDATE] Speaker " + speakerName + " registrato correttamente il (giorno/sessione/slot): " + day + "/" + sessionNumber + "/" + participationNumber);
        }
        else{
            System.out.println("[UPDATE] Richiesta di registrazione per " + speakerName + " rifiutata: slot occupato.");
        }
        return success;
    }
    //Request to register a speaker in a specified day and session, without any slot number preference.
    public boolean registerSpeaker(String speakerName, int day, int sessionNumber) throws IllegalArgumentException{
        if(day < 1 || day > congressLength)
            throw new IllegalArgumentException("Invalid day.");
        if(sessionNumber < 1 || sessionNumber > sessionsPerDay)
            throw new IllegalArgumentException("Invalid session number.");

        int dayIndex = day - 1;
        Session registerSessionRequest = congressSchedule[dayIndex].getSession(sessionNumber);
        int[] results = registerSessionRequest.registerParticipationOnFirstFreeSlot(speakerName);
        if(results[0] == 1){
            System.out.println("[UPDATE] Speaker " + speakerName + " registrato correttamente il (giorno/sessione/slot): " + day + "/" + sessionNumber + "/" + results[1]);
            return true;
        }
        else{
            System.out.println("[UPDATE] Richiesta di registrazione per " + speakerName + " rifiutata: sessione piena.");
            return false;
        }

    }
    //Request to register a speaker in a specified day, without any session (and slot) preference.
    public boolean registerSpeaker(String speakerName, int day) throws IllegalArgumentException{
        if(day < 1 || day > congressLength)
            throw new IllegalArgumentException("Invalid day.");

        int dayIndex = day - 1;
        Day tryRegisterDay = congressSchedule[dayIndex];

        return tryRegisterDay.registerOnFirstFreeSession(speakerName, day);
    }
    @Override
    public String toString() {
        String msg = "                 --- PROGRAMMA DEL CONGRESSO ---          SV = Slot Vuoto\n";
        int i;
        for(i = 0; i < congressLength; i++){
            msg += String.format("                        Programma del giorno %d\n", (i+1));
            msg += congressSchedule[i].toString();
            msg += "-------------------------------------------------------------------------\n";
        }

        return msg;
    }

}
