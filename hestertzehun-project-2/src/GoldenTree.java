/**
 * This class is an inheritance of Actor class.
 * It defines the methods and attributes related to Golden Tree.
 */
public class GoldenTree extends Actor{
    public static final String TYPE = "GoldenTree";

    /**
     * This method is used to construct Golden Tree
     * @param x This is to store the x-coordinate of Golden Tree
     * @param y This is to store the y-coordinate of Golden Tree
     */
    public GoldenTree(int x, int y){
        super("res/images/gold-tree.png",TYPE,x,y);
    }

    /**
     * This method is used to update Golden Tree.
     */
    @Override
    public void update() {
    }
}
