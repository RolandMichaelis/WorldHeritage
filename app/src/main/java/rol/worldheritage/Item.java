package rol.worldheritage;

/**
 * Created by Gourav on 10-01-2016.
 */
public class Item {

    String animalName;
    String textContent;
    int animalImage;
    int statusImage;
    int registered;
    String type;

    public Item(String animalName,String textContent,int animalImage, int statusImage, int registered, String type)
    {
        this.type=type;
        this.registered=registered;
        this.statusImage=statusImage;
        this.animalImage=animalImage;
        this.animalName=animalName;
        this.textContent=textContent;
    }
    public String getAnimalName()
    {
        return animalName;
    }
    public String getTextContent()
    {
        return textContent;
    }
    public int getAnimalImage()
    {
        return animalImage;
    }
    public int getStatusImage()
    {
        return statusImage;
    }
    public int getRegistered()
    {
        return registered;
    }
    public String getType()
    {
        return type;
    }
}
