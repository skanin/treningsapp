package relasjoner;

public class ØvelseITreningsøkt {
    Integer treningsøktID = null;
    Integer øvelseID = null;
    String prestasjon = null; // can be NULL

    public ØvelseITreningsøkt(Integer treningsøktID, Integer øvelseID) {
        this.treningsøktID = treningsøktID;
        this.øvelseID = øvelseID;
    }
}
