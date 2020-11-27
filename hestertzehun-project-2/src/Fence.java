/**
 * This class is an inheritance of Actor class.
 * It stores the attributes and methods related to Fence.
 */
public class Fence extends Actor{
    public static final String TYPE = "Fence";

    /**
     * This method is used as the constructor for Fence
     * @param x This is to store the x-coordinate of Fence
     * @param y This is to store the y-coordinate of Fence
     */
    public Fence(int x, int y) {
        super("res/images/fence.png", TYPE, x, y);
    }

    /**
     * This method is used to update the coordinates for Fence
     */
    @Override
    public void update() {}
}
