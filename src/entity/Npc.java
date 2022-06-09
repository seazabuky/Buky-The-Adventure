package entity;

import graphic.Sprite;
import math.AABB;
import math.Vector2f;
import java.awt.Graphics2D;

public class Npc extends Entity {
    private AABB sense;
    private int count = 0;
    public boolean talked = false;
    public int countTalked = 0;
    public boolean finishDialog = false;
    public Npc(Sprite sprite, Vector2f orgin, int size,int r) {
        super(sprite, orgin, size);
        acc = 1f;
        maxSpeed = 2.5f;

        bounds.setWidth(36);
        bounds.setHeight(20);
        bounds.setXOffset(15);
        bounds.setYOffset(45);
        sense = new AABB(new Vector2f(orgin.x + size / 2 - r / 2, orgin.y + size / 2 - r / 2), r);
    }
    public void setFinishDialog(boolean temp) {
        this.finishDialog = temp;
    }
    public boolean isTalked() {
        return this.talked;
    }
    public int getCountTalked(){
        if(this.talked){
            this.countTalked = 1;
        }
        else{
            this.countTalked = 0;
        }
        return this.countTalked;
    }
    public boolean getFinishDialog() {
        return this.finishDialog;
    }

    public void npcInteract(Player player) { // condition สำหรับเช็ควว่าอยู่ในระยะ interact สำหรับเข้า dialogStateไหม
        if (sense.colCircleBox(player.getBounds())) {
            player.setToDialogs(true);
            count += 1;
            if (count > 1) {
                player.setToDialogs(false);
                this.setTalked(true);
        

            }
        }
    }

    public void setTalked(boolean temp) {
        this.talked = temp;
    }// เอาไว้เซ็ตว่าคุยกับnpcตัวไหนแล้วบ้าง

    public void update(Player player) {
        super.update();
        npcInteract(player);
    }

    @Override
    public void render(Graphics2D g) {
        // g.setColor(Color.green);
        // g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()),
        //         (int) (bounds.getWidth()), (int) (bounds.getHeight()));

        // g.setColor(Color.blue);
        // g.drawOval((int) (sense.getPos().getWorldVar().x), (int) (sense.getPos().getWorldVar().y), r, r);

        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }

}
