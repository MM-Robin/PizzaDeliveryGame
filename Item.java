/**
 * this class is to store the item propertises found in the game and provide
 * them when needed.
 * it has five instance varibles the item weight , the description, attackpoints, healthPoints and defense points.
 * @author (Mainuddin Monsur Robin)
 * @version (1.0)
 */
public class Item
{
    // instance variables
    private String name;
    private String description;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;

    }
    
    public String getName()
        {
            // returning description
            return this.name;
        }
        

    
    /**
     * Method getDescription
     * --Description getter--
     * @return The return the description value
     */
    public String getDescription()
    {
        // returning description
        return this.description;
    }

}
