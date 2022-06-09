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

public class GameOverState extends GameState {

    private String gameover = "GAME OVER";
    private Button btnReplay;
    private Button btnExit;
    private Font font;

    public GameOverState(GameStateManager gsm){
        super(gsm);
        BufferedImage imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnReplay = new Button("REPLAY", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);
        btnExit = new Button("EXIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 48), 32, 16);

        btnReplay.addHoverImage(btnReplay.createButton("REPLAY", imgHover, font, btnReplay.getWidth(), btnReplay.getHeight(), 32, 20));
        btnExit.addHoverImage(btnExit.createButton("EXIT", imgHover, font, btnExit.getWidth(), btnExit.getHeight(), 32, 20));

        btnReplay.addEvent(e -> {
            Audio.play("pop2",3);
            DialogState.setToDefault();
            PlayState.countStart = 0;
            gsm.unloadState(GameStateManager.GAMEOVER);
            gsm.loadState(GameStateManager.PLAY);
            Audio.setFramePosition("bgMusic", 0);
            Audio.resume("bgMusic");
            Audio.setLoop("bgMusic");
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
        btnReplay.input(mouse, key);
        btnExit.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        Sprite.drawArray(g, gameover, new Vector2f(GamePanel.width / 2 - gameover.length() * (28 / 2), GamePanel.height / 2 - 32 / 2), 32, 32, 32);
        btnReplay.render(g);
        btnExit.render(g);
    }
}
