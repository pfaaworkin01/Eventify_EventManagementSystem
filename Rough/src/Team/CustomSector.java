package Team;

/*import java.io.Serializable;*/
import java.util.ArrayList;

public class CustomSector extends Sector implements Serializable {
    private static final long serialVersionUID = 1L;
    public CustomSector(String sectorName) {
        super(sectorName, new ArrayList<>());
    }
}
