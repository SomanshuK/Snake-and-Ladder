package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player extends Circle {
    private int stage = 1;
    private int playerLevel = 1;
    private int xPos;
    private int yPos;

    public int getStage(){
        return this.stage;
    }

    public void setStage(int n){
        this.stage = n;
    }

    public Player(String playerId, Color color, int radius){
        this.setRadius(radius);
        this.setId(playerId);
//        this.setTranslateX(xPos);
//        this.setTranslateY(yPos);
        this.setFill(color);


    }

    public void setAtInitialPos(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;

        this.setTranslateX(xPos);
        this.setTranslateY(yPos);

    }

    public void translatePlayer(int xPos, int yPos) {  // animation
        TranslateTransition animate = new TranslateTransition(Duration.millis(100.0D));
        animate.setToX((double)xPos);
        animate.setToY((double)yPos);
        animate.setAutoReverse(false);
        animate.play();
    }


    public int getPlayerLevel(){
        return this.playerLevel;
    }

    public void increasePlayerLevel(){
        this.playerLevel += 1;
    }

    public int getxPos(){
        return this.xPos;
    }

    public int getyPos(){
        return this.yPos;
    }

    public void setxPos(int x){
        this.xPos = x;
    }

    public void setyPos(int y){
        this.yPos = y;
    }

    public void setPlayerLevel(int l){
        this.playerLevel = l;
    }

}
