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
 * Creates an after-game scene that shows the winner.
 *
 */
public class EndScene extends AbstractScene {

    private final Text winnerText;
    private final Text proceedText;
    private final Text menuText;
    /**
     * Constructor that creates the end scene.
     * 
     * @param application application
     * @param one player one
     * @param two player two
     */
    public EndScene(PongUi application, Player one, Player two) {
        super(new Group(), WIDTH, HEIGHT);

        menuText = new Text("Press [SPACE] to go back to main menu");
        menuText.setFont(SMALL_FONT);
        menuText.setFill(Color.PINK);
        menuText.setLayoutX(150);
        menuText.setLayoutY(500);

        String winner;
        String loser;

        if (one.getPoints() == 10) {
            winner = one.getName();
            loser = two.getName();
        } else {
            winner = two.getName();
            loser = one.getName();
        }

        winnerText = new Text(winner + " wins!");
        winnerText.setFill(Color.PINK);
        winnerText.setFont(BIG_FONT);
        winnerText.setTextOrigin(VPos.CENTER);
        winnerText.setLayoutX(200);
        winnerText.setLayoutY(200);

        proceedText = new Text("Salty, " + loser + "? Press [ENTER] to rematch!");
        proceedText.setFill(Color.WHITE);
        proceedText.setFont(SMALL_FONT);
        proceedText.setTextOrigin(VPos.CENTER);
        proceedText.setLayoutX(80);
        proceedText.setLayoutY(400);

        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(winnerText);
        rootGroup.getChildren().add(proceedText);
        rootGroup.getChildren().add(menuText);

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
