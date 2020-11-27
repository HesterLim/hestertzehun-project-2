import java.util.ArrayList;
import java.util.List;

/**
 * This class is an inheritance of Actor class.
 * It defines the attributes and methods related to Gatherer
 */
public class Gatherer extends Actor {
    public static final String TYPE = "Gatherer";

    private int direction;
    private boolean carrying;
    private boolean active;

    private Gatherer gatherer1;
    private Gatherer gatherer2;
    private int size;
    String type;

    public static List<Actor> duplicateActors = new ArrayList<>(); // to store extra gatherers
    public static List<Actor> removeIndex = new ArrayList<>(); // to store extra gatherers

    private int x;
    private int y;

    /**
     * This method is used to construct the Gatherer class
     * @param x This is to store the x-coordinate of Gatherer
     * @param y This is to store the y-coordinate of Gatherer
     */
    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        // Algorithm 1 initialisation
        direction = Direction.LEFT;
        carrying = false;
        active = true;
    }

    /**
     * This method is used to check if the gatherer is active or not
     * @return It returns true or false if the gatherer is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method is used to move the gatherer after rotation
     * @param direction This is to store the direction of Gatherer.
     */
    public void rotateMove(int direction){
        if(direction == Direction.UP){
            move(0, -ShadowLife.TILE_SIZE );
        }else if(direction == Direction.DOWN){
            move(0, ShadowLife.TILE_SIZE);
        }else if(direction == Direction.LEFT){
            move(-ShadowLife.TILE_SIZE, 0);
        }else if(direction == Direction.RIGHT){
            move(ShadowLife.TILE_SIZE, 0);
        }
    }

    /**
     * This method is to update the gatherer for each tick
     */
    @Override
    public void update() {
        x = getX();
        y = getY();
        int prevX = x;
        int preY = y;

        if(active == true) {//Move 64 pixels (1 tile) in its direction.
            switch (direction) {
                case Direction.UP:
                    move(0, -ShadowLife.TILE_SIZE);
                    y -= ShadowLife.TILE_SIZE;
                    break;
                case Direction.DOWN:
                    move(0, ShadowLife.TILE_SIZE);
                    y += ShadowLife.TILE_SIZE;
                    break;
                case Direction.LEFT:
                    move(-ShadowLife.TILE_SIZE, 0);
                    x -= ShadowLife.TILE_SIZE;
                    break;
                case Direction.RIGHT:
                    move(ShadowLife.TILE_SIZE, 0);
                    x += ShadowLife.TILE_SIZE;
                    break;
            }
        }

        size = ShadowLife.actors.size(); // Get the array size
        for(int i = 0; i < size; i++){ //Loop throught the actors list.
            Actor actor = ShadowLife.actors.get(i);
            type = actor.type;
            switch (type){
                case MitosisPool.TYPE:
                    MitosisPool pool = (MitosisPool) actor;
                    if(x == pool.getX() && y== pool.getY()){ //Gatherer stands on Mitosis pool
                        gatherer1 = new Gatherer(x,y); // Create one new gatherer
                        gatherer1.direction = Direction.rotate90(direction); //Rotate the gatherer
                        gatherer1.rotateMove(gatherer1.direction); //Move after rotation
                        duplicateActors.add(gatherer1); //Add into a new actor list

                        gatherer2 = new Gatherer(x,y); //Create another gatherer
                        gatherer2.direction = Direction.rotate270(direction); //Rotate the gatherer
                        gatherer2.rotateMove(gatherer2.direction); //Move after rotation
                        duplicateActors.add(gatherer2); // add into another actor list.

                        active = false;
                        for(Actor actor1 : ShadowLife.actors){
                            if(actor1.type == Gatherer.TYPE){
                                Gatherer gatherer = (Gatherer) actor1;
                                if(gatherer.getX() == x && gatherer.getY() == y){
                                    removeIndex.add(gatherer); //able to add the old gatherer here to remove
                                }
                            }
                        }
                    }
                    break;
                case Tree.TYPE:
                    Tree tree = (Tree) actor;
                    // If gatherer standing on tree and carrying == false
                    if((x == tree.getX() && y == tree.getY()) && carrying == false) {
                        if(tree.getNumFruit() >= 1){ //if the tree has at least 1 fruit
                            tree.setNumFruit(tree.getNumFruit() - 1); //decrease the tree's fruit by 1
                            carrying = true;
                            switch (direction){ // Rotate direction by 180 degrees
                                case Direction.UP:
                                    direction = Direction.DOWN;
                                    break;
                                case Direction.DOWN:
                                    direction = Direction.UP;
                                    break;
                                case Direction.LEFT:
                                    direction = Direction.RIGHT;
                                    break;
                                case Direction.RIGHT:
                                    direction = Direction.LEFT;
                                    break;
                            }
                        }
                    }
                    break;
                case GoldenTree.TYPE:
                    GoldenTree goldenTree = (GoldenTree) actor;
                    //Thief stands on golden tree
                    if((x == goldenTree.getX() && y == goldenTree.getY()) && (carrying == false)){
                        carrying = true;
                    }
                    break;
                case Stockpile.TYPE:
                    Stockpile stockpile = (Stockpile) actor;
                    if(x == stockpile.getX() && y == stockpile.getY()){ //Gatherer stands on stockpile
                        if(carrying == true){
                            carrying = false;
                            stockpile.setNumFruit(stockpile.getNumFruit() + 1);
                        }
                        switch (direction){ // Rotate direction by 180 degrees
                            case Direction.UP:
                                direction = Direction.DOWN;
                                break;
                            case Direction.DOWN:
                                direction = Direction.UP;
                                break;
                            case Direction.LEFT:
                                direction = Direction.RIGHT;
                                break;
                            case Direction.RIGHT:
                                direction = Direction.LEFT;
                                break;
                        }
                    }
                    break;
                case Hoard.TYPE:
                    Hoard hoard = (Hoard) actor;
                    if(x == hoard.getX() && y == hoard.getY()){ //Gatherer stands on hoard
                        if(carrying == true){
                            carrying = false;
                            hoard.setNumFruit(hoard.getNumFruit() + 1);
                        }
                        switch (direction){ // Rotate direction by 180 degrees
                            case Direction.UP:
                                direction = Direction.DOWN;
                                break;
                            case Direction.DOWN:
                                direction = Direction.UP;
                                break;
                            case Direction.LEFT:
                                direction = Direction.RIGHT;
                                break;
                            case Direction.RIGHT:
                                direction = Direction.LEFT;
                                break;
                        }
                    }
                    break;
                case Fence.TYPE:
                    Fence fence = (Fence) actor;
                    if(x == fence.getX() && y == fence.getY()){ // if the gatherer is standing on the fence
                        active = false;
                        move(prevX- x, preY -y); //Move back to the previous tick
                    }
                    break;
                case SignUp.TYPE:
                    SignUp signUp = (SignUp) actor;
                    if(x == signUp.getX() && y == signUp.getY()){ //Gatherer standing on UP sign
                        direction = Direction.UP; //Set direction to Up
                    }
                    break;
                case SignDown.TYPE:
                    SignDown signDown = (SignDown) actor;
                    if(x == signDown.getX() && y == signDown.getY()){// Gatherer standing DOWN on sign
                        direction = Direction.DOWN;
                    }
                    break;
                case SignLeft.TYPE:
                    SignLeft signLeft = (SignLeft) actor;
                    if(x == signLeft.getX() && y == signLeft.getY()){//Gatherer standing on LEFT sign
                        direction = Direction.LEFT;
                    }
                    break;
                case SignRight.TYPE:
                    SignRight signRight = (SignRight) actor;
                    if(x == signRight.getX() && y == signRight.getY()){//Gatherer standing on RIGHT sign.
                        direction = Direction.RIGHT;
                    }
                    break;
            }
        }
    }
}