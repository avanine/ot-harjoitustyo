package ui;

import domain.Ball;
import domain.Paddle;
import domain.Player;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static ui.PongUi.HEIGHT;
import static ui.PongUi.LAYOUT;
import static ui.PongUi.WIDTH;

/**
 * Creates the game scene.
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

    private int countdown = 50;

    private final Player playerOne;
    private final Player playerTwo;

    /**
     * Constructor that creates instances and sets up the scene.
     *
     * @param application application
     * @param one player one
     * @param two player two
     */
    public GameScene(PongUi application, Player one, Player two) {
        super(new Group(), WIDTH, HEIGHT);

        this.application = application;
        this.playerOne = one;
        this.playerTwo = two;

        // create walls
        top = new Rectangle(0, 0, WIDTH, LAYOUT);
        bottom = new Rectangle(0, HEIGHT - LAYOUT, WIDTH, LAYOUT);
        left = new Rectangle(-WIDTH, 0, WIDTH - LAYOUT, HEIGHT);
        right = new Rectangle(WIDTH + LAYOUT, 0, WIDTH, HEIGHT);

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

        playerOne.setVisibleScore(p1Score);
        playerTwo.setVisibleScore(p2Score);
    }

    @Override
    public void tick() {
        if (countdown > 0) {
            countdown--;
            return;
        }
        // move ball and paddles
        ball.moveBall();
        p1Paddle.movePaddle();
        p2Paddle.movePaddle();

        // check that paddles stay within boundaries
        p1Paddle.boundCheck(top, bottom);
        p2Paddle.boundCheck(top, bottom);

        // check if ball hits walls/paddles/goals
        if (!ball.boundCheck(p1Paddle.getBoundsInParent(), p2Paddle.getBoundsInParent(), top, bottom.getBoundsInParent())) {
            if (ball.getBoundsInParent().intersects(left.getBoundsInParent())) {
                playerTwo.newPoint(p2Score);
                reset();
            } else if (ball.getBoundsInParent().intersects(right.getBoundsInParent())) {
                playerOne.newPoint(p1Score);
                reset();
            }
        }
    }

    /**
     * Resets game after a player scores.
     */
    private void reset() {

        if (playerOne.win() || playerTwo.win()) {
            Stage stage = application.getPrimaryStage();
            stage.setScene(new EndScene(application, playerOne, playerTwo));
        } else {
            ball.setLayoutX(WIDTH / 2 - LAYOUT / 2);
            ball.setLayoutY(HEIGHT / 2 - LAYOUT / 2);

            ball.randomiseDirection(new Random().nextInt(3));
            ball.resetMovementSpeed();

            p1Paddle.setLayoutY(HEIGHT / 2 - p1Paddle.getHeight() / 2);
            p2Paddle.setLayoutY(p1Paddle.getLayoutY());

            countdown = 50;
        }
    }
}
