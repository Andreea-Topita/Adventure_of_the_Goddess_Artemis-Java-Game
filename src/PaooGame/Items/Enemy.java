package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Character{

    public  BufferedImage image;
    public boolean movingRight = true;
    public float leftLimit = this.x; //++400
    public float rightLimit = this.x; //+400
    public boolean AttackFlag =false;
    public boolean isDead = false;
    public boolean AttackHit = false;
    public boolean removeEnemy = false;
    public long lastHitTime;
    public long HitCooldown = 250;
    public float damage;
    public int tier;
    public int maxLife;
    public long timerr = 0;


   public Enemy(RefLinks refLink, float x, float y) {
        super(refLink,x,y, Character.DEFAULT_CREATURE_WIDTH,Character.DEFAULT_CREATURE_HEIGHT);
        image = Assets.inamicRight[0];
        this.maxLife = 20;
        this.life = maxLife;
        this.SetSpeed(2.0f);
        damage=5;


        this.tier=1;

       this.leftLimit = x - 400;  // 400 pixeli la stânga
       this.rightLimit = x + 400; // 400 pixeli la dreapta


        //damage la focuri = 4, deci din 5 lovituri inamicul va muri




        normalBounds.x = 20; //17
        normalBounds.y = 22; //25
        normalBounds.width = 24; //20
        normalBounds.height = 40; //32

        attackBounds.x = 50; //45
        attackBounds.y = 50; //45
        attackBounds.width = 50; //38
        attackBounds.height = 50; //38

    }
    public void setLife(int life) {
        this.life = life;
    }

    // Metoda pentru a seta starea decesului
    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }
    public BufferedImage getHealthBarImage() {
        if (life > maxLife) life = maxLife;  // Ensure life does not exceed maxLife
        float healthPercentage = ((float) life / maxLife);
        int index = 4 - (int)(healthPercentage * 4);  // Assuming 5 stages including full to empty
        index = Math.min(Math.max(index, 0), Assets.barEnemy.length - 1);  // Ensure index is within bounds
        return Assets.barEnemy[index];
    }

    public void Update()
    {
        AttackState();
        Attack();
        NPCMove();
        Death();
        Animation();
        //CheckCollision();
    }
    public void CheckCollision() {
        super.CheckCollision(); // Verificam coliziunile cu tile-urile

        // Verificam coliziunea cu eroul
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

    private boolean isCollidingWithHero(float newX, float newY) {
        Hero hero = refLink.GetHero();
        Rectangle enemyBounds = new Rectangle((int) newX + normalBounds.x, (int) newY + normalBounds.y, normalBounds.width, normalBounds.height);
        Rectangle heroBounds = new Rectangle((int) hero.GetX() + hero.normalBounds.x, (int) hero.GetY() + hero.normalBounds.y, hero.normalBounds.width, hero.normalBounds.height);
        return enemyBounds.intersects(heroBounds);
    }

    public void AttackState()
    {
        int attackRangeX = 400; //400
        int attackRangeY = 240; //250

        if(Math.abs(refLink.GetHero().GetX() - this.x) <= attackRangeX && Math.abs(refLink.GetHero().GetY() - this.y) <= attackRangeY && refLink.GetHero().life>0) {
            this.AttackFlag = true;
        } else {
            this.AttackFlag = false;
        }

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
            this.SetSpeed(2.2f);

            if(this.x<refLink.GetHero().GetX()) limita = -50; //50
            else if(this.x>refLink.GetHero().GetX()) limita = 50;

            if(this.x<refLink.GetHero().GetX()+75 && xMove<0) AttackHit = true; //25 , 75
            else if(this.x>refLink.GetHero().GetX()-75 && xMove>0) AttackHit = true;
            else AttackHit = false;

            if(refLink.GetHero().GetX()+limita <= this.x)
                xMove = -speed;
            else if(refLink.GetHero().GetX()+limita >= this.x)
                xMove = speed;
            if(refLink.GetHero().GetY() > this.y) //nimic 10
                yMove = speed;
            else if(refLink.GetHero().GetY() < this.y) //nimic 10
                yMove = -speed;
        }
        else {
            this.SetSpeed(2.2f);
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

        CheckCollision();
        Move();
    }

    //era void inainte
    public boolean Death() {
        if (this.life <= 0 && !isDead) {
            isDead = true; // Marcheaza inamicul ca fiind mort

            // Creaza inima imediat ce inamicul moare
            Heart tempHeart = new Heart(refLink, GetX(), GetY(), 40, 40);
            refLink.getHearts().add(tempHeart);

            refLink.GetHero().SCORE += 20;
            return true;
        }
        return false;
    }

    public void Attack()
    {

        if(AttackFlag && AttackHit)
        {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastHitTime >= HitCooldown){
               /* refLink.GetHero().life-=damage;
                lastHitTime = currentTime;*/
                if (!refLink.GetHero().isInvulnerable) {
                    refLink.GetHero().life -= damage;
                }
                lastHitTime = currentTime;
            }
        }
    }
    private void Animation() {
        spriteCounter++;

        if (spriteCounter > 2 && tier==1) { //2
            spriteNum++;
            if (spriteNum > 9)
                spriteNum = 0;
            spriteCounter = 0;

            if (isDead) {
                if (lastSprite == 1)
                    image = Assets.inamicDeathRight[spriteNum];
                else image = Assets.inamicDeathLeft[spriteNum];
            } else if (AttackHit) {
                if (lastSprite == 1)
                    image = Assets.inamicAttackRight[spriteNum];
                else image = Assets.inamicAttackLeft[spriteNum];
            }
            else {
                if (xMove < 0) {
                    lastSprite = 0;
                    image = Assets.inamicLeft[spriteNum];
                } else if (xMove > 0) {
                    lastSprite = 1;
                    image = Assets.inamicRight[spriteNum];
                } else if (yMove < 0) {
                    if (lastSprite == 1)
                        image = Assets.inamicRight[spriteNum];
                    else image = Assets.inamicLeft[spriteNum];
                } else if (yMove > 0) {
                    if (lastSprite == 1)
                        image = Assets.inamicRight[spriteNum];
                    else image = Assets.inamicLeft[spriteNum];
                }
            }
        }
    }

    @Override
    public void Draw(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, (int) x, (int) y, width, height, null);
        }
        BufferedImage healthBarImage = getHealthBarImage();
        int barWidth = healthBarImage.getWidth() /2;
        int barHeight = healthBarImage.getHeight() /2;
        int barX = (int) (x + (width - barWidth) / 2);
        int barY = (int) y - barHeight - 5;
        g.drawImage(healthBarImage, barX, barY, barWidth, barHeight, null);


        //g.drawImage(image, (int) x, (int) y, width, height, null);
       // g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}

