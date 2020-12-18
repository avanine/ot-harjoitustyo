package pong.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.INSETS_30;
import static pong.ui.PongUi.INSETS_50;
import static pong.ui.PongUi.SMALL_FONT;
import static pong.ui.PongUi.WIDTH;
import static pong.ui.PongUi.colorDao;
import static pong.ui.PongUi.dao;

/**
 * Creates a scene that shows players with the most wins.
 *
 */
public class HighScoreScene extends AbstractScene {

    private final Hyperlink back;
    private final Text topPlayers;
    private final Text highScoreText = new Text("High Scores");

    /**
     * Creates the scene for showing high scores.
     *
     * @param application application
     * @throws java.lang.Exception if file doesn't exist
     */
    public HighScoreScene(PongUi application) throws Exception {
        super(new Group(), WIDTH, HEIGHT);

        highScoreText.setFill(Color.WHITE);
        highScoreText.setFont(Font.font("Impact", 60));

        back = new Hyperlink("Back to Menu");
        back.setTextFill(Color.WHITE);
        back.setFont(SMALL_FONT);

        topPlayers = new Text(dao.getTopScores(5));
        topPlayers.setFill(colorDao.getColor());
        topPlayers.setFont(SMALL_FONT);

        BorderPane pane = new BorderPane();
        pane.setPrefSize(WIDTH, HEIGHT);
        
        Image image = new Image(getClass().getResource("/confetti.png").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background CONFETTI_BACKGROUND = new Background(backgroundImage);
        
        pane.setBackground(CONFETTI_BACKGROUND);

        pane.setTop(highScoreText);
        pane.setCenter(topPlayers);
        pane.setBottom(back);
        BorderPane.setMargin(back, INSETS_30);
        BorderPane.setAlignment(highScoreText, Pos.CENTER);
        BorderPane.setMargin(highScoreText, INSETS_50);

        back.setOnAction(event -> {
            application.getPrimaryStage().setScene(new WelcomeScene(application));
        });

        Parent root = getRoot();
        Group rootGroup = (Group) root;

        rootGroup.getChildren().add(pane);
    }

    @Override
    public void tick() {

    }

}
