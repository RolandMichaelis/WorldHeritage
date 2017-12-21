package rol.worldheritage;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root
public class Heritage {

    @Attribute
    private int id;

    @Attribute
    private String name;

    @Attribute
    private int registered;

    @Attribute
    private String type;

    @Attribute
    private String country;

    @Attribute
    private String search;

    @Attribute
    private String text;

    @Attribute
    private String navi;
    @Attribute
    private String wiki;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRegistered() {
        return registered;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public String getSearch() {
        return search;
    }

    public String getText() {
        return text;
    }

    public String getNavi() {
        return navi;
    }
    public String getWiki() {
        return wiki;
    }

}
