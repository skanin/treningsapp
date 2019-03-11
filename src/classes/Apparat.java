package classes;

import java.sql.Connection;

public class Apparat extends ActiveDomainObject {
    Integer id = null;
    String navn = null;
    String beskrivelse = null;


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
