package Menu;

import Game_State.Sudoku;
import Puzzle.Puzzle;
import Screens.Main_Render;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Difficulty extends ApplicationAdapter implements Screen {

    private final Sudoku sudoku;
    public Stage stage;
    private TextButton easy;        // Textbuttons for the difficulties
    private TextButton hard;
    private TextButton med;
    private TextButton back;
    private Texture texture;
    public Skin skin;

    public Difficulty(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.stage = new Stage(new StretchViewport(500.0F, 500.0F, sudoku.cam));
        this.skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
        this.easy = new TextButton("EASY",skin,"default");
        this.med = new TextButton("MEDIUM",skin,"default");
        this.hard = new TextButton("HARD",skin,"default");
        this.back = new TextButton("BACK",skin,"default");
        this.texture  = new Texture("Images/Sudoku_Menu.jpg");
    }


    @Override
    public void show() {
        easy.setPosition(200,300);
        easy.setSize(100,50);
        easy.setColor(Color.VIOLET);
        easy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Puzzle.SIZE = 30;
                sudoku.setScreen(new Main_Render(sudoku));
               // sudoku.load_render();
            }
        });
        med.setPosition(200,200);
        med.setSize(100,50);
        med.setColor(Color.VIOLET);
        med.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Puzzle.SIZE = 45;
                sudoku.setScreen(new Main_Render(sudoku));
            }
        });
        hard.setPosition(200,100);
        hard.setSize(100,50);
        hard.setColor(Color.VIOLET);
        hard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Puzzle.SIZE = 60;
                sudoku.setScreen(new Main_Render(sudoku));
            }
        });
        back.setPosition(0,0);
        back.setSize(100,50);
        back.setColor(Color.VIOLET);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                sudoku.setScreen(new Main_Menu(sudoku));
            }
        });
        stage.addActor(back);
        stage.addActor(easy);
        stage.addActor(med);
        stage.addActor(hard);
    }

    @Override
    public void render(float delta) {
        super.render();
        sudoku.batch.begin();
        Gdx.gl.glClearColor(2.0F, 2.0F, 2.0F, 2.0F);
        sudoku.batch.draw(texture,0,0,1000,2000);
        sudoku.batch.end();
        Gdx.input.setInputProcessor(this.stage);
        stage.draw();
    }

    @Override
    public void hide() {

    }
}
