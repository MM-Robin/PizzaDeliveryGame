import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

/**
 * Write a description of class GameHistory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameHistory
{
    // instance variables - replace the example below with your own
    FileWriter fileWriter;

    /**
     * Constructor for objects of class GameHistory
     */
    public GameHistory(String name)
    {
        try{
            fileWriter = new FileWriter("robin.txt");
        } catch (IOException exception) {
          System.out.println("An error occurred when creating file");
          exception.printStackTrace();
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void writeHistory(String content)
    {
        content = content + "\n";
        try{
            System.out.println(content);
            fileWriter.write(content);
        }catch(IOException e){
            System.out.println("An error occurred when writting content to file");
            e.printStackTrace();
        }
    }
    
    public void closeWriter(){
        try{
            
            fileWriter.close();
        }catch(IOException e){
            System.out.println("An error occurred when closing file");
            e.printStackTrace();
        }
    }
}
