import bagel.Font;

/**
 * This class is an inheritance of Tree class
 * It defines attributes and methods related to Sign Down.
 */
public class Tree extends Actor {
    public static final String TYPE = "Tree";
    private int numFruit;

    public final Font displayFruit = new Font("res/VeraMono.ttf", 12);

    /**
     * This method is used as a constructor for Tree class
     * @param x This is to store the x-coordinate for Tree class
     * @param y This is to store the y-coordinate for Tree class
     */
    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);
        numFruit = 3; // It begins with 3 fruit
    }

    /**
     * This method is to get the number of fruits of Tree
     * @return This returns the number of fruits of the Tree
     */
    public int getNumFruit() {
        return numFruit;
    }

    /**
     * This method is to set the number of fruits of Tree
     * @param numFruit This returns the number of fruits of the Tree
     */
    public void setNumFruit(int numFruit) {
        this.numFruit = numFruit;
    }

    /**
     * This method is to update the coordinates of Tree
     */
    @Override
    public void update() {
        // Add update of numFruit
    }

    /**
     * This method is to draw the Tree image and
     * display the number of fruits
     */
    @Override
    public void render() {
        super.render();
        displayFruit.drawString("" + numFruit, getX(), getY()); // Add to draw numFruit
    }
}
