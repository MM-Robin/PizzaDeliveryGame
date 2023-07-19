import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.io.FileInputStream;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @Edits by (Mainuddin Monsur Robin)
 * @version v2
 */
public class Room 
{
    String description;
    private HashMap<String, Room> directions;
    private ArrayList<Item> items;
    private static final int ItemsLimit = 5;
    private int itemsCount;
    
    private Item secretKey;
    private String imagPath;
    // private BufferedImage img;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String imagPath) 
    {
        this.directions = new HashMap<String, Room>();
        this.description = description;
        this.items = new ArrayList<Item>();
        this.itemsCount = 0;
    
        secretKey = null;
        this.imagPath = imagPath;
        // img = ImageIO.read(new FileInputStream(path));
    }

    /*<-----------------Room and Exits related methods-------------------------------------------------------------->*/
    /**
     * Method setExit
     *
     * @param direction A parameter which is the key of the hashmap
     * that describes the location of another room relative to this one.
     * @param room A parameter the room in that direction.
     */
    public void setExit(String direction, Room room)
    {
        // making sure that there is no duplicates to avoid over write of data
        if(directions.containsKey(direction))
        {
            directions.remove("direction");
            directions.put(direction,room);
        }
        else
        {
            directions.put(direction,room);
        }
    }

    /**
     * Method getExit
     *
     * @param direction A parameterwhich is the key of the HashMap
     * @return The return value is the coresponding room to vthat direction
     */
    public Room getExit(String direction)
    {
        return directions.get(direction);
    }

    /**
     * @return The Full description of the room witb its currents Exits.
     */
    public String getDescription()
    {
        return description;

    }

    /**
     * Method getDirections
     *
     * @return The return value is the possible directoin to go
     */
    public Set<String> getDirections()
    {
        return directions.keySet();
    }

    /*<-----------------Items Arraylist related methods-------------------------------------------------------------->*/
    /**
     * Method setItem
     *-- items arraylist Setter -- adds an item by just declaring ints propetises
     *-- called by the environment class --
     *
     * @param newItems the arrsylist of items to enter.
     * @return  return true if the addation is done, otherwise return false and dont add any thing
     * if it will exceed the itemlimit.
     */
    public boolean setItem(String name, String description)
    {
        // adding the new items as an array list to the main one
        if(itemsCount + 1 <= ItemsLimit)
        {
            items.add(new Item(name, description ));
            itemsCount++;
            return true; // return true if the addation is done
        }
        else
        {
            return false;   // return flase if the addation fails and its valueis the current capacity
            //return itemsCount doesnt work;
        }

    }

    /**
     * Method removeItem
     *-- will be used by the player class --
     * @param item the item to be removed.
     * @return returns true if succesfully removed or false if not.
     */
    public boolean removeItem(Item item)
    {
        // updating the items count if sucessfull -- consistancy
        if(items.remove(item))
        {
            itemsCount--;
            return true; //true if succesfully removed
        }
        else
        {
            return false;  //false if failed
        }
    }

    /**
     * Method addItem
     *-- will be called by the player class -- adds specific item to the room items regarfing the maximun condition
     * @param item the item to be add.
     * @return returns true if succesfully added or false if not
     */
    public boolean addItem(Item item)
    {
        if(itemsCount + 1 < ItemsLimit)
        {
            items.add(item);
            // updating the items count if sucessfull -- consistancy
            itemsCount++;
            return true; //true if succesfully removed
        }
        else{
            return false; //false if failed
        }
    }

    /**
     * Method printItemList
     * --Will be called by game class
     *
     * prints the itemslist to the user with its description
     */
    public void printItemList()
    {

        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        System.out.printf("| %-10s | %-50s | %-6s | %-13s | %-13s | %-13s |%n", "Name", "Description", "Weight", "Attack Points", "Defense Points", "Health Points");
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");

        for (Item item : items)
        {
            System.out.printf("| %-10s | %-50s | %-6d | %-13d | %-14d | %-13d |%n", item.getName(), item.getDescription()/*, item.getWeight() item.getAttackPoints(), item.getDefensePoints(), item.getHealthPoints()*/);

        }
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        System.out.printf("| %44s = %d                                                                          |%n","itemsCouts", itemsCount);
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
    }
    
    /**
    *items arraylist getter
    *
    * @return   items arraylist
    */
    public ArrayList<Item> getItems()
    {
        // returns the items arraylist
        return items;
    }
    
 
    //Immage Getter 
    public String getImagePath()
    {
        return imagPath;
    }
}
