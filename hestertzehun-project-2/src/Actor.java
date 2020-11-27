import bagel.Image;

/**
 * This abstract class defines the methods and attrivutes
 * of all actors in ShadowLife.
 */
public abstract class Actor {
    private int x;
    private int y;

    private final Image image;
    public final String type;

    /**
     * This method is used to construct Actor
     * @param filename This is to store the filename of the images
     * @param type This is to store the type of the actor
     * @param x This is to store the x-coordinate of actor
     * @param y This is to store the y-coordinate of actor
     */
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**
     * This method is to get the type of the actor
     * @return This is to return the type of the actor
     */
    public String getType() {
        return type;
    }

    /**
     * This method is to get the x-coordinate of the actor
     * @return This is to return the x-coordinate of the actor
     */
    public int getX() {
        return x;
    }

    /**
     * This method is to get the y-coordinate of the actor
     * @return This is to return the y-coordinate of the actor
     */
    public int getY() {
        return y;
    }

    /**
     * This method is used to call update method
     */
    public final void tick() {
        update();
    }

    /**
     * This method is used to draw the images of actor
     */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /**
     * This method is used to move the actor
     * @param deltaX This is to move the x-coordinate of actor
     * @param deltaY This is to move the y-coordinate of actor
     */
    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    /**
     * This method is to update the actor.
     */
    public abstract void update();
}
