import java.util.ArrayList;
import java.util.List;

/**
 * This class is an inheritance of Actor class
 * It defines attributes and methods related to Sign Down.
 */
public class Thief extends Actor {
    public static final String TYPE = "Thief";

    private int direction;
    private boolean carrying;
    private boolean consuming;
    private boolean active;

    private Thief thief1;
    private Thief thief2;

    private int size;
    String type;

    public static List<Actor> duplicateActors = new ArrayList<>(); // to store extra thieves
    public static List<Actor> removeIndex = new ArrayList<>(); // to store extra thieves

    private int x;
    private int y;

    /**
     * This method is used to construct the Thief
     * @param x This is to store the x-coordinate of thief
     * @param y This is to store the y-coordinate of thief
     */
    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);
        // Algorithm 3 initialisation
        direction = Direction.UP;
        carrying = false;
        consuming = false;
        active = true;
    }

    /**
     * This method is to get whether the thief is active or not
     * @return true or false depending whether the thief is active or not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method is used to move the thief after rotation
     * @param direction This is to store the direction of thief.
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
     * This method is to update coordinates of the thief
     */
    @Override
    public void update() {
        x = getX();
        y = getY();
        int prevX = x;
        int prevY = y;

        if (active == true) {//Move 64 pixels (1 tile) in its direction.
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
        for (int i = 0; i < size; i++) {
            Actor actor = ShadowLife.actors.get(i);
            type = actor.type;
            switch (type) {
                case MitosisPool.TYPE:
                    MitosisPool pool = (MitosisPool) actor;
                    if(x == pool.getX() && y== pool.getY()){ //Thief stands on Mitosis pool
                        thief1 = new Thief(x,y); // Create one new thief
                        thief1.direction = Direction.rotate90(direction); // Rotate the thief
                        thief1.rotateMove(thief1.direction); // Move the thief after rotation
                        duplicateActors.add(thief1);

                        thief2 = new Thief(x,y); // Create another thief
                        thief2.direction = Direction.rotate270(direction);
                        thief2.rotateMove(thief2.direction);
                        duplicateActors.add(thief2);

                        active = false;
                        for(Actor actor1 : ShadowLife.actors){
                            if(actor1.type == Thief.TYPE){
                                Thief thief = (Thief) actor1;
                                if(thief.getX() == x && thief.getY() == y){
                                    removeIndex.add(thief); //able to add the previous thief here
                                }
                            }
                        }
                    }
                    break;
                case SignUp.TYPE:
                    SignUp signUp = (SignUp) actor;
                    if(x == signUp.getX() && y == signUp.getY()){ //Thief standing on UP sign
                        direction = Direction.UP; //Set direction to Up
                    }
                    break;
                case SignDown.TYPE:
                    SignDown signDown = (SignDown) actor;
                    if(x == signDown.getX() && y == signDown.getY()){//Thief standing DOWN on sign
                        direction = Direction.DOWN;
                    }
                    break;
                case SignLeft.TYPE:
                    SignLeft signLeft = (SignLeft) actor;
                    if(x == signLeft.getX() && y == signLeft.getY()){//Thief standing on LEFT sign
                        direction = Direction.LEFT;
                    }
                    break;
                case SignRight.TYPE:
                    SignRight signRight = (SignRight) actor;
                    if(x == signRight.getX() && y == signRight.getY()){//Thief standing on RIGHT sign.
                        direction = Direction.RIGHT;
                    }
                    break;
                case Pad.TYPE:
                    Pad pad = (Pad) actor;
                    if(x == pad.getX() && y == pad.getY()){//Thief standing on pad
                        consuming = true;
                    }
                    break;
                case Gatherer.TYPE:
                    Gatherer gatherer = (Gatherer) actor;
                    if(x == gatherer.getX() && y == gatherer.getY()){ // Thief stands on Gatherer
                        direction = Direction.rotate90(direction); // Rotate anticlockwise 90 degree
                    }
                    break;
                case Tree.TYPE:
                    Tree tree = (Tree) actor;
                    if((x == tree.getX() && y == tree.getY()) && (carrying == false)){//Thief stands on tree
                        if(tree.getNumFruit() >= 1 ){ //tree has at least 1 fruit
                            tree.setNumFruit(tree.getNumFruit() - 1);
                            carrying = true;
                        }
                    }
                    break;
                case GoldenTree.TYPE:
                    GoldenTree goldenTree = (GoldenTree) actor;
                    if((x == goldenTree.getX() && y == goldenTree.getY()) && (carrying == false)){ //Thief stands on golden tree
                        carrying = true;
                    }
                    break;
                case Hoard.TYPE:
                    Hoard hoard = (Hoard) actor;
                    if(x == hoard.getX() && y == hoard.getY()){ //Thief stands on hoard
                        if(consuming == true) {
                            consuming = false;
                            if(carrying == false){
                                if(hoard.getNumFruit()>=1){
                                    carrying = true;
                                    hoard.setNumFruit(hoard.getNumFruit() - 1); //Decrease Hoard's fruit by 1
                                }else{
                                    direction = Direction.rotate270(direction); // Rotate by 90 degree clockwise
                                }
                            }
                        }else if(carrying == true){
                            carrying = false;
                            hoard.setNumFruit(hoard.getNumFruit() + 1); //Increase Hoard's fruit by 1
                            direction = Direction.rotate270(direction); // Rotate by 90 degree clockwise
                        }
                    }
                    break;
                case Stockpile.TYPE:
                Stockpile stockpile = (Stockpile) actor;
                if(x == stockpile.getX() && y == stockpile.getY()){ //Thief stands on stockpile
                    if(carrying == false){
                        if(stockpile.getNumFruit() >= 1){ // Stockpile has at least 1 fruit
                            carrying = true;
                            consuming = false;
                            stockpile.setNumFruit(stockpile.getNumFruit() - 1); //Decrease stockpile by 1
                            direction = Direction.rotate270(direction); // Rotate by 90 degree clockwise
                        }
                    }else{
                        direction = Direction.rotate270(direction); // Rotate by 90 degree clockwise
                    }
                }
                    break;
                case Fence.TYPE:
                    Fence fence = (Fence) actor;
                    if (x == fence.getX() && y == fence.getY()) { // if the gatherer is standing on the fence
                        active = false;
                        move(prevX- x, prevY - y);
                    }
                    break;
            }
        }
    }
}