package entity;

import graphic.Sprite;
import math.AABB;
import math.Vector2f;
import java.awt.Graphics2D;


public class Enemy extends Entity{
    private AABB sense;
    private int r;
    
    
    private void destroy(){
        
    }
    
    public Enemy(Sprite sprite,Vector2f orgin,int size){
        super(sprite,orgin,size);

        acc = 1f;
        maxSpeed = 2.5f;
        r = 450;
        
        bounds.setWidth(36);
        bounds.setHeight(20);
        bounds.setXOffset(15);
        bounds.setYOffset(45);

        sense = new AABB(new Vector2f(orgin.x + size / 2 - r / 2 ,orgin.y + size / 2 - r / 2 ),r);
    }
    public void move(Player player){
        if(sense.colCircleBox(player.getBounds())){
     
            if(pos.y > player.pos.y + 1){
                dy -= acc;
                up = true;
                down = false;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else if(pos.y < player.pos.y - 1){
                dy += acc;
                down = true;
                up = false;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            } else {
                dy = 0;
                up = false;
                down = false;
            }
            if(pos.x > player.pos.x + 1){
                dx -= acc;
                left = true;
                right = false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            }
            else if(pos.x < player.pos.x - 1){
                dx += acc;    
                right = true;
                left = false;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            } else {
                dx = 0;
                right = false;
                left = false;
            }
        }else {
            up = false;
            down = false;
            left = false;
            right = false;
            dx = 0;
            dy = 0;
        }
    }
    public void update(Player player){
        super.update();
        move(player);
        if(!fallen){
            if(!tc.collisionTile(dx,0)){
            sense.getPos().x += dx;
            pos.x += dx;
            }
            if(!tc.collisionTile(0,dy)){
                sense.getPos().y += dy;
                pos.y += dy;
                
            } 
        }else {
            destroy();
        }
    
       
            
        }
    
    @Override
    public void render(Graphics2D g){
        g.drawImage(ani.getImage(),(int)(pos.getWorldVar().x),(int)(pos.getWorldVar().y),size,size,null);
    }
}
