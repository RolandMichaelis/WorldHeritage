package rol.worldheritage;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class heritagePack {
    @Attribute
    private String name;
    @ElementList
    private List<Heritage> heritages;

    public List<Heritage> getHeritages() {
        return heritages;
    }
}