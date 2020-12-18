package pong.ui;

import pong.domain.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static pong.ui.PongUi.BIG_FONT;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.INSETS_20;
import static pong.ui.PongUi.INSETS_30;
import static pong.ui.PongUi.INSETS_50;
import static pong.ui.PongUi.SMALL_FONT;
import static pong.ui.PongUi.WIDTH;
import static pong.ui.PongUi.colorDao;

/**
 * Creates a scene that asks the players' names for keeping track of high
 * scores.
 *
 */
public class NameScene extends AbstractScene {

    private final Text playerOne = new Text("Player One:");
    private final Text playerTwo = new Text("Player Two:");

    private final TextField playerOneTextField = new TextField();
    private final TextField playerTwoTextField = new TextField();
    private final Background textFieldBackground = new Background(new BackgroundFill(colorDao.getColor(), CornerRadii.EMPTY, Insets.EMPTY));

    private final Button start = new Button("Start Game!");
    private final Hyperlink back = new Hyperlink("Back To Menu");
    private final BorderPane pane = new BorderPane();

    private final Background startBackground = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
    private final Background startBackgroundHover = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));

    /**
     * Constructor for creating the scene.
     *
     * @param application application
     */
    public NameScene(PongUi application) {
        super(new Group(), WIDTH, HEIGHT);

        styleText(playerOne);
        styleText(playerTwo);

        styleTextField(playerOneTextField);
        styleTextField(playerTwoTextField);

        start.setBackground(startBackground);
        start.setFont(SMALL_FONT);
        start.setTextFill(Color.BLACK);
        start.setOnMouseEntered(e -> start.setBackground(startBackgroundHover));
        start.setOnMouseExited(e -> start.setBackground(startBackground));

        back.setFont(SMALL_FONT);
        back.setTextFill(Color.WHITE);

        VBox p1 = new VBox(playerOne, playerOneTextField);
        VBox p2 = new VBox(playerTwo, playerTwoTextField);
        VBox.setMargin(playerOne, INSETS_30);
        VBox.setMargin(playerTwo, INSETS_30);
        p1.setPadding(INSETS_20);
        p2.setPadding(INSETS_20);

        HBox names = new HBox(p1, p2);
        names.setSpacing(30);
        HBox.setMargin(p1, INSETS_50);
        HBox.setMargin(p2, INSETS_50);

        pane.setPrefSize(WIDTH, HEIGHT);
        pane.setTop(names);
        pane.setCenter(start);
        pane.setBottom(back);
        BorderPane.setMargin(back, INSETS_30);

        Image image = new Image(getClass().getResource("/space.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background SPACE_BACKGROUND = new Background(backgroundImage);

        pane.setBackground(SPACE_BACKGROUND);

        start.setOnAction(event -> {
            try {
                String firstName = "";
                String secondName = "";

                if (playerOneTextField != null) {
                    firstName = playerOneTextField.getText();
                }
                if (playerTwoTextField != null) {
                    secondName = playerTwoTextField.getText();
                }
                Player one = new Player(firstName);
                Player two = new Player(secondName);

                if (two.getName().equals("He Who Must Not Be Named")) {
                    two = new Player("The Nameless");
                }
                application.getPrimaryStage().setScene(new GameScene(application, one, two));

            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        back.setOnAction(event
                -> {
            application.getPrimaryStage().setScene(new WelcomeScene(application));
        }
        );

        Parent root = getRoot();
        Group rootGroup = (Group) root;

        rootGroup.getChildren().add(pane);
    }

    private void styleText(Text text) {
        text.setFill(Color.WHITE);
        text.setFont(BIG_FONT);
    }

    private void styleTextField(TextField textField) {
        textField.setFont(SMALL_FONT);
        textField.setBackground(textFieldBackground);
    }

    @Override
    public void tick() {

    }
}
