package state;

import java.awt.Graphics2D;

import audio.Audio;
import entity.Npc;
import entity.Enemy;
import entity.Player;
import util.Camera;
import util.KeyHandler;
import util.MouseHandler;
import graphic.Font;
import graphic.Sprite;
import main.GamePanel;
import math.Vector2f;
import tiles.TileManager;

public class PlayState extends GameState {
    protected static int countStart = 0;
    protected Font font;
    private Player player;
    private TileManager tm;
    private Camera cam;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;

    // private DialogLoader dl = new DialogLoader();
    public String returnToOldman = "Return to Oldman";
    public static Npc npc1;
    public static Npc npc2;
    public static Npc npc3;
    public static Npc npc4;
    public static Npc npc5;
    public static Npc oldman;
    public static Vector2f map;
    private DialogState dg = new DialogState(gsm, oldman);

    public PlayState(GameStateManager gsm, Camera cam) {
        super(gsm);

        Audio.loadBackgroundMusic("audio/undertale.wav","bgMusic",-14,true);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        this.cam = cam;

        tm = new TileManager("map/BKMAP10.xml", cam);

        font = new Font("font/ZeldaFont.png", 16, 16);
        npc1 = new Npc(new Sprite("entity/NPC/Woman/Woman.png", 32, 32), new Vector2f(3420, 3514), 64,250);
        npc2 = new Npc(new Sprite("entity/NPC/Oldman/Oldman.png", 32, 32), new Vector2f(962, 682), 64,250);
        npc3 = new Npc(new Sprite("entity/NPC/girl/girl.png", 32, 32), new Vector2f(6018, 620), 64,250);
        npc4 = new Npc(new Sprite("entity/NPC/Jester/Jester.png", 32, 32), new Vector2f(5674, 2410), 64,250);
        npc5 = new Npc(new Sprite("entity/NPC/Soldier/Soldier.png", 32, 32), new Vector2f(889, 4947), 64,250);
        oldman = new Npc(new Sprite("entity/NPC/Oldman/Oldman.png", 32, 32), new Vector2f(962, 682), 64,250);
        enemy5 = new Enemy(new Sprite("entity/covid.png", 48, 48), new Vector2f(773, 2334), 64);
        enemy4 = new Enemy(new Sprite("entity/covid.png", 48, 48), new Vector2f(2957, 1268), 64);
        enemy3 = new Enemy(new Sprite("entity/covid.png", 48, 48), new Vector2f(4673, 1311), 64);
        enemy2 = new Enemy(new Sprite("entity/covid.png", 48, 48), new Vector2f(817, 3631), 64);
        enemy1 = new Enemy(new Sprite("entity/covid.png", 48, 48), new Vector2f(3084, 5550), 64);
        player = new Player(cam,
                new Sprite("player/buky.png"),
                new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 100);

        cam.target(player);

    }
    private int countNpcTalked(){
        int count = 0;
        if(npc1.isTalked()){
            count++;
        }
        if(npc2.isTalked()){
            count++;
        }
        if(npc3.isTalked()){
            count++;
        }
        if(npc4.isTalked()){
            count++;
        }
        if(npc5.isTalked()){
            count++;
        }
        return count;
    }
    public void update(double time) {
        Vector2f.setWorldVar(map.x, map.y);

        if (!gsm.isStateActive(GameStateManager.PAUSE) && !gsm.isStateActive(GameStateManager.DIALOG)) {
            player.update(enemy1, enemy2, enemy3, enemy4, enemy5);
            enemy1.update(player);
            enemy2.update(player);
            enemy3.update(player);
            enemy4.update(player);
            enemy5.update(player);

            if (player.playerDeath == true) {
                Audio.stopBackgroundMusic("bgMusic");
                gsm.unloadState(GameStateManager.PLAY);
                gsm.loadState(GameStateManager.GAMEOVER);
                Audio.play("death",3);
            }
            if (player.toDialogs == true) {
                player.updateToPause(false);
                gsm.loadState(GameStateManager.DIALOG);

                if (dg.getCountMessage() > 3) {
                    player.toDialogs = false;
                    gsm.unloadState(GameStateManager.DIALOG);
                }
                System.out.println("NPC1 : " + npc1.talked + "\t NPC2 : " + npc2.talked + "\t NPC3 : " + npc3.talked
                + "\t NPC4 : " + npc4.talked + "\t NPC5 : " + npc5.talked);
            }
              if (DialogState.isStart&&countStart == 0) {
                  player.updateToPause(false);
                  gsm.loadState(GameStateManager.DIALOG);
                  countStart += 1;
             }
            if (npc1.talked && npc2.talked && npc3.talked && npc4.talked && npc5.talked) {
                oldman.update(player);
        
            } else {
                npc1.update(player);
                npc2.update(player);
                npc3.update(player);
                npc4.update(player);
                npc5.update(player);
            }

            
            cam.update();
        }
    }
    public String questCount(){
        String quest = String.format("NPC %d/5",countNpcTalked());
        return quest;
    }
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();
        key.enter.tick();

        // player.input(mouse, key);
        if (!gsm.isStateActive(GameStateManager.PAUSE)) {
            if (cam.getTarget() == player) {
                player.input(mouse, key);
            }
            cam.input(mouse, key);
            enemy1.setMaxSpeed(2.5f);
            enemy2.setMaxSpeed(2.5f);
            enemy3.setMaxSpeed(2.5f);
            enemy4.setMaxSpeed(2.5f);
            enemy5.setMaxSpeed(2.5f);
        }

        if (key.escape.clicked) {
            if (gsm.isStateActive(GameStateManager.PAUSE)) {
                gsm.unloadState(GameStateManager.PAUSE);
            } else {
                player.updateToPause(false);
                enemy1.setMaxSpeed(0f);
                enemy2.setMaxSpeed(0f);
                enemy3.setMaxSpeed(0f);
                enemy4.setMaxSpeed(0f);
                enemy5.setMaxSpeed(0f);
                gsm.loadState(GameStateManager.PAUSE);
            }
        }
    }

    public void render(Graphics2D g) {
        tm.render(g);
        // Sprite.drawArray(g, font, "buky the adventure", new Vector2f(GamePanel.width-
        // 192,32),32,24);
        player.render(g);

        npc1.render(g);
        npc2.render(g);
        npc3.render(g);
        npc4.render(g);
        npc5.render(g);
        oldman.render(g);

          if(countNpcTalked() != 5){
              Sprite.drawArray(g, questCount(), new Vector2f(GamePanel.width / 2 - questCount().length() * (28 / 2) + 480, GamePanel.height / 2 - 32 / 2 - 300), 32, 32, 32);
          }else{
              DialogLoader.tl5.render(g);
          }
        

        enemy1.render(g);
        enemy2.render(g);
        enemy3.render(g);
        enemy4.render(g);
        enemy5.render(g);

        cam.render(g);
    }
}
