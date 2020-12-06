package domain;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Creates numbers that indicate players' scores.
 *
 */
public class NumberGroup extends Group {
   
    private static final double NUMBER_WIDTH = 85;
    private static final double NUMBER_HEIGHT = 100;
    private static final double NUMBER_THICKNESS = 20;
    /**
     * Creates a number out of rectangles.
     * 
     * @param number number
     */
    public NumberGroup(int number) {
        
        ObservableList<Node> children = getChildren();
        switch (number) {
            case 0:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
                break;
            case 1:
                children.add(whiteRect(NUMBER_WIDTH / 2 - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                break;
            case 2:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
                children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
                break;
            case 3:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                break;
            case 4:
                children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                break;
            case 5:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, NUMBER_HEIGHT / 2, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
                children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
                break;
            case 6:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, NUMBER_HEIGHT / 2, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
                children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
                break;
            case 7:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                break;
            case 8:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
                break;
            case 9:
                children.add(whiteRect(0, 0, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(0, 0, NUMBER_THICKNESS, NUMBER_HEIGHT / 2));
                children.add(whiteRect(0, NUMBER_HEIGHT / 2 - NUMBER_THICKNESS / 2, NUMBER_WIDTH, NUMBER_THICKNESS));
                children.add(whiteRect(NUMBER_WIDTH - NUMBER_THICKNESS, 0, NUMBER_THICKNESS, NUMBER_HEIGHT));
                children.add(whiteRect(0, NUMBER_HEIGHT - NUMBER_THICKNESS, NUMBER_WIDTH, NUMBER_THICKNESS));
                break;
            default:
                break;
        }
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
