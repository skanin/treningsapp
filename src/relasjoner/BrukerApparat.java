package relasjoner;

import classes.ActiveDomainObject;

import java.sql.Connection;

public class BrukerApparat extends ActiveDomainObject {
    Integer apparatID = null;
    Integer ovelseID = null;
    Integer kilo = null;
    Integer sett = null;

    public BrukerApparat(Integer apparatID, Integer ovelseID, Integer kilo, Integer sett) {
        /*this.apparatID = apparatID;
        this.ovelseID = ovelseID;
        this.kilo = kilo;
        this.sett = sett;*/
    }
    /*Only need 1 ID for it to work*/
    public BrukerApparat(Integer apparatID, Integer ovelseID){

    }

    @Override
    public void initialize(Connection conn) {

    }

    @Override
    public void refresh(Connection conn) {

    }

    @Override
    public void save(Connection conn) {

    }
}
