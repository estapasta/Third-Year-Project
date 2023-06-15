package Sudoku_Solver;

import Game_State.Cells;
import Puzzle.Puzzle;

import java.util.*;

public class Constraint_solver {


    ArrayList<Integer> constraints = new ArrayList<Integer>();  // Stores the constraints
    HashMap<String, Integer> hm = new HashMap<String, Integer>();       // stores the constraints and along with the coordinates
    Map<String, Integer> hm1 = new HashMap<String,Integer>();          // used to prioritise/order the hm Hashmap
    String coordinates;                                   // Combines row and column to form a string
    public Cells[] cells = new Cells[Puzzle.SIZE];        //Call the Cells class with set amount of instances

    public void solver2(int[][] arr)                    //Checks all possible constraints of each cell
    {
        int counter = 0;
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                constraints.clear();                    // Clears after each iteration
                if(arr[j][i] == 0)
                {
                   for(int put = 0; put<9;put++)
                    {
                        if(arr[put][i] != 0)
                        {

                            constraints.add(arr[put][i]);
                        }
                        if(arr[j][put] != 0)
                        {

                           constraints.add(arr[j][put]);
                        }

                    }
                    int r = i - i % 3;
                    int c = j - j % 3;

                    for (int row = r; row < r + 3; ++row)
                    {

                        for (int col = c; col < c + 3; ++col)
                        {
                            if(arr[col][row] != 0)
                            {
                              // counter++;
                                constraints.add(arr[col][row]);
                            }
                        }

                    }

                    coordinates = String.valueOf(j) + String.valueOf(i);    // Combines row and column

                    Set<Integer> set = new HashSet<Integer>(constraints);
                    constraints.clear();
                    constraints.addAll(set);
                    ArrayList<Integer> input = new ArrayList<Integer>();
                    for (int inp =1;inp<=9;inp++ )
                    {
                        if(constraints.contains(inp) == false)
                        {
                            input.add(inp);
                        }
                    }

                    cells[counter] = new Cells(j,i,input);          //input it in cells
                    hm.put(coordinates, input.size());
                   // input.clear();
                    counter++;


                }

            }

        }

         hm1 = sortByValue(hm);

    }


    public  HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)               //priority sort
    {

        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >()
        {
            public int compare(Map.Entry<String, Integer> n1,Map.Entry<String, Integer> n2)
            {
                return (n1.getValue()).compareTo(n2.getValue());
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> hm_temp : list)
        {
            temp.put(hm_temp.getKey(), hm_temp.getValue());
        }
        return temp;
    }


}