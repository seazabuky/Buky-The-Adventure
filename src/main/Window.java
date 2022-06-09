package main;

import javax.swing.JFrame;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {

    public static final long serialVersionUID = 1L;

    private BufferStrategy bs;
    private GamePanel gp;
    
    public Window(){
        setTitle("Buky The Adventure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addNotify() {
        super.addNotify();

        createBufferStrategy(2);
        bs = getBufferStrategy();

        gp = new GamePanel(bs, 1280, 720);
        //add(gp);
        setContentPane(gp);
        
    }

}
