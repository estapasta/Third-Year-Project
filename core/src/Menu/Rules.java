package Menu;

import Game_State.Sudoku;
import Puzzle.Puzzle;
import Screens.Main_Render;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Rules extends ApplicationAdapter implements Screen {

    private final Sudoku sudoku;
    public Stage stage;
    public Skin skin;
    private Texture texture;
    private Texture texture2;
    private BitmapFont font;            // Used to write and display the rules
    private BitmapFont font2;
    private TextButton back;

    public Rules(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.stage = new Stage(new StretchViewport(500.0F, 500.0F, sudoku.cam));
        this.skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
        this.back = new TextButton("BACK",skin,"default");
        this.texture  = new Texture("Images/game_bg.jpg");
        this.texture2 = new Texture("Images/game_bg.jpg");
        this.font = new BitmapFont();
        this.font2 = new BitmapFont();
    }

    @Override
    public void show() {
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
    }

    @Override
    public void render(float delta)
    {
        super.render();
        sudoku.batch.begin();
        Gdx.gl.glClearColor(2.0F, 2.0F, 2.0F, 2.0F);
        sudoku.batch.draw(texture,0,0,1000,2000);
        sudoku.batch.draw(texture2,45,600,600,410);
        sudoku.batch.draw(texture2,195,100,710,310);
        font.draw(sudoku.batch,"RULES:\nNumbers (1-9) should not be the same:\n\n1] In the same row\n\n2] In the same column\n\n3] In the same 3x3 grid", 50, 980);
        font.setColor(Color.SKY);
        font.getData().setScale(2);

        font.draw(sudoku.batch,"KEYS\n\nESC--->Back to Main Menu\n\nENTER--->Starts correction of users input of numbers\n\nR--->Removes highlighted cells",200,400);
        font2.getData().setScale(2);
        sudoku.batch.end();
        Gdx.input.setInputProcessor(this.stage);
        stage.draw();
    }

    @Override
    public void hide() {

    }
}
