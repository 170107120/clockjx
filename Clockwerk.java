/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clockwerk;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Home
 */
public class Clockwerk extends Application {
    private int hour, minute, second;
    private double w = 250, h = 250;
    private Line sLine, mLine, hLine;
    private Timeline hTime, mTime, sTime;
    private double centerX = w / 2;
    private double centerY = h / 2;
    private double circleRadius = Math.min(w, h) * 0.8 * 0.5;
    private double sLength = circleRadius * 0.8;
    private double mLength = circleRadius * 0.65;
    private double hLength = circleRadius * 0.5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Calendar calendar = new GregorianCalendar();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
                
        Circle circle = new Circle(centerX, centerY, circleRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        
        Text t1 = new Text(centerX - 5, centerY - circleRadius + 12, "12");
        Text t2 = new Text(centerX - circleRadius + 3, centerY + 5, "9");
        Text t3 = new Text(centerX + circleRadius - 10, centerY + 3, "3");
        Text t4 = new Text(centerX - 3, centerY + circleRadius - 3, "6");
        
        double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
        sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);
        
        double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
        double yMinute = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
        mLine = new Line(centerX, centerY, xMinute, yMinute);
        mLine.setStroke(Color.BLUE);
        
        
        double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);
        
        pane.getChildren().addAll(circle,t1,t2,t3,t4,sLine,mLine,hLine);
        
        sTime = new Timeline();
        sTime = new Timeline(new KeyFrame(Duration.seconds(1), e->sTea()));
        sTime.setCycleCount(Timeline.INDEFINITE);
        sTime.play();
        
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("Clocks");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
    void sTea(){
        second++;
        
        double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));

        sLine.setEndX(secondX);
        sLine.setEndY(secondY);

        if(second == 60)
        {
        second = 0;
        minute++;
    
        double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
        double yMinute = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
        
        mLine.setEndX(xMinute);
        mLine.setEndY(yMinute);
    
        }
        if(minute == 60)
        {
        minute = 0;
        hour++;
        
        double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        
        hLine.setEndX(hourX);
        hLine.setEndY(hourY);
        }
    }
    
}
