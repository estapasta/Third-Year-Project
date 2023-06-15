package Menu;

import Game_State.Sudoku;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Win extends ApplicationAdapter implements Screen{
    private final Sudoku sudoku;
    public Stage stage;
    public Skin skin;
    private TextButton yes;
    private TextButton no;
    private Texture texture;
    private Texture border[] = new Texture[4];
    private BitmapFont font;

    public Win(Sudoku sudoku) {
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
        yes.setPosition(250,270);
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
        sudoku.batch.draw(texture,400,400,300,400);
        sudoku.batch.draw(border[0],390,400,10,400);            //drawing borders for the mini screen
        sudoku.batch.draw(border[0],690,400,10,400);
        sudoku.batch.draw(border[0],390,400,300,10);
        sudoku.batch.draw(border[0],390,800,300,10);
        font.draw(sudoku.batch,"CONGRATULATIONS\n\n      YOU WIN!!",400,750);
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
