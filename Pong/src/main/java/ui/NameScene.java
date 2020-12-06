package ui;

import domain.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static ui.PongUi.BIG_FONT;
import static ui.PongUi.HEIGHT;
import static ui.PongUi.INSETS_20;
import static ui.PongUi.INSETS_30;
import static ui.PongUi.INSETS_50;
import static ui.PongUi.SMALL_FONT;
import static ui.PongUi.WIDTH;
/**
 * Creates a scene that asks the players' names for keeping track of high scores.
 *
 */
public class NameScene extends AbstractScene {

    private final Text playerOne = new Text("Player One:");
    private final Text playerTwo = new Text("Player Two:");
    
    private final TextField playerOneTextfield = new TextField();
    private final TextField playerTwoTextfield = new TextField();
    private final Background textfieldBackground = new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY));
    
    private final Button start = new Button("Start Game!");
    private final Hyperlink back = new Hyperlink("Back To Menu");
    private final BorderPane pane = new BorderPane();
    
    private final Background startBackground = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
    private final Background startBackgroundHover = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
    /**
     * Constructor for creating the scene.
     * 
     * @param application 
     */
    public NameScene(PongUi application) {
        super(new Group(), WIDTH, HEIGHT);

        playerOne.setFill(Color.WHITE);
        playerOne.setFont(BIG_FONT);        
        playerOneTextfield.setFont(SMALL_FONT);
        playerOneTextfield.setBackground(textfieldBackground);

        playerTwo.setFill(Color.WHITE);
        playerTwo.setFont(BIG_FONT);
        playerTwoTextfield.setFont(SMALL_FONT);
        playerTwoTextfield.setBackground(textfieldBackground);

        start.setBackground(startBackground);
        start.setFont(SMALL_FONT);
        start.setTextFill(Color.BLACK);
        start.setOnMouseEntered(e -> start.setBackground(startBackgroundHover));
        start.setOnMouseExited(e -> start.setBackground(startBackground));

        back.setFont(SMALL_FONT);
        back.setTextFill(Color.WHITE);

        VBox p1 = new VBox(playerOne, playerOneTextfield);
        VBox p2 = new VBox(playerTwo, playerTwoTextfield);
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
        
        start.setOnAction(event -> {
            try {
                String firstName = "";
                String secondName = "";
                
                if (playerOneTextfield != null) {
                    firstName = playerOneTextfield.getText();
                }
                if (playerTwoTextfield != null) {
                    secondName = playerTwoTextfield.getText();
                }
                Player one = new Player(firstName);
                Player two = new Player(secondName);
                
                if (one.getName().equals("He Who Must Not Be Named")) {
                    two = new Player("The Nameless");
                }
                application.getPrimaryStage().setScene(new GameScene(application, one, two));

            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
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
