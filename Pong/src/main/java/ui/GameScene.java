package ui;

import domain.Ball;
import domain.Paddle;
import domain.Player;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static ui.PongUi.HEIGHT;
import static ui.PongUi.LAYOUT;
import static ui.PongUi.WIDTH;
/**
 * Creates the game scene
 *
 */
public class GameScene extends AbstractScene {
    
    private final PongUi application;

    private final Rectangle top;
    private final Rectangle bottom;
    private final Rectangle left;
    private final Rectangle right;
    private final Paddle p1Paddle = new Paddle();
    private final Paddle p2Paddle = new Paddle();
    
    private final Ball ball;

    private final Group p1Score;
    private final Group p2Score;
    private final Group centerLine;
    
    private static final double NUMBER_WIDTH = 85;
    private static final double NUMBER_HEIGHT = 100;
    private static final double NUMBER_THICKNESS = 20;

    private final Random random = new Random();
    private int countdown = 50;
    
    private final Player playerOne;
    private final Player playerTwo;

    public GameScene(PongUi application, Player one, Player two) {
        super(new Group(), WIDTH, HEIGHT);
        
        this.application = application;
        
        this.playerOne = one;
        this.playerTwo = two;

        top = new Rectangle();
        top.setLayoutX(0);
        top.setLayoutY(0);
        top.setWidth(WIDTH);
        top.setHeight(LAYOUT);

        bottom = new Rectangle();
        bottom.setLayoutX(0);
        bottom.setLayoutY(HEIGHT - LAYOUT);
        bottom.setWidth(WIDTH);
        bottom.setHeight(LAYOUT);

        left = new Rectangle();
        left.setLayoutX(-WIDTH);
        left.setLayoutY(0);
        left.setWidth(WIDTH - LAYOUT);
        left.setHeight(HEIGHT);

        right = new Rectangle();
        right.setLayoutX(WIDTH + LAYOUT);
        right.setLayoutY(0);
        right.setWidth(WIDTH);
        right.setHeight(HEIGHT);

        p1Paddle.setLayoutX(WIDTH / 20);
        p2Paddle.setLayoutX(WIDTH - (WIDTH / 20) - LAYOUT);

        p1Score = new Group();
        p1Score.setLayoutX(WIDTH / 2 - 165);
        p1Score.setLayoutY(60);

        p2Score = new Group();
        p2Score.setLayoutX(WIDTH / 2 + 80);
        p2Score.setLayoutY(60);

        centerLine = new Group();
        centerLine.setLayoutX(WIDTH / 2 - LAYOUT / 2);
        for (double y = LAYOUT; y < HEIGHT; y += (1.93 * LAYOUT)) {
            Rectangle box = new Rectangle(0, y, LAYOUT, LAYOUT);
            box.setFill(Color.WHITE);
            centerLine.getChildren().add(box);
        }

        ball = new Ball();

        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(top);
        rootGroup.getChildren().add(bottom);
        rootGroup.getChildren().add(left);
        rootGroup.getChildren().add(right);
        rootGroup.getChildren().add(p1Paddle);
        rootGroup.getChildren().add(p2Paddle);
        rootGroup.getChildren().add(p1Score);
        rootGroup.getChildren().add(p2Score);
        rootGroup.getChildren().add(centerLine);
        rootGroup.getChildren().add(ball);
              
        // activate paddle movement
        setOnKeyPressed(x -> {
            p1Paddle.p1ActivateMovement(x);
            p2Paddle.p2ActivateMovement(x);
        });
        
        // disable paddle movement
        setOnKeyReleased(x -> {
            p1Paddle.disableMovement(x);
            p2Paddle.disableMovement(x);
        });
        
        setPlayerScore(1, 0);
        setPlayerScore(2, 0);
    }

    @Override
    public void tick() {
        if (countdown > 0) {
            countdown--;
            return;
        }

        // move the ball
        ball.setLayoutX(ball.getLayoutX() + ball.getMovementSpeed() * ball.getXDirection());
        ball.setLayoutY(ball.getLayoutY() + ball.getMovementSpeed() * ball.getYDirection());

        // move paddles
        p1Paddle.setLayoutY(p1Paddle.getLayoutY() + (p1Paddle.getYDirection() * p1Paddle.getMovementSpeed()));
        p2Paddle.setLayoutY(p2Paddle.getLayoutY() + (p2Paddle.getYDirection() * p2Paddle.getMovementSpeed()));
        
        // top and bottom boundaries
        Bounds topBounds = top.getBoundsInParent();
        Bounds bottomBounds = bottom.getBoundsInParent();
        
        // ball and paddle boundaries
        Bounds ballBounds = ball.getBoundsInParent();
        Bounds p1PaddleBounds = p1Paddle.getBoundsInParent();
        Bounds p2PaddleBounds = p2Paddle.getBoundsInParent();
        
        // check that paddles stay within boundaries
        if (p1PaddleBounds.intersects(topBounds)) {
            p1Paddle.setLayoutY(top.getLayoutY() + top.getHeight() + 0.1);
        } else if (p1PaddleBounds.intersects(bottomBounds)) {
            p1Paddle.setLayoutY(bottomBounds.getMinY() - p1Paddle.getHeight() - 0.1);
        }
        
        if (p2PaddleBounds.intersects(topBounds)) {
            p2Paddle.setLayoutY(top.getLayoutY() + top.getHeight() + 0.1);
        } else if (p2PaddleBounds.intersects(bottomBounds)) {
            p2Paddle.setLayoutY(bottomBounds.getMinY() - p2Paddle.getHeight() - 0.1);
        }

        // if ball hits p1 paddle
        if (ballBounds.intersects(p1PaddleBounds)) {
            ball.setLayoutX(p1PaddleBounds.getMaxX() + 0.1);
            ball.setXDirection(1.0);
            ball.increaseMovementSpeed();
        // if ball hits p2 paddle
        } else if (ballBounds.intersects(p2PaddleBounds)) {
            ball.setLayoutX(p2PaddleBounds.getMinX() - ball.getWidth() - 0.1);
            ball.setXDirection(-1.0);
            ball.increaseMovementSpeed();
        // if ball hits top wall
        } else if (ballBounds.intersects(topBounds)) {
            ball.setLayoutY(top.getLayoutY() + top.getHeight() + 0.01);
            ball.setYDirection(1.0);
        // if ball hits bottom wall
        } else if (ballBounds.intersects(bottomBounds)) {
            ball.setLayoutY(bottomBounds.getMinY() - ball.getHeight() - 0.01);
            ball.setYDirection(-1.0);
        // if ball hits goal
        } else {
            Bounds leftGoalBounds = left.getBoundsInParent();
            Bounds rightGoalBounds = right.getBoundsInParent();
            
            if (ball.getLayoutX() < 20) {
                playerTwo.newPoint();
                if (playerTwo.getPoints() >= 10) {
                    Stage stage = application.getPrimaryStage();
                    stage.setScene(new EndScene(application, playerOne, playerTwo));
                } else {
                    setPlayerScore(2, playerTwo.getPoints());
                    reset();
                }
            } else if (ballBounds.intersects(rightGoalBounds)) {
                playerOne.newPoint();
                if (playerOne.getPoints() >= 10) {
                    Stage stage = application.getPrimaryStage();
                    stage.setScene(new EndScene(application, playerOne, playerTwo));
                } else {
                    setPlayerScore(1, playerOne.getPoints());
                    reset();
                }
            }
        }
    }
    /**
     * resets game after a player scores
     */
    private void reset() {
        ball.setLayoutX(WIDTH / 2 - LAYOUT / 2);
        ball.setLayoutY(HEIGHT / 2 - LAYOUT / 2);
        
        int randomValue = random.nextInt(3);
        ball.randomiseDirection(randomValue);
        ball.resetMovementSpeed();
        
        p1Paddle.setLayoutY(HEIGHT / 2 - p1Paddle.getHeight() / 2);
        p2Paddle.setLayoutY(p1Paddle.getLayoutY());
        
        countdown = 50;
    }
    /**
     * sets visible scores for players
     * 
     * @param player player whose score needs to be updated
     * @param score new score
     */
    private void setPlayerScore(int player, int score) {
        if (player == 1) {
            p1Score.getChildren().clear();
            p1Score.getChildren().add(numberGroup(score));
        } else if (player == 2) {
            p2Score.getChildren().clear();
            p2Score.getChildren().add(numberGroup(score));
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
    /**
     * creates numbers that indicate players' scores
     * 
     * @param number number to be created
     * @return group of rectangles that form a number
     */
    private static Group numberGroup(int number) {
        Group group = new Group();
        ObservableList<Node> children = group.getChildren();
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
        return group;
    }

}
