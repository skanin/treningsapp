package classes;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
public class Øvelsegruppe   extends ActiveDomainObject{
    Integer id = null;
    String kategori = null;
    List<Øvelse> øvelser = new ArrayList<Øvelse>();  //(0, N)

    public Øvelsegruppe(Integer id, String kategori, List<Øvelse> øvelser) {
        this.id = id;
        this.kategori = kategori;
        this.øvelser = øvelser;
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
