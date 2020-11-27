/**
 * This class is an inheritance of Actor class
 * It defines attributes and methods related to Sign Left.
 */
public class SignLeft extends Actor{
    public static final String TYPE = "SignLeft";

    /**
     * This method is used as constructor for Sign Left
     * @param x This is to store the x-coordinate of Sign Left
     * @param y This is to store the y-coordinate of Sign Left
     */
    public SignLeft(int x, int y){
        super("res/images/left.png", TYPE, x, y);
    }

    /**
     * This is method is used to update the coordinates for Sing Left
     */
    @Override
    public void update() {
    }
}
