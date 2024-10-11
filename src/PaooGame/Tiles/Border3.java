package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


public class Border3 extends Tile {
    public Border3(int id, int number) {
        super(Assets.tile_lvl3[number], id);
    }

    public boolean IsSolid() {
        return true;
    }
}