package PaooGame;
import PaooGame.Tiles.Tile;

public class Camera {
    private RefLinks refLink;
    private float x;
    private float y;
    private int width;
    private int height;


    private float mapHeight;
    private float mapWidth;

    public Camera(RefLinks refLink,float x,float y, int width,int height)
    {
        this.refLink = refLink;
        this.x = x ;
        this.y = y;
        this.width = width;
        this.height = height;

        mapHeight =  refLink.GetMap().getHeight() * Tile.TILE_HEIGHT; //dimensionea totala a hartii jocului in pixeli
        mapWidth =  refLink.GetMap().getWidth() * Tile.TILE_WIDTH;

    }
    public void move(float playerX, float playerY){

        //determina pozitia pe axa x unde incepe marginea din stanga a camerei pentru a avea jucatorul centrat orizontal
        //se scade jumatate din latimea camerei din poz jucatorului pentru a-l plasa in centru
        float newX = playerX - getWidth() / 2 + Tile.TILE_HEIGHT;
        float newY = playerY - getHeight() / 2 + Tile.TILE_HEIGHT;
        //se adauga Tile Height pentru a ne asigura ca camera nu incepe exact la marginea unde se afla jucatorul


        //ne asiguram ca nu depasim limitele hartii
        newX = Math.max(0, Math.min(newX, mapWidth - getWidth()));
        newY = Math.max(0, Math.min(newY, mapHeight - getHeight()));
        //max ca sa nu fie negativ, daca ar fi negativ ar permite camerei sa vada in afara hartii pe partea stanga
        //min de map Width si Height ca sa impiedice camera sa depaseasca limitele superioare sau inf ale hartii
        x = newX;
        y = newY;
    }
    public void reset(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(float heroX, float heroY) {
        int gameWidth = refLink.GetGame().GetWidth();
        int gameHeight = refLink.GetGame().GetHeight();

        x = heroX - gameWidth / 2;
        y = heroY - gameHeight / 2;

        // Clamp values to prevent the camera from showing out of bounds areas
        x = Math.max(0, Math.min(x, mapWidth - gameWidth));
        y = Math.max(0, Math.min(y, mapHeight - gameHeight));
    }
    public float getXOffset() {
        return x;
    }

    public float getYOffset() {
        return y;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

}
