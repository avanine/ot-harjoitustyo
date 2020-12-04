package ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static ui.PongUi.BIG_FONT;
import static ui.PongUi.HEIGHT;
import static ui.PongUi.WIDTH;
/**
 * Creates a menu that allows the user to choose a scene
 *
 */
public class WelcomeScene extends AbstractScene {

    private final Hyperlink START_GAME = new Hyperlink("New Game");
    private final Hyperlink SCORES = new Hyperlink("High Scores");
    private final Hyperlink EXIT = new Hyperlink("Exit");
    private final BorderPane bp = new BorderPane();

    public WelcomeScene(PongUi application) {

        super(new Group(), WIDTH, HEIGHT);

        START_GAME.setFont(BIG_FONT);
        START_GAME.setTextFill(Color.PINK);

        SCORES.setFont(BIG_FONT);
        SCORES.setTextFill(Color.PINK);

        EXIT.setFont(BIG_FONT);
        EXIT.setTextFill(Color.PINK);

        Node newTitle = createTitle("Pong");
        FlowPane fp = new FlowPane(START_GAME, SCORES);
        fp.setAlignment(Pos.CENTER);
        Insets gap = new Insets(5);
        FlowPane.setMargin(START_GAME, gap);
        FlowPane.setMargin(SCORES, gap);

        bp.setTop(newTitle);
        bp.setCenter(fp);
        bp.setBottom(EXIT);
        BorderPane.setAlignment(EXIT, Pos.CENTER);

        BorderPane.setMargin(newTitle, new Insets(30));

        Parent root = getRoot();
        Group rootGroup = (Group) root;
        root.setTranslateX(210);
        root.setTranslateY(30);

        rootGroup.getChildren().add(bp);

        START_GAME.setOnAction(event -> {
            try {
                application.getPrimaryStage().setScene(new NameScene(application));
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        SCORES.setOnAction(event -> {
            try {
                application.getPrimaryStage().setScene(new HighScoreScene(application));
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        EXIT.setOnAction(event -> {
            try {
                Platform.exit();
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    /**
     * creates a fancy animated title of the given word
     * 
     * @param title title to be created
     * @return animated title
     */
    private Node createTitle(String title) {
        final double movement = 25;
        HBox letters = new HBox();
        letters.setAlignment(Pos.CENTER);
        letters.setPadding(new Insets(movement, 0, 0, 0));

        for (int i = 0; i < title.length(); i++) {
            Text letter = new Text(title.charAt(i) + "");
            letter.setFont(Font.font("Impact", FontWeight.BOLD, 80));
            letter.setFill(Color.WHITE);
            letters.getChildren().add(letter);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(2), letter);
            tt.setDelay(Duration.millis(i * 50));
            tt.setToY(-movement);
            tt.setAutoReverse(true);
            tt.setCycleCount(TranslateTransition.INDEFINITE);
            tt.play();
        }

        return letters;
    }

    @Override
    public void tick() {
    }

}
