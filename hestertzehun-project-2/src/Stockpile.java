import bagel.Font;
/**
 * This class is an inheritance of Actor class
 * It defines attributes and methods related to Stockpile.
 */
public class Stockpile extends Actor {
    public static final String TYPE = "Stockpile";
    private int numFruit;

    public final Font displayFruit = new Font("res/VeraMono.ttf", 12);

    /**
     * This method is used as constructor of Stockpile
     * @param x This is to store the x-coordinate of Stockpile
     * @param y This is to store the y-coordinate of Stockpile
     */
    public Stockpile(int x, int y){
        super("res/images/cherries.png", TYPE, x ,y);
        numFruit = 0; // It begins with 0 fruit
    }

    /**
     * This method is to get the number of fruits of Stockpile
     * @return This is to return the number of fruits of Stockpile
     */
    public int getNumFruit() {
        return numFruit;
    }

    /**
     * This method is to store the number of fruits of Stockpile
     * @param numFruit This is to store the number of fruits of Stockpile
     */
    public void setNumFruit(int numFruit) {
        this.numFruit = numFruit;
    }

    /**
     * This is to update the coordinates of Stockpile
     */
    @Override
    public void update() {}

    /***
     * This is to draw the image of Stockpile and
     * display the number of fruits of Stockpile
     */
    @Override
    public void render() {
        super.render();
        displayFruit.drawString("" + numFruit, getX(), getY());
    }
}
