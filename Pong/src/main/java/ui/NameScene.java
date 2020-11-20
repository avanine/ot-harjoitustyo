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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NameScene extends AbstractScene {

    private final Text player1;
    private final Text player2;
    private final TextField player1Name;
    private final TextField player2Name;
    private final Button start;
    private final BorderPane pane;

    public NameScene(PongUi application) {
        super(new Group(), 850, 600);

        player1 = new Text("Player One:");
        player1.setFill(Color.WHITE);
        player1.setFont(Font.font("Impact", 40));
        player1Name = new TextField();
        player1Name.setFont(Font.font("Impact", 30));
        player1Name.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

        player2 = new Text("Player Two:");
        player2.setFill(Color.WHITE);
        player2.setFont(Font.font("Impact", 40));
        player2Name = new TextField();
        player2Name.setFont(Font.font("Impact", 30));
        player2Name.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

        start = new Button("Start Game!");
        start.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        start.setFont(Font.font("Impact", 30));
        start.setTextFill(Color.BLACK);
        start.setOnMouseEntered(e -> start.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));
        start.setOnMouseExited(e -> start.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY))));

        Hyperlink back = new Hyperlink("Back To Menu");
        back.setFont(Font.font("Impact", 30));
        back.setTextFill(Color.WHITE);

        VBox p1 = new VBox(player1, player1Name);
        VBox p2 = new VBox(player2, player2Name);
        VBox.setMargin(player1, new Insets(30));
        VBox.setMargin(player2, new Insets(30));
        p1.setPadding(new Insets(20));
        p2.setPadding(new Insets(20));
        
        HBox names = new HBox(p1, p2);
        names.setSpacing(30);
        HBox.setMargin(p1, new Insets(50));
        HBox.setMargin(p2, new Insets(50));
        
        pane = new BorderPane();
        pane.setPrefSize(850, 600);
        pane.setTop(names);
        pane.setCenter(start);
        pane.setBottom(back);
        BorderPane.setMargin(back, new Insets(30));
        
        start.setOnAction(event -> {
            try {
                String firstName = "";
                String secondName = "";
                
                if (player1Name != null) {
                    firstName = player1Name.getText();
                }
                if (player2Name != null) {
                    secondName = player2Name.getText();
                }
                Player one = new Player(firstName);
                Player two = new Player(secondName);
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
