package Menu;

import Puzzle.Puzzle;

import java.util.concurrent.ThreadLocalRandom;

public class Hints {

    public int x1,x2;   // to store the coordinates
    public int[][] display_hint(int[][] arr)
    {
        int temp;
        int r1, r2;
        while (true) {
            r1 = ThreadLocalRandom.current().nextInt(0, 9);          //check if cell is 0
            r2 = ThreadLocalRandom.current().nextInt(0, 9);          //check for unique solutions

            if (arr[r1][r2] == 0)
            {
                temp = Puzzle.puzzle_temp2[r1][r2];
               // temp = 0;
                arr[r1][r2] = temp;
                x1=r1;
                x2=r2;
                break;

            }

        }
        return arr;
    }

    public int[][] remove_hint(int x,int y,int[][] arr)     // called to not affect constraint clashing in the cells
    {
        arr[x][y] = 0;

        return arr;
    }

}
