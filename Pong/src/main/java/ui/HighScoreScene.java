
package ui;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HighScoreScene extends AbstractScene {
    
    private final Text noScoresYet;
    private final Hyperlink back;
    
    public HighScoreScene(WelcomeScene welcomeScene) {
        super(new Group(), 850, 600);
        setFill(Color.BLACK);
        
        noScoresYet = new Text("This game hasn't been played yet :(");
        noScoresYet.setFill(Color.PINK);
        noScoresYet.setFont(Font.font("Impact", 40));
        back = new Hyperlink("Back to Menu");
        back.setTextFill(Color.WHITE);
        back.setFont(Font.font("Impact", 30));
        
        BorderPane pane = new BorderPane();
        pane.setPrefSize(850, 600);
        pane.setCenter(noScoresYet);
        pane.setBottom(back);
        BorderPane.setMargin(back, new Insets(30));
        
        back.setOnAction(event -> {
            welcomeScene.getPrimaryStage().setScene(welcomeScene.getScene());
        });
        
        Parent root = getRoot();
        Group rootGroup = (Group) root;

        rootGroup.getChildren().add(pane);
    }

    @Override
    public void tick() {
        
    }
    
}
