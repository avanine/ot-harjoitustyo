
package ui;

import domain.Player;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScene extends AbstractScene {
    
    private final Text winner;
    private final Text proceed;
    
    public EndScene(PongUi application, Player one, Player two) {
        super(new Group(), 850, 600);
        
        String win;
        
        if (one.getPoints() == 10) {
            win = one.getName();
        } else {
            win = two.getName();
        }
        winner = new Text(win + " wins!");
        winner.setFill(Color.PINK);
        winner.setFont(Font.font("Impact", 40));
        winner.setTextOrigin(VPos.CENTER);
        winner.setLayoutX(200);
        winner.setLayoutY(200);
        
        proceed = new Text("Press [ENTER] to proceed");
        proceed.setFill(Color.WHITE);
        proceed.setFont(Font.font("Impact", 40));
        proceed.setTextOrigin(VPos.CENTER);
        proceed.setLayoutX(190);
        proceed.setLayoutY(400);
        
        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(winner);
        rootGroup.getChildren().add(proceed);
        
        setOnKeyReleased(x -> {
            if (x.getCode() == KeyCode.ENTER) {
                Stage stage = application.getPrimaryStage();
                stage.setScene(new WelcomeScene(application));
            }
        });
    }

    @Override
    public void tick() {
        
    }
    
}
