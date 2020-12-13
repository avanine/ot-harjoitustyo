package pong.ui;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Creates numbers that indicate players' scores.
 *
 */
public final class NumberGroup extends Group {
   
    private static final double NUMBER_WIDTH = 85;
    private static final double NUMBER_HEIGHT = 100;
    private static final double NUMBER_THICKNESS = 20;
    
    ObservableList<Node> children = getChildren();
    /**
     * Creates a number out of rectangles.
     * 
     * @param number number
     */
    public NumberGroup(int number) {       
        
        switch (number) {
            case 0:
                zero();
                break;
            case 1:
                one();
                break;
            case 2:
                two();
                break;
            case 3:
                three();
                break;
            case 4:
                four();
                break;
            case 5:
                five();
                break;
            case 6:
                six();
                break;
            case 7:
                seven();
                break;
            case 8:
                eight();
                break;
            case 9:
                nine();
                break;
            default:
                break;
        }
    }
    /**
     * Creates the number zero.
     */
    public void zero() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
        children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
    }
    /**
     * Creates the number one.
     */
    public void one() {
        children.add(whiteRect(NUMBER_WIDTH / 2 - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
    }
    /**
     * Creates the number two.
     */
    public void two() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
        children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
    }
    /**
     * Creates the number three.
     */
    public void three() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
    }
    /**
     * Creates the number four.
     */
    public void four() {
        children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
    }
    /**
     * Creates the number five.
     */
    public void five() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, NUMBER_HEIGHT / 2, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
        children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
    }
    /**
     * Creates the number six.
     */
    public void six() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, NUMBER_HEIGHT / 2, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
        children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
    }
    /**
     * Creates the number seven.
     */
    public void seven() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
    }
    /**
     * Creates the number eight.
     */
    public void eight() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
        children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
    }
    /**
     * Creates the number nine.
     */
    public void nine() {
        children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
        children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
        children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
        children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
    }

    /**
     * creates smaller rectangles that are needed for displaying scores
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param w width
     * @param h height
     * @return rectangle
     * @throws IllegalArgumentException
     */
    private static Rectangle whiteRect(double x, double y, double w, double h) throws IllegalArgumentException {
        Rectangle rectangle = new Rectangle(x, y, w, h);
        rectangle.setFill(Color.WHITE);
        return rectangle;
    }
}
