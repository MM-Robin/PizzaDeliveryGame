/**
 * Pizza Delivery game. In this game 
 * the pizza delivery boy will start from 
 * a center point of a road. The player will 
 * have to find the correct destination in order
 * to deliver the Pizza and win the match. 
 * 
 * @author  Mainuddin Monsur Robin
 * @version 1.0
 */

public class Game implements Playable
{
    private Parser parser;
    private Room currentRoom;
    private boolean item;
    private Player player;
    private UI ui;
    private Environement environment; 
    
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game(String playerName) 
    {
        createRooms();
        player = new Player(playerName);
        environment = new Environement();
        player.setCurrentLocation(environment.startRoom());
        parser = new Parser();
        ui = UI.UiStart(this);
        item = false;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room kitchen,aBlock,bBlock, cBlock, dBlock, eBlock,street1, street2, street3;
      
        kitchen = new Room("You are at kitchen where delicious pizzas are cooked.\n","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//kitchen.jpeg");
        aBlock = new Room("This is the first block and closest to the restaurant.\n","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//ablock.jpg");
        bBlock = new Room("This is the Second block and also closest to the restaurant.\n ","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//bblock.jpg");
        cBlock = new Room("You are at block ´C´.\n","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//cblock.jpg");
        dBlock = new Room("You are at block ´D´.\n","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//dblock.jpg");
        eBlock = new Room("You are at block ´E´.\n","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//eblock.jpg");
        street1 = new Room("You are now at street1.\nBlock A is at your right and Block B is at your left.\nGo straight to go to Street2.","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//street1.jpg");
        street2 = new Room("You are now at street2.\nBlock C is at your right and Block D is at your left.\nGo straight to go to Street3.","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//street2.jpg");
        street3 = new Room("You are now at street3.\nGo straight to go to Block E.\nThis is the last Block.","//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2//lab2022_Renz//lab6//Pizza Delivery//pic//street3.jpg");
        
        // initialise room exits
        kitchen.setExit("straight", street1);
        
        street1.setExit("straight", street2);
        street1.setExit("left", bBlock);
        bBlock.setExit("back", street1);
        street1.setExit("right", aBlock);
        aBlock.setExit("back", street1);
        street1.setExit("back", kitchen);
        
        street2.setExit("straight", street3);
        street2.setExit("left", dBlock);
        dBlock.setExit("back", street2);
        street2.setExit("right", cBlock);
        cBlock.setExit("back", street2);
        street2.setExit("back", street1);
        
        eBlock.setExit("back", street3);
        street3.setExit("straight", eBlock);
        street3.setExit("back", street2);

        currentRoom = kitchen;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        welcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    public String welcome()
    {
 
        return String.format("Welcome to the Pizza Delivery Game! \n" +
        "Pizza Delivery is a new, incredibly boring adventure game. \n" + 
        "Click on  'help' button if you need help. \n");
    }
    
    public Room getCurrentRoom()
    {
        return player.getCurrentLocation();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
           
        }
        
        else if (commandWord.equals("pick")) {
            //pick();
           
        }
        else if (commandWord.equals("quit") ) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
  
        System.out.println("Go around the blocks and pick item from packet station.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(command.getCommandWord().equals("go"))
        {
             if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
                return;
            }
        }
        
       

        String direction = command.getSecondWord();

        System.out.println(direction);
        System.out.println(currentRoom.getDirections().toString());
        // Try to leave current room.
        player.setPreviousLocation(currentRoom);
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no block!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getDirections());
            player.setCurrentLocation(currentRoom);
            
            /*
            if(item){
                    System.out.println(currentRoom.getDescription());
                }else{
                System.out.println("Fool you didn't bring the Item. You looser");
                }*/
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    public String getHelp(){
        
        return "Go around the blocks and pick item from packet station.";
    }
    
    public String playerName(){
        return this.player.getName();
    }
    /*
    public void loadGame(){
    GameSerializer gameDeserializer = new GameSerializer();
    Game game = gameDeserializer.deSerialize();
    
    this.player = game.player;
    this.environment = game.environment;
    
    ui.gamePlayWindowMaker(player.getName(), player.getCurrentLocation().getRoomName())
    }
    
    public void saveGame(){
    
    }
    /*
    private void pick()
    {
        if(currentRoom.getRoomNumber().equals("D"))
        {
            System.out.println("You picked up the item.");
            System.out.println(currentRoom.getLongDescription());
            item = true;
        }else{
            System.out.println("This is not a packet station!");
            System.out.println(currentRoom.getLongDescription());
        }
    }*/
}
