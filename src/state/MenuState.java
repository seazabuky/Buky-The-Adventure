package state;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import audio.Audio;

import java.awt.Graphics2D;
import java.awt.Font;

import graphic.Sprite;
import main.GamePanel;
import math.Vector2f;
import ui.Button;
import util.KeyHandler;
import util.MouseHandler;

public class MenuState extends GameState {

    private Button btnNext;
    private Button btnPrevious;

    private String gameTitle1 = "BUKY";
    private String gameTitle2 = "THE ADVENTURE";
    private Button bg;

    private Button btnStart;
    private Button btnExit;
    private Button btnTutorial;
    private Font font;

    private int count = 0;
    private boolean tutorial = false;
    protected static ArrayList<Button> mgForTutorial;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        Audio.loadBackgroundMusic("audio/undertale.wav","bgMusic",-14,true);

        BufferedImage imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);
        BufferedImage imgBg = GameStateManager.bg.getSubimage(0, 0, 640, 384);


        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnStart = new Button("START", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 10),
                32, 16);
        btnExit = new Button("EXIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 100), 32,
                16);
        btnTutorial = new Button("Tutorial", imgButton, font, new Vector2f(GamePanel.width / 2 + 480, GamePanel.height / 2 + 300), 32,
                16);
        btnPrevious = new Button("<", imgButton, font,
                new Vector2f(GamePanel.width / 2 - 75, GamePanel.height / 2 + 300), 32, 16);
        btnNext = new Button(">", imgButton, font,
                new Vector2f(GamePanel.width / 2+ 50, GamePanel.height / 2 + 300), 32, 16);

        btnStart.addHoverImage(
                btnStart.createButton("START", imgHover, font, btnStart.getWidth(), btnStart.getHeight(), 32, 20));
        btnExit.addHoverImage(
                btnExit.createButton("EXIT", imgHover, font, btnExit.getWidth(), btnExit.getHeight(), 32, 20));
        btnTutorial.addHoverImage(
                btnTutorial.createButton("Tutorial", imgHover, font, btnTutorial.getWidth(), btnTutorial.getHeight(), 32, 20));
        btnPrevious.addHoverImage(btnNext.createButton("<", imgHover, font, btnPrevious.getWidth(),
                btnPrevious.getHeight(), 32, 20));
        btnNext.addHoverImage(
                btnNext.createButton(">", imgHover, font, btnNext.getWidth(), btnNext.getHeight(), 32, 20));


        bg = new Button("", imgBg, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), GamePanel.width,
                GamePanel.height);
        mgForTutorial = new ArrayList<Button>(Arrays.asList(DialogLoader.tl1, DialogLoader.tl2, DialogLoader.tl3, DialogLoader.tl4));

        btnStart.addEvent(e -> {
            Audio.play("pop1",3);
            DialogState.setToDefault();
            PlayState.countStart = 0;
            gsm.unloadState(GameStateManager.MENU);
            gsm.loadState(GameStateManager.PLAY);
        });

        btnExit.addEvent(e -> {
            Audio.play("pop2",3);
            System.exit(0);
        });

         btnTutorial.addEvent(e -> {
                 Audio.play("pop2",3);
                 tutorial = true;
                 
             });
        btnNext.addEvent(e -> {
            this.count += 1;
            Audio.play("pop2", 3); // Play Audio
            System.out.println(this.count);
        });
        btnPrevious.addEvent(e -> {
            if (this.count > 0) {
                Audio.play("pop1", 3);
                this.count -= 1;
                System.out.println(this.count);
            }
        });
    }
    private int getCount() {
        return this.count;
    }

    @Override
    public void update(double time) {
        getCount();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if(!tutorial){
        btnStart.input(mouse, key);
        btnExit.input(mouse, key);
        btnTutorial.input(mouse, key);
        }
        if(tutorial){
        btnNext.input(mouse, key);
        btnPrevious.input(mouse, key);
        }
    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);
        if(!tutorial){
        Sprite.drawArray(g, gameTitle1,
                new Vector2f(GamePanel.width / 2 - gameTitle1.length() * (50 / 2), GamePanel.height / 2 - 200), 68, 68,
                50);
        Sprite.drawArray(g, gameTitle2,
                new Vector2f(GamePanel.width / 2 - gameTitle2.length() * (28 / 2), GamePanel.height / 2 - 120), 40, 40,
                32);
                btnStart.render(g);
                btnExit.render(g);
                btnTutorial.render(g);
        }
        
        if (tutorial){
                if (this.count <= (mgForTutorial.size()-1)) {
                mgForTutorial.get(this.count).render(g);
                btnNext.render(g);
                if (this.count > 0) {
                        btnPrevious.render(g);
                    }
            }
                if (this.count > (mgForTutorial.size()-1)) {
                        this.count = 0;
                        this.tutorial = false;
                    }
                
        }
    }

}
 