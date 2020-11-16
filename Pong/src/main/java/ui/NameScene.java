package ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NameScene extends AbstractScene {

    private final Text player1;
    private final Text player2;
    private final TextField player1Name;
    private final TextField player2Name;
    private final Button start;
    private BorderPane pane;

    public NameScene(WelcomeScene welcomeScene) {
        super(new Group(), 850, 600);
        setFill(Color.BLACK);

        player1 = new Text("Player One:");
        player1.setFill(Color.WHITE);
        player1.setFont(Font.font("Impact", 40));
        player1Name = new TextField();

        player2 = new Text("Player Two:");
        player2.setFill(Color.WHITE);
        player2.setFont(Font.font("Impact", 40));
        player2Name = new TextField();

        start = new Button("Start Game!");

        FlowPane flow = new FlowPane(player1, player1Name);
        FlowPane flow2 = new FlowPane(player2, player2Name);
        FlowPane.setMargin(player1, new Insets(30));
        FlowPane.setMargin(player2, new Insets(30));
        FlowPane.setMargin(player1Name, new Insets(30));
        FlowPane.setMargin(player2Name, new Insets(30));
        flow.setPadding(new Insets(30, 0, 0, 30));
        flow2.setPadding(new Insets(30, 60, 0, 0));

        pane = new BorderPane();
        pane.setLeft(flow);
        pane.setRight(flow2);
        pane.setBottom(start);

        pane.setPrefSize(600, 450);

        Insets insets = new Insets(40);
        BorderPane.setMargin(flow, insets);
        BorderPane.setMargin(flow2, insets);
        BorderPane.setAlignment(start, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(start, new Insets(60));

        start.setOnAction((event) -> {
            try {
                // !!! these don't work, figure out why !!!
                //welcomeScene.getPrimaryStage().setScene(new GameScene(welcomeScene));
                //welcomeScene.changeToGameScene();
                Text text = new Text("Game coming soon...");
                text.setFill(Color.PINK);
                text.setFont(Font.font("Impact", 40));
                pane.setBottom(text);
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Parent root = getRoot();
        Group rootGroup = (Group) root;

        rootGroup.getChildren().add(pane);
    }

    @Override
    public void tick() {

    }

}
