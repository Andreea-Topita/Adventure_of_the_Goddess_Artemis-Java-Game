package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Path extends Tile{
    public Path(int id,int number)
    {
        super(Assets.path[number],id);
    }

    public boolean IsSolid()
    {
        return false ;
    }
}
