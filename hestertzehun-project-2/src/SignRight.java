/**
 * This class is an inheritance of Actor class
 * It defines attributes and methods related to Sign Right.
 */
public class SignRight extends Actor{
    public static final String TYPE = "SignRight";

    /**
     * This method is used as a constructor for Sign Right
     * @param x This is to store the x-coordinate of Sign Right
     * @param y This is to store the y-coordinate of Sign Right
     */
    public SignRight(int x, int y){
        super("res/images/right.png", TYPE, x, y);
    }

    /**
     * This method is used to update the coordinates for Sign Right
     */
    @Override
    public void update() {
    }

}
