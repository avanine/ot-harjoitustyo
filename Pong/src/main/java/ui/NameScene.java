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
import static ui.PongUi.SMALL_FONT;
import static ui.PongUi.WIDTH;
/**
 * Creates a scene that asks the players' names for keeping track of high scores
 *
 */
public class NameScene extends AbstractScene {

    private final Text PLAYER_ONE = new Text("Player One:");
    private final Text PLAYER_TWO = new Text("Player Two:");
    
    private final TextField PLAYER_ONE_TEXTFIELD = new TextField();
    private final TextField PLAYER_TWO_TEXTFIELD = new TextField();
    private final Background TEXTFIELD_BACKGROUND = new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY));
    
    private final Button START = new Button("Start Game!");
    private final Hyperlink BACK = new Hyperlink("Back To Menu");
    private final BorderPane PANE = new BorderPane();
    
    private final Background START_BACKGROUND = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
    private final Background START_BACKGROUND_HOVER = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
    
    private final Insets INSETS_20 = new Insets(20);
    private final Insets INSETS_30 = new Insets(30);
    private final Insets INSETS_50 = new Insets(50);
    
    
    public NameScene(PongUi application) {
        super(new Group(), WIDTH, HEIGHT);

        PLAYER_ONE.setFill(Color.WHITE);
        PLAYER_ONE.setFont(BIG_FONT);        
        PLAYER_ONE_TEXTFIELD.setFont(SMALL_FONT);
        PLAYER_ONE_TEXTFIELD.setBackground(TEXTFIELD_BACKGROUND);

        PLAYER_TWO.setFill(Color.WHITE);
        PLAYER_TWO.setFont(BIG_FONT);
        PLAYER_TWO_TEXTFIELD.setFont(SMALL_FONT);
        PLAYER_TWO_TEXTFIELD.setBackground(TEXTFIELD_BACKGROUND);

        START.setBackground(START_BACKGROUND);
        START.setFont(SMALL_FONT);
        START.setTextFill(Color.BLACK);
        START.setOnMouseEntered(e -> START.setBackground(START_BACKGROUND_HOVER));
        START.setOnMouseExited(e -> START.setBackground(START_BACKGROUND));

        BACK.setFont(SMALL_FONT);
        BACK.setTextFill(Color.WHITE);

        VBox p1 = new VBox(PLAYER_ONE, PLAYER_ONE_TEXTFIELD);
        VBox p2 = new VBox(PLAYER_TWO, PLAYER_TWO_TEXTFIELD);
        VBox.setMargin(PLAYER_ONE, INSETS_30);
        VBox.setMargin(PLAYER_TWO, INSETS_30);
        p1.setPadding(INSETS_20);
        p2.setPadding(INSETS_20);
        
        HBox names = new HBox(p1, p2);
        names.setSpacing(30);
        HBox.setMargin(p1, INSETS_50);
        HBox.setMargin(p2, INSETS_50);
        
        PANE.setPrefSize(WIDTH, HEIGHT);
        PANE.setTop(names);
        PANE.setCenter(START);
        PANE.setBottom(BACK);
        BorderPane.setMargin(BACK, INSETS_30);
        
        START.setOnAction(event -> {
            try {
                String firstName = "";
                String secondName = "";
                
                if (PLAYER_ONE_TEXTFIELD != null) {
                    firstName = PLAYER_ONE_TEXTFIELD.getText();
                }
                if (PLAYER_TWO_TEXTFIELD != null) {
                    secondName = PLAYER_TWO_TEXTFIELD.getText();
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
        
        BACK.setOnAction(event -> {
            application.getPrimaryStage().setScene(new WelcomeScene(application));
        });

        Parent root = getRoot();
        Group rootGroup = (Group) root;

        rootGroup.getChildren().add(PANE);
    }

    @Override
    public void tick() {

    }

}
