package sample;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Button[] buttons = new Button[12];

        GridPane gridPane = new GridPane();

        int myScore = 0;
        int oppScore = 0;
        Random rnd = new Random();
        int turn = rnd.nextInt(2);



        Label myHouse =  new Label("My Score:  " + myScore);
        Label opponentHouse =  new Label("Opponent Score: " + oppScore);


        Controller controller = new Controller(buttons,myScore, myHouse,oppScore,opponentHouse);


        int temp = 0;

        for (int i = 0; i < buttons.length ; i++) {

            buttons[i] = new Button("4");

            buttons[i].setOnAction(controller);

            buttons[i].setShape(new Circle(5.0));

            buttons[i].setMinSize(40,40);

            if(turn == 0) {

                if( i < 6 ) {

                    buttons[i].setDisable(true);

                }

            } else {

                if(i > 5) {

                    buttons[i].setDisable(true);

                }


            }

            if(i < 6) {
                gridPane.add(buttons[i],i,0);


            } else {


                gridPane.add(buttons[i],temp++,1);


            }

        }


        gridPane.setAlignment(Pos.CENTER);

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(gridPane);
        borderPane.setLeft(opponentHouse);
        borderPane.setRight(myHouse);

        borderPane.setPadding(new Insets(10,10,10,10));


        StackPane root = new StackPane();
        root.setId("pane");
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();



        primaryStage.setTitle("Oware");
        primaryStage.setScene(new Scene(borderPane, 500, 400));

        primaryStage.sizeToScene();
        primaryStage.show();





    }


    public static void main(String[] args) {
        launch(args);
    }
}
