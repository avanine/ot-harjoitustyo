package pong.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static pong.ui.PongUi.BIG_FONT;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.WIDTH;
import static pong.ui.PongUi.colorDao;

/**
 * Creates a menu that allows the user to choose a scene.
 *
 */
public class WelcomeScene extends AbstractScene {

    private final Hyperlink startGame = new Hyperlink("New Game");
    private final Hyperlink practiceMode = new Hyperlink("Practice");
    private final Hyperlink scores = new Hyperlink("High Scores");
    private final Hyperlink settings = new Hyperlink("Settings");
    private final Hyperlink exit = new Hyperlink("Exit");

    private final BorderPane bp = new BorderPane();

    /**
     * Constructor for creating the scene.
     *
     * @param application application
     */
    public WelcomeScene(PongUi application) {

        super(new Group(), WIDTH, HEIGHT);

        styleHyperlink(startGame);
        styleHyperlink(practiceMode);
        styleHyperlink(scores);
        styleHyperlink(settings);
        styleHyperlink(exit);

        Node newTitle = createTitle("Pong");

        GridPane gp = new GridPane();
        gp.add(startGame, 0, 0);
        gp.add(practiceMode, 0, 1);
        gp.add(scores, 0, 2);
        gp.add(settings, 0, 3);
        gp.add(exit, 0, 4);

        GridPane.setHalignment(startGame, HPos.CENTER);
        GridPane.setHalignment(practiceMode, HPos.CENTER);
        GridPane.setHalignment(scores, HPos.CENTER);
        GridPane.setHalignment(settings, HPos.CENTER);
        GridPane.setHalignment(exit, HPos.CENTER);

        gp.setAlignment(Pos.CENTER);

        bp.setTop(newTitle);
        BorderPane.setAlignment(newTitle, Pos.CENTER);
        BorderPane.setMargin(newTitle, new Insets(55, 0, 0, 0));
        bp.setCenter(gp);

        bp.setPrefSize(WIDTH, HEIGHT);

        Image image = new Image(getClass().getResource("/space.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background spaceBackground = new Background(backgroundImage);

        bp.setBackground(spaceBackground);

        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(bp);

        startGame.setOnAction(event
                -> {
            try {
                application.getPrimaryStage().setScene(new NameScene(application));
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        practiceMode.setOnAction(event
                -> {
            try {
                application.getPrimaryStage().setScene(new PracticeScene(application));
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        scores.setOnAction(event
                -> {
            try {
                application.getPrimaryStage().setScene(new HighScoreScene(application));
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        settings.setOnAction(event
                -> {
            try {
                application.getPrimaryStage().setScene(new SettingsScene(application));
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        exit.setOnAction(event
                -> {
            try {
                Platform.exit();
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
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

    /**
     * Sets the style for menu hyperlinks.
     *
     * @param link hyperlink
     */
    private void styleHyperlink(Hyperlink link) {
        link.setFont(BIG_FONT);
        link.setTextFill(colorDao.getColor());
    }

    @Override
    public void tick() {
    }
}
