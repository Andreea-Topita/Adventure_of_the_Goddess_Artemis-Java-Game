package PaooGame;

import PaooGame.Graphics.Assets;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class UserInterface {
    private final RefLinks refLinks;
    private final Font UIFont = new Font("Tahoma", Font.PLAIN, 32);
    private final Color UIColor = Color.WHITE;
    public int command = 0;
    public int deelay = 0;
    public ArrayList<String> input = new ArrayList<>();
    Font times,times_70;



    public UserInterface(RefLinks refLinks) {
        this.refLinks = refLinks;


        times = new Font("Times New Roman", Font.PLAIN,25);
        times_70 = new Font("Times New Roman", Font.PLAIN,70);
    }

    public void Update() {
        deelay++;
    }


    public void Draw(Graphics2D g) {

        if (State.GetState().toString() == "PlayState") {
            //hearts
            //int i = refLinks.GetHero().GetLife() / 20;

            //aici se afiseaza numarul de inimi, bazat pe viata curenta a eroului
            //pentru a se rontunji numarul de inimi, ca sa ramana 1 : de ex daca viata e 21+19= 40/20 = 2 etc....
            int i = (refLinks.GetHero().GetLife() + 19) / 20;  //Ajustare pentru a rotunji in sus numarul de inimi
            drawHearts(g, (int) refLinks.GetCamera().getX(), (int) refLinks.GetCamera().getY(), 35, 35, i);

            //coins:
            //calculam latimea in pixeli a stringului care reprezinta numarul de monede pe care le are Artemis
            int x = (int) refLinks.GetCamera().getX() + refLinks.GetWidth() - g.getFontMetrics().stringWidth(String.valueOf(refLinks.GetHero().getCoins())) - 23; //23 de pixeli la stanga
            int y = (int) refLinks.GetCamera().getY() + refLinks.GetHeight() - 820; //fereastra jocului minus 820, adica cat de in sus sa fie
            if (refLinks.GetHero().getCoins() > 0) {
                drawString(g, x, y, String.valueOf(refLinks.GetHero().getCoins()));
                drawCoins(g, x - 46, y - 36);
            }

            //snakes
            int w = x-6; // Coordonata x este aceeași pentru ambele contoare
            int space = 50; // înălțimea graficii monedei plus spațiul suplimentar între contoare
            int z = y + space;
            if (refLinks.GetHero().getSnakes() > 0) {
                drawString(g, w, z, String.valueOf(refLinks.GetHero().getSnakes()));
                drawSnakes(g, w - 59, z - 78);
            }
            // Score
            int scoreX = w-120; // Coordonata x este aceeași pentru toate contoarele
            int scoreY = z + space+700; // Ajustăm poziția y pentru a fi sub șerpi
            drawString(g, scoreX, scoreY, "Score: " + String.valueOf(refLinks.GetHero().SCORE));

        }


        //TITLE SCREEN
        if (State.GetState().toString() == "MeniuState") {

            g.drawImage(Assets.artemisFundal, 0, 0, 1050, 850, null);

            String text = "Adventure of the Goddess Artemis";
            g.setFont(new Font("Times New Roman", Font.BOLD, 63));

            //posionare text titlu
            int x = getCenteredXPos(g, text);
            int y = Tile.TILE_HEIGHT * 2;
            //shadow
            g.setColor(Color.black);
            g.drawString(text, x + 3, y + 3);
            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, x, y);

            //
            g.setFont(new Font("Times New Roman", Font.BOLD, 30));

            //NEW GAME
            text = "NEW GAME";
            x = Tile.TILE_HEIGHT * 2;
            y += Tile.TILE_HEIGHT * 3;
            g.drawString(text, x, y);
            if (command == 0) {
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }
            //DivineChampions
            text = "DIVINE CHAMPIONS";
            x = Tile.TILE_HEIGHT * 2;
            y += Tile.TILE_HEIGHT;
            g.drawString(text, x, y);
            if (command == 1) {
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }

            //OPTIONS:
            text = "OPTIONS";
            x = Tile.TILE_HEIGHT * 2;
            y += Tile.TILE_HEIGHT;
            g.drawString(text, x, y);
            if (command == 2) {
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }
            text = "LOAD GAME";
            x = Tile.TILE_HEIGHT * 2;
            y += Tile.TILE_HEIGHT;
            g.drawString(text, x, y);
            if (command == 3) {
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }

            //QUIT
            text = "QUIT";
            x = Tile.TILE_HEIGHT * 2;
            y += Tile.TILE_HEIGHT;
            g.drawString(text, x, y);
            if (command == 4) {
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }

        }

        //ARTEMIS died
        if (State.GetState().toString() == "ArtemisDiedState") {
            String text = "You died.";
            g.setFont(new Font("Times New Roman", Font.BOLD, 96));

            g.setColor(new Color(131, 91, 131, 255));
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            //pozitionare text
            int a = getCenteredXPos(g, text);
            int b = refLinks.GetHeight() / 3;

            //shadow
            g.setColor(Color.black);
            g.drawString(text, a + 4, b + 4);

            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, a, b);

            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            text = "Don't give up, try again!";
            a = getCenteredXPos(g, text);
            b = b + 90; //ajustare fata de centrul ecranului

            g.setColor(Color.black);
            g.drawString(text, a + 2, b + 2);

            g.setColor(new Color(244, 238, 224));
            g.drawString(text, a, b);

            BufferedImage brotherImage = Assets.Ares;
            int imageScale = 6; // Adjust this scale factor to control size
            int imageWidth = brotherImage.getWidth() * imageScale;
            int imageHeight = brotherImage.getHeight() * imageScale;
            int imageX = refLinks.GetWidth() / 2 - imageWidth / 2; // Center the image horizontally
            int imageY = b + 40; // Position the image below the last line of text

            g.drawImage(brotherImage, imageX, imageY, imageWidth, imageHeight, null);


            if (deelay < 50 && deelay >= 0) {
                g.setFont(new Font("Times New Roman", Font.BOLD, 24));
                text = "Press ENTER!";
                a = getCenteredXPos(g, text);
                b = b + Tile.TILE_HEIGHT * 7;
                g.drawString(text, a, b);

            }
            if (deelay > 30) {
                deelay = -35;
            }
           /* g.setFont(new Font("Times New Roman", Font.BOLD, 24));
            text = "Press ENTER!";
            a = getCenteredXPos(g, text);
            b = b + Tile.TILE_HEIGHT * 7;
            g.drawString(text, a, b);
        }*/
        }

        if (State.GetState().toString() == "ArtemisWonState") {


            String text = "YOU WON !! ";
            g.setFont(new Font("Times New Roman", Font.BOLD, 96));

            // bg color
            g.setColor(new Color(131, 91, 131, 255));
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            //pos text titlu
            int a = getCenteredXPos(g, text);
            int b = refLinks.GetHeight() / 3;

            //shadow
            g.setColor(Color.black);
            g.drawString(text, a + 4, b + 4);

            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, a, b);

            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            text = "Congratulations!";
            a = getCenteredXPos(g, text);
            b = b + 70; // ajustare

            g.setColor(Color.black);
            g.drawString(text, a + 2, b + 2);

            g.setColor(new Color(244, 238, 224));
            g.drawString(text, a, b);


            text = "You saved your brother, Apollo!";
            a = getCenteredXPos(g, text);
            b = b + 70; //ajustare ca sa fie mai jos

            g.setColor(Color.black);
            g.drawString(text, a + 2, b + 2);

            g.setColor(new Color(244, 238, 224));
            g.drawString(text, a, b);

            BufferedImage brotherImage = Assets.Apollo;
            int imageScale = 6; // Adjust this scale factor to control size
            int imageWidth = brotherImage.getWidth() * imageScale;
            int imageHeight = brotherImage.getHeight() * imageScale;
            int imageX = refLinks.GetWidth() / 2 - imageWidth / 2; // Center the image horizontally
            int imageY = b + 40; // Position the image below the last line of text

            g.drawImage(brotherImage, imageX, imageY, imageWidth, imageHeight, null);


            if (deelay < 50 && deelay >= 0) {
                g.setFont(new Font("Times New Roman", Font.BOLD, 24));
                text = "Press ENTER!";
                a = getCenteredXPos(g, text);
                b = b + Tile.TILE_HEIGHT * 7;
                g.drawString(text, a, b);
            }
            if (deelay > 30) {
                deelay = -35;
            }
        }

        //Options
        if (State.GetState().toString() == "OptionsState") {

            String text = "Instructions";
            g.setFont(new Font("Times New Roman", Font.BOLD, 63));

            g.setColor(new Color(131, 91, 131, 255)); // Background color
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            int x = getCenteredXPos(g, text);
            int y = Tile.TILE_HEIGHT * 2;
            //shadow
            g.setColor(Color.black);
            g.drawString(text, x + 3, y + 3);
            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, x, y);

            //pentru instructiuni:

            g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            text = "Use the arrow keys to move your character.";

            int a = getCenteredXPos(g, text);
            int b = refLinks.GetHeight() / 4;

            g.setColor(new Color(244, 238, 224));
            g.drawString(text, a, b);
            String[] instructions = {
                    "Press 'W' to attack.",
                    "Press 'E' to activate a magic shield.",
                    "Press 'S' to save the game.",
                    "Press 'M' to go to the menu."
            };

            // Ajustează `b` pentru a continua de unde s-a terminat primul text
            for (String line : instructions) {
                b += Tile.TILE_HEIGHT;
                a = getCenteredXPos(g, line);
                g.drawString(line, a, b);
            }

            if (deelay < 50 && deelay >= 0) {
                g.setFont(new Font("Times New Roman", Font.BOLD, 25));
                String backMessage = "Press ESC to return to the menu.";
                int backX = getCenteredXPos(g, backMessage);
                g.drawString(backMessage, backX, y + Tile.TILE_HEIGHT * 14);
            }
            if (deelay > 30) {
                deelay = -35;
            }
        }
        // USER INPUT
        if (State.GetState().toString() == "UserInputState") {
            String userInput = JOptionPane.showInputDialog(null, "Username: ");
            if (userInput != null) {
                refLinks.username = userInput;
                State.SetState(new PlayState(refLinks, "map1.txt"));
            }
        }

        //Divine Champhions
        if (State.GetState().toString() == "DivineChampions") {
            Database db = new Database();
            input = db.selectAllDivineChampions();
            db.closeConnection();

            String text = "Divine Champions";
            g.setFont(new Font("Times New Roman", Font.BOLD, 63));

            // bg color
            g.setColor(new Color(131, 91, 131, 255));
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            //pos text titlu
            int x = getCenteredXPos(g, text);
            int y = Tile.TILE_HEIGHT * 2;
            //shadow
            g.setColor(Color.black);
            g.drawString(text, x + 3, y + 3);
            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, x, y);


            // BAZA DE DATE
            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            String[] headers = {"ID", "USERNAME", "TIME", "SCORE"};
            int[] columnWidths = {50, 200, 100, 100};
            int tableStartX = 100; // Ajustează în funcție de poziția dorită
            y += Tile.TILE_HEIGHT * 2;

           /* text = "ID \t\t USERNAME \t\t TIME \t\t SCORE";
            y += Tile.TILE_HEIGHT * 2;
            x = getCenteredXPos(g, text);
            g.drawString(text, x, y);*/
            for (int i = 0; i < headers.length; i++) {
                g.drawString(headers[i], tableStartX + getColumnX(columnWidths, i), y);
            }

            // TEXT
            //y = y + Tile.TILE_HEIGHT;
            g.setFont(new Font("Times New Roman", Font.BOLD, 15));
            y += Tile.TILE_HEIGHT;
            for (String row : input) {
                String[] columns = row.split("\t");
                for (int i = 0; i < columns.length; i++) {
                    g.drawString(columns[i], tableStartX + getColumnX(columnWidths, i), y);
                }
                y += Tile.TILE_HEIGHT / 2;
            }
        }
    }
    private int getColumnX(int[] columnWidths, int columnIndex) {
        int x = 0;
        for (int i = 0; i < columnIndex; i++) {
            x += columnWidths[i];
        }
        return x;
    }

    public void drawString(Graphics g, int x, int y, String text) {
        g.setColor(UIColor);
        g.setFont(UIFont);

        g.drawString(text, x, y);
    }

    public int getCenteredXPos(Graphics g ,String text){
        int lungime = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = refLinks.GetGame().getWindow().GetWndWidth()/2 - lungime/2;
        return x;
    }


    public void drawHearts(Graphics g, int x, int y, int width, int height, int heartNumber) {
        int drawX = x;
        int drawY = y;
        for (int j = 0; j < heartNumber; j++) {
            g.drawImage(Assets.heart[0], drawX, drawY, width, height, null);
            drawX += width;
            if ((j + 1) % 5 == 0) { // Dupa fiecare 5 inimi, incepe un nou rand
                drawX = x;
                drawY += height;
            }
        }
    }

    public void drawCoins(Graphics g, int x, int y) {
        int coinWidth = 50;
        int coinHeight = 50;


        g.drawImage(Assets.coin[4], x, y, coinWidth, coinHeight, null);

    }
    public void drawSnakes(Graphics g,int x,int y){
        int snakeWidth=80;
        int snakeHeight=80;

        g.drawImage(Assets.snakeL[0],x,y,snakeWidth,snakeHeight,null);
    }


}
