package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;



//gestionarea hartilor
public class Map {
    private RefLinks refLink;
    private int width;
    private int height;
    private int[][] tiles;
    private int[][] tiles_overlay;
   private final File mapFile;


     public Map(RefLinks refLink, String levelName) {
        this.refLink = refLink;
         this.mapFile=new File(levelName);
         LoadWorld();

    }

    public void Update() {

    }
    private BufferedImage mapImage;
    private boolean mapNeedsRedraw = true;
    private BufferedImage overlayImage;
    private boolean overlayNeedsRedraw = true;

    public void Draw(Graphics g) {
        if (mapNeedsRedraw) {
            // initializeaza buffer-ul daca nu exista sau dimensiunile s-au schimbat
            if (mapImage == null || mapImage.getWidth() != width * Tile.TILE_WIDTH || mapImage.getHeight() != height * Tile.TILE_HEIGHT) {
                mapImage = new BufferedImage(width * Tile.TILE_WIDTH, height * Tile.TILE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            }


            Graphics2D g2d = mapImage.createGraphics();

            // Deseneaza fiecare tile pe buffer
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Tile t = GetTile(x, y);
                    if (t != null) {
                        t.Draw(g2d, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
                    }
                }
            }
            g2d.dispose();
            mapNeedsRedraw = false; // Seteaza flag-ul pentru a evita redesenarea inutilă
        }

        // Desenează imaginea pe ecran
        g.drawImage(mapImage, 0, 0, null);
    }

    //getTile= determina ce tip de dala se afla la poz specificata
    //se apeleaza draw pe dala resp
    //se calculeaza coordonatele pixelului pe ecran unde trebuie desenata dala
    //int(x) *tile calc pozitia orizontala iar y poz veritala in pixeli

    public void DrawOverlay(Graphics g) {
        for(int y = 0 ; y < height; y++)
        {
            //for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            for(int x = 0 ; x < width; x++)
            {
                try {
                    GetTile(x, y, true).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);
                } catch(Exception e){
                }
            }
        }
    }
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grass;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        return t;
    }

    public Tile GetTile(int x, int y, boolean Overlay) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return null;
        }
        if (tiles_overlay[x][y] < 0) {
            return null;
        }
        Tile t = Tile.tiles[tiles_overlay[x][y]];
        if (t == null) {
            return null;
        }
        return t;
    }
    private void LoadWorld(){
        try {
            //cream un scanner pentru a citi din fis
            Scanner input = new Scanner(this.mapFile);
            //prima linie din fisier
            String line = input.nextLine();
            System.out.println(line);
            //verificam daca prima linie este map lenght
            if(line.equals("<MAP LENGHT>")){ // prima linia a fisierului.
                line = input.nextLine();
                String[] temp = line.split(" ");
                this.width = Integer.parseInt(temp[1]);
                this.height = Integer.parseInt(temp[0]);
                //citim dimensiunile hartii

            }
            //initializam matricile tiles si overlay cu dim citire
            tiles = new int[width][height];
            tiles_overlay = new int[width][height];

            //verificam daca urm linie e map tiles
            if(input.nextLine().equals("<MAP TILES>")){
                int y = 0;
                //citim liniile pana cand intalnim map overlay
                while(input.hasNextLine()){
                    line = input.nextLine();
                    //System.out.println(line);
                    if(line.equals("<MAP OVERLAY>")){
                        break;
                    }
                    String[] numbers = line.split(" ");
                    //System.out.println(line);
                    for (int x = 0; x < width; x++) {
                        //System.out.println(Integer.parseInt(numbers[y]));
                        tiles[x][y] = Integer.parseInt(numbers[x]);
                        //stocam valorile in matricea tiles
                        //tiles_overlay[x][y] = Level1_Overlay(y, x);
                    }
                    y++;
                }
                //daca linia curenta este map overlay, citim matricea de overlay
                if(line.equals("<MAP OVERLAY>")) {

                    y = 0;
                    while (input.hasNextLine()) {
                        line = input.nextLine();
                        if(line.equals("<COINS>")){
                            return;
                        }
                        String[] numbers = line.split(" ");
                        for (int x = 0; x < width; x++) {
                            //tiles[x][y] = Integer.parseInt(numbers[x]);
                            //stocam valorile in matricea tiles overlay
                            tiles_overlay[x][y] = Integer.parseInt(numbers[x]);
                        }
                        y++;
                    }
                }
            }
            input.close();
        } catch(FileNotFoundException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
