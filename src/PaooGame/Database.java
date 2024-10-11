package PaooGame;
import PaooGame.Items.*;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import java.sql.*;
import java.util.ArrayList;
public class Database {

    private Connection conn;
    private String databaseName = "GameBaseDateArtemis.db";
    RefLinks reflink;
    private String levelPath;


    public Database() {
        try {
            // Încercarea de a se conecta la baza de date
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            this.createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //am adaugat coins si snakes
    private void createTables() {
        createTable("GameBase", new String[]{
                "id INTEGER PRIMARY KEY AUTOINCREMENT",
                "heroPosX FLOAT",
                "heroPosY FLOAT",
                "levelName TEXT",
                "currentLevel INTEGER",
                "score INTEGER",
                "life INTEGER",
                "coins INTEGER", // Adăugat câmpul pentru monede
                "snakes INTEGER" // Adăugat câmpul pentru șerpi
        });

        createTable("Coins", new String[]{
                "id INTEGER PRIMARY KEY AUTOINCREMENT",
                "x FLOAT",
                "y FLOAT",
                "collected BOOLEAN"
        });

        createTable("Hearts", new String[]{
                "id INTEGER PRIMARY KEY AUTOINCREMENT",
                "x FLOAT",
                "y FLOAT",
                "collected BOOLEAN"
        });

        createTable("Snakes", new String[]{
                "id INTEGER PRIMARY KEY AUTOINCREMENT",
                "x FLOAT",
                "y FLOAT",
                "collected BOOLEAN"
        });
        createTable("Enemies", new String[]{
                "id INTEGER PRIMARY KEY AUTOINCREMENT",
                "x FLOAT",
                "y FLOAT",
                "life INTEGER",
                "tier INTEGER",
                "isDead BOOLEAN"
        });
        createTable("DivineChampions", new String[]{
                "id INTEGER PRIMARY KEY AUTOINCREMENT",
                "username TEXT",
                "time TEXT",
                "score INTEGER"
        });
    }
    private void createTable(String tableName, String[] fields) {
        try (Statement stmt = conn.createStatement()) {
            StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
            for (String field : fields) {
                sql.append(field).append(", ");
            }
            sql.delete(sql.length() - 2, sql.length());
            sql.append(");");
            stmt.execute(sql.toString());
            System.out.println("Tabelul " + tableName + " a fost creat cu succes.");
        } catch (SQLException e) {
            System.out.println("Eroare: Eroare la crearea tabelului " + tableName + ".");
            e.printStackTrace();
        }
    }
    public void InsertData(String username, String time, int score) {
        String sql = "INSERT INTO DivineChampions (username, time, score) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, time);
            pstmt.setInt(3, score);
            pstmt.executeUpdate();
            System.out.println("Datele au fost inserate cu succes în tabelul DivineChampions.");
        } catch (SQLException e) {
            System.out.println("Eroare la inserarea datelor în tabelul DivineChampions: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public ArrayList<String> selectAllDivineChampions() {
        ArrayList<String> results = new ArrayList<>();
        String sql = "SELECT * FROM DivineChampions";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String result = rs.getInt("id") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("time") + "\t" +
                        rs.getInt("score");
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la selectarea datelor din tabelul DivineChampions: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }


    //am adaugat acolo jos coins si snakes
    public void saveGame(float heroPosX, float heroPosY, String levelName, int currentLevel, int score, int life,int coins,int snakes, ArrayList<Coin> coinsList, ArrayList<Heart> hearts, ArrayList<Snake> snakesList, ArrayList<Enemy> enemies) {
        String sql = "INSERT INTO GameBase (heroPosX, heroPosY, levelName, currentLevel, score, life,coins,snakes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setFloat(1, heroPosX);
            pstmt.setFloat(2, heroPosY);
            pstmt.setString(3, levelName);
            pstmt.setInt(4, currentLevel);
            pstmt.setInt(5, score);
            pstmt.setInt(6, life);

            pstmt.setInt(7, coins); // Setăm numărul de monede
            pstmt.setInt(8, snakes); // Setăm numărul de șerpi
            pstmt.executeUpdate();
            System.out.println("Jocul a fost salvat.");
        } catch (SQLException e) {
            System.out.println("Eroare la salvarea jocului: " + e.getMessage());
            e.printStackTrace();
        }

        saveCoins(coinsList);
        saveHearts(hearts);
        saveSnakes(snakesList);
        saveEnemies(enemies);
    }

    private void saveCoins(ArrayList<Coin> coins) {
       String sql = "DELETE FROM Coins";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Eroare la ștergerea monedelor: " + e.getMessage());
            e.printStackTrace();
        }

        sql = "INSERT INTO Coins (x, y, collected) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Coin coin : coins) {
                pstmt.setFloat(1, coin.getX());
                pstmt.setFloat(2, coin.getY());
                pstmt.setBoolean(3, coin.isCollected());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Eroare la salvarea monedelor: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void saveHearts(ArrayList<Heart> hearts) {
        String sql = "DELETE FROM Hearts";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Eroare la ștergerea inimilor: " + e.getMessage());
            e.printStackTrace();
        }

        sql = "INSERT INTO Hearts (x, y, collected) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Heart heart : hearts) {
                pstmt.setFloat(1, heart.GetX());
                pstmt.setFloat(2, heart.GetY());
                pstmt.setBoolean(3, heart.Collected());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Eroare la salvarea inimilor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveSnakes(ArrayList<Snake> snakes) {
        String sql = "DELETE FROM Snakes";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Eroare la ștergerea șerpilor: " + e.getMessage());
            e.printStackTrace();
        }

        sql = "INSERT INTO Snakes (x, y, collected) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Snake snake : snakes) {
                pstmt.setFloat(1, snake.getX());
                pstmt.setFloat(2, snake.getY());
                pstmt.setBoolean(3, snake.isCollected());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Eroare la salvarea șerpilor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void saveEnemies(ArrayList<Enemy> enemies) {
        String sql = "DELETE FROM Enemies";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Eroare la ștergerea inamicilor: " + e.getMessage());
            e.printStackTrace();
        }

        sql = "INSERT INTO Enemies (x, y, life, tier, isDead) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Enemy enemy : enemies) {
                pstmt.setFloat(1, enemy.GetX());
                pstmt.setFloat(2, enemy.GetY());
                pstmt.setInt(3, enemy.GetLife());
                pstmt.setInt(4, enemy.tier);
                pstmt.setBoolean(5, enemy.isDead); // Include coloana isDead
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Eroare la salvarea inamicilor: " + e.getMessage());
            e.printStackTrace();
        }
    }
  public ResultSet loadGame() {
      String sql = "SELECT heroPosX, heroPosY, levelName, currentLevel, score, life, coins, snakes FROM GameBase ORDER BY id DESC LIMIT 1";
      try {
          Statement stmt = conn.createStatement();
          return stmt.executeQuery(sql);
      } catch (SQLException e) {
          System.out.println("Eroare la încărcarea jocului: " + e.getMessage());
          e.printStackTrace();
          return null;
      }
  }
    public ArrayList<Coin> loadCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        String sql = "SELECT x, y, collected FROM Coins";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Coin coin = new Coin(reflink, rs.getFloat("x"), rs.getFloat("y"), 42, 42);
                coin.setCollected(rs.getBoolean("collected"));
                coins.add(coin);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la încărcarea monedelor: " + e.getMessage());
            e.printStackTrace();
        }
        return coins;
    }

    public ArrayList<Heart> loadHearts() {
        ArrayList<Heart> hearts = new ArrayList<>();
        String sql = "SELECT x, y, collected FROM Hearts";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Heart heart = new Heart(reflink, rs.getFloat("x"), rs.getFloat("y"), 40, 40);
                heart.setCollected(rs.getBoolean("collected"));
                hearts.add(heart);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la încărcarea inimilor: " + e.getMessage());
            e.printStackTrace();
        }
        return hearts;
    }

    public ArrayList<Snake> loadSnakes() {
        ArrayList<Snake> snakes = new ArrayList<>();
        String sql = "SELECT x, y, collected FROM Snakes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Snake snake = new Snake(reflink, rs.getFloat("x"), rs.getFloat("y"), 75, 75);
                snake.setCollected(rs.getBoolean("collected"));
                snakes.add(snake);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la încărcarea șerpilor: " + e.getMessage());
            e.printStackTrace();
        }
        return snakes;
    }
    public ArrayList<Enemy> loadEnemies(RefLinks refLink) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        String sql = "SELECT x, y, life, tier, isDead FROM Enemies";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int life = rs.getInt("life");
                int tier = rs.getInt("tier");
                boolean isDead = rs.getBoolean("isDead");

                Enemy enemy = EnemyFactory.createEnemy(refLink, x, y, tier);
                enemy.setLife(life);
                enemy.setDead(isDead);
                enemies.add(enemy);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la încărcarea inamicilor: " + e.getMessage());
            e.printStackTrace();
        }
        return enemies;
    }

    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Eroare la închiderea conexiunii bazei de date.");
            e.printStackTrace();
        }
    }
}