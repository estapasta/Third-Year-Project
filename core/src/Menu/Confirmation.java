package Menu;

import Game_State.Sudoku;
import Screens.Main_Render;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Confirmation extends ApplicationAdapter implements Screen{

    private final Sudoku sudoku;
    public Stage stage;
    public Skin skin;       // UI for various widgets to use
    private TextButton yes;
    private TextButton no;
    private Texture texture;
    private Texture border[] = new Texture[4];      //Borders for the mini screen
    private BitmapFont font;

    public Confirmation(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.stage = new Stage(new StretchViewport(500.0F, 500.0F, sudoku.cam));
        this.skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
        this.yes = new TextButton("OK",skin,"default");
        this.no = new TextButton("NO",skin,"default");
        this.texture = new Texture("Images/black_bg.jpg");
        for(int i=0;i<4;i++) {
            this.border[i] = new Texture("Images/brown_border.jpg");
        }
        this.font = new BitmapFont();
    }


    @Override
    public void show() {
        yes.setPosition(250,300);
        yes.setColor(Color.VIOLET);
        yes.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                sudoku.load_menu();
            }
        });
        stage.addActor(yes);
        no.setPosition(250,250);
        no.setColor(Color.VIOLET);
        no.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);



            }
        });
       // stage.addActor(no);
    }

    @Override
    public void render(float delta) {
        super.render();
        sudoku.batch.begin();
        sudoku.batch.draw(texture,400,550,300,310);             // Background for the mini screen
        sudoku.batch.draw(border[0],400,550,10,310);           // Draws the borders
        sudoku.batch.draw(border[1],700,550,10,310);
        sudoku.batch.draw(border[2],400,550,300,10);
        sudoku.batch.draw(border[3],400,850,300,10);
        font.draw(sudoku.batch,"PRESS OK\n\nTO QUIT GAME",450,800);
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        sudoku.batch.end();
        Gdx.input.setInputProcessor(this.stage);
        stage.draw();
    }

    @Override
    public void hide() {

    }
}
