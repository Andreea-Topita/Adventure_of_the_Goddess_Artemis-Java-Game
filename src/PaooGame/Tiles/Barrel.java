package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class Barrel extends Tile {
    public Barrel(int id) {
        super(Assets.butoi, id);
    }


    public boolean IsSolid()
    {
        return true ;
    }
}
