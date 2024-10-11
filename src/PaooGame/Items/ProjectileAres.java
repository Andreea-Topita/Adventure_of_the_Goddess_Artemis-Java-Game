package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ProjectileAres extends Item{
    private final BufferedImage image;
    public static final float projectile_speed = 6.0f;
    private float velocity;
    private final float limit = this.x + 2000;

    public static final int width = 32;
    public static final int height = 32;
    private static float damage = 4;
    public ProjectileAres(RefLinks refLink, Ares ares, int direction)
    {
        super(refLink,ares.GetX()+ares.bounds.width,ares.GetY()+ares.bounds.height/2,width,height);
        if(direction == 0) {
            image = Assets.projectileAres[0];
            velocity -= projectile_speed;
        }
        else {
            image = Assets.projectileAres[1];
            velocity = projectile_speed;
        }
    }
    public void Update() {
        x+=velocity;
    }
    public boolean ShouldRemove() {
        boolean hit = false;
        Shape projectileShape = new Rectangle2D.Float(x, y, width, height);
        Hero hero = refLink.GetHero();

        Shape heroShape = new Rectangle2D.Float(hero.GetX(), hero.GetY(), hero.bounds.width, hero.bounds.height);
        if (projectileShape.intersects(heroShape.getBounds2D())) {
            hit = true;
            hero.life -= damage;  // Scăderea vieții aici este corectă
        }

        return hit || (velocity < 0 && x < -limit) || (velocity > 0 && x > limit);
    }
    public void Draw(Graphics2D g)
    {
        g.drawImage(image,(int)x,(int)y, width,height, null);
        //  g.setColor(Color.blue);
        // g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width/2, bounds.height/2);
    }
}
