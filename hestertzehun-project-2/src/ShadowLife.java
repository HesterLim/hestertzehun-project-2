// Import libraries
import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This classed is used to construct the ShadowLife simulation.
 */
public class ShadowLife extends AbstractGame {
    public static final int TILE_SIZE = 64; //Pixel Size (1 tile)
    private final Image background = new Image("res/images/background.png");
    private long time = System.currentTimeMillis();
    int tick_counter = 1; // to store the number of ticks

    public static List<Actor> actors = new ArrayList<>();

    private static int tickRate;
    private static int maxTick;
    private static String worldFile;

    private int lineNum = 0;

    /**
     * This is to construct Shadow Life
     */
    public ShadowLife() {
        try {// try putting 3 arguments into its corresponding variables
            tickRate = Integer.parseInt(argsFromFile()[0]);
            maxTick = Integer.parseInt(argsFromFile()[1]);
            worldFile = argsFromFile()[2];

            // If more or fewer than 3 arguments are provided and first/second arguments
            // are not valid non-negative integers
            if(argsFromFile().length != 3 || tickRate < 0 || maxTick < 0) {
                // Print the exit line
                System.out.println("usage: ShadowLife " + tickRate + " " + maxTick + " " + worldFile);
            }else{ // Valid argument
            }
        } catch (NumberFormatException e) { //when there is an error
            System.out.print("usage: ShadowLife "); //Print the exit line
            for (int i = 0; i < argsFromFile().length; i++) {
                System.out.print(argsFromFile()[i] + " ");
                if (i == 2) { // Check until 3 arguments
                    System.exit(-1); // Exit with return code -1
                }
            }
            System.exit(-1);// Exit with return code -1
        }
        try (BufferedReader br = new BufferedReader(new FileReader(worldFile))){// try to read from the world file.
            String line;
            while ((line = br.readLine()) != null){ // read through the file line by line.
                //System.out.println(line);
                String[] data = line.split(",");
                String type = data[0];
                int x = Integer.parseInt(data[1]);
                int y = Integer.parseInt(data[2]);
                lineNum += 1; // Increment line number
                switch (type) {
                    case Tree.TYPE:
                        actors.add(new Tree(x,y));
                        break;
                    case Gatherer.TYPE:
                        actors.add(new Gatherer(x,y));
                        break;
                    case Stockpile.TYPE:
                        actors.add(new Stockpile(x,y));
                        break;
                    case Fence.TYPE:
                        actors.add(new Fence(x,y));
                        break;
                    case MitosisPool.TYPE:
                        actors.add(new MitosisPool(x,y));
                        break;
                    case SignUp.TYPE:
                        actors.add(new SignUp(x,y));
                        break;
                    case SignDown.TYPE:
                        actors.add(new SignDown(x,y));
                        break;
                    case SignLeft.TYPE:
                        actors.add(new SignLeft(x,y));
                        break;
                    case SignRight.TYPE:
                        actors.add(new SignRight(x,y));
                        break;
                    case Thief.TYPE:
                        actors.add(new Thief(x,y));
                        break;
                    case Hoard.TYPE:
                        actors.add(new Hoard(x,y));
                        break;
                    case Pad.TYPE:
                        actors.add(new Pad(x,y));
                        break;
                    case GoldenTree.TYPE:
                        actors.add(new GoldenTree(x,y));
                        break;
                }
            }
        }catch(FileNotFoundException e){//File does not exist or fail to open it
            System.out.println("error: file " + argsFromFile()[2] + " not found");
            System.exit(-1);
        } catch(IOException e){
            e.printStackTrace();
        } catch(NumberFormatException e){ //File exists but one of its line is invalid
            System.out.println("error: in file " + argsFromFile()[2] + " at line " + lineNum);
            System.exit(-1);
        }
    }

    /**
     * This is the main method which makes use of the Shadow Life method.
     * @param args This store the command line arguments
     */
    public static void main(String[] args) {
        // Load the simulation
        ShadowLife game = new ShadowLife();
        game.run();
    }

    /**
     * This is the method to read Command Line arguments
     * @return This return nothing
     */
    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("args.txt"), Charset.defaultCharset())
                    .split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to update the Shadow Life
     * @param input  This take the input from Shadow Life
     */
    @Override
    protected void update(Input input) {
        //Ensure at least more than tick rate has passes between each ticks
        if(System.currentTimeMillis() > (time + tickRate)){
            tick_counter += 1; // Increment the number of ticks
            time = System.currentTimeMillis(); // Set a new timing after checking if 500ms has passed.
            for(Actor actor : actors){ // In each inherited actor classes
                if(actor != null){ // if the classes are not empty
                    actor.tick(); // actor is updated by tick
                }
            }

            // add gatherers into the actors array
            for(int i = 0; i < Gatherer.duplicateActors.size() ; i++){//Put in the new gatherers into old array list
                actors.add(Gatherer.duplicateActors.get(i));
            }
            Gatherer.duplicateActors.clear(); //Empty the new ArrayList

            // remove the gatherer at a particular index
            for(int i = 0; i < Gatherer.removeIndex.size(); i++){
                actors.remove(Gatherer.removeIndex.get(i)); //able to remove the gatherer here
            }
            Gatherer.removeIndex.clear(); //Empty the ArrayList

            // add thieves into the actors array
            for(int i = 0; i < Thief.duplicateActors.size(); i++){
                actors.add(Thief.duplicateActors.get(i));
            }
            Thief.duplicateActors.clear(); //Empty the new ArrayList

            // remove the thief at a particular index
            for(int i = 0; i < Thief.removeIndex.size(); i++){
                actors.remove(Thief.removeIndex.get(i));
            }
            Thief.removeIndex.clear(); /// Empty the ArrayList

        }

        if(tick_counter > maxTick){ // If more than the max number of ticks passes
            System.out.println("Timed out"); //the simulation print "Time out"
            System.exit(-1); //exit with return code -1
        }

        int numGatherer = 0;
        int numThief = 0;
        for(Actor actor : actors){ // Count the number of gatherers and thieves
            if(actor.type == Gatherer.TYPE){
                numGatherer += 1;
            }else if(actor.type == Thief.TYPE){
                numThief += 1;
            }
        }

        int inactiveGatherer = 0;
        int inactiveThief = 0;
        for(Actor actor : actors){ // Count the number of inactive gatherer and thieves
            if(actor.type == Gatherer.TYPE){
                Gatherer gatherer = (Gatherer) actor;
                if(gatherer.isActive() == false){
                    inactiveGatherer += 1;
                }
            }else if(actor.type == Thief.TYPE){
                Thief thief = (Thief) actor;
                if(thief.isActive() == false){
                    inactiveThief += 1;
                }
            }
        }

        String type;
        int size = actors.size();
        if((inactiveGatherer == numGatherer) && (inactiveThief == numThief)){
            System.out.println((tick_counter-1) + " ticks"); //Display the number of ticks elapsed
            for(int i = 0; i < size; i++){ // Display according to their appearance in word file
                Actor actor = actors.get(i);
                type = actor.type;
                if(type == Stockpile.TYPE){ //Display the number of fruits of Stockpile
                    Stockpile stockpile = (Stockpile) actor;
                    System.out.println(stockpile.getNumFruit());
                }else if(type == Hoard.TYPE){ //Display the number of fruits of Hoard
                    Hoard hoard = (Hoard) actor;
                    System.out.println(hoard.getNumFruit());
                }
            }
            System.exit(0); //Exit with return code 0
        }

        // draw all elements
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            if (actor != null) {
                actor.render();
            }
        }
    }
}
