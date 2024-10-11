package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.ArtemisDiedState;
import PaooGame.States.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

public class Hero extends Character {

    private static Hero instance;
    private BufferedImage image;
    private int coins=0;
    private int snakes=0;
    private boolean AttackFlag = false;
    public boolean isDead = false;
    private boolean deathAnimationPlayed = false;
    private long lastShotTime;
    private final long shotCooldown = 300;
    public int deathstate = 0;
    private ArrayList<ProjectileHero> projectilesHero;
    public boolean isInvulnerable = false;
    private int invulnerabilityTimer = 0;
    private final int INVULNERABILITY_DURATION = 120;
    public long timeOfDeath = 0;

    //baza de date:
    public int SCORE = 0;
    public String Username;

    Date startDate = new Date();
    public int seconds;
    public int minutes;


    //pt singleton trb pus privat
    private Hero(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        image = Assets.artemisRight[0];

        this.life = 100;
        this.SetSpeed(4.0f);

        normalBounds.x = 17; //36 15 tot 17  17    17
        normalBounds.y =25; //15 32    8     25     12
        normalBounds.width = 20; //5 20   20   20    25
        normalBounds.height = 32; //32 26   47   32   48

        attackBounds.x = 45; //10
        attackBounds.y = 45;
        attackBounds.width = 38;
        attackBounds.height = 38;

        projectilesHero = new ArrayList<>();
        lastShotTime = System.currentTimeMillis();


        this.Username = refLink.username;

    }
    public static Hero getInstance(RefLinks refLink,float x,float y)
    {
        if(instance==null)
        {
            instance=new Hero(refLink,x,y);
        }
        else {
            instance.x=x;
            instance.y=y;
        }
        return instance;
    }


    public void Update()
    {
        Timer(this.startDate);

        // Verificam daca moartea a fost declansata
        if (deathstate == 35) {
            if (System.currentTimeMillis() - timeOfDeath >= 2000) { // 2000 milisecunde = 2 secunde
                State.SetState(new ArtemisDiedState(refLink));
                return;
            }
        }
        GetInput();
        CheckCollision();
        Attack();
        Move();
        Death();
        Animation();
        UpdateProjectiles();
        manageInvulnerability();

    }
    public void Timer(Date startDate){

        Date endDate = new Date();
        long elapsedTime = endDate.getTime() - startDate.getTime();
        this.seconds = (int) (elapsedTime / 1000) % 60;
        this.minutes = (int) (elapsedTime / 1000) / 60;
    }



    private void UpdateProjectiles()
    {
        for (int i = 0; i < projectilesHero.size(); i++) {
            ProjectileHero p = projectilesHero.get(i);
            p.Update();
            if (p.ShouldRemove()) {
                projectilesHero.remove(i);
                i--;
            }
        }
    }

    private void shootProjectile()
    {
        if (projectilesHero.isEmpty() || projectilesHero.get(projectilesHero.size() - 1).GetX() > 30) {
            int direction = (lastSprite==1) ? 1 : 0;
            ProjectileHero p = new ProjectileHero(refLink, this, direction);
            projectilesHero.add(p);
        }
    }
    public void Death()
    {
        if (life <= 0 && !isDead) {
            isDead = true;
            timeOfDeath = System.currentTimeMillis(); //seteaza timpul morții
            System.out.println("dead.");
        } else if (life > 0) {
            System.out.println("life: " + life);
        }
    }
    private void Attack()
    {
        if(refLink.GetKeyManager().attack) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastShotTime >=shotCooldown){
                AttackFlag = true;
                shootProjectile();
                lastShotTime = currentTime;
            }
            xMove=0;
            yMove=0;
        }
    }
    public void receiveDamage(int damage) {
        if (!isInvulnerable) {
            life -= damage;
        }
    }
    public void activateMagic() {
        invulnerabilityTimer = INVULNERABILITY_DURATION;
    }
    private void manageInvulnerability() {
        if (invulnerabilityTimer > 0) {
            invulnerabilityTimer--;
            System.out.println("Invulnerability time remaining: " + invulnerabilityTimer + " frames");
            isInvulnerable = true;
        } else {
            System.out.println("vulnerable.");
            isInvulnerable = false;
        }
    }
    private void Animation()
    {
        spriteCounter++;
        //daca pragul este depasit, se reseteaza contorul si se trece la urmatorul frame
        if(spriteCounter > 2)
        {
            spriteNum++;
            if (spriteNum > 9) //daca a depasit numarul total de cadre disponibile, se trece la 0
                spriteNum = 0;

            spriteCounter = 0;

            if (isDead) {
                if(!deathAnimationPlayed) { //daca nu a fost redata deja animatia complet
                    if (lastSprite == 1)
                        image = Assets.artemisDeathRight[spriteNum];
                    else image = Assets.artemisDeathLeft[spriteNum];

                    if (spriteNum == 9) {
                        image = Assets.artemisDeathRight[9];
                    }
                    if(spriteNum==9) deathAnimationPlayed =true; //cand s a ajung la ulrtimul cadru=>sfarsitul animatiei
                }

            }
            else if(AttackFlag){ //daca e in stare de atac, semnalizata de AttackFlag, se selecteaza cadrele coresp
                if(lastSprite == 1)
                    image = Assets.artemisAttackRight[spriteNum];
                else image = Assets.artemisAttackLeft[spriteNum];
                if(spriteNum == 9)
                    AttackFlag = false; //sfarsitul animatiei de atac
            }
            else { //miscare si stari statice in fct de inputul de la tastatura
                if (refLink.GetKeyManager().left) {
                    lastSprite = 0;
                    image = Assets.artemisLeft[spriteNum];
                } else if (refLink.GetKeyManager().right) {
                    lastSprite = 1;
                    image = Assets.artemisRight[spriteNum];
                } else if (refLink.GetKeyManager().up) {
                    if (lastSprite == 1)
                        image = Assets.artemisRight[spriteNum];
                    else image = Assets.artemisLeft[spriteNum];
                } else if (refLink.GetKeyManager().down) {
                    if (lastSprite == 1)
                        image = Assets.artemisRight[spriteNum];
                    else image = Assets.artemisLeft[spriteNum];
                }
                else if(refLink.GetKeyManager().circle){
                    activateMagic();
                    if(lastSprite==1)
                        image=Assets.artemisMagicRight[spriteNum];
                    else image =Assets.artemisMagicLeft[spriteNum];
                }
                else {
                    if (lastSprite == 1)
                        image = Assets.artemisIdleRight[spriteNum];
                    else image = Assets.artemisIdleLeft[spriteNum];
                }
            }

        }
    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
        for(ProjectileHero p : projectilesHero)
            p.Draw(g);

       // g.setColor(Color.blue);
       // g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    private void GetInput() {
        //Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        //tasta "sus"
        if (refLink.GetKeyManager().up) {
            yMove = -speed;
        }

        // tasta "jos"
        if (refLink.GetKeyManager().down)
        {
            yMove = speed;

        }

        // tasta "left"
        if (refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }

        //tasta "dreapta"
        if (refLink.GetKeyManager().right)
        {
            xMove = speed;
        }

        if (isDead)
        {
            xMove = 0;
            yMove = 0;
        }
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setSnakes(int snakes) {
        this.snakes = snakes;
        refLink.setSnakesCount(snakes);
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void addCoins(int number){
        coins+=number;
        refLink.setCoinsCount(coins);
    }
    public int getCoins(){return coins;}
    public void setCoins(int coins) {
        this.coins = coins;
        refLink.setCoinsCount(coins);
    }
    public void addSnakes(int number){
        snakes+=number;
        refLink.setSnakesCount(snakes);
    }
    public int getSnakes(){return snakes;}

    public void setLife(int life) {
        this.life = life;
    }
    public void resetHero() {
        this.life = 100;
        this.coins = 0;
        this.snakes = 0;
        this.isDead = false;
        this.deathAnimationPlayed = false;
        this.invulnerabilityTimer = 0;
        this.projectilesHero.clear();
        this.SCORE = 0; // Resetarea scorului
    }
}
