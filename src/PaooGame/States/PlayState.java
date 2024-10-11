package PaooGame.States;
import PaooGame.Database;
import PaooGame.Items.*;

import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.UserInterface;
import java.awt.*;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static PaooGame.Items.EnemyFactory.createEnemy;
import static PaooGame.Items.ItemFactory.*;
import PaooGame.LevelLoader;

public class PlayState extends State {

    private final Hero hero;
    private Map map;
    private int counter = 0;

    private ArrayList<Enemy> enemies;
    private ArrayList<Heart> hearts;
    private  ArrayList<Coin> coins;
    private  ArrayList<Snake> snakes;
    private final ArrayList<String> levels = new ArrayList<String>(); //niciodata static

    private final UserInterface UI;
    private String levelPath;

    private final LevelLoader Level;


    public void loadGame() {
        Database db = new Database();
        ResultSet rs = null;
        try {
            rs = db.loadGame();
            if (rs != null && rs.next()) {
                float heroPosX = rs.getFloat("heroPosX");
                float heroPosY = rs.getFloat("heroPosY");
                String levelName = rs.getString("levelName");
                int currentLevel = rs.getInt("currentLevel");
                int score = rs.getInt("score");
                int life = rs.getInt("life");

                int coins = rs.getInt("coins"); // Adăugat câmpul pentru monede
                int snakes = rs.getInt("snakes"); // Adăugat câmpul pentru șerpi

                PlayState loadedState = new PlayState(refLink, levelName);
                State.SetState(loadedState);

                // seteaza valorile incarcate
                loadedState.hero.SetX(heroPosX);
                loadedState.hero.SetY(heroPosY);
                refLink.currentLevel = currentLevel;
                refLink.score = score;
                loadedState.hero.SCORE = score;
                loadedState.hero.setLife(life);

                loadedState.hero.setCoins(coins); // Setăm numărul de monede
                loadedState.hero.setSnakes(snakes); // Setăm numărul de șerpi

                loadedState.coins.clear();
                loadedState.coins.addAll(db.loadCoins());

                loadedState.hearts.clear();
                loadedState.hearts.addAll(db.loadHearts());

                loadedState.snakes.clear();
                loadedState.snakes.addAll(db.loadSnakes());

                // incarca inamicii si ii adauga la lista de inamici
                loadedState.enemies.clear();
                loadedState.enemies.addAll(db.loadEnemies(refLink));

                for (Coin coin : loadedState.coins) {
                    coin.setRefLink(refLink);
                }

                for (Heart heart : loadedState.hearts) {
                    heart.setRefLink(refLink);
                }

                for (Snake snake : loadedState.snakes) {
                    snake.setRefLink(refLink);
                }

                for (Enemy enemy : loadedState.enemies) {
                    enemy.setRefLink(refLink);
                }



                System.out.println("Joc încărcat cu succes.");


            }
        } catch (Exception e) {
            System.out.println("Eroare la încărcarea jocului: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            db.closeConnection();
        }
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public ArrayList<Heart> getHearts() {
        return hearts;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    //setarea hartii si a obiectelor
    public PlayState(RefLinks refLink, String levelName) {
        super(refLink);
        refLink.setLevelName(levelName);


        try {
            this.levelPath = getClass().getResource("/maps/" + levelName).toURI().getPath();
        } catch (URISyntaxException e) {
            System.out.println("eroare la mapa");
            throw new RuntimeException(e);
        }

        map = new Map(refLink, levelPath);
        refLink.SetMap(map);

        this.UI = new UserInterface(refLink);
        refLink.setUI(UI);

        this.Level=new LevelLoader(refLink,levelName);


        hero=Hero.getInstance(refLink,Level.HeroX,Level.HeroY);
        refLink.SetHero(hero);


        coins=Level.LoadCoins();
        hearts=Level.LoadHearts();
        snakes= Level.LoadSnakes();
        enemies = Level.LoadEnemies();

        refLink.setEnemy(enemies);
        refLink.setHearts(hearts);
        refLink.setCoins(coins);
        refLink.setSnakes(snakes);


        levels.add("map1.txt");
        levels.add("map2.txt");
        levels.add("map3.txt");

        hero.setStartDate(new Date());
        hero.SCORE = refLink.score;

    }
  public void resetGameState() {
      hero.resetHero();
      refLink.currentLevel = 0;
      refLink.score = 0;
      refLink.levelTime.clear();
      //resetam startDate pentru noul joc
      hero.setStartDate(new Date());
      //reincarca starea initiala a jocului
      coins = Level.LoadCoins();
      hearts = Level.LoadHearts();
      snakes = Level.LoadSnakes();
      enemies = Level.LoadEnemies();
      refLink.setEnemy(enemies);
      refLink.setHearts(hearts);
  }
    public void saveLevelData(){
        refLink.levelTime.add(refLink.GetHero().minutes);
        refLink.levelTime.add(refLink.GetHero().seconds);
        refLink.score += refLink.GetHero().SCORE;
    }

    private void transitionToNextLevel() {
        refLink.currentLevel++;
        if (refLink.currentLevel < levels.size()) {
            refLink.GetCamera().reset(0, 0);
           // refLink.GetHero().setLife(100);

            //save
            saveLevelData();
            System.out.print("!!!!!SCOR !!!!!!: ");
            System.out.println(refLink.score);

            System.out.println("Loading level: " + refLink.currentLevel);
            State.SetState(new PlayState(refLink, levels.get(refLink.currentLevel)));
        } else {
            saveLevelData();
            System.out.println("All levels completed!");
            State.SetState(new ArtemisWonState(refLink));

             String username = refLink.username;

             int minute_final = 0;
             int secunde_final = 0;

            int totalMinutes = 0;
            int totalSeconds = 0;

                for(int i = 0; i < refLink.levelTime.size(); i += 2){
                    totalMinutes += refLink.levelTime.get(i);
                    // minute_final += refLink.levelTime.get(i);
                }
                for(int i = 1; i < refLink.levelTime.size(); i += 2){
                    totalSeconds += refLink.levelTime.get(i);
                    //secunde_final += refLink.levelTime.get(i);
                }
                totalMinutes += totalSeconds / 60;
                totalSeconds = totalSeconds % 60;

                while(secunde_final >= 60){
                    secunde_final = secunde_final % 60;
                    minute_final++;
                }

            String time = String.format("%02d:%02d", totalMinutes, totalSeconds);
               // String time = minute_final + " : " + secunde_final;
            System.out.println(time);
            int score = refLink.score;

            System.out.print("!!!!!SCOR !!!!!!: ");
            System.out.println(refLink.score);


            Database db = new Database();
            db.InsertData(username, time, score);
            db.closeConnection();
        }
    }


    public void Update() {

        map.Update();
        hero.Update();


        switch (refLink.currentLevel) {
            case 0: // Nivelul 1
                // trebuie sa colectezi toti banii si sa omori toti inamicii pentru a trece la nivelul urmator
                if (coins.isEmpty() && enemies.isEmpty()) {
                    transitionToNextLevel();
                }
                break;
            case 1: // Nivelul 2
            case 2: // Nivelul 3
                // trebuie sa omori toti inamicii pentru a trece la nivelul urmator
                if (enemies.isEmpty()) {
                    transitionToNextLevel();
                }
                break;
            default:
                break;
        }

        if (hero.GetLife() <= 0) {
            //State.SetState(new ArtemisDiedState(refLink));
            if (System.currentTimeMillis() - hero.timeOfDeath >= 3000) { // 2000 milisecunde = 2 secunde
                State.SetState(new ArtemisDiedState(refLink));
            }
        }

        //actualizam coins:
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            coin.Update();
            if (coin.hasCollided()) {
                coins.remove(coin);
            }
        }
      /*  for(Enemy enemy: enemies){
            enemy.Update();
        }*/

        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy enemy = it.next();
            enemy.Update();
            if (enemy.isDead) { // Verifică dacă inamicul este mort
                it.remove(); // Elimină inamicul din listă
            }
        }

      /*  for(Coin coin: coins){
            coin.Update();
        }*/


        //actualizam hearts
        for (int i = 0; i < hearts.size(); i++) {
            Heart heart = hearts.get(i);
            heart.Update();
            if (heart.isCollected) {
                hearts.remove(heart);
            }
        }

        //actualizam snakes
        for (int i = 0; i < snakes.size(); i++) {
            Snake snake = snakes.get(i);
            snake.Update();
            if (snake.hasCollided()) {
                snakes.remove(snake);
            }
        }
     /*   for(int i=0;i<enemies.size();i++){
            Enemy enemy = enemies.get(i);
            enemy.Update();
            if(enemy.removeEnemy)
                enemies.remove(enemy);
        }

      */

        //actualizam pozitia eroului pentru a asigura ca jucatorul ramane in centrul ecranului
        float playerX = hero.GetX();
        float playerY = hero.GetY();

        refLink.GetCamera().move(playerX, playerY);


    }

    public void Draw(Graphics2D g) {


        map.Draw(g);
        hero.Draw(g);

        for(Enemy enemy : enemies){
            enemy.Draw(g);
        }


        /*for (Coin coin : coins) {
            coin.Draw(g);
            //  g.setColor(Color.RED); // Setează culoarea la roșu
            //g.drawRect((int) coin.getX(), (int) coin.getY());
        }*/

        for(int i = 0; i < coins.size(); i++){
            Coin coin = coins.get(i);
            if(coin.hasCollided() == true){
                coins.remove(coin);
            } else {
                coin.Draw(g);
            }
        }

        for (Heart heart : hearts) {

            heart.Draw(g);
        }

        for (Snake snake : snakes) {

            snake.Draw(g);
        }
        map.DrawOverlay(g);

        UI.Draw(g);
    }

    private boolean isCoinAtPosition(int x, int y) {
        for (Coin coin : coins) {
            if (coin.getX() == x && coin.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void generateSnakes(int numSnakes)
    {
        int generatedSnakes = 0;
        while (generatedSnakes < numSnakes) {
            int x = (int) (Math.random() * map.getWidth());
            int y = (int) (Math.random() * (map.getHeight() - 9));

            if (!map.GetTile(x, y).IsSolid() && !isCoinAtPosition(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT)) {
                snakes.add(createSnake(refLink, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, 75, 75));
                generatedSnakes++;
            }
        }
    }

    public boolean allCoinsCollected() {
        //Verifica daca toate monedele au fost colectate
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "PlayState";
    }
}