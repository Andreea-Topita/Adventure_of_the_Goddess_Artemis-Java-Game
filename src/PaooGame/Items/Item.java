package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

//entitate abstracta de baza pentru obiecte in joc
public abstract class Item {

    public float x;
    public float y;

    protected int width;
    protected int height;
    protected RefLinks refLink;
    public Rectangle bounds;         //dreptunghiul de coliziune
    public Rectangle normalBounds;
    protected Rectangle attackBounds;
    public int lastSprite = 1;

    protected BufferedImage image; // imaginea propriu zisa
    protected long timer = 0;
    public Item(RefLinks refLink, float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;     //latimea imaginii
        this.height = height;   //inaltimea imaginii
        this.refLink = refLink;

        normalBounds = new Rectangle(0, 0, width, height);
        attackBounds = new Rectangle(0,0,width,height);
        bounds = normalBounds;
    }
    public abstract void Update();
    public abstract void Draw(Graphics2D g);

    public float GetX()
    {
        return x;
    }
    public float GetY()
    {
        return y;
    }
    public int GetWidth()
    {
        return width;
    }
    public int GetHeight()
    {
        return height;
    }
    public void SetX(float x)
    {
        this.x = x;
    }
    public void SetY(float y)
    {
        this.y = y;
    }
    public void SetWidth(int width)
    {
        this.width = width;
    }
    public void SetHeight(int height)
    {
        this.height = height;
    }
    public RefLinks getRefLink(){
        return refLink;
    }

}
