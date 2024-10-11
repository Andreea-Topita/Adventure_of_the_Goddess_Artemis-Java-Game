package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Coin extends Item{

    public BufferedImage image;
    public int textureCounter = 0;
    public long timer = 0;
    private boolean collected;
    public Coin(RefLinks refLink, float x, float y, int width, int height){
        super(refLink, x, y, width, height);
        //imaginea initiala
        image = Assets.coin[0];

    }
    public void Update() {
        LoadTexture(Assets.coin, 8);
        //hasCollided();
    }

    public void Draw(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
       // g.setColor(Color.blue);
        //g.drawRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    public void LoadTexture(BufferedImage[] texture, int dimension){
        long currentTime = System.currentTimeMillis();
        if(currentTime - timer > 150) {
            textureCounter++;
            image = texture[textureCounter];
            timer = currentTime;
        }
        if(textureCounter >= dimension){
            textureCounter = 0;
        }
    }
    public boolean hasCollided(){

        float playerX = refLink.GetHero().GetX()-5;
        float playerY = refLink.GetHero().GetY()-5;
        float playerWidth = refLink.GetHero().width;
        float playerHeight = refLink.GetHero().height;

        if(this.x >= playerX && this.x <= playerX + playerWidth &&
                this.y >= playerY && this.y <= playerY + playerHeight){
            setCollected(true);
            refLink.GetHero().addCoins(1);
            refLink.GetGame().playSoundEffect(1);
            // adauga scor
            refLink.GetHero().SCORE += 15;

            return true;
        }
        return false;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }



    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }
}
