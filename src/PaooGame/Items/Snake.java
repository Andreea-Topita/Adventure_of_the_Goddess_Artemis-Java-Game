package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Snake extends Item{
    private BufferedImage image;
    public int textureCounter = 0;
    public long timer = 0;
    private static float damage=10;
    private boolean hasDealtDamage = false;
    private boolean collected=false;
    private static final long DAMAGE_COOLDOWN = 1000; // Cooldown de 1000 ms (1 secunda)

    public Snake(RefLinks refLink, float x, float y, int width, int height)
    {
        super(refLink, x, y, width, height);

        //imaginea initiala
        image = Assets.snakeR[0];
    }

    public void Update() {
        LoadTexture(Assets.snakeR, 9);
        }

    public void Draw(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
       // g.setColor(Color.blue);
       // g.drawRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    //animatia sarpelui schimband cadrele la un interval de timp
    public void LoadTexture(BufferedImage[] texture, int dimension){
        long currentTime = System.currentTimeMillis(); //timpul curent al sistemului in milisecunde
        if(currentTime - timer > 150) { //daca au trecut mai mult de 150 de ms de la ultima schimbare a frame ului
            textureCounter++;
            image = texture[textureCounter];
            timer = currentTime;
        }
        if(textureCounter >= dimension){
            textureCounter = 0;
        }
    }

    public boolean hasCollided(){
        //o zona mai mare in jurul eroului in care  o coliziunea cu sarpele poate fi detectata
        //scad deoarece astfel de extinde aria de coliziunea
        //daca adun, sarpele si personajul se suprapun
        float playerX = refLink.GetHero().GetX()-40;   //-30
        float playerY = refLink.GetHero().GetY()-40;    //-30
        float playerWidth = refLink.GetHero().width;
        float playerHeight = refLink.GetHero().height-40;    //-30 reducem inaltimea pentru a ajuta coliziunea

         //verifica daca sarpele intra in zona de coliziune a eroului
        if(this.x >= playerX && this.x <= playerX + playerWidth &&
                this.y >= playerY && this.y <= playerY + playerHeight) {
            refLink.GetGame().playSoundEffect(3);
            if (!hasDealtDamage) { // Verifica daca dauna nu a fost deja aplicata
                refLink.GetHero().life -= damage; //daca se detecteaza o coliziune, atunci viata este redusa cu 10
                hasDealtDamage = true;
                refLink.GetHero().SCORE -= 10;
            }
            setCollected(true);
            refLink.GetHero().addSnakes(1);
            return true;
        }
        return false;
    }
    public void setCollected(boolean collected) {
        this.collected = collected;
    }
    public boolean isCollected() {
        return collected;
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

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

}
