package pong.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.paint.Color;
/**
 * Class that is responsible for saving and loading theme color.
 *
 */
public final class ThemeColorDao {
    
    String color;
    int markerLayout;
    private final String file;
    /**
     * Sets up the initial theme color.
     * 
     * @param file file
     * @throws IOException if file doesn't exist
     */
    public ThemeColorDao(String file) throws IOException {
        this.color = "pink";
        this.markerLayout = 495;
        this.file = file;
        load();
    }
    /**
     * Loads theme color from file.
     * 
     * @throws IOException if file doesn't exist
     */
    public void load() throws IOException {
        try (Scanner scan = new Scanner(new File(file))) {
            while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(";");
            color = split[0];
            markerLayout = Integer.parseInt(split[1]);
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    /**
     * Saves new theme color.
     */
    private void save() {
        try (FileWriter writer = new FileWriter(new File(file))) {
            writer.write("");
            writer.write(color + ";" + markerLayout);
        } catch (IOException e) {
        }
    }
    /**
     * Changes theme color.
     * 
     * @param color color
     * @param markerLayout marker layout
     */
    public void changeColor(String color, int markerLayout) {
        this.color = color;
        this.markerLayout = markerLayout;
        save();
    }
    
    public Color getColor() {
        switch(color) {
            case "white":
                return Color.WHITE;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.YELLOWGREEN;
            case "yellow":
                return Color.YELLOW;
            default:
                return Color.PINK;
        }
    }
    
    public int getMarkerLayout() {
        return markerLayout;
    }
}
