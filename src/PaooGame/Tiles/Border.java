package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Border extends Tile{
    public Border(int id,int number)
    {
        super(Assets.path[number],id);
    }

    public boolean IsSolid()
    {
        return true ;
    }
}
