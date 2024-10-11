package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Heart extends Item{

    private BufferedImage image;
    public boolean isCollected;
    public float timer = 0;

    public Heart(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
        this.image = Assets.heart[0];
        isCollected = false;
    }

    public void Update() {
        if(hasCollided() && !isCollected){
            long currentTime = System.currentTimeMillis();
            if (currentTime - timer > 200) {
                int currentLife = refLink.GetHero().life;
                refLink.GetHero().setLife(currentLife + 20);
                timer = currentTime;
                refLink.GetHero().SCORE += 10;
            }
            isCollected = true;
        }
    }

    public void Draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }
    public boolean hasCollided(){
        float playerX = refLink.GetHero().GetX();
        float playerY = refLink.GetHero().GetY();
        float playerWidth = refLink.GetHero().width + 15;
        float playerHeight = refLink.GetHero().height + 15;

        if(this.x >= playerX && this.x <= playerX + playerWidth &&
                this.y >= playerY && this.y <= playerY + playerHeight){
            refLink.GetGame().playSoundEffect(1);
            return true;
        }
        return false;
    }
    public boolean Collected() {
        return isCollected;
    }

    public void setCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }
    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }
}
