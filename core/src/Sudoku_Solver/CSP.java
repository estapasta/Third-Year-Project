package Sudoku_Solver;

import Puzzle.Puzzle;

import java.util.ArrayList;

public class CSP extends Constraint_solver{
    public int[][] board = new int[9][9];
    public static final int EMPTY = 0;



    public void creation(int[][] board)         // Acquires puzzle
    {

        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.board[i][j] = board[i][j];
            }
        }

    }

    private boolean isInRow(int row, int number) {
        for(int i = 0; i < 9; ++i) {
            if (this.board[row][i] == number) {
                return true;
            }
        }

        return false;
    }

    private boolean isInCol(int col, int number) {
        for(int i = 0; i < 9; ++i) {
            if (this.board[i][col] == number) {
                return true;
            }
        }

        return false;
    }

    private boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for(int i = r; i < r + 3; ++i) {
            for(int j = c; j < c + 3; ++j) {
                if (this.board[i][j] == number) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checking(int row, int col, int number) {     // checking for violations
        return !this.isInRow(row, number) && !this.isInCol(col, number) && !this.isInBox(row, col, number);
    }

    public boolean solve() {


        for (String key : hm1.keySet())         //Iterates based on priority
        {
            int num = Integer.parseInt(key);
            int coor[] = new int[2];
            int count = 0;
            int length = String.valueOf(num).length();
            while (num > 0) {
                if (length == 1) {
                    coor[0] = num;
                    coor[1] = 0;
                    break;
                } else
                {
                    coor[count] = num % 10;
                    num = num / 10;
                    count++;
                }
            }
            int row = coor[1];
            int col = coor[0];

            ArrayList<Integer> list = new ArrayList<>();



            for(int it = 0; it< Puzzle.SIZE; it++)
            {
                if(cells[it].colPosition == col && cells[it].rowPosition == row)
                {
                      list = cells[it].constraint;
                }

            }



            if (this.board[row][col] == 0)      // Backtracking when there is a violation
            {

                   for(int number = 0; number < list.size(); ++number)      //ONly goes through constraints
                    {

                        if (this.checking(row, col, list.get(number)))
                        {
                            this.board[row][col] = list.get(number);
                            if (this.solve()) {
                                return true;
                            }

                            this.board[row][col] = 0;
                        }

                    }

                    return false;
                }
            }

        return true;
    }


    public void display(int[][] arr) {      //Displays onto the cells
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                arr[i][j] = this.board[i][j];

            }

        }

    }

}
