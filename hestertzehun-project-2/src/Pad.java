/**
 * This class is an inheritance of Actor class
 * It defines methods and attributes related to Pad.
 */
public class Pad extends Actor{
    public static final String TYPE = "Pad";

    /**
     * This is method is used as a constructor for Pad
     * @param x This is to store the x-coordinate of Pad
     * @param y This is to store the y-coordinate of Pad
     */
    public Pad(int x, int y) {
        super("res/images/pad.png", TYPE, x, y);
    }

    /**
     * This is used to update the coordinate for Pad
     */
    @Override
    public void update() {}
}
