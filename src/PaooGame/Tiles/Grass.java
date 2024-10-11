package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Grass extends Tile {
    public Grass(int id)
    {
        super(Assets.grass,id);
    }

    public boolean IsSolid()
    {
        return true;
    }
}