
package ui;

import domain.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScene extends AbstractScene {
    
    private final Text winner;
    private final Text proceed;
    private final Text menu;
    
    public EndScene(PongUi application, Player one, Player two) {
        super(new Group(), 850, 600);
        
        menu = new Text("Press [SPACE] to go back to main menu");
        menu.setFont(Font.font("Impact", 30));
        menu.setFill(Color.PINK);
        menu.setLayoutX(150);
        menu.setLayoutY(500);
        
        String win;
        String lose;
        
        if (one.getPoints() == 10) {
            win = one.getName();
            lose = two.getName();
        } else {
            win = two.getName();
            lose = one.getName();
        }
        winner = new Text(win + " wins!");
        winner.setFill(Color.PINK);
        winner.setFont(Font.font("Impact", 40));
        winner.setTextOrigin(VPos.CENTER);
        winner.setLayoutX(200);
        winner.setLayoutY(200);
        
        proceed = new Text("Salty, " + lose + "? Press [ENTER] to rematch!");
        proceed.setFill(Color.WHITE);
        proceed.setFont(Font.font("Impact", 30));
        proceed.setTextOrigin(VPos.CENTER);
        proceed.setLayoutX(80);
        proceed.setLayoutY(400);
        
        Parent root = getRoot();
        Group rootGroup = (Group) root;
        rootGroup.getChildren().add(winner);
        rootGroup.getChildren().add(proceed);
        rootGroup.getChildren().add(menu);

        setOnKeyReleased(x -> {
            if (x.getCode() == KeyCode.ENTER) {
                one.resetPoints();
                two.resetPoints();
                Stage stage = application.getPrimaryStage();
                stage.setScene(new GameScene(application, one, two));
            }
            if (x.getCode() == KeyCode.SPACE) {
                Stage stage = application.getPrimaryStage();
                stage.setScene(new WelcomeScene(application));
            }
        });

    }

    @Override
    public void tick() {
        
    }
    
}
