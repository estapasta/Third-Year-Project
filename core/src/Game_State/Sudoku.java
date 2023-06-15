package Game_State;


import Menu.Main_Menu;
import Screens.Main_Render;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sudoku extends Game {
    public OrthographicCamera cam;              //for objects to appear on the same scale
    public SpriteBatch batch;                   //USed for grouping of various textures
    private Main_Menu main_menu;
    private Main_Render main_render;

    public Sudoku() {
    }

    public void load_menu(){
        this.setScreen(this.main_menu);
    }

    public void load_render(){
        this.setScreen(this.main_render);
    }


    public void create() {
        this.cam = new OrthographicCamera();
        this.batch = new SpriteBatch();
        this.main_menu = new Main_Menu(this);
        this.main_render = new Main_Render(this);
       this.setScreen(this.main_menu);

    }

    public void render() {
        super.render();
    }
}
