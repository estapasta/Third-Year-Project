package Grid_Features;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Grid extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    public Grid(){
        spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();       //Used to create shapes
    }

    public void render() {
        super.render();

        spriteBatch.begin();


        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for(int i = 0;i<9;i++)
        {
            this.shapeRenderer.line(930.0F-(70.F*i), 295.0F, 930.0F-(70.F*i), 990.0F);     //vertical lines
            this.shapeRenderer.line(300.0F, 855.0F-(70.F*i), 1000.0F, 855.0F-(70.F*i));    //horizontal lines
        }

        for(int i = 0;i<4;i++)   //red borders
        {
            this.shapeRenderer.line(1000.0F-(210.F*i), 295.0F, 1000.0F-(210.F*i), 990.0F, Color.RED, Color.RED);
            this.shapeRenderer.line(300.0F, 925.0F-(210.F*i), 1000.0F, 925.0F-(210.F*i), Color.RED, Color.RED);
        }


        this.shapeRenderer.end();

        spriteBatch.end();

    }

}

