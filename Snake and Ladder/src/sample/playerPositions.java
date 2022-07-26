package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class playerPositions {
    private static HashMap<Integer, ArrayList<Integer>> mapPlayer1 = new HashMap<>();
    private static HashMap<Integer, ArrayList<Integer>> mapPlayer2 = new HashMap<>();

    public static HashMap<Integer, ArrayList<Integer>> getMapPlayer1() {
        return mapPlayer1;
    }

    public static void setMapPlayer1(HashMap<Integer, ArrayList<Integer>> mapPlayer1) {
        playerPositions.mapPlayer1 = mapPlayer1;
    }

    public static HashMap<Integer, ArrayList<Integer>> getMapPlayer2() {
        return mapPlayer2;
    }

    public static void setMapPlayer2(HashMap<Integer, ArrayList<Integer>> mapPlayer2) {
        playerPositions.mapPlayer2 = mapPlayer2;
    }
}
