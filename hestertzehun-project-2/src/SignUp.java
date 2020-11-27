/**
 * This class is an inheritance of Actor class
 * It defines attributes and methods related to Sign Up.
 */
public class SignUp extends Actor {
    public static final String TYPE = "SignUp";

    /**
     * This method is used as constructor for Sign Up
     * @param x This is the x-coordinate of Sign Up
     * @param y This is the y-coordinate of Sign Up
     */
    public SignUp(int x, int y){
        super("res/images/up.png", TYPE, x, y);
    }

    /**
     * This method is used to update coordinates of Sign Up.
     */
    @Override
    public void update() {
    }
}
