package pong.ui;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import static pong.ui.PongUi.BIG_FONT;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.SMALL_FONT;
import static pong.ui.PongUi.WIDTH;
import static pong.ui.PongUi.colorDao;
import static pong.ui.PongUi.dao;

/**
 * Creates a settings scene.
 *
 */
public class SettingsScene extends AbstractScene {

    private final int rectangleSize = 50;

    private final Text chooseColor = new Text("Choose a color theme for the game:");
    private final Hyperlink menu = new Hyperlink("Back To Menu");
    private final Button clearScores = new Button("Clear High Scores");
    private final Rectangle white = new Rectangle(rectangleSize, rectangleSize);
    private final Rectangle blue = new Rectangle(rectangleSize, rectangleSize);
    private final Rectangle green = new Rectangle(rectangleSize, rectangleSize);
    private final Rectangle pink = new Rectangle(rectangleSize, rectangleSize);
    private final Rectangle yellow = new Rectangle(rectangleSize, rectangleSize);

    private Text x;

    PongUi application;

    /**
     * Sets up the scene.
     *
     * @param application application
     */
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

        clearScores.setFont(SMALL_FONT);
        clearScores.setLayoutX(290);
        clearScores.setLayoutY(380);
        clearScores.setOnMouseEntered(e -> clearScores.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))));
        clearScores.setOnMouseExited(e -> clearScores.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY))));

        clearScores.setOnAction(event -> {
            Alert alert = createAlert();
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                dao.clearScores();
            } else {
                alert.close();
            }

        });

        chooseColor.setFill(Color.WHITE);
        chooseColor.setFont(SMALL_FONT);
        chooseColor.setLayoutX(210);
        chooseColor.setLayoutY(120);

        white.setFill(Color.WHITE);
        white.setLayoutX(240);
        white.setLayoutY(180);

        white.setOnMouseClicked(event -> {
            rectangleClicked(white);
            colorDao.changeColor("white", (int) x.getLayoutX());
        });

        blue.setFill(Color.BLUE);
        blue.setLayoutX(320);
        blue.setLayoutY(180);

        blue.setOnMouseClicked(event -> {
            rectangleClicked(blue);
            colorDao.changeColor("blue", (int) x.getLayoutX());
        });

        green.setFill(Color.YELLOWGREEN);
        green.setLayoutX(400);
        green.setLayoutY(180);

        green.setOnMouseClicked(event -> {
            rectangleClicked(green);
            colorDao.changeColor("green", (int) x.getLayoutX());
        });

        pink.setFill(Color.PINK);
        pink.setLayoutX(480);
        pink.setLayoutY(180);

        pink.setOnMouseClicked(event -> {
            rectangleClicked(pink);
            colorDao.changeColor("pink", (int) x.getLayoutX());
        });

        yellow.setFill(Color.YELLOW);
        yellow.setLayoutX(560);
        yellow.setLayoutY(180);

        yellow.setOnMouseClicked(event -> {
            rectangleClicked(yellow);
            colorDao.changeColor("yellow", (int) x.getLayoutX());
        });

        x = new Text("X");
        x.setFont(BIG_FONT);
        x.setFill(Color.RED);
        x.setLayoutX(colorDao.getMarkerLayout());
        x.setLayoutY(220);

        Parent root = getRoot();
        Group rootGroup = (Group) root;

        rootGroup.getChildren().add(menu);
        rootGroup.getChildren().add(clearScores);
        rootGroup.getChildren().add(chooseColor);
        rootGroup.getChildren().add(white);
        rootGroup.getChildren().add(blue);
        rootGroup.getChildren().add(green);
        rootGroup.getChildren().add(pink);
        rootGroup.getChildren().add(yellow);
        rootGroup.getChildren().add(x);
    }

    /**
     * Changes x-layout of the marker when rectangle is clicked.
     *
     * @param color rectangle that was clicked
     */
    private void rectangleClicked(Rectangle color) {
        x.setLayoutX(color.getLayoutX() + 15);
    }

    /**
     * Creates an alert that confirms deleting high scores.
     *
     * @return alert
     */
    private Alert createAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Image image = new Image(getClass().getResource("/warning.png").toExternalForm());
        ImageView view = new ImageView(image);
        view.setFitWidth(62);
        view.setFitHeight(54);
        alert.setGraphic(view);
        alert.setTitle("!DELETING HIGH SCORES!");
        alert.setHeaderText("This action cannot be undone.");
        alert.setResizable(false);
        alert.setContentText("Are you sure?");

        return alert;
    }

    @Override
    public void tick() {

    }

}
