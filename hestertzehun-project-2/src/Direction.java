/**
 * This class defines the attributes and methods that
 * are used in all actors in defining its direction.
 */
public class Direction {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    /**
     * This method is to rotate Gatherer/Thief 90 degree anticlockwise
     * @param direction This is to store the direction of Gatherer/Thief
     * @return This return the direction of Gatherer/Thief
     */
    public static int rotate90(int direction){ //anticlockwise
        if(direction == Direction.LEFT){
            direction = Direction.DOWN;
        }else if(direction == Direction.RIGHT){
            direction = Direction.UP;
        }else if(direction == Direction.DOWN){
            direction = Direction.RIGHT;
        }else if(direction == Direction.UP){
            direction = Direction.LEFT;
        }
        return direction;
    }

    /**
     * This method is used to rotate Gatherer/Thief 90 degree clockwise
     * @param direction This is to store the direction of Gatherer/Thief
     * @return This return the direction of Gatherer/Thief
     */
    public static int rotate270(int direction){ //clockwise
        if(direction == Direction.LEFT){
            direction = Direction.UP;
        }else if(direction == Direction.RIGHT){
            direction = Direction.DOWN;
        }else if(direction == Direction.DOWN){
            direction = Direction.LEFT;
        }else if(direction == Direction.UP){
            direction = Direction.RIGHT;
        }
        return direction;
    }

}
