package Menu;

import Game_State.Sudoku;
import Screens.Main_Render;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

public class Main_Menu extends ApplicationAdapter implements Screen {
    private final Sudoku sudoku;
    public Stage stage;     // To handle input events
    private TextButton play;
    private TextButton exit;
    private TextButton rules;
    private Texture bg;
    public Skin skin;

    public Main_Menu(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
        this.stage = new Stage(new StretchViewport(500.0F, 500.0F, sudoku.cam));
        this.play = new TextButton("PLAY",skin,"default");
        this.exit = new TextButton("EXIT",skin,"default");
        this.rules = new TextButton("RULES",skin,"default");
        this.bg = new Texture("Images/Sudoku_Menu.jpg");

    }

    @Override
    public void create() {

    }

    @Override
    public void show() {
        play.setPosition(200,300);
        play.setSize(100,50);
        play.setColor(Color.VIOLET);
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sudoku.setScreen(new Difficulty(sudoku));
            }
        });
        rules.setPosition(200,200);
        rules.setSize(100,50);
        rules.setColor(Color.VIOLET);
        rules.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                super.clicked(event, x, y);
                sudoku.setScreen(new Rules(sudoku));

            }
        });

        exit.setPosition(200,100);
        exit.setSize(100,50);
        exit.setColor(Color.VIOLET);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
              Gdx.app.exit();
            }
        });
        stage.addActor(play);
        stage.addActor(exit);
        stage.addActor(rules);
    }

    @Override
    public void render(float v) {
        super.render();
        sudoku.batch.begin();
        Gdx.gl.glClearColor(2.0F, 2.0F, 2.0F, 2.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sudoku.batch.draw(bg,0,0,1000,2000);
        sudoku.batch.end();
        Gdx.input.setInputProcessor(this.stage);
        stage.draw();
    }

    @Override
    public void hide() {

    }
}
