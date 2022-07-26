package sample;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main extends Application {
    public static int blockSize = 60;
    public static int height = 10;
    public static int width = 10;
    public static int circleSize = 25;

    public static final int  player1xPos = 30;
    public static final int player1ypos = 570;
    public static final int player2xPos = 30;
    public static final int player2ypos = 570;

    public static boolean startButtonLock = false;
    public static boolean hasGameStart = false;
    public static boolean player1Turn = false;
    public static boolean player2Turn = false;
    public static boolean player1Won = false;
    public static boolean player2Won = false;

    Button start = new Button("Start Game");


    Font font;

    // creating players
    Player p1 = new Player("Player 1", Color.BLACK, circleSize);
    Player p2 = new Player("Player 2", Color.RED, circleSize);


    public Group blocks = new Group();

    public Parent createGame(){
        StackPane root = new StackPane();
        root.setPrefSize((double)(height * blockSize), (double)(width * blockSize + 180));
        root.getChildren().addAll(blocks);
        // creating layouts

        createMapPlayer1();
        createMapPlayer2();



        // creating background image

        Image backGroundImage = new Image("snakeandladder.jpeg");
        ImageView imgView = new ImageView(backGroundImage);
        imgView.setFitHeight(600.0D);
        imgView.setFitWidth(600.0D);

        font = Font.font("Verdana", FontWeight.BOLD, 15.0D);  // creating a genral font.

        // creating Dice Image;

        Image initial = new Image("dice1.png");

        ImageView DiceP1 = new ImageView(initial);
        DiceP1.setFitHeight(50);
        DiceP1.setFitWidth(50);
        DiceP1.setX(30);
        DiceP1.setY(620);

        ImageView DiceP2 = new ImageView(initial);
        DiceP2.setFitHeight(50);
        DiceP2.setFitWidth(50);
        DiceP2.setX(512.5);
        DiceP2.setY(620);

        Dice.makeDiceFaces();

        blocks.getChildren().addAll( DiceP1, DiceP2);



        // creating start button.

        Label l1 = new Label("The game has started");
        start.setTranslateX(250.0D);
        start.setTranslateY(635.0D);
        start.setFont(this.font);
        blocks.getChildren().add(start);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(!startButtonLock){
                    p1.setStage(1);
                    p2.setStage(1);
                    hasGameStart = true;
                    l1.setFont(Main.this.font);
                    l1.setTranslateX(230.0D);
                    l1.setTranslateY(-34.0D);
                    blocks.getChildren().add(l1);
                    p1.setAtInitialPos(player1xPos, player1ypos);
                    p2.setAtInitialPos(player2xPos, player2ypos);
                    blocks.getChildren().addAll(p1,p2);

                    player1Turn = true;
                    start.setText("Player 1 Turn");
                    startButtonLock = true;


                }
            }
        });

        // player 1 button.

        Button player1Button = new Button("P1");
        player1Button.setTranslateX(32.0D);
        player1Button.setTranslateY(680.0D);
        player1Button.setTextFill(Color.BLACK);
        player1Button.setFont(this.font);
        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(hasGameStart && player1Turn){
                    int diceRoll = (new Random()).nextInt(6) + 1;

                    Image img = Dice.diceFaces.get(diceRoll);
                    DiceP1.setImage(img);
                    int difference = 100 - p1.getStage();

                    if(p1.getStage() == 1){
                        if(diceRoll == 1){
                            movePlayer(diceRoll, p1);
                            p1.setStage(p1.getStage() + diceRoll);
                            player1Turn = false;
                            player2Turn = true;
                            start.setText("Player 2 turn");

                        }

                        else{
                            player1Turn = false;
                            player2Turn = true;
                            start.setText("Player 2 turn");
                        }
                    }


                    else if(p1.getStage() >= 94){
                        if(diceRoll + p1.getStage() < 100){
                            movePlayer(diceRoll, p1);
                            p1.setStage(p1.getStage() + diceRoll);
                            player1Turn = false;
                            player2Turn = true;
                            start.setText("Player 2 turn");

                        }

                        else if(diceRoll + p1.getStage() == 100){
                            p1.setStage(100);
                            translatePlayer(30, 30, p1);
                            start.setText("Player 1 won");
                            player1Won = true;
                            hasGameStart = false;
                            l1.setText("Game Over");

                        }

                        else{
                            player1Turn = false;
                            player2Turn = true;
                            start.setText("Player 2 turn");
                        }
                    }



                    else if(p1.getStage() < 94 && p1.getStage() > 1){
                        p1.setStage(p1.getStage() + diceRoll);
                        Main.this.movePlayer(diceRoll, p1);
                        player1Turn = false;
                        player2Turn = true;
                        start.setText("Player 2 turn");

                    }


                }
            }
        });

        // player2button

        Button player2Button = new Button("P2");
        player2Button.setTranslateX(514.0D);
        player2Button.setTranslateY(680.0D);
        player2Button.setTextFill(Color.RED);
        player2Button.setFont(this.font);
        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(hasGameStart && player2Turn){
                    int diceRoll = (new Random()).nextInt(6) + 1;

                    Image img = Dice.diceFaces.get(diceRoll);
                    DiceP2.setImage(img);

                    if(p2.getStage() == 1){
                        if(diceRoll == 1){
                            p2.setStage(p2.getStage() + diceRoll);
                            movePlayer(diceRoll, p2);
                            player2Turn = false;
                            player1Turn = true;
                            start.setText("Player 1 turn");

                        }

                        else{
                            player2Turn = false;
                            player1Turn = true;
                            start.setText("Player 1 turn");
                        }
                    }

                    else if(p2.getStage() >= 94){
                        if(diceRoll + p2.getStage() < 100){
                            p2.setStage(p2.getStage() + diceRoll);
                            movePlayer(diceRoll, p2);
                            player2Turn = false;
                            player1Turn = true;
                            start.setText("Player 1 turn");


                        }

                        else if(diceRoll + p2.getStage() == 100){
                            translatePlayer(30, 30, p2);
                            start.setText("Player 2 won");
                            player2Won = true;

                            hasGameStart = false;
                            l1.setText("Game Over");
                        }

                        else{
                            player2Turn = false;
                            player1Turn = true;
                            start.setText("Player 1 turn");
                        }
                    }


                    else if(p2.getStage() < 94 && p2.getStage() > 1){
                        p2.setStage(p2.getStage() + diceRoll);
                        Main.this.movePlayer(diceRoll, p2);
                        player2Turn = false;
                        player1Turn = true;
                        start.setText("Player 1 turn");


                    }

                }
            }
        });

        blocks.getChildren().addAll(player1Button, player2Button);
        blocks.getChildren().addAll(imgView);

        return root;
    }

    public void movePlayer(int diceRoll, Player player){
        Animate b = new Animate(player);
        Double[] arr = new Double[(2*diceRoll)+2];
        arr[0] = (double) player.getxPos();
        arr[1] = (double) player.getyPos();
        int k=2;

        for(int i = 0; i < diceRoll; ++i) {
            if (player.getPlayerLevel() % 2 != 0) {
                player.setxPos(player.getxPos() + 60);
            }

            if (player.getPlayerLevel() % 2 == 0) {
                player.setxPos(player.getxPos() - 60);

            }

            if (player.getxPos() > 570) {
                player.setyPos(player.getyPos() - 60);
                player.setxPos(player.getxPos() - 60);
                player.increasePlayerLevel();
            }

            if (player.getxPos() < 30) {
                player.setyPos(player.getyPos() - 60);
                player.setxPos(player.getxPos() + 60);
                player.increasePlayerLevel();
            }


            arr[k]=(double) player.getxPos();
            arr[k+1]=(double) player.getyPos();
            k+=2;

        }
        if(checkUpandDown(player)){
            Double[] arr2 = new Double[(2*diceRoll)+2+2];
            for(int i=0; i<arr.length; i++){
                arr2[i] = arr[i];
            }

            arr2[(2*diceRoll)+2] = (double) player.getxPos();
            arr2[(2*diceRoll)+3] = (double) player.getyPos();
            b.temp(arr2);


        }
        else{
            b.temp(arr);
        }

    }




    public void translatePlayer(int xPos, int yPos, Circle player) {  // animation
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000.0D), player);
        animate.setToX((double)xPos);
        animate.setToY((double)yPos);
        animate.setAutoReverse(false);
        SequentialTransition sq = new SequentialTransition(player, animate);
        sq.play();
    }

    public void createMapPlayer1(){
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 1; i <= 10; i ++){
            for(int j = 1; j <= 10; j ++){
                int xcor = 0;
                if(i %2 != 0){
                    xcor = 30;
                }
                else{
                    xcor = 570;
                }

                ArrayList<Integer> arr = new ArrayList<>();
                int n = (i - 1)*10 + j;
                if(i % 2 != 0){
                    xcor = xcor + (j-1)*60;
                    arr.add(xcor);

                }
                else{
                    xcor = xcor - (j-1)*60;
                    arr.add(xcor);

                }
                int ycor = 570 - (i-1)*60;
                arr.add(ycor);
                map.put(n, arr);

            }
        }
        playerPositions.setMapPlayer1(map);

    }

    public void createMapPlayer2(){
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 1; i <= 10; i ++){
            for(int j = 1; j <= 10; j ++){
                int xcor = 0;
                if(i %2 != 0){
                    xcor = 30;
                }
                else{
                    xcor = 570;
                }

                ArrayList<Integer> arr = new ArrayList<>();
                int n = (i - 1)*10 + j;
                if(i % 2 != 0){
                    xcor = xcor + (j-1)*60;
                    arr.add(xcor);

                }
                else{
                    xcor = xcor - (j-1)*60;
                    arr.add(xcor);

                }
                int ycor = 570 - (i-1)*60;
                arr.add(ycor);
                map.put(n, arr);

            }
        }
        playerPositions.setMapPlayer2(map);
    }

    public boolean checkUpandDown(Player player){
        if(player.getStage() == 3){  // 1st ladder
            int xcor = playerPositions.getMapPlayer1().get(24).get(0);
            int ycor = playerPositions.getMapPlayer1().get(24).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setPlayerLevel(player.getPlayerLevel() + 2);
            player.setStage(24);
            return true;
        }

        else if(player.getStage() == 7){  // 2nd ladder
            int xcor = playerPositions.getMapPlayer1().get(34).get(0);
            int ycor = playerPositions.getMapPlayer1().get(34).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(34);
            player.setPlayerLevel(player.getPlayerLevel() + 3);

            return true;
        }

        else if(player.getStage() == 12){  // 3rd ladder
            int xcor = playerPositions.getMapPlayer1().get(31).get(0);
            int ycor = playerPositions.getMapPlayer1().get(31).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(31);

            player.setPlayerLevel(player.getPlayerLevel() + 2);

            return true;
        }

        else if(player.getStage() == 20){   // 4th ladder
            int xcor = playerPositions.getMapPlayer1().get(41).get(0);
            int ycor = playerPositions.getMapPlayer1().get(41).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(41);

            player.setPlayerLevel(player.getPlayerLevel() +3);

            return true;
        }

        else if(player.getStage() == 36){   // 5th ladder
            int xcor = playerPositions.getMapPlayer1().get(46).get(0);
            int ycor = playerPositions.getMapPlayer1().get(46).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(46);

            player.setPlayerLevel(player.getPlayerLevel() + 1);

            return true;
        }

        else if(player.getStage() == 56){   // 6th ladder
            int xcor = playerPositions.getMapPlayer1().get(63).get(0);
            int ycor = playerPositions.getMapPlayer1().get(63).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(63);

            player.setPlayerLevel(player.getPlayerLevel() + 1);

            return true;
        }
        else if(player.getStage() == 60){   // 7th ladder
            int xcor = playerPositions.getMapPlayer1().get(81).get(0);
            int ycor = playerPositions.getMapPlayer1().get(81).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(81);

            player.setPlayerLevel(player.getPlayerLevel() + 3);

            return true;
        }

        else if(player.getStage() == 69){   // 8th ladder
            int xcor = playerPositions.getMapPlayer1().get(93).get(0);
            int ycor = playerPositions.getMapPlayer1().get(93).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(93);

            player.setPlayerLevel(player.getPlayerLevel() + 3);

            return true;
        }

        else if(player.getStage() == 75){   // 9th ladder
            int xcor = playerPositions.getMapPlayer1().get(95).get(0);
            int ycor = playerPositions.getMapPlayer1().get(95).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(95);

            player.setPlayerLevel(player.getPlayerLevel() + 2);

            return true;
        }

        else if(player.getStage() == 78){   // 10th ladder
            int xcor = playerPositions.getMapPlayer1().get(97).get(0);
            int ycor = playerPositions.getMapPlayer1().get(97).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(97);

            player.setPlayerLevel(player.getPlayerLevel() + 2);

            return true;
        }


        // snakes



        else if(player.getStage() == 15){  // 1st snake
            int xcor = playerPositions.getMapPlayer1().get(5).get(0);
            int ycor = playerPositions.getMapPlayer1().get(5).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(5);

            player.setPlayerLevel(player.getPlayerLevel() - 1);

            return true;
        }

        else if(player.getStage() == 22){  // 2nd snake
            int xcor = playerPositions.getMapPlayer1().get(2).get(0);
            int ycor = playerPositions.getMapPlayer1().get(2).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(2);

            player.setPlayerLevel(player.getPlayerLevel() - 2);

            return true;
        }

        else if(player.getStage() == 33){  // 3rd snake
            int xcor = playerPositions.getMapPlayer1().get(8).get(0);
            int ycor = playerPositions.getMapPlayer1().get(8).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(8);

            player.setPlayerLevel(player.getPlayerLevel() - 3);

            return true;
        }

        else if(player.getStage() == 44){  // 4th snake
            int xcor = playerPositions.getMapPlayer1().get(23).get(0);
            int ycor = playerPositions.getMapPlayer1().get(23).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(23);

            player.setPlayerLevel(player.getPlayerLevel() - 2);

            return true;
        }

        else if(player.getStage() == 68){  // 5th snake
            int xcor = playerPositions.getMapPlayer1().get(50).get(0);
            int ycor = playerPositions.getMapPlayer1().get(50).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(50);

            player.setPlayerLevel(player.getPlayerLevel() - 2);

            return true;
        }

        else if(player.getStage() == 79){  // 6th snake
            int xcor = playerPositions.getMapPlayer1().get(43).get(0);
            int ycor = playerPositions.getMapPlayer1().get(43).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(43);

            player.setPlayerLevel(player.getPlayerLevel() - 3);

            return true;
        }

        else if(player.getStage() == 85){   // 7th snake
            int xcor = playerPositions.getMapPlayer1().get(65).get(0);
            int ycor = playerPositions.getMapPlayer1().get(65).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(65);

            player.setPlayerLevel(player.getPlayerLevel() - 2);

            return true;
        }

        else if(player.getStage() == 92){   // 8th snake
            int xcor = playerPositions.getMapPlayer1().get(71).get(0);
            int ycor = playerPositions.getMapPlayer1().get(71).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(71);

            player.setPlayerLevel(player.getPlayerLevel() - 2);

            return true;
        }

        else if(player.getStage() == 94){   // 9th snake
            int xcor = playerPositions.getMapPlayer1().get(47).get(0);
            int ycor = playerPositions.getMapPlayer1().get(47).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(47);

            player.setPlayerLevel(player.getPlayerLevel() - 5);
            return true;
        }

        else if(player.getStage() == 98){   // 10th snake
            int xcor = playerPositions.getMapPlayer1().get(82).get(0);
            int ycor = playerPositions.getMapPlayer1().get(82).get(1);
            player.setxPos(xcor);
            player.setyPos(ycor);
            player.setStage(82);

            player.setPlayerLevel(player.getPlayerLevel() - 1);

            return true;
        }






        return false;


    }


    public void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(this.createGame());
        primaryStage.setTitle("Snake and Ladder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
