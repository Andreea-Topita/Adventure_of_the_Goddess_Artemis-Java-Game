package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Grass3 extends Tile {
    public Grass3(int id, int number) {
        super(Assets.tile_lvl3[number], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
