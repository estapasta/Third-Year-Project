package Sudoku_Solver;

import Game_State.Sudoku;
import Screens.Main_Render;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class Error_Checking {

    public int uni_counter=0;       // Checks if there are no 0 or violation of rules

    public void check(TextField[][] textFields) {       // checks if there are numbers in the same row
                uni_counter = 0;
        for ( int n = 1; n <= 9; n++)
        {
            for (int row = 0; row < 9; row++) {
                String temp = String.valueOf(n);
                int counter = 0;


                for (int i = 0; i < 9; ++i) {

                    if (textFields[row][i].getText().equals(temp)) {
                        counter++;
                        uni_counter++;
                    }

                    if (counter > 1) {
                        for (int k = 0; k < 9; k++) {
                            textFields[row][k].setColor(Color.BLUE);
                        }
                    } else {
                        for (int k = 0; k < 9; k++) {
                            //    trials[row][k].setColor(Color.LIGHT_GRAY);
                        }
                    }

                }

            }

        }

    }






    public void check2(TextField[][] textFields)    // checks if there are numbers in the same column
    {
        uni_counter = 0;
        for ( int n = 1; n <= 9; n++) {
            String temp = String.valueOf(n);

            for (int row = 0; row < 9; row++) {
                int counter = 0;


                for (int i = 0; i < 9; ++i) {

                    if (textFields[i][row].getText().equals(temp)) {
                        counter++;
                        uni_counter++;
                    }
                    if (counter > 1) {
                        for (int k = 0; k < 9; k++) {
                            textFields[k][row].setColor(Color.YELLOW);
                        }
                    } else {
                        for (int k = 0; k < 9; k++) {
                            //  trials[k][row].setColor(Color.LIGHT_GRAY);
                        }
                    }

                }
            }

        }
    }


    public void check3(TextField[][] textFields,int row,int col){       // checks if there are numbers in the same 3x3 grid
        uni_counter = 0;
        for ( int n = 1; n <= 9; n++) {
            int counter = 0;

            int r = row - row % 3;
            int c = col - col % 3;

            for (int i = r; i < r + 3; ++i) {

                for (int j = c; j < c + 3; ++j) {
                    String temp = String.valueOf(n);
                    if (textFields[i][j].getText().equals(temp)) {
                        counter++;
                        uni_counter++;
                    }


                    if (counter > 1) {
                        for (int l = r; l < r + 3; ++l) {

                            for (int m = c; m < c + 3; ++m) {
                                textFields[l][m].setColor(Color.TEAL);
                            }
                        }
                    } else {
                        for (int l = r; l < r + 3; ++l) {

                            for (int m = c; m < c + 3; ++m) {
                                // trials[l][m].setColor(Color.LIGHT_GRAY);
                            }
                        }

                    }


                }

            }
        }
    }

    public void check4(TextField[][] textFields){           //checks if there are any 0/empty cells present
        uni_counter = 0;
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                if (textFields[i][j].getText().equals("")) {
                 //   textFields[i][j].setColor(Color.RED);
                    uni_counter++;
                }


            }

        }
    }
}

