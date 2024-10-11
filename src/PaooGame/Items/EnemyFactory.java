package PaooGame.Items;

import PaooGame.RefLinks;

public class EnemyFactory{
    public static Enemy createEnemy(RefLinks refLink, int x, int y,int tier){

        switch (tier)
        {
            case 1:
                return new Enemy(refLink,x,y);
            case 2:
                return new Ares(refLink,x,y);
            default:
                return null;
        }
    }
}
