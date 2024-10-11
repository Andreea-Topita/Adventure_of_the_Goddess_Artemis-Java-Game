package PaooGame;
import PaooGame.Input.KeyManager;
import PaooGame.Items.*;
import PaooGame.Maps.Map;
import PaooGame.States.*;


import java.lang.Character;
import java.util.ArrayList;

//obiectele pe care le folosesc in joc
//intermediar de legaturi intre diferite componente ale jocului

//rol: e a facilita accesul la diverse resurse și obiecte partajate în întregul proiect, astfel încât acestea să fie ușor accesibile din diferite părți ale codului
//ofera acces la obiecte specifice cum ar fi hero sau enemy sau alte obiecte

public class RefLinks {
    private Game game;
    private Hero hero;
    private Ares ares;
    private Map map;
    private Camera camera;
    private ArrayList<Enemy> enemy;
    private ArrayList<Coin> coin;
    private ArrayList<Snake> snakes;

    private ArrayList<Character> characters= new ArrayList<>();
    private UserInterface UI;
    private ArrayList<Heart> hearts;
    private ArrayList<String> levels = new ArrayList<String>();
    private ArrayList<ProjectileAres> projectiles;

    public String username=null;
    public int score=0;
    public int coinsCount = 0;
    public int snakesCount = 0;
    public int currentLevel=1; //era 1
    public ArrayList<Integer> levelTime = new ArrayList<>();
    public boolean selectfromDB = false;
    private String levelName;
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    //METODE:

    public RefLinks(Game game){
        this.game=game;
        //this.characters = new ArrayList<>();
    }
    public ArrayList<ProjectileAres> GetProjectiles() {
        return projectiles;
    }

    public void AddProjectile(ProjectileAres projectile) {
        projectiles.add(projectile);
    }
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
    public ArrayList<String> getLevels() {
        return levels;
    }

    public void setLevel(ArrayList<String> levels) {
        this.levels = levels;
    }

    public KeyManager GetKeyManager(){
        return game.GetKeyManager();
    }

    public int GetWidth(){
        return game.GetWidth();
    }
    public int GetHeight(){
        return game.GetHeight();
    }

    public Game GetGame(){
        return game;
    }
    public void SetGame(Game game){
        this.game=game;
    }

    public Map GetMap(){return map;}
    public void SetMap(Map map){this.map = map;}

    public Hero GetHero(){
        return hero;
    }
    public void SetHero(Hero hero){
        this.hero = hero;
    }
    public Ares GetAres(){
        return ares;
    }
    public void SetAres(Ares ares ){
        this.ares = ares;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    public Camera GetCamera() {
        return this.camera;
    }

    public ArrayList<Heart> getHearts() {
        return this.hearts;
    }
    public void setHearts(ArrayList<Heart> hearts) {
        this.hearts = hearts;
    }
    public ArrayList<Coin> getCoins() {
        return this.coin;
    }
    public void setCoins(ArrayList<Coin> coin) {
        this.coin = coin;
    }
    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }
    public void setSnakes(ArrayList<Snake> snakes) {
        this.snakes= snakes;
    }

    public void setUI(UserInterface ui) {
        this.UI = ui;
    }

    public UserInterface getUI() {
        return UI;
    }

    public ArrayList<Enemy> getEnemy() {
        return enemy;
    }

    public void setEnemy(ArrayList<Enemy> enemy) {
        this.enemy = enemy;
    }




    public int getCoinsCount() {
        return coinsCount;
    }

    public void setCoinsCount(int coinsCount) {
        this.coinsCount = coinsCount;
    }

    public int getSnakesCount() {
        return snakesCount;
    }

    public void setSnakesCount(int snakesCount) {
        this.snakesCount = snakesCount;
    }
}
