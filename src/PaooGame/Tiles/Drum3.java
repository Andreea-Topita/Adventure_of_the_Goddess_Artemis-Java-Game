package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Drum3 extends Tile {
    public Drum3(int id, int number) {
        super(Assets.tile_lvl3[number], id);
    }

    public boolean IsSolid() {
        return false;
    }
}