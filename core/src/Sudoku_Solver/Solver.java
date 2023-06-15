package Sudoku_Solver;


import Puzzle.Puzzle;

import java.util.ArrayList;

public class Solver
{
    public static int[][] board = new int[9][9];
    public static final int EMPTY = 0;



    public static void creation(int[][] board2)     // acquires the puzzle from the puzzle class thats been generated
    {
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
               board2[i][j] = board[i][j];
            }
        }

    }

    public static boolean isInRow(int row, int number) {            //checks if there is a violation of rules in the row
        for(int i = 0; i < 9; ++i) {
            if (board[row][i] == number) {
                return true;
            }
        }

        return false;
    }

    public  static boolean isInCol(int col, int number) {       //checks if there is a violation of rules in the column
        for(int i = 0; i < 9; ++i) {
            if (board[i][col] == number) {
                return true;
            }
        }

        return false;
    }

    public static boolean isInBox(int row, int col, int number) {   //checks if there is a violation of rules in the 3x3 grid
        int r = row - row % 3;
        int c = col - col % 3;

        for(int i = r; i < r + 3; ++i) {
            for(int j = c; j < c + 3; ++j) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checking(int row, int col, int number) {      //checks all the conditions satisfy
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }

    public static boolean solve() {
        for(int row = 0; row < 9; ++row) {

            for(int col = 0; col < 9; ++col) {

                if (board[row][col] == 0)
                {

                    for(int number = 0; number <= 9; ++number)
                    {

                        if (checking(row, col, number))
                        {
                            board[row][col] = number;
                            if (solve()) {          //true if conditions are met
                                return true;
                            }

                            board[row][col] = 0;        // set cell back to 0
                        }
                    }

                    return false;           //backtracking begins
                }
            }
        }

        return true;
    }


    public static void display(int[][] arr) {       // displays the output onto the cells
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                arr[i][j] = board[i][j];

            }

        }

    }
}
