package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class Tree extends Tile {
    public Tree(int id, int number) {
        super(Assets.trees[number], id);
    }

    public void Draw(Graphics g, int x, int y) {
        int scaledWidth = TILE_WIDTH * 2; // Sau orice alt factor de scalare dorești
        int scaledHeight = TILE_HEIGHT * 2;
        g.drawImage(img, x, y, scaledWidth, scaledHeight, null);
    }


    public boolean IsSolid()
    {
        return true ;
    }
}
