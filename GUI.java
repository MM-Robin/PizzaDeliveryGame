import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color; 
import javax.swing.border.*;  


/**
 * Write a description of class G here.
 *
 * @author (Mainuddin Monsur Robin)
 * @version (1.0)
 */
public class GUI implements UI
{
    private Playable player;
    private JFrame frame;
    private JLabel background;
    private JTextPane title;
    private JTextPane gameOutputArea; 
    private JPanel buttons;
    GameHistory gameHistory;
    /**
     * Constructor for objects of class G
     */
    public GUI(Playable game)
    {
        // initialise instance variables
        player = game;
        System.out.println(game.getCurrentRoom().getDirections());
        this.makeFrame();
        this.makeMenuBar();
        this.makeBackground();
        this.makeGameStartBtn();
        gameHistory = new GameHistory(game.playerName());
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return the sum of x and y
     */
    private void makeFrame()
    {
        frame = new JFrame();
        
        frame.setLayout(new BorderLayout());
        frame.setTitle("PIZZA DELIVERY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(580, 620);
        frame.setResizable(true);
        frame.setVisible(true);

    }
    
    private void makeMenuBar()
    {
        // menu bar -------------------------------------------------------------------->
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
    
        // File Menu ----------------------------------------------------------------->        
        JMenu file = new JMenu("File");
        menubar.add(file);
        
            // file memu items
            JMenuItem newGame = new JMenuItem("New Game");
            JMenuItem quit = new JMenuItem("Quit");
            quit.addActionListener(e -> {
                gameHistory.closeWriter();
                System.exit(0);
            });
            file.add(newGame);
            file.add(quit);
        
        // Setting menu--------------------------------------------------------------->
        JMenu settings = new JMenu("Settings");
        menubar.add(settings);

            // Settings memu items
            JMenuItem muteAudio = new JMenuItem("Mute Audio");
            JMenuItem unmuteAudio = new JMenuItem("Unmute Audio");
            settings.add(muteAudio);
            settings.add(unmuteAudio);
    }
    
    private void makeBackground()
    {
        background = new JLabel (new ImageIcon("//Users//mainuddinrobin//Library//CloudStorage//OneDrive-haw-hamburg.de//02.HAW Hamburg//2nd semester//SO2/lab2022_Renz//lab6//Pizza Delivery//pic//bg-main.jpg"));

        background.setLayout(new FlowLayout());
        frame.add(background); 
        
        title = new JTextPane();
        title.setFont(new Font("MV Boli",Font.PLAIN,15));
        title.setForeground(new Color(0xffff00));
        title.setOpaque(false);
        title.setMargin( new Insets(30,0,0,0));
        title.setAlignmentX(0);
        
        gameOutputArea = new JTextPane();
        gameOutputArea.setFont(new Font("MV Boli",Font.PLAIN,15));
        gameOutputArea.setForeground(new Color(0xffff00));
        gameOutputArea.setOpaque(false);
        gameOutputArea.setMargin( new Insets(5,0,0,0));
        gameOutputArea.setAlignmentX(0);
        
        background.add(title);
        background.add(gameOutputArea);
        title.setText(player.welcome());
              
    }
    
    public void updateBackground()
    {
        background.setIcon(new ImageIcon(player.getCurrentRoom().getImagePath()));
        
        title.setText(player.getCurrentRoom().getDescription());
        background.revalidate();
        background.repaint();
            
    }
    
    
    public void makeGameStartBtn(){
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton newGameButton = new JButton("New Game");
        
        buttons.add(newGameButton);
        
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                background.remove(buttons);
                makeKitchen();
                gameOutputArea.setText(null);
            }
        });
        background.add(buttons);
    }
    
    public void makeKitchen()
    {

        updateBackground();
        
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton straightButton = new JButton("Go Straight");
        JButton quitButton = new JButton("Quit");
        JButton helpButton = new JButton("Help");
        JButton pickButton = new JButton("Pick");
        
        buttons.add(straightButton);
        buttons.add(pickButton);
        buttons.add(helpButton);
        buttons.add(quitButton);        
        background.add(buttons);
        
        
        //Actions 
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Help");
                gameOutputArea.setText(player.getHelp());
            }
        });
        
        straightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "straight");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeStreet1();
                
            }
        });
        
        pickButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Pick");
                gameOutputArea.setText("You have picked pizza for delivery.");
            }
        });
        
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Quit");
                gameHistory.closeWriter();
                gameOutputArea.setFont(new Font("MV Boli",Font.PLAIN,50));
                gameOutputArea.setText("GAME OVER");
                background.remove(buttons);
                makeGameStartBtn();
            }
        });
    }
    
    //Street1
    public void makeStreet1(){
        updateBackground();
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton straightButton = new JButton("Go Straight");
        JButton backButton = new JButton("Back");
        JButton leftButton = new JButton("GO Left");
        JButton rightButton = new JButton("GO Right");
        
        buttons.add(straightButton);
        buttons.add(leftButton);
        buttons.add(rightButton);
        buttons.add(backButton);
        
        background.add(buttons);
        
        //Actions
        //go straight button
        straightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "straight");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeStreet2();
            }
        });
        //go left button
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "left");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeBBlock();
            }
        });
        
        //go right button
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "right");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeABlock();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "back");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeKitchen();
            }
        });
    }
    // for street2 
    public void makeStreet2(){
        updateBackground();
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton straightButton = new JButton("Go Straight");
        JButton backButton = new JButton("Back");
        JButton leftButton = new JButton("GO Left");
        JButton rightButton = new JButton("GO Right");
        
        buttons.add(straightButton);
        buttons.add(leftButton);
        buttons.add(rightButton);
        buttons.add(backButton);
        
        background.add(buttons);
        
        //Actions
        //go straight button
        straightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "straight");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeStreet3();
            }
        });
        //go left button
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "left");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeDBlock();
            }
        });
        
        //go right button
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "right");
                 player.processCommand(command);
                 background.remove(buttons);
                 makeCBlock();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "back");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeStreet1();
            }
        });
    }
    
    public void makeStreet3(){
        updateBackground();
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton straightButton = new JButton("Go Straight");
        JButton backButton = new JButton("Back");
        
        buttons.add(straightButton);
        buttons.add(backButton);
        
        background.add(buttons);
        
        //Actions
        //go straight button
        straightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "straight");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeEBlock();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Command command = new Command("go", "back");
                 gameHistory.writeHistory(command.getUserCommand());
                 player.processCommand(command);
                 background.remove(buttons);
                 makeStreet2();
            }
        });
    }
    //AState
    public void makeABlock()
    {

        updateBackground();
        
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton backButton = new JButton("Back");
        JButton deliverButton = new JButton("Deliver");
        
        buttons.add(backButton);
        buttons.add(deliverButton);
        background.add(buttons);
        
        
        //Actions 
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Command command = new Command("go", "back");
                gameHistory.writeHistory(command.getUserCommand());
                player.processCommand(command);
                background.remove(buttons);
                gameOutputArea.setText(null);
                makeStreet1();
            }
        });
        
        deliverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Delivered");
                gameOutputArea.setFont(new Font("MV Boli",Font.PLAIN,50));
                gameOutputArea.setText("Pizza deliverd!");
            }
        });

    }
    
    //For B-Block
    public void makeBBlock()
    {

        updateBackground();
        
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton backButton = new JButton("Back");
        JButton deliverButton = new JButton("Deliver");
        
        buttons.add(backButton);
        buttons.add(deliverButton);
        background.add(buttons);
        
        
        //Actions 
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Command command = new Command("go", "back");
                gameHistory.writeHistory(command.getUserCommand());
                player.processCommand(command);
                background.remove(buttons);
                gameOutputArea.setText(null);
                makeStreet1();
            }
        });
        
        deliverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Deliver");
                gameOutputArea.setFont(new Font("MV Boli",Font.PLAIN,50));
                gameOutputArea.setText("Pizza deliverd!");
            }
        });

    }
    
    //For C-Block
    public void makeCBlock()
    {

        updateBackground();
        
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton backButton = new JButton("Back");
        JButton deliverButton = new JButton("Deliver");
        
        buttons.add(backButton);
        buttons.add(deliverButton);
        background.add(buttons);
        
        
        //Actions 
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Command command = new Command("go", "back");
                gameHistory.writeHistory(command.getUserCommand());
                player.processCommand(command);
                background.remove(buttons);
                gameOutputArea.setText(null);
                makeStreet2();
            }
        });
        
        deliverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Deliver");
                gameOutputArea.setFont(new Font("MV Boli",Font.PLAIN,50));
                gameOutputArea.setAlignmentX(Component.RIGHT_ALIGNMENT);
                gameOutputArea.setText("\nPizza deliverd!\n");
            }
        });

    }
    
    //For D-Block
    public void makeDBlock()
    {

        updateBackground();
        
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton backButton = new JButton("Back");
        JButton deliverButton = new JButton("Deliver");
        
        buttons.add(backButton);
        buttons.add(deliverButton);
        background.add(buttons);
        
        
        //Actions 
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Command command = new Command("go", "back");
                gameHistory.writeHistory(command.getUserCommand());
                player.processCommand(command);
                background.remove(buttons);
                gameOutputArea.setText(null);
                makeStreet2();
            }
        });
        
        deliverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Deliver");
                gameOutputArea.setFont(new Font("MV Boli",Font.PLAIN,50));
                gameOutputArea.setText("\nPizza deliverd!\n");
            }
        });

    }
    
    //For E-Block
    public void makeEBlock()
    {

        updateBackground();
        
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.setPreferredSize(new Dimension(300, 300));
        buttons.setOpaque(false);
        
        JButton backButton = new JButton("Back");
        JButton deliverButton = new JButton("Deliver");
        
        buttons.add(backButton);
        buttons.add(deliverButton);
        background.add(buttons);
        
        
        //Actions 
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Command command = new Command("go", "back");
                gameHistory.writeHistory(command.getUserCommand());
                player.processCommand(command);
                background.remove(buttons);
                gameOutputArea.setText(null);
                makeStreet3();
            }
        });
        
        deliverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameHistory.writeHistory("Deliver");
                gameOutputArea.setFont(new Font("MV Boli",Font.PLAIN,50));
                gameOutputArea.setText("\nPizza deliverd!\n");
            }
        });

    }

    
}
