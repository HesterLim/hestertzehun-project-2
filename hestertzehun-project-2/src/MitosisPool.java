/**
 * This class is an inheritance of Actor class.
 * It defines the methods and attributes related to Mitosis Pool.
 */
public class MitosisPool extends Actor{
    public static final String TYPE = "Pool";

    /**
     * This method is used to construct Mitosis Pool
     * @param x This is the x-coordinate of Mitosis Pool
     * @param y This is the y-coordinate of Mitosis Pool
     */
    public MitosisPool(int x, int y){
        super("res/images/pool.png", TYPE, x ,y);
    }

    /**
     * This method is update the coordinate of Mitosis Pool
     */
    @Override
    public void update() {}
}
