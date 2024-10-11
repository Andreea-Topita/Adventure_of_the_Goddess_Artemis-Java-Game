package PaooGame.Items;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

//clasa abstracta derivata din item
public abstract class Character extends Item{
    public static final int DEFAULT_LIFE =10;
    public static final float DEFAULT_SPEED = 2.2f;
    public static final int DEFAULT_CREATURE_WIDTH = 64; //56
    public static final int DEFAULT_CREATURE_HEIGHT = 64; //56
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int life;
    protected float speed;
    protected float xMove;
    protected float yMove;

    public Character(RefLinks refLinks,float x,float y, int width,int height)
    {
        super(refLinks,x,y,width,height);

        life = DEFAULT_LIFE;
        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }

    public void Move() {
        x += xMove;
        y += yMove;
        //CheckCollision();

    }

    public void CheckCollision() {

        //coordonatele urmatoarei pozitii
        int nextX, nextY;

        if (xMove != 0) {
            //coordonate y ale marginii superioare a obiectului
            int topY = (int) ((y + bounds.y) / Tile.TILE_HEIGHT);
            int bottomY = (int) ((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT);
            //marginii inferioare a obiectului

            if (xMove > 0) {
                nextX = (int) ((x + bounds.x + bounds.width + xMove) / Tile.TILE_WIDTH);
                //coord x ale marginii drepte a obiectului dupa miscare

                if (refLink.GetMap().GetTile(nextX, topY).IsSolid() || refLink.GetMap().GetTile(nextX, bottomY).IsSolid()) {
                    xMove = 0;
                    //setam xmove la 0 pentru a opri miscarea
                }
            } else {
                nextX = (int) ((x + bounds.x + xMove) / Tile.TILE_WIDTH);
                //xmove este negativ deci ne miscam spre stanga
                if (refLink.GetMap().GetTile(nextX, topY).IsSolid() || refLink.GetMap().GetTile(nextX, bottomY).IsSolid()) {
                    xMove = 0;
                    //xmove pe 0 daca tile urile sunt solide
                }
            }
        }


        if (xMove != 0) {

            int topY = (int) ((y + bounds.y) / Tile.TILE_HEIGHT);
            int bottomY = (int) ((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT);

            if (xMove > 0) {
                nextX = (int) ((x + bounds.x + bounds.width + xMove) / Tile.TILE_WIDTH);

                if (refLink.GetMap().GetTile(nextX, topY).IsSolid() || refLink.GetMap().GetTile(nextX, bottomY).IsSolid()) {

                    xMove = 0;
                }
            } else {
                nextX = (int) ((x + bounds.x + xMove) / Tile.TILE_WIDTH);

                if (refLink.GetMap().GetTile(nextX, topY).IsSolid() || refLink.GetMap().GetTile(nextX, bottomY).IsSolid()) {

                    xMove = 0;
                }
            }
        }
        if (yMove != 0) {
            //coordonatele x ale marginii stangi a obiectului
            int leftX = (int) ((x + bounds.x) / Tile.TILE_WIDTH);
            int rightX = (int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH);
            //coordonatele x ale marginii drepte a obiectului

            //ymove negativ=> ne miscam in sus
            if (yMove < 0) {
                nextY = (int) ((y + bounds.y + yMove) / Tile.TILE_HEIGHT);
                //calculam coordonatele y ale marginii superioare a obiectelui dupa miscare

                if (refLink.GetMap().GetTile(leftX, nextY).IsSolid() || refLink.GetMap().GetTile(rightX, nextY).IsSolid()) {
                //verificam daca tile urile sunt solide
                    yMove = 0;
                }
            } else { //daca ymove e pozitiv ne miscam in joc
                nextY = (int) ((y + bounds.y + bounds.height + yMove) / Tile.TILE_HEIGHT);
                //coordonatele y ale marginii inferioare a obiectului dupa miscare
                if (refLink.GetMap().GetTile(leftX, nextY).IsSolid() || refLink.GetMap().GetTile(rightX, nextY).IsSolid()) {

                    yMove = 0;
                }
            }
        }
    }

    public void SetSpeed(float speed){this.speed=speed;}


    public RefLinks getRefLink(){
        return refLink;
    }

    public int GetLife()
    {
        return life;
    }
}
