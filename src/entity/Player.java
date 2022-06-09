package entity;

import java.awt.Graphics2D;

import state.PlayState;
import graphic.Sprite;
import main.GamePanel;
import math.Vector2f;
import util.Camera;
import util.KeyHandler;
import util.MouseHandler;


public class Player extends Entity{
    
    public boolean playerDeath = false;
    private Camera cam;
    public boolean toDialogs = false;
    
    public Player(Camera cam, Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);
        this.cam = cam;
        acc = 3f;
        maxSpeed = 4f;  // Set Character Speed
        bounds.setWidth(36);
        bounds.setHeight(20);
        bounds.setXOffset(25);
        bounds.setYOffset(60);
    }

    
    private void resetPosition(){
        System.out.println("Reseting Player... ");
        pos.x = GamePanel.width / 2 - 32;
        PlayState.map.x = 0;
        cam.getPos().x = 0;

        pos.y = GamePanel.height /2 - 32;
        PlayState.map.y = 0;
        cam.getPos().y = 0;

        setAnimation(RIGHT,sprite.getSpriteArray(RIGHT),10);
    }
    public void update(Enemy enemy1, Enemy enemy2,Enemy enemy3,Enemy enemy4,Enemy enemy5) {
        super.update();

        if(bounds.collides(enemy1.getBounds())||bounds.collides(enemy2.getBounds())||
        bounds.collides(enemy3.getBounds())||bounds.collides(enemy4.getBounds())||bounds.collides(enemy5.getBounds())){
            playerDeath = true;
        }

        if(!fallen){
            move();
            if(!tc.collisionTile(dx,0)){
                //PlayState.map.x += dx;
                pos.x += dx;
                xCol = false;
            } else {
                xCol = true;
            }
            if(!tc.collisionTile(0,dy)){
                //PlayState.map.y += dy;
                pos.y += dy;
                yCol = false;
            } else {
                yCol = true;
            }

            tc.normalTile(dx, 0);
            tc.normalTile(0, dy);
        } else {
            xCol = true;
            yCol = true;
            if(ani.hasPlayedOnce()){
                resetPosition();
                dx = 0;
                dy = 0;	
                fallen = false;
            }
        }
        
    }
    public void setToDialogs(boolean toDialogs){
        this.toDialogs = toDialogs;
    }
    @Override
    public void render(Graphics2D g) {
        // g.setColor(Color.green);
        // g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()),(int) bounds.getWidth(),(int)bounds.getHeight());
        
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }
    public void updateToPause(boolean temp){
        this.up = temp;
        this.down = temp;
        this.left = temp;
        this.right = temp;
    }

    public void input(MouseHandler mouse, KeyHandler key ){
        if (mouse.getButton() == 1){
            //System.out.println("Player: " + pos.x + " " + pos.y);
        }
        if(!fallen){
            if(key.up.down){
            up = true;
            } else {
                up = false;
            }
            if(key.down.down){
                down = true;
            } else {
                down = false;
            }
            if(key.left.down){
                left = true;
            } else {
                left = false;
            }
            if(key.right.down){
                right = true;
            } else {
                right = false;
            }
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
        
    }
    
}
