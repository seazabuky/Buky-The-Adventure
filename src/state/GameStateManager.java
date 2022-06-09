package state;

import java.awt.Graphics2D;

import graphic.Font;
import graphic.Fontf;
import graphic.Sprite;
import main.GamePanel;
import math.AABB;
import math.Vector2f;
import util.MouseHandler;
import util.Camera;
import util.KeyHandler;
import audio.Audio;

public class GameStateManager {

    // private ArrayList<GameState> states;
    private GameState[] gs;
    private int CurrentState;
    public static Vector2f map;

    public static Graphics2D g;
    public static Sprite button;
    public static Sprite ui;
    public static Sprite bg;
    public static Font font;
    public static Fontf fontf;
    public static Camera cam;



    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static final int DIALOG = 4;
    public static final int END = 5;

    public GameStateManager(Graphics2D g) {
        GameStateManager.g = g;
        gs = new GameState[6];
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);

        font = new Font("font/font.png", 10, 10);
        fontf = new Fontf();
        fontf.loadFont("font/Stackedpixel.ttf", "MeatMadness");
        fontf.loadFont("font/GravityBold8.ttf", "GravityBold8");
        Sprite.currentFont = font;

        Audio.init();
        DialogLoader.init();

        bg = new Sprite("menu/bg.png", 640, 384);
        ui = new Sprite("ui/ui.png", 64, 64);
        button = new Sprite("ui/buttons.png", 122, 57);

        // ******************************** AUDIO ********************************
        Audio.loadSound("audio/pop1.wav", "pop1", 0);
        Audio.loadSound("audio/pop2.wav", "pop2", 0);
        Audio.loadSound("audio/Death.wav", "death", 0);
        // ******************************** CAMERA ********************************

        cam = new Camera(new AABB(new Vector2f(0, 0), GamePanel.width + 64, GamePanel.height + 64));
        // cam = new Camera(new AABB(new Vector2f(GamePanel.width / 2 - 1280 / 2,
        // GamePanel.height / 2 - 720 / 2),1280,720));

        // Set States
        CurrentState = MENU;
        loadState(CurrentState);
    }

    public void loadState(int state) {
        if (state == PLAY) {
            gs[state] = new PlayState(this, cam);
        }
        if (state == MENU) {
            gs[state] = new MenuState(this);
        }
        if (state == PAUSE) {
            gs[state] = new PauseState(this);
        }
        if (state == GAMEOVER) {
            gs[state] = new GameOverState(this);
        }
        if (state == DIALOG) {
            gs[state] = new DialogState(this, PlayState.oldman);
        }
        if (state == END) {
            gs[state] = new EndState(this);
        }
    }

    public void unloadState(int state) {
        gs[state] = null;
    }

    public boolean isStateActive(int state) {
        return gs[state] != null;
    }

    public GameState getState(int state) {
        return gs[state];
    }

    public void addAndpop(int state) {
        addAndpop(state, 0);
    }

    public void addAndpop(int state, int remove) {
        unloadState(state);
        loadState(state);
    }

    public void update(double time) {
        for (int i = 0; i < gs.length; i++) {
            if (gs[i] != null) {
                gs[i].update(time);
            }
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for (int i = 0; i < gs.length; i++) {
            if (gs[i] != null) {
                gs[i].input(mouse, key);
            }
        }
    }

    public void render(Graphics2D g) {
        g.setFont(GameStateManager.fontf.getFont("MeatMadness"));
        for (int i = 0; i < gs.length; i++) {
            if (gs[i] != null) {
                gs[i].render(g);
            }
        }
    }

}
