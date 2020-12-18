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
import static pong.ui.PongUi.colorDao;

/**
 * Creates the game scene.
 *
 */
public final class GameScene extends AbstractScene {

    private final PongUi application;

    private final Paddle playerOnePaddle = new Paddle();
    private final Paddle playerTwoPaddle = new Paddle();

    private Ball ball;
    
    Rectangle topWall;
    Rectangle bottomWall;
    Rectangle leftWall;
    Rectangle rightWall;

    private final Group leftScoreGroup;
    private final Group rightScoreGroup;

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

        playerOnePaddle.setLayoutX(WIDTH / 20);
        
        playerOnePaddle.setFill(colorDao.getColor());
        playerTwoPaddle.setFill(colorDao.getColor());
        
        Wall wallBuilder = new Wall();
        topWall = wallBuilder.topWall();
        bottomWall = wallBuilder.bottomWall();
        leftWall = wallBuilder.leftWall();
        rightWall = wallBuilder.rightWall();

        leftScoreGroup = new Group();
        leftScoreGroup.setLayoutX(WIDTH / 2 - 165);
        leftScoreGroup.setLayoutY(60);

        rightScoreGroup = new Group();
        rightScoreGroup.setLayoutX(WIDTH / 2 + 80);
        rightScoreGroup.setLayoutY(60);      

        ball = new Ball();
        ball.setFill(colorDao.getColor());

        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(topWall);
        rootGroup.getChildren().add(bottomWall);
        rootGroup.getChildren().add(leftWall);
        rootGroup.getChildren().add(rightWall);
        createCenterLine(rootGroup);
        rootGroup.getChildren().add(playerOnePaddle);
        rootGroup.getChildren().add(playerTwoPaddle);
        rootGroup.getChildren().add(leftScoreGroup);
        rootGroup.getChildren().add(rightScoreGroup);
        rootGroup.getChildren().add(ball);

        // activate paddle movement
        setOnKeyPressed(x -> {
            playerOnePaddle.leftPaddleActivateMovement(x);
            playerTwoPaddle.rightPaddleActivateMovement(x);
        });

        // disable paddle movement
        setOnKeyReleased(x -> {
            playerOnePaddle.disableMovement(x);
            playerTwoPaddle.disableMovement(x);
        });

        setVisibleScore(leftScoreGroup, playerOne.getPoints());
        setVisibleScore(rightScoreGroup, playerTwo.getPoints());
    }
    /**
     * Creates the center line for the game.
     * 
     * @param rootGroup root group
     */
    public void createCenterLine(Group rootGroup) {
        Group centerLine = new Group();
        centerLine.setLayoutX(WIDTH / 2 - LAYOUT / 2);

        for (double y = LAYOUT; y < HEIGHT; y += (1.93 * LAYOUT)) {
            Rectangle box = new Rectangle(0, y, LAYOUT, LAYOUT);
            box.setFill(Color.WHITE);
            centerLine.getChildren().add(box);
        }
        
        rootGroup.getChildren().add(centerLine);
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
