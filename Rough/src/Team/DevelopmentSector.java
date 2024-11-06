package Team;

import java.io.Serializable;
import java.util.ArrayList;

public class DevelopmentSector extends Sector implements Serializable {
    private static final long serialVersionUID = 1L;
public DevelopmentSector(){
    super("Development", new ArrayList<>());
}
}
