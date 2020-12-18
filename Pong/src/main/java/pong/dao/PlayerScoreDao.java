package pong.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that saves high scores.
 *
 */
public final class PlayerScoreDao {

    private final Map<String, Integer> scores;
    private final String file;
    private final ArrayList<Integer> numberOfWins;

    /**
     * Initializes the handling of scores.
     *
     * @param file name of the file that is used
     * @throws IOException if file doesn't exist
     */
    public PlayerScoreDao(String file) throws IOException {
        this.scores = new HashMap<>();
        this.file = file;
        this.numberOfWins = new ArrayList<>();
        load();
    }

    /**
     * Copies wins from the file to the hashmap, so they can be updated.
     *
     * @throws IOException if file doesn't exist
     */
    public void load() throws IOException {
        try (Scanner scan = new Scanner(new File(file))) {
            while (scan.hasNextLine()) {
                String[] split = scan.nextLine().split(";");
                String name = split[0];
                int score = Integer.parseInt(split[1]);
                scores.put(name, score);
                numberOfWins.add(score);
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    /**
     * Transfers wins from the hashmap to the file.
     */
    private void save() {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (String name : scores.keySet()) {
                writer.write(name + ";" + scores.get(name) + "\n");
            }

        } catch (IOException e) {
        }
    }

    /**
     * Adds new win for given player.
     *
     * @param name winner's name
     */
    public void addNewWin(String name) {
        if (!scores.containsKey(name)) {
            scores.put(name, 0);
        }
        int previousScore = scores.get(name);
        scores.put(name, previousScore + 1);
        numberOfWins.add(scores.get(name));
        save();
    }

    /**
     * Returns a string representation of the chosen amount of top players and
     * their scores.
     *
     * @param amount amount of top players to be returned
     * @return top players and their scores
     */
    public String getTopScores(int amount) {

        if (scores.isEmpty()) {
            return "";
        }

        Collections.sort(numberOfWins);
        Collections.reverse(numberOfWins);
        String topPlayers = "";
        for (int i = 0; i < numberOfWins.size(); i++) {
            for (String player : scores.keySet()) {
                if (Objects.equals(scores.get(player), numberOfWins.get(i)) && !topPlayers.contains(player)) {
                    topPlayers += player + "    -   " + numberOfWins.get(i) + "\n";
                }
            }
        }
        if (scores.size() > amount) {
            return chosenAmountOfPlayers(amount, topPlayers);
        }
        return topPlayers;
    }

    /**
     * Returns selected amount of top players.
     *
     * @param amount how many players should be returned
     * @param topPlayers all players who have wins
     * @return chosen amount of players and their scores
     */
    private String chosenAmountOfPlayers(int amount, String topPlayers) {
        String chosenAmountOfPlayers = "";
        String[] split = topPlayers.split("\n");
        for (int i = 0; i < amount; i++) {
            chosenAmountOfPlayers += split[i] + "\n";
        }
        return chosenAmountOfPlayers;
    }

    /**
     * Clears all saved scores.
     */
    public void clearScores() {
        try (FileWriter writer = new FileWriter(new File(file))) {
            writer.write("");
            writer.close();
            scores.clear();

        } catch (IOException e) {
        }
    }
}
