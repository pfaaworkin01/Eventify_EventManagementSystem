package Team;
import java.io.Serializable;
import java.util.ArrayList;

public class CreativeSector extends Sector implements Serializable {
    private static final long serialVersionUID = 1L;
    public CreativeSector() {
        super("Creative", new ArrayList<>());
    }
}
