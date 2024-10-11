package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Ares extends Enemy {
    private ArrayList<ProjectileAres> projectilesAres;
    private long lastShotTime;
    public Ares(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y);


        this.tier=2;
        damage=10;
        image = Assets.aresRight[0];

        this.maxLife=10;
        this.life = maxLife;
        HitCooldown = 300; //atacuri mai frecvente
        this.SetSpeed(10.8f);

        this.leftLimit = x - 400;  // 400 pixeli la stânga
        this.rightLimit = x + 400; // 400 pixeli la dreapta

        normalBounds.x = 30; //17
        normalBounds.y = 30; //25
        normalBounds.width = 50; //20
        normalBounds.height = 77; //32

        attackBounds.x = 45;
        attackBounds.y = 45;
        attackBounds.width = 38;
        attackBounds.height = 38;

        projectilesAres = new ArrayList<>();
        lastShotTime = System.currentTimeMillis();

        this.width *= 1.7;
        this.height *= 1.7;
    }

    private void Animation()
    {
        spriteCounter++;

        if(spriteCounter > 2 && tier==2)
        {
            spriteNum++;
            if (spriteNum > 9)
                spriteNum = 0;
            spriteCounter = 0;

            if (isDead) {
                if (lastSprite == 1)
                    image = Assets.aresDeathRight[spriteNum];
                else image = Assets.aresDeathLeft[spriteNum];
            } else if (AttackHit) {
                if (lastSprite == 1)
                    image = Assets.aresAttackRight[spriteNum];
                else image = Assets.aresAttackLeft[spriteNum];
            }
            else {
                if (xMove < 0) {
                    lastSprite = 0;
                    image = Assets.aresLeft[spriteNum];
                } else if (xMove > 0) {
                    lastSprite = 1;
                    image = Assets.aresRight[spriteNum];
                } else if (yMove < 0) {
                    if (lastSprite == 1)
                        image = Assets.aresRight[spriteNum];
                    else image = Assets.aresLeft[spriteNum];
                } else if (yMove > 0) {
                    if (lastSprite == 1)
                        image = Assets.aresRight[spriteNum];
                    else image = Assets.aresLeft[spriteNum];
                }
            }
        }
    }
    public void Update()
    {
        AttackState();
        Attack();
        NPCMove();
        Death();
        Animation();

        Iterator<ProjectileAres> it = projectilesAres.iterator();
        while (it.hasNext()) {
            ProjectileAres proj = it.next();
            proj.Update();
            if (proj.ShouldRemove()) {
                it.remove();
            }
        }
    }
    public void AttackState()
    {
        int attackRangeX = 700; //400
        int attackRangeY = 700; //250

        if(Math.abs(refLink.GetHero().GetX() - this.x) <= attackRangeX &&
                Math.abs(refLink.GetHero().GetY() - this.y) <= attackRangeY &&
                refLink.GetHero().life > 0) {
            this.AttackFlag = true;
        } else {
            this.AttackFlag = false;
        }
    }
    private void CheckCollisionAres() {
        // coliziuni cu tile-urile
        Rectangle futureBounds = new Rectangle((int) (x + xMove + normalBounds.x), (int) (y + yMove + normalBounds.y), normalBounds.width, normalBounds.height);

        // coliziuni pe axa X
        if (xMove > 0) { // ce misca spre dreapta
            int tx = (int) (futureBounds.x + futureBounds.width) / Tile.TILE_WIDTH;
            if (collisionWithTile(tx, (int) (futureBounds.y) / Tile.TILE_HEIGHT) ||
                    collisionWithTile(tx, (int) (futureBounds.y + futureBounds.height) / Tile.TILE_HEIGHT)) {
                x = tx * Tile.TILE_WIDTH - normalBounds.x - normalBounds.width;
                xMove = 0;
            }
        } else if (xMove < 0) { // Se miscăaspre stanga
            int tx = (int) (futureBounds.x) / Tile.TILE_WIDTH;
            if (collisionWithTile(tx, (int) (futureBounds.y) / Tile.TILE_HEIGHT) ||
                    collisionWithTile(tx, (int) (futureBounds.y + futureBounds.height) / Tile.TILE_HEIGHT)) {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - normalBounds.x;
                xMove = 0;
            }
        }

        // coliziuni pe axa Y
        if (yMove > 0) { // ce micca in jos
            int ty = (int) (futureBounds.y + futureBounds.height) / Tile.TILE_HEIGHT;
            if (collisionWithTile((int) (futureBounds.x) / Tile.TILE_WIDTH, ty) ||
                    collisionWithTile((int) (futureBounds.x + futureBounds.width) / Tile.TILE_WIDTH, ty)) {
                y = ty * Tile.TILE_HEIGHT - normalBounds.y - normalBounds.height;
                yMove = 0;
            }
        } else if (yMove < 0) { // se misca in sus
            int ty = (int) (futureBounds.y) / Tile.TILE_HEIGHT;
            if (collisionWithTile((int) (futureBounds.x) / Tile.TILE_WIDTH, ty) ||
                    collisionWithTile((int) (futureBounds.x + futureBounds.width) / Tile.TILE_WIDTH, ty)) {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - normalBounds.y;
                yMove = 0;
            }
        }

        // verifica coliziunea cu eroul
        if (isCollidingWithHero(this.x + xMove, this.y + yMove)) {
            if (xMove > 0) {
                xMove = 0;
                x = refLink.GetHero().GetX() - bounds.width;
            } else if (xMove < 0) {
                xMove = 0;
                x = refLink.GetHero().GetX() + refLink.GetHero().bounds.width;
            }

            if (yMove > 0) {
                yMove = 0;
                y = refLink.GetHero().GetY() - bounds.height;
            } else if (yMove < 0) {
                yMove = 0;
                y = refLink.GetHero().GetY() + refLink.GetHero().bounds.height;
            }
        }
    }
    private boolean collisionWithTile(int x, int y) {
        return refLink.GetMap().GetTile(x, y).IsSolid();
    }
    private boolean isCollidingWithHero(float newX, float newY) {
        Hero hero = refLink.GetHero();
        Rectangle enemyBounds = new Rectangle((int) newX + normalBounds.x, (int) newY + normalBounds.y, normalBounds.width, normalBounds.height);
        Rectangle heroBounds = new Rectangle((int) hero.GetX() + hero.normalBounds.x, (int) hero.GetY() + hero.normalBounds.y, hero.normalBounds.width, hero.normalBounds.height);
        boolean isColliding = enemyBounds.intersects(heroBounds);

        if (isColliding) {
            System.out.println("Ares colliding with Hero at position: " + newX + ", " + newY);
        }

        return isColliding;
    }
    public void NPCMove()
    {
        int limita=0;
        if (isDead) {
            xMove = 0;
            yMove = 0;
            if (spriteNum == 8)
                removeEnemy = true;
        }
        else if(this.AttackFlag){
            this.SetSpeed(2.9f);

            if(this.x<refLink.GetHero().GetX()) limita = -75; //75
            else if(this.x>refLink.GetHero().GetX()) limita = 75; //75

            if(this.x<refLink.GetHero().GetX()+250 && xMove<0) AttackHit = true; //25 , 75
            else if(this.x>refLink.GetHero().GetX()-250 && xMove>0) AttackHit = true;
            else AttackHit = false;

            if(refLink.GetHero().GetX()+limita <= this.x)
                xMove = -speed;
            else if(refLink.GetHero().GetX()+limita >= this.x)
                xMove = speed;
            if(refLink.GetHero().GetY() -50> this.y)
                yMove = speed;
            else if(refLink.GetHero().GetY() -50 < this.y)
                yMove = -speed;
        }
        else {
            this.SetSpeed(2.5f);
            if (movingRight) {
                xMove = speed;
                if (x >= rightLimit) {
                    movingRight = false;
                    xMove = -speed;
                }
            } else {
                xMove = -speed;
                if (x <= leftLimit) {
                    movingRight = true;
                    xMove = speed;
                }
            }
        }
        CheckCollisionAres();
        Move();

    }

    public boolean Death() {
        if (this.life <= 0 && !isDead) {
            isDead = true;

            int numberOfHearts = 3;
            for (int i = 0; i < numberOfHearts; i++) {
                float heartX = this.x + (i * 20) - 30;
                float heartY = this.y + (i % 2 == 0 ? -20 : 20);
                Heart tempHeart = new Heart(refLink, heartX, heartY, 40, 40);
                refLink.getHearts().add(tempHeart);
            }

            refLink.GetHero().SCORE += 50;
            return true;
        }
        return false;
    }
    public void Attack()
    {
        if(AttackFlag) {
        //if(AttackFlag && AttackHit)
       // {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastHitTime >= HitCooldown){
                System.out.println("Firing projectile");  //debug
                //refLink.GetHero().life-=damage;
                lastHitTime = currentTime;

                int direction = (lastSprite == 1) ? 1 : 0;
                ProjectileAres projectile = new ProjectileAres(refLink, this, direction);
                projectilesAres.add(projectile);
            }
        }
    }
    public void Draw(Graphics2D g) {

        super.Draw(g);
        for (ProjectileAres proj : projectilesAres) {
            proj.Draw(g);
        }
    }
}
