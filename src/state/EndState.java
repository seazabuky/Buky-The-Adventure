package state;

import java.awt.image.BufferedImage;

import audio.Audio;

import java.awt.Graphics2D;
import java.awt.Font;

import graphic.Sprite;
import main.GamePanel;
import math.Vector2f;
import ui.Button;
import util.KeyHandler;
import util.MouseHandler;

public class EndState extends GameState {

    private String end = "Ending";
    private Button bg;
    private Button btnMenu;
    private Button btnExit;
    private Font font;

    public EndState(GameStateManager gsm){
        super(gsm);
        
        BufferedImage imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);
        BufferedImage imgBg = GameStateManager.bg.getSubimage(0, 0, 640, 384);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnMenu = new Button("MENU", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);
        btnExit = new Button("EXIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 48), 32, 16);

        btnMenu.addHoverImage(btnMenu.createButton("MENU", imgHover, font, btnMenu.getWidth(), btnMenu.getHeight(), 32, 20));
        btnExit.addHoverImage(btnExit.createButton("EXIT", imgHover, font, btnExit.getWidth(), btnExit.getHeight(), 32, 20));
        
        bg = new Button("", imgBg, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), GamePanel.width, GamePanel.height);

        btnMenu.addEvent(e -> {
            Audio.play("pop2",3);
            gsm.unloadState(GameStateManager.END);
            gsm.loadState(GameStateManager.MENU);
        });
        
        btnExit.addEvent(e -> {
            Audio.play("pop2",3);
            System.exit(0);
        });
    }
    @Override
    public void update(double time) {
        
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnMenu.input(mouse, key);
        btnExit.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);
        Sprite.drawArray(g, end, new Vector2f(GamePanel.width / 2 - end.length() * (28 / 2), GamePanel.height / 2 - 32 / 2), 32, 32, 32);
        btnMenu.render(g);
        btnExit.render(g);
    }
}
