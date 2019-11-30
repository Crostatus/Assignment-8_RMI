//A Day object implements an ordered set of sessions.
public class Day {

    private Session[] daySchedule;
    private int sessionsPerDay;

    public Day(int sessionsPerDay, int maxParticipationsPerSession) throws IllegalArgumentException{
        if(sessionsPerDay < 1 || maxParticipationsPerSession < 1)
            throw new IllegalArgumentException("Invalid arguments: there must be at least 1 session with 1 partecipation in a Day.");
        this.daySchedule = new Session[sessionsPerDay];
        this.sessionsPerDay = sessionsPerDay;
        int i;
        for(i = 0; i < sessionsPerDay; i++)
            this.daySchedule[i] = new Session(maxParticipationsPerSession);
    }

    public Session getSession(int sessionNumber) throws IllegalArgumentException{
        int sessionIndex = sessionNumber - 1;
        if( sessionIndex < 0 || sessionIndex >= sessionsPerDay)
            throw new IllegalArgumentException("Invalid session number.");

        return daySchedule[sessionIndex];
    }
    //Register a participation, given the speaker's name, on the first free slot found in the specified day.
    //The research is made in increasing order.
    public boolean registerOnFirstFreeSession(String speaker, int day){
        int i = 0;
        int[] results = new int[2];
        while(i < sessionsPerDay && results[0] == 0) {
            results = daySchedule[i].registerParticipationOnFirstFreeSlot(speaker);
            i++;
        }
        if(results[0] == 1){
            System.out.println("[UPDATE] Speaker " + speaker + " registrato correttamente il (giorno/sessione/slot): " + day + "/" + i + "/" + results[1]);
            return true;
        }
        else{
            System.out.println("[UPDATE] Richiesta di registrazione per " + speaker + " rifiutata: giornata piena");
            return false;
        }


    }
    @Override
    public String toString(){
        String msg = "";
        int i;
        for(i = 0; i < sessionsPerDay; i++){
            msg += String.format("[Sessione %d] ", (i + 1));
            msg += daySchedule[i].toString();
        }
        return msg;
    }

}
