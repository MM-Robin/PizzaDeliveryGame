import java.util.HashMap;
import java.util.ArrayList;
/**
 * this class is used to provide the game environement-- Cohesion concept.
 *
 * @author (Mainuddin Monsur Robin)
 * @version (1.0)
 */
public class Environement
{
    private Room kitchen;  // private to be read only
    private Room aBlock;
    private Room bBlock;
    private Room cBlock;
    private Room dBlock;
    private Room eBlock;
    private Room street1;
    private Room street2;
    private Room street3;

    /**
     * Constructor for objects of class Map
     */
    public Environement()
    {
        // initialise instance variables
        Room kitchen, aBlock, bBlock, cBlock, dBlock, eBlock, street1, street2, street3;

        // create the rooms
        this.kitchen = new Room("You are at kitchen where delicious pizzas are cooked.", "//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//kitchen.jpeg");
        
        /*<----------------------------------------First Floor-------------------------------------------------------------------------------------------------------------------->*/
        this.aBlock = new Room("This is the first block and closest to the restaurant.", "//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//ablock.jpeg");
        this.bBlock = new Room("This is the Second block and also closest to the restaurant.", "//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//bblock.jpg");
        this.cBlock = new Room("You are at block ´C´.\n", "//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//cblock.jpg");
        this.dBlock = new Room("You are at block ´D´.\n", "//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//dblock.jpg");
        this.eBlock = new Room("You are at block ´E´.\n", "//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//eblock.jpg");
        this.street1 = new Room("You are now at street1.\nBlock A is at your right and Block B is at your left.\nGo straight to go to Street2.","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//street1.jpg");
        this.street2 = new Room("You are now at street1.\nBlock C is at your right and Block D is at your left.\nGo straight to go to Street3.","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//street2.jpg");
        this.street3 = new Room("You are now at street3.\nGo straight to go to Block E.\nThis is the last Block.","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//street3.jpg");


        // initialise room exits
        
        this.kitchen.setExit("straight", this.street1);
        
        this.street1.setExit("straight", this.street2);
        this.street1.setExit("left", this.bBlock);
        this.bBlock.setExit("back", this.street1);
        this.street1.setExit("right", this.aBlock);
        this.aBlock.setExit("back", this.street1);
        this.street1.setExit("back", this.kitchen);
        
        this.street2.setExit("straight", this.street3);
        this.street2.setExit("left", this.dBlock);
        this.dBlock.setExit("back", this.street2);
        this.street2.setExit("right", this.cBlock);
        this.cBlock.setExit("back", this.street2);
        this.street2.setExit("back", this.street1);
        
        this.eBlock.setExit("back", this.street3);
        this.street3.setExit("straight", this.eBlock);
        this.street3.setExit("back", this.street2);

        /*-----------------------------------room items setters------------------------------------------------------------*/ 
        //setItem(String name, String description)
        this.kitchen.setItem("Vegan Pizza", "Pizza for the vegeterian people");
        this.kitchen.setItem("Beef Pizza", "a Pizza for the beef lover");
        this.kitchen.setItem("Chicken Pizza", "a Pizza for the chicken lover");
        this.kitchen.setItem("Soft Drinks", "complementary drinks");
        this.kitchen.setItem("Beer", "Complementary Beer");

    }
    
    /*-----------------------------------------------------------------------------------------------------*/
    /**
     * Method startRoom
     *-- Sart room of tghe environment getter --
     * @return The Start room of the environment
     */
    public Room startRoom()
    {
        // put your code here
        return kitchen;
    }
    
}
