package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.image.BufferedImage;

public class ItemFactory {

    public static Coin createCoin(RefLinks refLink, float x, float y,int width, int height){
        return new Coin(refLink,x,y,width,height);
    }
    public static Heart createHeart(RefLinks refLinks, float x, float y, int width, int height) {
        return new Heart(refLinks, x, y, width, height);
    }

    public static Snake createSnake(RefLinks refLinks, float x, float y, int width, int height){
        return new Snake(refLinks,x,y,width,height);
    }
}
