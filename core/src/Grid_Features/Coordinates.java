package Grid_Features;

import Game_State.Sudoku;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Coordinates extends ApplicationAdapter {
    public BitmapFont font;         // Used to write string on the screen
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    public Coordinates(){
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        super.render();

        spriteBatch.begin();


        font.setColor(Color.GREEN);
        font.getData().setScale(3);
        int y=0;
        for(int i=0;i<9;i++) {                              // Draws numbers from 1 to 9 horizontally and vertically
            String num = String.valueOf(i+1);
            font.draw(spriteBatch, num, 330, 350+y);
            y = y+70;
        }
        int x = 0;
        for(int i=0;i<9;i++) {
            String num = String.valueOf(i+1);
            font.draw(spriteBatch, num, 400+x, 980);
            x= x+70;
        }

        spriteBatch.end();

    }


}


