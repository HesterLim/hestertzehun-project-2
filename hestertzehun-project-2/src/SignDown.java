/**
 * This class is an inheritance of Actor class
 * It defines attributes and methods related to Sign Down.
 */
public class SignDown extends Actor {
    public static final String TYPE = "SignDown";

    /**
     * This method is used as constructor of Sign Down class
     * @param x This is to store the x coordinate of Sign Down
     * @param y This is to store the y coordinate of Sign Down
     */
    public SignDown(int x, int y){
        super("res/images/down.png", TYPE, x, y);
    }

    /**
     * This method is to update the coordinate of Sign Down class
     */
    @Override
    public void update() {
    }
}
