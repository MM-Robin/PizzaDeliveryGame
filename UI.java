
/**
 * Write a description of interface UI here.
 *
 * @author (Mainuddin Monsur Robin)
 * @version (1.0)
 */
public interface UI
{
    /**
     * An example of a method header - replace this comment with your own
     *
     * @param  y a sample parameter for a method
     * @return   the result produced by sampleMethod
     */
    
    public static UI UiStart(Playable game)
    {
        return new GUI(game);
    }
    
    public void updateBackground();
}
