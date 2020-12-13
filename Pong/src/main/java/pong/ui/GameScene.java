package pong.ui;

import pong.domain.Ball;
import pong.domain.Paddle;
import pong.domain.Player;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.LAYOUT;
import static pong.ui.PongUi.WIDTH;

/**
 * Creates the game scene.
 *
 */
public final class GameScene extends AbstractScene {

    private final PongUi application;

    private final Rectangle topWall;
    private final Rectangle bottomWall;
    private final Rectangle leftWall;
    private final Rectangle rightWall;
    private final Paddle playerOnePaddle = new Paddle();
    private final Paddle playerTwoPaddle = new Paddle();

    private final Ball ball;

    private final Group leftScoreGroup;
    private final Group rightScoreGroup;
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
        topWall = new Rectangle(0, 0, WIDTH, LAYOUT);
        bottomWall = new Rectangle(0, HEIGHT - LAYOUT, WIDTH, LAYOUT);
        leftWall = new Rectangle(-WIDTH, 0, WIDTH - LAYOUT, HEIGHT);
        rightWall = new Rectangle(WIDTH + LAYOUT, 0, WIDTH, HEIGHT);

        playerOnePaddle.setLayoutX(WIDTH / 20);
        playerTwoPaddle.setLayoutX(WIDTH - (WIDTH / 20) - LAYOUT);

        leftScoreGroup = new Group();
        leftScoreGroup.setLayoutX(WIDTH / 2 - 165);
        leftScoreGroup.setLayoutY(60);

        rightScoreGroup = new Group();
        rightScoreGroup.setLayoutX(WIDTH / 2 + 80);
        rightScoreGroup.setLayoutY(60);

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
        rootGroup.getChildren().add(topWall);
        rootGroup.getChildren().add(bottomWall);
        rootGroup.getChildren().add(leftWall);
        rootGroup.getChildren().add(rightWall);
        rootGroup.getChildren().add(playerOnePaddle);
        rootGroup.getChildren().add(playerTwoPaddle);
        rootGroup.getChildren().add(leftScoreGroup);
        rootGroup.getChildren().add(rightScoreGroup);
        rootGroup.getChildren().add(centerLine);
        rootGroup.getChildren().add(ball);

        // activate paddle movement
        setOnKeyPressed(x -> {
            playerOnePaddle.p1ActivateMovement(x);
            playerTwoPaddle.p2ActivateMovement(x);
        });

        // disable paddle movement
        setOnKeyReleased(x -> {
            playerOnePaddle.disableMovement(x);
            playerTwoPaddle.disableMovement(x);
        });

        setVisibleScore(leftScoreGroup, playerOne.getPoints());
        setVisibleScore(rightScoreGroup, playerTwo.getPoints());
    }

    @Override
    public void tick() {
        if (countdown > 0) {
            countdown--;
            return;
        }
        moveObjects();
        checkBounds();
    }

    /**
     * Moves paddles and ball.
     */
    public void moveObjects() {
        // move ball and paddles
        ball.moveBall();
        playerOnePaddle.movePaddle();
        playerTwoPaddle.movePaddle();
    }

    /**
     * Checks that nothing goes over the boundaries.
     */
    public void checkBounds() {
        // check that paddles stay within boundaries
        playerOnePaddle.boundCheck(topWall, bottomWall);
        playerTwoPaddle.boundCheck(topWall, bottomWall);

        // check if ball hits walls/paddles/goals
        if (!ball.boundCheck(playerOnePaddle.getBoundsInParent(), playerTwoPaddle.getBoundsInParent(), topWall, bottomWall.getBoundsInParent())) {
            if (ball.getBoundsInParent().intersects(leftWall.getBoundsInParent())) {
                playerTwo.newPoint();
                if (playerTwo.getPoints() < 10) {
                    setVisibleScore(rightScoreGroup, playerTwo.getPoints());
                }
                reset();
            } else if (ball.getBoundsInParent().intersects(rightWall.getBoundsInParent())) {
                playerOne.newPoint();
                if (playerTwo.getPoints() < 10) {
                    setVisibleScore(leftScoreGroup, playerOne.getPoints());
                }
                reset();
            }
        }
    }

    /**
     * Resets game after a player scores.
     */
    private void reset() {

        try {
            if (playerOne.win() || playerTwo.win()) {
                Stage stage = application.getPrimaryStage();
                stage.setScene(new EndScene(application, playerOne, playerTwo));
            } else {
                ball.setLayoutX(WIDTH / 2 - LAYOUT / 2);
                ball.setLayoutY(HEIGHT / 2 - LAYOUT / 2);

                ball.randomiseDirection(new Random().nextInt(4));
                ball.resetMovementSpeed();

                playerOnePaddle.setLayoutY(HEIGHT / 2 - playerOnePaddle.getHeight() / 2);
                playerTwoPaddle.setLayoutY(playerOnePaddle.getLayoutY());

                countdown = 50;
            }
        } catch (Exception ex) {
            Logger.getLogger(GameScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets visible score for player.
     *
     * @param playerScore group containing rectangles
     * @param points new score
     */
    public void setVisibleScore(Group playerScore, int points) {
        playerScore.getChildren().clear();
        playerScore.getChildren().add(new NumberGroup(points));
    }
}
