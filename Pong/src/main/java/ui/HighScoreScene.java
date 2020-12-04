package ui;

import dao.PlayerScoreDao;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static ui.PongUi.BIG_FONT;
import static ui.PongUi.HEIGHT;
import static ui.PongUi.SMALL_FONT;
import static ui.PongUi.WIDTH;

/**
 * Creates a scene that shows players with the most wins
 *
 */
public class HighScoreScene extends AbstractScene {

    private final Text noScoresYet;
    private final Hyperlink back;
    // private final PlayerScoreDao dao = new PlayerScoreDao();

    public HighScoreScene(PongUi application) {
        super(new Group(), WIDTH, HEIGHT);

        noScoresYet = new Text("This game hasn't been played yet :(");
        noScoresYet.setFill(Color.PINK);
        noScoresYet.setFont(BIG_FONT);

        back = new Hyperlink("Back to Menu");
        back.setTextFill(Color.WHITE);
        back.setFont(SMALL_FONT);

        BorderPane pane = new BorderPane();
        pane.setPrefSize(WIDTH, HEIGHT);
        /*
        if (dao.isEmpty()) {*/
        pane.setCenter(noScoresYet);
        /*} else {
            Text highscores = new Text("High Scores");
            pane.setTop(highscores);
            pane.setCenter(new Text("Scores"));
        }
         */
        pane.setBottom(back);
        BorderPane.setMargin(back, new Insets(30));

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
