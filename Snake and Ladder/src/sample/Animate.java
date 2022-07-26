package sample;


import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;



public class Animate {
    Player p;
    Animate(Player p){
        this.p = p;
    }
    public void temp(Double[] sec){
        Polyline polyline = new Polyline();

        polyline.getPoints().addAll(sec);

        PathTransition transition = new PathTransition();
        transition.setNode(p);
        transition.setPath(polyline);
        transition.setDuration(Duration.seconds(3));
        transition.play();

    }

}
