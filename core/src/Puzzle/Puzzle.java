package Puzzle;

import Sudoku_Solver.Solver;
import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Puzzle extends Solver  {


    public static int SIZE ;  // Removing of cells is based on difficulty size


    public  int[][] puzzle_main = new int[9][9];                  //Verify that its is the only unique solution


    public  int temp,temp2;
    public  int[][] puzzle_temp = new int[9][9];
    public static int[][] puzzle_temp2 = new int[9][9];

    public Puzzle(int[][] pm)
    {
        puzzle_main = pm;
    }

    public int[][] getPuzzle_main()
    {
        return puzzle_main;
    }

    public void setPuzzle_main(int[][] puz_m)
    {
        puzzle_main = puz_m;
    }

    public Object clone() throws CloneNotSupportedException {
        Puzzle p = (Puzzle) super.clone();
        p.setPuzzle_main((p.getPuzzle_main().clone()));
        return p;
    }


    public void puzzle_solve() {

        for (int i = 1; i <= 9; i++) {
            int r1, r2;
            r1 = ThreadLocalRandom.current().nextInt(0, 9);
            r2 = ThreadLocalRandom.current().nextInt(0, 9);


            puzzle_main[r1][r2] = i;
        }

        Solver.creation(puzzle_main);           // Storing the randomly generated solved puzzle for hints, puzzle generation and puzzle comparison
        Solver.solve();
        Solver.display(puzzle_main);
        Solver.creation(puzzle_temp);
        Solver.solve();
        Solver.creation(puzzle_temp2);
        Solver.solve();

    }



    public int[][] puzzle_generate() {

        for (int num = 0; num < SIZE; num++)                                                        //removing cells
        {


            int r1, r2;
            while (true) {
                r1 = ThreadLocalRandom.current().nextInt(0, 9);          //check if cell is 0
                r2 = ThreadLocalRandom.current().nextInt(0, 9);          //check for unique solutions

                if (puzzle_main[r1][r2] != 0)
                {
                    temp = puzzle_main[r1][r2];
                    temp2 = puzzle_temp[r1][r2];
                    break;

                }
            }
            puzzle_main[r1][r2] = 0;
            puzzle_temp[r1][r2] = 0;
        }


        return puzzle_main;
    }

    public int[][] check(){             // Checks if the puzzle returns a unique solution
        Solver.creation(puzzle_temp);
        Solver.solve();

        if(puzzle_temp2 != puzzle_temp){
                puzzle_solve();
                puzzle_generate();
        }

        return puzzle_main;
    }


}

