package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Bulb extends Tile{
    public Bulb(int id,int number)
    {
        super(Assets.grass_bulb[number],id);
    }

    public boolean IsSolid()
    {
        return true ;
    }
}
