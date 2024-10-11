package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 100;
    public static Tile[] tiles  = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    public static final int TILE_WIDTH  = 48;    //48                   /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;        //48               /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public static Tile grass =new Grass(0);


    //lvl 3 :
    public static Tile drum_lvl3=new Drum3(69,0);
    public static Tile iarba_lvl3= new Grass3(68,1);
    public static Tile bordura_lvl3=new Border3(70,2);


    public static Tile brazi0=new Brazi3(71,0);
    public static Tile brazi1=new Brazi3(72,1);
    public static Tile foc2=new Brazi3(73,2);

    public static Tile statuie0=new Statui3(74,0);
    public static Tile statuie1=new Statui3(75,1);


    //drum,colturi,laterale LVL 1:
    public static Tile drum = new Path(1, 0);
    public static Tile path1 = new Border(2, 1);
    public static Tile path2 = new Border(3, 2);
    public static Tile path3 = new Border(4, 3);
    public static Tile path4 = new Border(5, 4);
    public static Tile path5 = new Border(6, 5);
    public static Tile path6 = new Border(7, 6);
    public static Tile path7 = new Border(8, 7);
    public static Tile path8 = new Border(9, 8);
    public static Tile path9 = new Border(10, 9);
    public static Tile path10 = new Border(11, 10);
    public static Tile path11 = new Border(12, 11);
    public static Tile path12 = new Border(13, 12);
    public static Tile path13 = new Border(14, 13);
    public static Tile path14 = new Border(15, 14);
    public static Tile path15 = new Border(16, 15);
    public static Tile path16 = new Border(17, 16);
    public static Tile path17 = new Border(18, 17);
    public static Tile path18 = new Border(19, 18);
    public static Tile path19 = new Border(20, 19);
    public static Tile path20 = new Border(21, 20);
    public static Tile path21 = new Border(22, 21);
    public static Tile path22 = new Border(23, 22);
    public static Tile path23 = new Border(24, 23);

    //iarba:
    public static Tile iarba=new IarbaFire(25,0);
    public static Tile iarba1=new IarbaFire(26,1);
    public static Tile iarba2=new IarbaFire(27,2);
    public static Tile iarba3=new IarbaFire(28,3);
    public static Tile iarba4=new IarbaFire(29,4);


    public static Tile path24 = new Border(30, 24);
    public static Tile path25 = new Border(31, 25);
    public static Tile path26 = new Border(32, 26);
    public static Tile path27 = new Border(33, 27);
    public static Tile path28 = new Border(34, 28);
    public static Tile path29 = new Border(35, 29);
    public static Tile path30 = new Border(36, 30);
    public static Tile path31 = new Border(37, 31);
    public static Tile path32 = new Border(38, 32);
    public static Tile path33 = new Border(39, 33);
    public static Tile path34 = new Border(40, 34);
    public static Tile path35 = new Border(41, 35);
    public static Tile path36 = new Border(42, 36);
    public static Tile path37 = new Border(43, 37);
    public static Tile path38 = new Border(44, 38);
    public static Tile path39 = new Border(45, 39);
    public static Tile path40 = new Border(46, 40);
    public static Tile path41 = new Border(47, 41);
    public static Tile path42 = new Border(48, 42);
    public static Tile path43 = new Border(49, 43);
    public static Tile path44 = new Border(50, 44);
    public static Tile path45 = new Border(51, 45);
    public static Tile path46 = new Border(52, 46);
    public static Tile path47 = new Border(53, 47);

    public static Tile grassBulb1=new Bulb(54,0);
    public static Tile grassBulb2=new Bulb(55,1);
    public static Tile grassBulb3=new Bulb(56,2);

    //copaci
     public static Tile tree0=new Tree(57,0);
    public static Tile tree1=new Tree(58,1);
    public static Tile tree2=new Tree(59,2);
    public static Tile tree3=new Tree(60,3);
    public static Tile tree4=new Tree(61,4);
    public static Tile tree5=new Tree(62,5);

    public static River river0=new River(63,0);
    public static River river1=new River(64,1);
    public static River river2=new River(65,2);
    public static River river3=new River(66,3);

     public static Tile butoi=new Barrel(67);


    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
