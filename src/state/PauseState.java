package state;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Font;

import main.GamePanel;
import math.Vector2f;
import ui.Button;
import util.KeyHandler;
import util.MouseHandler;
import audio.Audio;
public class PauseState extends GameState {

    private Button btnResume;
    private Button btnExit;
    private Font font;
    

    public PauseState (GameStateManager gsm) {
        super(gsm);

        BufferedImage imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnResume = new Button("RESUME", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);
        btnExit = new Button("EXIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 48), 32, 16);

        btnResume.addHoverImage(btnResume.createButton("RESUME", imgHover, font, btnResume.getWidth(), btnResume.getHeight(), 32, 20));
        btnExit.addHoverImage(btnExit.createButton("EXIT", imgHover, font, btnExit.getWidth(), btnExit.getHeight(), 32, 20));
        
        btnResume.addEvent(e -> {
            Audio.play("pop1",3);
            gsm.unloadState(GameStateManager.PAUSE);
            Audio.resume("bgMusic");
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
        btnResume.input(mouse, key);
        btnExit.input(mouse, key);
        
    }

    @Override
    public void render(Graphics2D g) {
        btnResume.render(g);
        btnExit.render(g);
    }


}
