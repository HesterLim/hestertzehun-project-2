import bagel.Font;

/**
 * This class is an inheritance of Actor class
 * It defines methods and attributes related to Hoard.
 */
public class Hoard extends Actor{
    public static final String TYPE = "Hoard";
    private int numFruit;

    public final Font displayFruit = new Font("res/VeraMono.ttf", 12);

    /**
     * This method is used to construct Hoard
     * @param x This is to store the x-coordinate of Hoard
     * @param y This is to store the y-coordinate of Hoard
     */
    public Hoard(int x, int y){
        super("res/images/hoard.png", TYPE, x ,y);
        numFruit = 0; // It begins with 0 fruit
    }

    /**
     * This method is to get the number of fruits in Hoard
     * @return This is to return the number of fruits in Hoard
     */
    public int getNumFruit() {
        return numFruit;
    }

    /**
     * This method is used to set the number of fruits in Hoard
     * @param numFruit This is to store the number of fruits in Hoard
     */
    public void setNumFruit(int numFruit) {
        this.numFruit = numFruit;
    }

    /**
     * This method is to update the coordinates of Hoard
     */
    @Override
    public void update() {}

    /**
     * This method is to draw the coordinates of Hoard
     * and display the number of fruits of Hoard
     */
    @Override
    public void render() {
        super.render();
        displayFruit.drawString("" + numFruit, getX(), getY());
    }
}
