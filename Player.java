import java.util.ArrayList;

/**
 * Write a description of class Player here.
 *
 * @author (Mainuddin Monsur Robin)
 * @version (1.0)
 */


public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private int lives;
    private int carriedWeight;
    private Room currentLocation;
    private static final int CarryWeightLimit = 30;
    private ArrayList<Item> inventory;
    private Room previousLocation;    

    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        // initialise instance variables
        this.carriedWeight = 0;
        this.name = name;
        this.lives = 3;
        this.inventory = new ArrayList<Item>();
        this.previousLocation = null;
    }
    

    /**
     * Method putItem
     * gives back an item from player items list to the room's list
     * @param item the item to be left
     * @return return true if it's left in the room successfully, and false if not, due to the room max limit
     */
    public boolean putItem(Item item)
    {
        //adding the item to the room and checking its condition
        if(this.currentLocation.addItem(item))
        {
            //removing it from the inventory and updating the status values.
            this.inventory.remove(item);
            return true;
        }
        return false;
    }

    
    /**
     * Method takeItem
     *-- adds specific item from the room to the player inventory
     * @param item the item to be taken
     * @return The return value is true if it doesn't exceed the weight limit, and false if it exceeded it
     */
    public boolean takeItem(Item item)
    {
        if(this.carriedWeight  <= CarryWeightLimit)
        {
            //removing the item from the room and checking its condition
            if(this.currentLocation.removeItem(item))
            {
            //adding it to the inventory and updating the status values.
                this.inventory.add(item);
                return true;
            }
        }
        return false;
    }


    /**
     * Method status
     *
     * @return The return value is a string includs all the player status
     */
    /*
    public String status()
    {
        return "your current Status is the following: -" + "\n" + "number of lives: " + lives + ", health Points: " + healthPoints + ", Attack Points: " + attackPoints + 
        "\n" + "Defense Points: " + defensePoints;  
    }
    */
    /**
     * Method printInventory
     * --Will be called by game class
     *
     * prints the itemslist to the user with its description
     */
    public void printInventory()
    {

        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        System.out.printf("| %-10s | %-50s | %-6s | %-13s | %-14s | %-13s |%n", "Name", "Description", "Weight", "Attack Points", "Defense Points", "Health Points");
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        
        for (Item item : inventory)
        {
            System.out.printf("| %-10s |%n", item.getName());

        }
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        System.out.printf("| %40s = %d/30                                                                           |%n","carriedWeight", carriedWeight);
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
    }
    
    /*<-----------------variables  getters-------------------------------------------------------------->*/
    public Room getCurrentLocation()
    {
        return this.currentLocation;
    }

    public ArrayList<Item> getInventory()
    {
        return this.inventory;
    }
    
    public int getLives()
    {
        return this.lives;
    }
    
    public Room getPreviousLocation()
    {
        return this.currentLocation;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    /*<-----------------variables  setters-------------------------------------------------------------->*/
    public void setCurrentLocation(Room room)
    {
        this.currentLocation = room;
    }
    
    public void setPreviousLocation(Room room)
    {
        this.previousLocation = room;
    }
}
