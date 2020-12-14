
package pong.ui;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Color;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.SMALL_FONT;
import static pong.ui.PongUi.WIDTH;

public class SettingsScene extends AbstractScene {
    
    private final Hyperlink menu = new Hyperlink("Back To Menu");
    
    PongUi application;
    
    public SettingsScene(PongUi application) {
        super(new Group(), WIDTH, HEIGHT);
        
        this.application = application;
        
        menu.setTextFill(Color.WHITE);
        menu.setFont(SMALL_FONT);
        menu.setLayoutX(30);
        menu.setLayoutY(520);
        
        menu.setOnAction(event -> {
            application.getPrimaryStage().setScene(new WelcomeScene(application));
        });
        
        Parent root = getRoot();
        Group rootGroup = (Group) root;

        rootGroup.getChildren().add(menu);
    }

    @Override
    public void tick() {
        
    }
    
}
