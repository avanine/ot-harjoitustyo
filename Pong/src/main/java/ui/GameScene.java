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

    private final Rectangle TOP;
    private final Rectangle BOTTOM;
    private final Rectangle LEFT;
    private final Rectangle RIGHT;
    private final Paddle P1_PADDLE = new Paddle();
    private final Paddle P2_PADDLE = new Paddle();
    
    private final Ball BALL;

    private final Group p1Score;
    private final Group p2Score;
    private final Group centerLine;
    
    private static final double NUMBER_WIDTH = 85;
    private static final double NUMBER_HEIGHT = 100;
    private static final double NUMBER_THICKNESS = 20;

    private final Random random = new Random();
    private int countdown = 50;
    
    private final Player PLAYER_ONE;
    private final Player PLAYER_TWO;

    public GameScene(PongUi application, Player one, Player two) {
        super(new Group(), WIDTH, HEIGHT);
        
        this.application = application;
        
        this.PLAYER_ONE = one;
        this.PLAYER_TWO = two;

        TOP = new Rectangle();
        TOP.setLayoutX(0);
        TOP.setLayoutY(0);
        TOP.setWidth(WIDTH);
        TOP.setHeight(LAYOUT);

        BOTTOM = new Rectangle();
        BOTTOM.setLayoutX(0);
        BOTTOM.setLayoutY(HEIGHT - LAYOUT);
        BOTTOM.setWidth(WIDTH);
        BOTTOM.setHeight(LAYOUT);

        LEFT = new Rectangle();
        LEFT.setLayoutX(-WIDTH);
        LEFT.setLayoutY(0);
        LEFT.setWidth(WIDTH - LAYOUT);
        LEFT.setHeight(HEIGHT);

        RIGHT = new Rectangle();
        RIGHT.setLayoutX(WIDTH + LAYOUT);
        RIGHT.setLayoutY(0);
        RIGHT.setWidth(WIDTH);
        RIGHT.setHeight(HEIGHT);

        P1_PADDLE.setLayoutX(WIDTH / 20);
        P2_PADDLE.setLayoutX(WIDTH - (WIDTH / 20) - LAYOUT);

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

        BALL = new Ball();

        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(TOP);
        rootGroup.getChildren().add(BOTTOM);
        rootGroup.getChildren().add(LEFT);
        rootGroup.getChildren().add(RIGHT);
        rootGroup.getChildren().add(P1_PADDLE);
        rootGroup.getChildren().add(P2_PADDLE);
        rootGroup.getChildren().add(p1Score);
        rootGroup.getChildren().add(p2Score);
        rootGroup.getChildren().add(centerLine);
        rootGroup.getChildren().add(BALL);
              
        // activate paddle movement
        setOnKeyPressed(x -> {
            P1_PADDLE.p1ActivateMovement(x);
            P2_PADDLE.p2ActivateMovement(x);
        });
        
        // disable paddle movement
        setOnKeyReleased(x -> {
            P1_PADDLE.disableMovement(x);
            P2_PADDLE.disableMovement(x);
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
        BALL.setLayoutX(BALL.getLayoutX() + BALL.getMovementSpeed() * BALL.getXDirection());
        BALL.setLayoutY(BALL.getLayoutY() + BALL.getMovementSpeed() * BALL.getYDirection());

        // move paddles
        P1_PADDLE.setLayoutY(P1_PADDLE.getLayoutY() + (P1_PADDLE.getYDirection() * P1_PADDLE.getMovementSpeed()));
        P2_PADDLE.setLayoutY(P2_PADDLE.getLayoutY() + (P2_PADDLE.getYDirection() * P2_PADDLE.getMovementSpeed()));
        
        // top and bottom boundaries
        Bounds topBounds = TOP.getBoundsInParent();
        Bounds bottomBounds = BOTTOM.getBoundsInParent();
        
        // ball and paddle boundaries
        Bounds ballBounds = BALL.getBoundsInParent();
        Bounds p1PaddleBounds = P1_PADDLE.getBoundsInParent();
        Bounds p2PaddleBounds = P2_PADDLE.getBoundsInParent();
        
        // check that paddles stay within boundaries
        if (p1PaddleBounds.intersects(topBounds)) {
            P1_PADDLE.setLayoutY(TOP.getLayoutY() + TOP.getHeight() + 0.1);
        } else if (p1PaddleBounds.intersects(bottomBounds)) {
            P1_PADDLE.setLayoutY(bottomBounds.getMinY() - P1_PADDLE.getHeight() - 0.1);
        }
        
        if (p2PaddleBounds.intersects(topBounds)) {
            P2_PADDLE.setLayoutY(TOP.getLayoutY() + TOP.getHeight() + 0.1);
        } else if (p2PaddleBounds.intersects(bottomBounds)) {
            P2_PADDLE.setLayoutY(bottomBounds.getMinY() - P2_PADDLE.getHeight() - 0.1);
        }

        // if ball hits p1 paddle
        if (ballBounds.intersects(p1PaddleBounds)) {
            BALL.setLayoutX(p1PaddleBounds.getMaxX() + 0.1);
            BALL.setXDirection(1.0);
            BALL.increaseMovementSpeed();
        // if ball hits p2 paddle
        } else if (ballBounds.intersects(p2PaddleBounds)) {
            BALL.setLayoutX(p2PaddleBounds.getMinX() - BALL.getWidth() - 0.1);
            BALL.setXDirection(-1.0);
            BALL.increaseMovementSpeed();
        // if ball hits top wall
        } else if (ballBounds.intersects(topBounds)) {
            BALL.setLayoutY(TOP.getLayoutY() + TOP.getHeight() + 0.01);
            BALL.setYDirection(1.0);
        // if ball hits bottom wall
        } else if (ballBounds.intersects(bottomBounds)) {
            BALL.setLayoutY(bottomBounds.getMinY() - BALL.getHeight() - 0.01);
            BALL.setYDirection(-1.0);
        // if ball hits goal
        } else {
            Bounds leftGoalBounds = LEFT.getBoundsInParent();
            Bounds rightGoalBounds = RIGHT.getBoundsInParent();
            
            if (BALL.getLayoutX() < 20) {
                PLAYER_TWO.newPoint();
                if (PLAYER_TWO.getPoints() >= 10) {
                    Stage stage = application.getPrimaryStage();
                    stage.setScene(new EndScene(application, PLAYER_ONE, PLAYER_TWO));
                } else {
                    setPlayerScore(2, PLAYER_TWO.getPoints());
                    reset();
                }
            } else if (ballBounds.intersects(rightGoalBounds)) {
                PLAYER_ONE.newPoint();
                if (PLAYER_ONE.getPoints() >= 10) {
                    Stage stage = application.getPrimaryStage();
                    stage.setScene(new EndScene(application, PLAYER_ONE, PLAYER_TWO));
                } else {
                    setPlayerScore(1, PLAYER_ONE.getPoints());
                    reset();
                }
            }
        }
    }
    /**
     * resets game after a player scores
     */
    private void reset() {
        BALL.setLayoutX(WIDTH / 2 - LAYOUT / 2);
        BALL.setLayoutY(HEIGHT / 2 - LAYOUT / 2);
        
        int randomValue = random.nextInt(3);
        BALL.randomiseDirection(randomValue);
        BALL.resetMovementSpeed();
        
        P1_PADDLE.setLayoutY(HEIGHT / 2 - P1_PADDLE.getHeight() / 2);
        P2_PADDLE.setLayoutY(P1_PADDLE.getLayoutY());
        
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
