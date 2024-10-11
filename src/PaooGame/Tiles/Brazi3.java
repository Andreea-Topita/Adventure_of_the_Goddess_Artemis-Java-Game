package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class Brazi3 extends Tile{
    public Brazi3(int id,int number)
    {
        super(Assets.brazi3[number],id);
    }
    public void Draw(Graphics g, int x, int y) {
        int scaledWidth = TILE_WIDTH; // Ajustează după necesități
        int scaledHeight = TILE_HEIGHT * 2; // Ajustează după necesități
        g.drawImage(img, x, y, scaledWidth, scaledHeight, null);
    }


    public boolean IsSolid()
    {
        return true ;
    }
}
