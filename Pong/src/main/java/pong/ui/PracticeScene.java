package pong.ui;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import pong.domain.Ball;
import pong.domain.Paddle;
import pong.domain.Player;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.LAYOUT;
import static pong.ui.PongUi.SMALL_FONT;
import static pong.ui.PongUi.WIDTH;

/**
 * Creates a practice game scene.
 *
 */
public class PracticeScene extends AbstractScene {

    private final Ball ball = new Ball();
    private final Paddle computerPaddle = new Paddle();
    private final Paddle playerPaddle = new Paddle();
    private final Text menu = new Text("Menu - Press [ENTER]");

    Rectangle topWall;
    Rectangle bottomWall;
    Rectangle leftWall;
    Rectangle rightWall;
    GameScene gameScene;
    PongUi application;

    private int countdown = 50;

    /**
     * Sets up the scene.
     *
     * @param application application
     */
    public PracticeScene(PongUi application) {
        super(new Group(), WIDTH, HEIGHT);

        this.application = application;

        gameScene = new GameScene(application, new Player(""), new Player(""));
        computerPaddle.setLayoutX(WIDTH / 20);
        computerPaddle.setHeight(HEIGHT);
        computerPaddle.setLayoutY(0);

        menu.setFont(SMALL_FONT);
        menu.setFill(Color.GRAY);
        menu.setLayoutX(90);
        menu.setLayoutY(550);

        Wall wallBuilder = new Wall();
        topWall = wallBuilder.topWall();
        bottomWall = wallBuilder.bottomWall();
        leftWall = wallBuilder.leftWall();
        rightWall = wallBuilder.rightWall();

        Parent root = getRoot();
        Group rootGroup = (Group) root;

        gameScene.createCenterLine(rootGroup);
        rootGroup.getChildren().add(menu);
        rootGroup.getChildren().add(computerPaddle);
        rootGroup.getChildren().add(playerPaddle);
        rootGroup.getChildren().add(topWall);
        rootGroup.getChildren().add(bottomWall);
        rootGroup.getChildren().add(leftWall);
        rootGroup.getChildren().add(rightWall);
        rootGroup.getChildren().add(ball);

        // activate paddle movement
        // go back to menu when enter is pressed
        setOnKeyPressed(x -> {
            if (x.getCode() == KeyCode.ENTER) {
                application.getPrimaryStage().setScene(new WelcomeScene(application));
            }
            playerPaddle.rightPaddleActivateMovement(x);
        });

        // disable paddle movement
        setOnKeyReleased(x -> {
            playerPaddle.disableMovement(x);
        });
    }

    /**
     * Checks that ball and paddle stay within boundaries.
     */
    public void checkBounds() {
        // check that paddle stays within boundaries
        playerPaddle.boundCheck(topWall, bottomWall);

        if (!ball.wallBoundCheck(topWall, bottomWall.getBoundsInParent())) {
            if (ball.getBoundsInParent().intersects(computerPaddle.getBoundsInParent())) {
                ball.setLayoutX(computerPaddle.getBoundsInParent().getMaxX() + 0.1);
                ball.setXDirection(1.0);
                ball.increaseMovementSpeed();
            } else if (ball.getBoundsInParent().intersects(playerPaddle.getBoundsInParent())) {
                ball.setLayoutX(playerPaddle.getBoundsInParent().getMinX() - ball.getWidth() - 0.1);
                ball.setXDirection(-1.0);
                ball.increaseMovementSpeed();
            } else if (ball.getBoundsInParent().intersects(rightWall.getBoundsInParent())) {
                reset();
            }
        }
    }

    @Override
    public void tick() {
        if (countdown > 0) {
            countdown--;
            return;
        }
        ball.moveBall();
        playerPaddle.movePaddle();
        checkBounds();
    }

    /**
     * Resets ball/paddle positions.
     */
    private void reset() {

        ball.setLayoutX(WIDTH / 2 - LAYOUT / 2);
        ball.setLayoutY(HEIGHT / 2 - LAYOUT / 2);

        ball.randomiseDirection(new Random().nextInt(4));
        ball.resetMovementSpeed();

        playerPaddle.setLayoutY(HEIGHT / 2 - playerPaddle.getHeight() / 2);

        countdown = 50;
    }

}
