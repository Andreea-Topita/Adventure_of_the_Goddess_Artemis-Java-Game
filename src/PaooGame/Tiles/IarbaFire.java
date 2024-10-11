package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class IarbaFire extends Tile {
    public IarbaFire(int id,int number)
    {
        super(Assets.fireIarba[number],id);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
