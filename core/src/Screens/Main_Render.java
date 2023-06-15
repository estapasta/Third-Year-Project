package Screens;

import Game_State.Sudoku;
import Grid_Features.Coordinates;
import Grid_Features.Grid;
import Menu.Confirmation;
import Menu.Hints;
import Menu.Main_Menu;
import Menu.Win;
import Puzzle.Puzzle;
import Sudoku_Solver.CSP;
import Sudoku_Solver.Error_Checking;
import Sudoku_Solver.Solver;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class Main_Render extends ApplicationAdapter implements Screen {
    private final Sudoku sudoku;        //Creation of object sudoku
    public Stage stage;
    private TextButton solver;      //Button used for the game
    private TextButton reset;
    private TextButton hint;
    public Skin skin;
    private ShapeRenderer shapeRenderer;
    private Texture texture;
    public TextField[][] trials = new TextField[9][9];      //Textfields/boxes that serves as the cells for the puzzle
    public int [][] reset_puzzle = new int[9][9];           //Contains the original puzzle problem
    public int [][] puzzle = new int[9][9];                 //Stores the puzzle that's generated


    public Main_Render(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.stage = new Stage(new StretchViewport(500.0F, 500.0F, sudoku.cam));        //sets stage
        this.skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
        this.shapeRenderer = new ShapeRenderer();
        this.solver = new TextButton("SOLVER",skin,"default");
        this.reset = new TextButton("RESET",skin,"default");
        this.hint = new TextButton("HINT",skin,"default");
        this.texture = new Texture("Images/game_bg.jpg");

            Puzzle puzz = new Puzzle(puzzle);
            puzz.puzzle_solve();

            puzzle = puzz.puzzle_generate();
            puzzle = puzz.check();          //generates a puzzle with a unique solution


    }

    public int[][] getReset_puzzle() {          //function to reset the puzzle
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                reset_puzzle[i][j] = puzzle[i][j];
                String temp = String.valueOf(reset_puzzle[i][j]);
                trials[i][j].setText(temp);
                trials[i][j].setColor(Color.GRAY);
            }
        }

        return reset_puzzle;
    }

    public void show() {
        Gdx.input.setInputProcessor(this.stage);


        solver.setPosition(50,300);
        solver.setColor(Color.VIOLET);
        solver.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                final long startTime = System.currentTimeMillis();
                    get3();     //calls the solver from the CSP class
                final long duration = System.currentTimeMillis() - startTime;

            }
        });

        hint.setPosition(50,350);
       hint.setColor(Color.VIOLET);
        hint.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Hints hints = new Hints();  //call the hint class
               puzzle = hints.display_hint(puzzle);
                String temp = String.valueOf(puzzle[hints.x1][hints.x2]);
                trials[hints.x1][hints.x2].setText(temp);
                trials[hints.x1][hints.x2].setColor(Color.GREEN);       //display hints on specified cell along with highlight
               hints.remove_hint(hints.x1, hints.x2, puzzle);

                for(int i = 0; i < 9; ++i) {
                    for(int j = 0; j < 9; ++j) {

                        if(trials[i][j].getText().equals("0"))
                        {
                            trials[i][j].setText("");
                        }

                    }

                }
            }
        });

        reset.setPosition(50,250);
        reset.setColor(Color.VIOLET);
        reset.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                getReset_puzzle();
                for(int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        if(trials[i][j].getText().equals("0"))
                        {
                            trials[i][j].setText("");
                        }
                    }
                }
            }
        });



        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.trials[i][j] = new TextField("", this.skin);       //defines attributes of the cells
                this.trials[i][j].setSize(30.0F, 30.0F);
               this.trials[i][j].setPosition((float)((195 + 35 * i)-9), (float)((155 + j * 35)-5));
                this.trials[i][j].setMaxLength(1);
                this.trials[i][j].setAlignment(10);
                this.trials[i][j].setColor(Color.DARK_GRAY);
                String temp = String.valueOf(this.puzzle[i][j]);
                this.trials[i][j].setText(temp);


                        if(trials[i][j].getText().equals("0"))
                        {
                            trials[i][j].setText("");
                        }
                        else {
                            trials[i][j].setDisabled(true);  //cannot edit numbers from puzzle
                        }



                this.stage.addActor(this.trials[i][j]);
            }
        }



            stage.addActor(reset);
            stage.addActor(solver);
            stage.addActor(hint);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.1F, 0.0F);
        Gdx.gl.glClear(16384);
        Coordinates coordinates = new Coordinates();//calls coordinate class
        Grid grid = new Grid();//calls grid class
        this.update(delta); //updates the screen for every instance that happens
        this.sudoku.batch.begin();

        coordinates.render();
        grid.render();


        if (Gdx.input.isKeyPressed(111)) //ESC key bind to some functionality (back to the main menu)
        {

           sudoku.setScreen(new Confirmation(sudoku));
        }

       if (Gdx.input.isKeyPressed(Input.Keys.R)) //R key binds to Removes error marks
        {
            for(int i = 0; i < 9; ++i) {
                for(int j = 0; j < 9; ++j) {
                    this.trials[i][j].setColor(Color.DARK_GRAY);
                }
            }
        }

        if (Gdx.input.isKeyPressed(66)) //ENTER key binds to check for violition of rules
        {
            Error_Checking er = new Error_Checking();
            er.check(trials);
            er.check2(trials);
            er.check4(trials);
            if (er.uni_counter == 0)
            {
                sudoku.setScreen(new Win(sudoku));      //calls win class if they are no errors and cells are filled
            }
           for(int i=1;i<=8;i++)
           {
               for(int j=1;j<=i;j++)
               {
                  er.check3(trials,i,j);
               }
           }

        }




        this.sudoku.batch.end();
        this.stage.draw();

    }

    public void get() {         //function used to call the Solver class
        Solver.creation(puzzle);
        Solver.solve();
        Solver.display(puzzle);
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                String temp = String.valueOf(this.puzzle[i][j]);
                this.trials[i][j].setText(temp);

            }

        }

    }


    public void get3() {        //Calls CSP class
        CSP solver = new CSP();

        solver.creation(this.puzzle);
        solver.solver2(this.puzzle);
        solver.solve();
        solver.display(this.puzzle);


        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                String temp = String.valueOf(this.puzzle[i][j]);
                this.trials[i][j].setText(temp);

            }

        }

    }


    public void hide() {
    }

    public void update(float delta) {
        this.stage.act(delta);

    }


    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
