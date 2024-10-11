package PaooGame;

import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelLoader {
    private List<String> fileLines = new ArrayList<>();
    private RefLinks refLink;
    public int HeroX = 0;
    public int HeroY = 0;

    //constructor
    public LevelLoader(RefLinks refLinks, String levelName) {
        InputStream inputStream = getClass().getResourceAsStream("/maps/" + levelName);
        if (inputStream == null) {
            throw new RuntimeException("Resource not found: /maps/" + levelName);
        }
        try (Scanner scanner = new Scanner(inputStream)) {
            //citim fiecare linie din fisierul resursa si le adaugam in lista
            while (scanner.hasNextLine()) {
                fileLines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error reading the level file: " + e.getMessage());
        }

        this.refLink = refLinks;
        //incarcam pozitia jucatorului
        LoadPlayerPosition();
    }

    //metoda pt incarcarcarea pozitiei jucatorului din fisierul resursa
    public void LoadPlayerPosition() {
        for (String line : fileLines) {
            if (line.equals("<HERO POS>")) {
                int index = fileLines.indexOf(line) + 1;
                if (index < fileLines.size()) {
                    //splitam linia pentru a obtine coordonatele jucatorului
                    String[] coords = fileLines.get(index).split(" ");
                    HeroX = Integer.parseInt(coords[0]) * Tile.TILE_WIDTH;
                    HeroY = Integer.parseInt(coords[1]) * Tile.TILE_HEIGHT;
                    break;
                }
            }
        }
    }

    //metoda pentru incarcarea monedelor din fis
    public ArrayList<Coin> LoadCoins() {
        ArrayList<Coin> tempCoins = new ArrayList<>();
        boolean foundCoins = false; // flag ce ajuta la identificarea secțiunii <COINS>

        for (String line : fileLines) {
            if (line.trim().equals("<COINS>")) {
                foundCoins = true; //am intrat în sectiunea <COINS>
                continue;
            }
            if (foundCoins) {
                if (line.trim().isEmpty() || line.trim().startsWith("<")) {
                    break; // sfarsitul sectiunii <COINS> sau inceputul unei noi sectiuni
                }
                String[] data = line.split(" ");
                if (data.length == 2) { // Verificam daca linia contine doua numere
                    try {
                        int x = Integer.parseInt(data[0]);
                        int y = Integer.parseInt(data[1]);
                        //cream o moneda si o adaugam in lista
                        Coin tempCoin = ItemFactory.createCoin(refLink, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                        tempCoins.add(tempCoin);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing coin coordinates: " + e.getMessage());
                    }
                }
            }
        }
        return tempCoins;
    }

    //metoda pentru incarcarea serpilor din fisierul resursa
    public ArrayList<Snake> LoadSnakes() {
        ArrayList<Snake> tempSnakes = new ArrayList<>();
        boolean foundSnakes = false;

        for (String line : fileLines) {
            if (line.trim().equals("<SNAKES>")) {
                foundSnakes = true; // am intrat in secțiunea <SNAKES>
                continue;
            }
            if (foundSnakes) {
                if (line.trim().isEmpty() || line.trim().startsWith("<")) {
                    break; // Sfarșitul sectiunii <SNAKES>, oprește bucla
                }
                String[] data = line.split(" ");
                if (data.length == 2) { // Verifica daca linia contine doua numere
                    try {
                        int x = Integer.parseInt(data[0]);
                        int y = Integer.parseInt(data[1]);
                        //cream un sarpe si il adaugam in lista
                        Snake tempSnake = ItemFactory.createSnake(refLink, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, 78, 78);
                        tempSnakes.add(tempSnake);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing snake coordinates: " + e.getMessage());
                    }
                }
            }
        }
        return tempSnakes;
    }
    //metoda pentru incarcarea inimilor
    public ArrayList<Heart> LoadHearts() {
        ArrayList<Heart> tempHearts = new ArrayList<>();
        boolean foundHearts = false;

        for (String line : fileLines) {
            if (line.trim().equals("<HEARTS>")) {
                foundHearts = true; //am intrat in sectiunea <HEARTS>
                continue;
            }
            if (foundHearts) {
                if (line.trim().isEmpty() || line.trim().startsWith("<")) {
                    break; // Sfarsitul sectiunii <HEARTS>, opreste bucla
                }
                String[] data = line.split(" ");
                if (data.length == 2) { // Verifica daca linia contine doua numere
                    try {
                        int x = Integer.parseInt(data[0]);
                        int y = Integer.parseInt(data[1]);
                        //cream o inima si o adaugam in lista
                        Heart tempHeart = ItemFactory.createHeart(refLink, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, 40, 40);
                        tempHearts.add(tempHeart);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing heart coordinates: " + e.getMessage());
                    }
                }
            }
        }
        return tempHearts;
    }


    public ArrayList<Enemy> LoadEnemies() {
        ArrayList<Enemy> tempEnemies = new ArrayList<>();
        boolean foundEnemies = false;  // indicator daca sectiunea <ENEMIES> a fost gasita

        for (String line : fileLines) {
            if (line.trim().equals("<ENEMIES>")) {
                foundEnemies = true;  //am intrat in secțiunea <ENEMIES>
                continue;
            }
            if (foundEnemies) {
                if (line.trim().isEmpty() || line.trim().startsWith("<")) {
                    break;  // sfarsitul secțtunii <ENEMIES> sau inceputul unei noi sectiuni
                }
                String[] data = line.split(" ");
                if (data.length >= 3) { // verifica daca linia contine suficiente date
                    try {
                        int x = Integer.parseInt(data[0]);
                        int y = Integer.parseInt(data[1]);
                        int tier = Integer.parseInt(data[2]);  // data[2] este un identificator pentru tipul de inamic
                        //cream un inamic si il adaugam in lista
                        Enemy tempEnemy = EnemyFactory.createEnemy(refLink, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, tier);
                        tempEnemies.add(tempEnemy);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing enemy data: " + e.getMessage());
                    }
                }
            }
        }
        return tempEnemies;
    }

}
