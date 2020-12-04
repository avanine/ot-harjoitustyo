package ui;

import dao.PlayerScoreDao;
import domain.Player;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static ui.PongUi.BIG_FONT;
import static ui.PongUi.HEIGHT;
import static ui.PongUi.SMALL_FONT;
import static ui.PongUi.WIDTH;

/**
 * Creates an after-game scene that shows the winner
 *
 */
public class EndScene extends AbstractScene {

    private final Text WINNER_TEXT;
    private final Text PROCEED_TEXT;
    private final Text MENU_TEXT;

    public EndScene(PongUi application, Player one, Player two) {
        super(new Group(), WIDTH, HEIGHT);

        MENU_TEXT = new Text("Press [SPACE] to go back to main menu");
        MENU_TEXT.setFont(SMALL_FONT);
        MENU_TEXT.setFill(Color.PINK);
        MENU_TEXT.setLayoutX(150);
        MENU_TEXT.setLayoutY(500);

        String winner;
        String loser;

        if (one.getPoints() == 10) {
            winner = one.getName();
            loser = two.getName();
        } else {
            winner = two.getName();
            loser = one.getName();
        }

        WINNER_TEXT = new Text(winner + " wins!");
        WINNER_TEXT.setFill(Color.PINK);
        WINNER_TEXT.setFont(BIG_FONT);
        WINNER_TEXT.setTextOrigin(VPos.CENTER);
        WINNER_TEXT.setLayoutX(200);
        WINNER_TEXT.setLayoutY(200);

        PROCEED_TEXT = new Text("Salty, " + loser + "? Press [ENTER] to rematch!");
        PROCEED_TEXT.setFill(Color.WHITE);
        PROCEED_TEXT.setFont(SMALL_FONT);
        PROCEED_TEXT.setTextOrigin(VPos.CENTER);
        PROCEED_TEXT.setLayoutX(80);
        PROCEED_TEXT.setLayoutY(400);

        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(WINNER_TEXT);
        rootGroup.getChildren().add(PROCEED_TEXT);
        rootGroup.getChildren().add(MENU_TEXT);

        Stage stage = application.getPrimaryStage();

        setOnKeyReleased(x -> {
            if (x.getCode() == KeyCode.ENTER) {
                one.resetPoints();
                two.resetPoints();
                stage.setScene(new GameScene(application, one, two));
            }
            if (x.getCode() == KeyCode.SPACE) {
                stage.setScene(new WelcomeScene(application));
            }
        });

    }

    @Override
    public void tick() {

    }

}
