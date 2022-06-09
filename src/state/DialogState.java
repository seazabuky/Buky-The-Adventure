package state;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.Arrays;

import audio.Audio;
import entity.Npc;
import main.GamePanel;
import math.Vector2f;
import ui.Button;
import util.KeyHandler;
import util.MouseHandler;

public class DialogState extends GameState {
    private Button btnNext;
    private Button btnPrevious;

    private Font font;
    public int countMessage = 0;

    protected static ArrayList<Button> mgForStart;
    protected static ArrayList<Button> mgForNpc1; // Woman
    protected static ArrayList<Button> mgForNpc2; // OldmanStart
    protected static ArrayList<Button> mgForNpc3; //Girl
    protected static ArrayList<Button> mgForNpc4; //Jester
    protected static ArrayList<Button> mgForNpc5; // soldier
    protected static ArrayList<Button> mgForOldman; //OldmanED

    protected static boolean isStart = true;
    protected static boolean npcT1 = true;
    protected static boolean npcT2 = true;
    protected static boolean npcT3 = true;
    protected static boolean npcT4 = true;
    protected static boolean npcT5 = true;
    protected static boolean oldmanT = true;

    public DialogState(GameStateManager gsm, Npc npc) {
        super(gsm);

        BufferedImage imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);

        btnPrevious = new Button("Previous", imgButton, font,
                new Vector2f(GamePanel.width / 2 - 480, GamePanel.height / 2 + 300), 32, 16);
        btnNext = new Button("NEXT", imgButton, font,
                new Vector2f(GamePanel.width / 2 + 480, GamePanel.height / 2 + 300), 32, 16);

        btnPrevious.addHoverImage(btnNext.createButton("Previous", imgHover, font, btnPrevious.getWidth(),
                btnPrevious.getHeight(), 32, 20));
        btnNext.addHoverImage(
                btnNext.createButton("NEXT", imgHover, font, btnNext.getWidth(), btnNext.getHeight(), 32, 20));

        btnNext.addEvent(e -> {
            this.countMessage += 1;
            Audio.play("pop1", 3); // Play Audio
            System.out.println(this.countMessage);
        });
        btnPrevious.addEvent(e -> {
            if (this.countMessage > 0) {
                Audio.play("pop2", 3);
                this.countMessage -= 1;
                System.out.println(this.countMessage);
            }
        });

        mgForStart = new ArrayList<Button>(Arrays.asList(DialogLoader.start1, DialogLoader.start2, DialogLoader.start3, DialogLoader.start4, DialogLoader.start5));
        mgForNpc1 = new ArrayList<Button>(Arrays.asList(DialogLoader.wm1,DialogLoader.wm2));
        mgForNpc2 = new ArrayList<Button>(Arrays.asList(DialogLoader.ol1));
        mgForNpc3 = new ArrayList<Button>(Arrays.asList(DialogLoader.gl1, DialogLoader.gl2));
        mgForNpc4 = new ArrayList<Button>(Arrays.asList(DialogLoader.je1, DialogLoader.je2));
        mgForNpc5 = new ArrayList<Button>(Arrays.asList(DialogLoader.sd1, DialogLoader.sd2,DialogLoader.sd3));
        mgForOldman = new ArrayList<Button>(Arrays.asList(DialogLoader.ol2));
    }

    public static void setToDefault() {
        isStart = true;
        npcT1 = true;
        npcT2 = true;
        npcT3 = true;
        npcT4 = true;
        npcT5 = true;
        oldmanT = true;
    }

    public int getCountMessage() {
        return this.countMessage;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    @Override
    public void update(double time) {
        getCountMessage();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnNext.input(mouse, key);
        btnPrevious.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        btnNext.render(g);

        if (this.countMessage > 0) {
            btnPrevious.render(g);
        }
        if (isStart) {
            if (this.countMessage <= (mgForStart.size() - 1)) {
                mgForStart.get(this.countMessage).render(g);
            }
            if (this.countMessage > mgForStart.size() - 1) {
                mgForStart = null;
                this.countMessage = 0;
                gsm.unloadState(GameStateManager.DIALOG);
                isStart = false;
            }
        }
        if (PlayState.npc1.talked && npcT1) {
            if (this.countMessage <= (mgForNpc1.size() - 1)) {
                mgForNpc1.get(this.countMessage).render(g);
            }
            if (this.countMessage > (mgForNpc1.size() - 1)) {
                mgForNpc1 = null;
                this.countMessage = 0;
                gsm.unloadState(GameStateManager.DIALOG);
                npcT1 = false;

            }
        }
        if (PlayState.npc2.talked && npcT2) {
            if (this.countMessage <= (mgForNpc2.size() - 1)) {
                mgForNpc2.get(this.countMessage).render(g);
            }

            if (this.countMessage > (mgForNpc2.size() - 1)) {
                mgForNpc2 = null;
                this.countMessage = 0;
                gsm.unloadState(GameStateManager.DIALOG);
                npcT2 = false;
            }

        }

        // System.out.println("NPC2 Talked");

        // System.out.println(npcT2);
        if (PlayState.npc3.talked && npcT3) {
            if (this.countMessage <= (mgForNpc3.size() - 1)) {
                mgForNpc3.get(this.countMessage).render(g);
            }
            if (this.countMessage > (mgForNpc3.size() - 1)) {
                mgForNpc3 = null;
                this.countMessage = 0;
                gsm.unloadState(GameStateManager.DIALOG);
                npcT3 = false;
            }
            // System.out.println("NPC3 Talked");
            // System.out.println(npcT3);
        }
        if (PlayState.npc4.talked && npcT4) {
            if (this.countMessage <= (mgForNpc4.size() - 1)) {
                mgForNpc4.get(this.countMessage).render(g);
            }
            if (this.countMessage > (mgForNpc4.size() - 1)) {
                mgForNpc4 = null;
                this.countMessage = 0;
                gsm.unloadState(GameStateManager.DIALOG);
                npcT4 = false;
            }

            // System.out.println("NPC4 Talked");

            // System.out.println(npcT4);
        }
        if (PlayState.npc5.talked && npcT5) {
            if (this.countMessage <= (mgForNpc5.size() - 1)) {
                mgForNpc5.get(this.countMessage).render(g);
            }
            if (this.countMessage > (mgForNpc5.size() - 1)) {
                mgForNpc5 = null;
                this.countMessage = 0;
                gsm.unloadState(GameStateManager.DIALOG);
                npcT5 = false;
            }

            // System.out.println("NPC5 Talked");
            // System.out.println(npcT5);

        }
        if (PlayState.oldman.talked && oldmanT) {
            if (this.countMessage <= (mgForOldman.size() - 1)) {
                mgForOldman.get(this.countMessage).render(g);
            }
            if (this.countMessage > (mgForOldman.size() - 1)) {
                mgForOldman = null;
                this.countMessage = 0;
                gsm.unloadState(GameStateManager.DIALOG);
                gsm.unloadState(GameStateManager.PLAY);
                gsm.loadState(GameStateManager.END);
                oldmanT = false;
            }
            // System.out.println("OldMan Talked");
            // System.out.println(oldmanT);
        }
        
    }
}
