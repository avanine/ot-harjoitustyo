package pong.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static pong.ui.PongUi.BIG_FONT;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.INSETS_30;
import static pong.ui.PongUi.INSETS_50;
import static pong.ui.PongUi.SMALL_FONT;
import static pong.ui.PongUi.WIDTH;
import static pong.ui.PongUi.dao;

/**
 * Creates a scene that shows players with the most wins.
 *
 */
public class HighScoreScene extends AbstractScene {

    private final Text noScoresYet;
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

        highScoreText.setFill(Color.CHOCOLATE);
        highScoreText.setFont(Font.font("Impact", 60));

        noScoresYet = new Text("This game hasn't been played yet :(");
        noScoresYet.setFill(Color.PINK);
        noScoresYet.setFont(BIG_FONT);

        back = new Hyperlink("Back to Menu");
        back.setTextFill(Color.WHITE);
        back.setFont(SMALL_FONT);

        topPlayers = new Text(dao.getTopScores(5));
        topPlayers.setFill(Color.PINK);
        topPlayers.setFont(SMALL_FONT);

        BorderPane pane = new BorderPane();
        pane.setPrefSize(WIDTH, HEIGHT);

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
