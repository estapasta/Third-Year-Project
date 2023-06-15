package Game_State;

import sun.jvm.hotspot.utilities.ObjectReader;

import java.util.ArrayList;

public class Cells {
    public int rowPosition;                     // stores the row number of the cell
    public int colPosition;                     // stores the column number of the cell
    public ArrayList<Integer> constraint;       // stores the possible constraints of the cell


    public Cells(int row, int col, ArrayList<Integer> cons)         // Constructor to store the values
    {
        this.rowPosition=row;
        this.colPosition=col;
        this.constraint=cons;
    }


}