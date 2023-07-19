
/**
 * Write a description of interface Playable here.
 *
 * @author (Mainuddin Monsur Robin)
 * @version (1.0)
 */
public interface Playable
{
    /**
     * An example of a method header - replace this comment with your own
     *
     * @param  y a sample parameter for a method
     * @return   the result produced by sampleMethod
     */
    public Room getCurrentRoom();
    void play();
    String welcome();
    String getHelp();
    boolean processCommand(Command command);
    String playerName();
}
