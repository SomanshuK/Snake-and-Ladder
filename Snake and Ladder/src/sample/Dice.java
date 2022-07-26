package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class Dice {
    private static int size;

    private static Image d1 = new Image("dice1.png");
    private static final ImageView face1 = new ImageView(d1);

    private static Image d2 = new Image("dice2.png");
    private static final ImageView face2 = new ImageView(d2);

    private static Image d3 = new Image("dice3.png");
    private static final ImageView face3 = new ImageView(d3);

    private static Image d4 = new Image("dice4.png");
    private static final ImageView face4 = new ImageView(d4);

    private static Image d5 = new Image("dice5.png");
    private static final ImageView face5 = new ImageView(d5);

    private static Image d6 = new Image("dice6.png");
    private static final ImageView face6 = new ImageView(d6);

    static HashMap<Integer, Image> diceFaces = new HashMap<>();

    public static void makeDiceFaces(){
        diceFaces.put(1, getD1());
        diceFaces.put(2, getD2());
        diceFaces.put(3, getD3());
        diceFaces.put(4, getD4());
        diceFaces.put(5, getD5());
        diceFaces.put(6, getD6());
    }

    public static Image getD1() {
        return d1;
    }

    public static Image getD2() {
        return d2;
    }

    public static Image getD3() {
        return d3;
    }

    public static Image getD4() {
        return d4;
    }

    public static Image getD5() {
        return d5;
    }

    public static Image getD6() {
        return d6;
    }




}
