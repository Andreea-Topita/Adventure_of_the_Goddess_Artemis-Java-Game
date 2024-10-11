package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


public class River extends Tile {
    public River(int id, int number) {
        super(Assets.water[number], id);
    }



    public boolean IsSolid()
    {
        return true ;
    }
}
