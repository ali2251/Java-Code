package sample;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Controller implements EventHandler<ActionEvent> {

    private Button[] localButtons;

    private boolean isMyTurn = false;

    private int myScore = 0;
    private int oppScore = 0;
    private Label myhouse = new Label("");
    private Label opphouse = new Label("");




    Controller(Button[] buttons, int myScore, Label myHouse,  int oppScore , Label oppHouse) {

        localButtons = buttons;
        this.myScore = myScore;
        this.oppScore = oppScore;
        myhouse = myHouse;
        this.opphouse = oppHouse;

    }


    @Override
    public void handle(ActionEvent event) {



        for (int i = 0; i < 12 ; i++) {

            localButtons[i].setDisable(false);
        }




        Button buttonPressed = (Button) event.getSource();

        int index = -1;
        int numberOfRocks = -1;

        for (int i = 0; i < 12; i++) {

            if (buttonPressed == localButtons[i]) {
                index = i;
            }

        }


        if(index < 6) {

            for (int i = 0; i <6 ; i++) {

                localButtons[i].setDisable(true);
            }

        }  else {


            for (int i = 6; i < 12 ; i++) {

                localButtons[i].setDisable(true);
            }

        }


        //starting

        numberOfRocks = Integer.parseInt(buttonPressed.getText());

        buttonPressed.setText("0");

        /*
        Aplying the board rules and anticlockwise motion of the game
         */

        int localIndex = -1;

        for (int i = 0; i < numberOfRocks; ++i) {

            if (index == 0 && localIndex == -1) {

                localIndex = 6;

            } else if (index == 11 && localIndex == -1) {

                localIndex = 5;

            } else {

                if (index < 6 && index != 0) {

                    localIndex = --index;
                } else if (index > 5 && index != 11) {

                    localIndex = ++index;
                }

            }


            if(localIndex == 12 ) {

                localIndex = 5;

            } else if(localIndex < 0) {

                localIndex = 6;
            }





            int temp = Integer.parseInt(localButtons[localIndex].getText());

            ++temp;

            if(temp == 2 || temp == 3) {

                //if (localIndex + 1 == index || localIndex - 1 == index) {

                    if (i + 1 == numberOfRocks) {

                        if (localIndex > 5) {

                            myScore += temp;

                            temp = 0;

                            myhouse.setText("My Score: " + myScore);

                        } else {

                            oppScore += temp;
                            temp = 0;

                            opphouse.setText("Opponent Score: " + oppScore);

                        }

                    }
               // }
            }

            localButtons[localIndex].setText(String.valueOf(temp));




            if (localIndex > 5 ) {

                ++localIndex;

            } else  {

                --localIndex;
            }


        }


    }


}
