package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import state.GameStateManager;
import util.KeyHandler;
import util.MouseHandler;

public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running;

    private BufferStrategy bs;
    private BufferedImage img;
    private Graphics2D g;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;

    // CONSTRUCTOR
    public GamePanel(BufferStrategy bs, int width, int height) {
        GamePanel.width = width;
        GamePanel.height = height;
        this.bs = bs;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this, "Game Thread");
            thread.start();
        }
    }

    public void initGraphics() {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void init() {
        running = true;

        initGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        //Audio.loadSound("audio/pop2.mp3", "pop1", 0);

        gsm = new GameStateManager(g);
    }

    // UPDATE
    public void update(double time) {
        gsm.update(time);
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        gsm.input(mouse, key);
    }

    // RENDER
    public void render() {
        if (g != null) {

            // rgb(54, 69, 79)
            g.setColor(new Color(54, 69, 79));
            g.fillRect(0, 0, width, height);
            gsm.render(g);
        }
    }

    // DRAW
    public void draw() {
        do {
            Graphics g2 = (Graphics) bs.getDrawGraphics();
            g2.drawImage(img, 3, 26, width + 10, height + 10, null); // true 8, 31
            g2.dispose();
            bs.show();
        } while(bs.contentsLost());
    }


    // RUN
    @Override
    public void run() {
        init();

        final double GAME_HERTZ = 60.0;
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double FPS = 60;
        final double TIME_BETWEEN_RENDERS = 1000000000 / FPS;

        int FRAME_COUNT = 0;
        int LAST_SECOND = (int) (lastUpdateTime / 1000000000);
        int OLD_FRAME_COUNT = 0;

        while (running) {
            double NOW = System.nanoTime();
            int NUM_UPDATES = 0;

            while (((NOW - lastUpdateTime) > TIME_BETWEEN_UPDATES) && (NUM_UPDATES < MAX_UPDATES_BEFORE_RENDER)) {
                update(NOW);
                input(mouse,key);
                lastUpdateTime += TIME_BETWEEN_UPDATES;
                NUM_UPDATES++;
            }
            if (NOW - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                lastUpdateTime = NOW - TIME_BETWEEN_UPDATES;
            }

            input(mouse,key);
            render();
            draw();
            lastRenderTime = NOW;
            FRAME_COUNT++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > LAST_SECOND) {
                if (FRAME_COUNT != OLD_FRAME_COUNT) {
                    System.out.println("FPS: " + FRAME_COUNT);
                    OLD_FRAME_COUNT = FRAME_COUNT;
                }
                FRAME_COUNT = 0;
                LAST_SECOND = thisSecond;
            }

            while (NOW - lastRenderTime < TIME_BETWEEN_RENDERS && (NOW - lastUpdateTime < TIME_BETWEEN_UPDATES)) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    e.printStackTrace();
                }
                NOW = System.nanoTime();
            }

        }
    }
} 
