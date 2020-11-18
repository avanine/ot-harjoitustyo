package ui;

import domain.Ball;
import domain.Box;
import domain.Paddle;
import domain.Player;
import domain.Scores;
import java.util.Random;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameScene extends AbstractScene {

    private final Box top;
    private final Box bottom;
    private final Box left;
    private final Box right;
    private final Paddle p1Paddle;
    private final Paddle p2Paddle;
    
    private final Ball ball;

    private final Group p1Score;
    private final Group p2Score;
    private final Group centerLine;

    private final Random random = new Random();
    private int countdown = 50;
    
    private final Player one;
    private final Player two;

    public GameScene(WelcomeScene welcomeScene, Player one, Player two) {
        super(new Group(), 850, 600);
        
        this.one = one;
        this.two = two;

        setFill(Color.BLACK);
        
        Text playerOneName = one.getTextName();
        Text playerTwoName = two.getTextName();
        Text playerOneScore = one.getTextScore();
        Text playerTwoScore = two.getTextScore();
        /*
        Box nameBoxOne = new Box();
        nameBoxOne.createNameBox();
        Box nameBoxTwo = new Box();
        nameBoxTwo.createNameBox();
        
        Box scoreBoxOne = new Box();
        scoreBoxOne.createScoreBox();
        Box scoreBoxTwo = new Box();
        scoreBoxTwo.createScoreBox();
        
        StackPane name1 = new StackPane();
        name1.getChildren().addAll(nameBoxOne, playerOneName);
        name1.setAlignment(Pos.CENTER);
        name1.setLayoutX(10);
        name1.setLayoutY(10);
        StackPane name2 = new StackPane();
        name2.getChildren().addAll(nameBoxTwo, playerTwoName);
        name2.setLayoutX(600);
        name2.setLayoutY(10);
        StackPane score1 = new StackPane();
        score1.getChildren().addAll(scoreBoxOne, playerOneScore);
        score1.setLayoutX(10);
        score1.setLayoutY(30);
        StackPane score2 = new StackPane();
        score2.getChildren().addAll(scoreBoxTwo, playerTwoScore);
        score2.setLayoutX(600);
        score2.setLayoutY(30);
        */
        top = new Box();
        top.createTopBox();

        bottom = new Box();
        bottom.createBottomBox();

        left = new Box();
        left.createLeftBox();

        right = new Box();
        right.createRightBox();

        p1Paddle = new Paddle();
        p1Paddle.setLayoutX(850 / 20);

        p2Paddle = new Paddle();
        p2Paddle.setLayoutX(850 - (850 / 20) - (850 / 40));

        p1Score = new Group();
        p1Score.setLayoutX(850 / 2 - (70 + 850 / 10));
        p1Score.setLayoutY(600 / 10);

        p2Score = new Group();
        p2Score.setLayoutX(850 / 2 + 70);
        p2Score.setLayoutY(600 / 10);

        centerLine = new Group();
        centerLine.setLayoutX(850 / 2 - (850/40) / 2);
        for (double y = (850/40); y < 600; y += (1.93 * (850/40))) {
            Rectangle box = new Rectangle(0, y, (850/40), (850/40));
            box.setFill(Color.WHITE);
            centerLine.getChildren().add(box);
        }

        ball = new Ball();

              
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
            ball.setLayoutX(p2PaddleBounds.getMinX()-ball.getWidth()-0.1);
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
            
            if (ball.intersects(leftGoalBounds)) {
                two.newPoint();
                if (two.getPoints() >= 10) {
                    // move to end scene
                    // Stage stage = welcomeScene.getPrimaryStage();
                    // stage.setScene(new endScene(welcomeScene));
                } else {
                    // show new score
                    reset();
                }
            } else if (ballBounds.intersects(rightGoalBounds)) {
                one.newPoint();
                if (one.getPoints() >= 10) {
                    // move to end scene
                    // Stage stage = welcomeScene.getPrimaryStage();
                    // stage.setScene(new endScene(welcomeScene));
                } else {
                    // show new score
                    reset();
                }
            }
        }
    }
    
    private void reset() {
        ball.setLayoutX(850/2 - (850/40)/2);
        ball.setLayoutY(850/2 - (850/40)/2);
        
        int randomValue = random.nextInt(3);
        switch (randomValue) {
            case 0:
                ball.setXDirection(-1.0);
                ball.setYDirection(-1.0);
                break;
            case 1:
                ball.setXDirection(-1.0);
                ball.setYDirection(1.0);
                break;
            case 2:
                ball.setXDirection(1.0);
                ball.setYDirection(-1.0);
                break;
            case 3:
                ball.setXDirection(1.0);
                ball.setYDirection(1.0);
                break;
        }
        ball.resetMovementSpeed();
        
        p1Paddle.setLayoutY(850/2 - p1Paddle.getHeight()/2);
        p2Paddle.setLayoutY(p1Paddle.getLayoutY());
        
        countdown = 50;
    }
    

}
