package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ProjectileHero extends Item {
    private final BufferedImage image;
    public static final float projectile_speed = 8.0f;

    public static final int width = 32;
    public static final int height = 32;

    private final float limit = this.x + 400;

    private float velocity;

    private static float damage = 4;

    public ProjectileHero(RefLinks refLink, Hero hero, int direction)
    {
        super(refLink,hero.GetX()+hero.bounds.width,hero.GetY()+hero.bounds.height/2,width,height);
        if(direction == 0) {
            image = Assets.projectile[0];
            velocity -= projectile_speed;
        }
        else {
            image = Assets.projectile[1];
            velocity = projectile_speed;
        }
    }
    public void Update() {
        x+=velocity;
    }
    public boolean ShouldRemove() {
        boolean Hit = false;
        Shape projectileShape = new Rectangle2D.Float(x, y, width, height);
        for(Enemy enemy : refLink.getEnemy()){
            Shape enemyShape = new Rectangle2D.Float(enemy.GetX(), enemy.GetY(), enemy.bounds.width, enemy.bounds.height);
            if(projectileShape.intersects(enemyShape.getBounds2D())){
                Hit = true;
                enemy.life-=damage;
                break;
            }
        }

        return Hit || (velocity < 0 && x < -limit) || (velocity > 0 && x > limit);
    }

  public void Draw(Graphics2D g)
  {
      g.drawImage(image,(int)x,(int)y, width,height, null);
      //  g.setColor(Color.blue);
      // g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width/2, bounds.height/2);
  }

}
