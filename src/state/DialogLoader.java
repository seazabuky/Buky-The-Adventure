package state;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Font;

import graphic.Sprite;
import main.GamePanel;
import math.Vector2f;
import ui.Button;

public class DialogLoader {
    private static Font font;
    public static Graphics2D g;

    public static Button tl1;
    public static Button tl2;
    public static Button tl3;
    public static Button tl4;
    public static Button tl5;

    public static Button start1;
    public static Button start2;
    public static Button start3;
    public static Button start4;
    public static Button start5;

    public static Button ol1;
    public static Button ol2;

    public static Button wm1;
    public static Button wm2;

    public static Button je1;
    public static Button je2;

    public static Button gl1;
    public static Button gl2;

    public static Button sd1;
    public static Button sd2;
    public static Button sd3;
 
    // * ---------------------Path---------------------------- *//
    public static Sprite tllog1;
    public static Sprite tllog2;
    public static Sprite tllog3;
    public static Sprite tllog4;
    
    public static Sprite tllog5;

    public static Sprite stlog1;
    public static Sprite stlog2;
    public static Sprite stlog3;
    public static Sprite stlog4;
    public static Sprite stlog5;

    public static Sprite gllog1;
    public static Sprite gllog2;

    public static Sprite jelog1;
    public static Sprite jelog2;

    public static Sprite ollog;
    public static Sprite ollogED;

    public static Sprite sdlog1;
    public static Sprite sdlog2;
    public static Sprite sdlog3;

    public static Sprite wmlog1;
    public static Sprite wmlog2;

    public static void init() {
        font = new Font("MeatMadness", Font.PLAIN, 48);
        // * ------------------Tutorial---------------------------- *//
        tllog1 = new Sprite("dialog/Tutorial/1.png", 1024, 768);
        tllog2 = new Sprite("dialog/Tutorial/2.png", 1024, 768);
        tllog3 = new Sprite("dialog/Tutorial/3.png", 1024, 768);
        tllog4 = new Sprite("dialog/Tutorial/4.png", 1024, 768);
        tllog5 = new Sprite("dialog/Tutorial/Return.png", 300, 100);

        // * ------------------ Start Dialog ------------------ *//
        stlog2 = new Sprite("dialog/Buky/2.png", 561, 407);
        stlog1 = new Sprite("dialog/Buky/1.png", 561, 407);
        stlog3 = new Sprite("dialog/Buky/3.png", 561, 407);
        stlog4 = new Sprite("dialog/Buky/4.png", 561, 407);
        stlog5 = new Sprite("dialog/Buky/5.png", 561, 407);

        // * ------------------ Oldman ------------------ *//
         ollog = new Sprite("dialog/Oldman/1.png", 561, 407);
         ollogED = new Sprite("dialog/Oldman/2.png", 561, 407);

        // * ------------------ Woman ------------------ *//
        wmlog1 = new Sprite("dialog/Woman/1.png", 561, 407);
        wmlog2 = new Sprite("dialog/Woman/2.png", 561, 407);

        // * ------------------ Jester ------------------ *//
        jelog1 = new Sprite("dialog/Jester/1.png", 561, 407);
        jelog2 = new Sprite("dialog/Jester/2.png", 561, 407);

        // * ------------------ Girls ------------------ *//
        gllog1 = new Sprite("dialog/Girl/1.png", 561, 407);
        gllog2 = new Sprite("dialog/Girl/2.png", 561, 407);

        // * ------------------ Soldier ------------------ *//
        sdlog1 = new Sprite("dialog/Soldier/1.png", 561, 407);
        sdlog2 = new Sprite("dialog/Soldier/2.png", 561, 407);
        sdlog3 = new Sprite("dialog/Soldier/3.png", 561, 407);

        // * ------------------ Buffer Sprite ------------------ *//

        tl1 = new Button("", Buffer(tllog1,0,0,1024,768), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        tl2 = new Button("", Buffer(tllog2,0,0,1024,768), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        tl3 = new Button("", Buffer(tllog3,0,0,1024,768), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        tl4 = new Button("", Buffer(tllog4,0,0,1024,768), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        tl5 = new Button("", Buffer(tllog5,0,0,300,100), font, new Vector2f(GamePanel.width / 2 + 480, GamePanel.height / 2 - 300), 300, 100);

        start1 = new Button("", Buffer(stlog1,0,0,561, 407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        start2 = new Button("", Buffer(stlog2,0,0,561, 407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561,407);
        start3 = new Button("", Buffer(stlog3,0,0,561, 407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561,407);
        start4 = new Button("", Buffer(stlog4,0,0,561, 407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        start5 = new Button("", Buffer(stlog5,0,0,561, 407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561,407);

        ol1 = new Button("", Buffer(ollog,0,0,561,401), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        ol2 = new Button("", Buffer(ollogED,0,0,561,401), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);

        wm1 = new Button("", Buffer(wmlog1,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        wm2 = new Button("", Buffer(wmlog2,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);

        je1 = new Button("", Buffer(jelog1,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        je2 = new Button("", Buffer(jelog2,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        
        gl1 = new Button("", Buffer(gllog1,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        gl2 = new Button("", Buffer(gllog2,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        
        sd1 = new Button("", Buffer(sdlog1,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        sd2 = new Button("", Buffer(sdlog2,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
        sd3 = new Button("", Buffer(sdlog3,0,0,561,407), font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2), 561, 407);
    }
    public static BufferedImage Buffer(Sprite sprites, int x, int y, int width, int height) {
        BufferedImage image = sprites.getSubimage(x, y, width, height);
        return image;
    }
}
