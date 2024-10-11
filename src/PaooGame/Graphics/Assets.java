package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    //artemis:
    public static BufferedImage[] artemisIdleLeft = new BufferedImage[10];
    public static BufferedImage[] artemisIdleRight = new BufferedImage[10];
    public static BufferedImage[] artemisRight= new BufferedImage[10];
    public static BufferedImage[] artemisLeft= new BufferedImage[10];
    public static BufferedImage[] artemisAttackRight=new BufferedImage[10];
    public static BufferedImage[] artemisAttackLeft=new BufferedImage[10];
    public static BufferedImage[] artemisDeathRight=new BufferedImage[10];
    public static BufferedImage[] artemisDeathLeft=new BufferedImage[10];
    public static BufferedImage[] artemisMagicRight=new BufferedImage[10];
    public static BufferedImage[] artemisMagicLeft=new BufferedImage[10];


        //inamici
    public static BufferedImage[] inamicRight=new BufferedImage[10];
    public static BufferedImage[] inamicLeft = new BufferedImage[10];
    public static BufferedImage[] inamicAttackRight=new BufferedImage[10];
    public static BufferedImage[] inamicAttackLeft=new BufferedImage[10];
    public static BufferedImage[] inamicDeathRight=new BufferedImage[10];
    public static BufferedImage[] inamicDeathLeft=new BufferedImage[10];

    //ares:
    public static BufferedImage[] aresIdleLeft = new BufferedImage[10];
    public static BufferedImage[] aresIdleRight = new BufferedImage[10];
    public static BufferedImage[] aresRight= new BufferedImage[10];
    public static BufferedImage[] aresLeft= new BufferedImage[10];
    public static BufferedImage[] aresAttackRight=new BufferedImage[10];
    public static BufferedImage[] aresAttackLeft=new BufferedImage[10];
    public static BufferedImage[] aresDeathRight=new BufferedImage[10];
    public static BufferedImage[] aresDeathLeft=new BufferedImage[10];


    //map tiles
    public static BufferedImage grass;
    public static BufferedImage[] tile_lvl3=new BufferedImage[4];
    public static BufferedImage[] brazi3=new BufferedImage[4];
    public static BufferedImage[] statui3=new BufferedImage[3];
    public static BufferedImage[] grass_bulb=new BufferedImage[4];

    public static BufferedImage[] path=new BufferedImage[50];
    public static BufferedImage[] fireIarba=new BufferedImage[5];


    //obj
    public static BufferedImage[] coin=new BufferedImage[9];
    public static BufferedImage[] heart=new BufferedImage[2];
    public static BufferedImage[] snakeR=new BufferedImage[10];
    public static BufferedImage[] snakeL=new BufferedImage[10];



    public static BufferedImage[] trees=new BufferedImage[6];
    public static BufferedImage butoi;
    public static BufferedImage[] water=new BufferedImage[5];


    public static BufferedImage[] barEnemy=new BufferedImage[5];
    public static BufferedImage[] projectile = new BufferedImage[2];
    public static BufferedImage[] projectileAres = new BufferedImage[2];

    public static BufferedImage Ares;
    public static BufferedImage Apollo;
    public static BufferedImage artemisFundal;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        Ares=ImageLoader.LoadImage("/textures/Ares_DiedState.png");
        Apollo=ImageLoader.LoadImage("/textures/Apollo.png");
        artemisFundal=ImageLoader.LoadImage("/textures/artemis.png");

            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet artemisR = new SpriteSheet(ImageLoader.LoadImage("/textures/Artemis1.png"));
        SpriteSheet artemisL = new SpriteSheet(ImageLoader.LoadImage("/textures/Artemis2.png"));
        SpriteSheet aresR = new SpriteSheet(ImageLoader.LoadImage("/textures/Ares1.png"));
        SpriteSheet aresL = new SpriteSheet(ImageLoader.LoadImage("/textures/Ares2.png"));
        SpriteSheet inamicR = new SpriteSheet(ImageLoader.LoadImage("/textures/Inamic1.png"));
        SpriteSheet inamicL = new SpriteSheet(ImageLoader.LoadImage("/textures/Inamic2.png"));

        SpriteSheet proR = new SpriteSheet(ImageLoader.LoadImage("/textures/projectile.png"));
        SpriteSheet proL = new SpriteSheet(ImageLoader.LoadImage("/textures/projectileL.png"));

        SpriteSheet proiectilRight = new SpriteSheet(ImageLoader.LoadImage("/textures/proiectilAres.png"));
        SpriteSheet proiectilLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/proiectilAresL.png"));

        SpriteSheet barSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Bar.png"));
        SpriteSheet braziSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/brazi.png"));
        SpriteSheet statuiSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/statui.png"));

        SpriteSheet tiles= new SpriteSheet(ImageLoader.LoadImage("/textures/forest.png"));
        SpriteSheet iarba= new SpriteSheet(ImageLoader.LoadImage("/textures/grass.png"));
        SpriteSheet grassSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/grassBulb2.png"));


       // BufferedImage artemisFundal = ImageLoader.LoadImage("/textures/artemis.png");
       // SpriteSheet artemisBg=new SpriteSheet(ImageLoader.LoadImage("/textures/grass.png"));

        //obj
        SpriteSheet coinSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/coin.png"));
        SpriteSheet heartSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/hearts.png"));
        SpriteSheet snakeSheetR=new SpriteSheet(ImageLoader.LoadImage("/textures/snakeRight.png"));
        SpriteSheet snakeSheetL=new SpriteSheet(ImageLoader.LoadImage("/textures/snakeLeft.png"));

        SpriteSheet treesSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/trees.png"));
        SpriteSheet butoiSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/butoi.png"));
        SpriteSheet riverSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/river_tileset.png"));

        SpriteSheet tileSheet3=new SpriteSheet(ImageLoader.LoadImage("/textures/tileSheet3.png"));


        //ARTEMIS:
        for(int i=0;i<10;i++)
        {
            artemisDeathRight[i]=artemisR.cropCharacter(i,4);
            artemisDeathLeft[i] =artemisL.cropCharacter(9-i,4);
            artemisAttackRight[i]=artemisR.cropCharacter(i,3);
            artemisAttackLeft[i] = artemisL.cropCharacter(9-i,3);
            artemisIdleRight[i] = artemisR.cropCharacter(i,0);
            artemisIdleLeft[i] = artemisL.cropCharacter(9-i,0);
            artemisMagicRight[i]=artemisR.cropCharacter(i,1);
            artemisMagicLeft[i]=artemisL.cropCharacter(9-i,1);
            artemisRight[i] = artemisR.cropCharacter(i,2);
            artemisLeft[i] = artemisL.cropCharacter(9-i,2);

            //ares:
            aresDeathRight[i]=aresR.cropCharacter(i,4);
            aresDeathLeft[i] =aresL.cropCharacter(9-i,4);
            aresAttackRight[i]=aresR.cropCharacter(i,3);
            aresAttackLeft[i] = aresL.cropCharacter(9-i,3);
            aresIdleRight[i] = aresR.cropCharacter(i,0);
            aresIdleLeft[i] = aresL.cropCharacter(9-i,0);
            aresRight[i] = aresR.cropCharacter(i,2);
            aresLeft[i] = aresL.cropCharacter(9-i,2);


            //inamic
            inamicRight[i]=inamicR.cropCharacter(i,2);
            inamicLeft[i]=inamicL.cropCharacter(9-i,2);
            inamicDeathRight[i]=inamicR.cropCharacter(i,4);
            inamicDeathLeft[i]=inamicL.cropCharacter(9-i,4);
            inamicAttackRight[i]=inamicR.cropCharacter(i,3);
            inamicAttackLeft[i]=inamicL.cropCharacter(9-i,3);
        }


        projectile[0] = proL.crop16(0,0);
        projectile[1] = proR.crop16(0,0);

        projectileAres[0] = proiectilRight.crop16(0,0);
        projectileAres[1] = proiectilLeft.crop16(0,0);


        //banuti
        coin[0] = coinSheet.crop(0, 0);
        coin[1] = coinSheet.crop(1, 0);
        coin[2] = coinSheet.crop(2, 0);
        coin[3] = coinSheet.crop(3, 0);
        coin[4] = coinSheet.crop(4, 0);
        coin[5] = coinSheet.crop(5, 0);
        coin[6] = coinSheet.crop(6, 0);
        coin[7] = coinSheet.crop(7, 0);
        coin[8] = coinSheet.crop(8, 0);

        //heart
        heart[0] = heartSheet.crop48(0, 0);
        heart[1] = heartSheet.crop48(1, 0);

        //snake
        snakeR[0]=snakeSheetR.cropCharacter(0,0);
        snakeR[1]=snakeSheetR.cropCharacter(1,0);
        snakeR[2]=snakeSheetR.cropCharacter(2,0);
        snakeR[3]=snakeSheetR.cropCharacter(3,0);
        snakeR[4]=snakeSheetR.cropCharacter(4,0);
        snakeR[5]=snakeSheetR.cropCharacter(5,0);
        snakeR[6]=snakeSheetR.cropCharacter(6,0);
        snakeR[7]=snakeSheetR.cropCharacter(7,0);
        snakeR[8]=snakeSheetR.cropCharacter(8,0);
        snakeR[9]=snakeSheetR.cropCharacter(9,0);

        snakeL[0] = snakeSheetL.cropCharacter(3, 0);
        snakeL[1] = snakeSheetL.cropCharacter(1, 0);
        snakeL[2] = snakeSheetL.cropCharacter(2, 0);
        snakeL[3] = snakeSheetL.cropCharacter(3, 0);
        snakeL[4] = snakeSheetL.cropCharacter(4, 0);
        snakeL[5] = snakeSheetL.cropCharacter(5, 0);
        snakeL[6] = snakeSheetL.cropCharacter(6, 0);
        snakeL[7] = snakeSheetL.cropCharacter(7, 0);
        snakeL[8] = snakeSheetL.cropCharacter(8, 0);
        snakeL[9] = snakeSheetL.cropCharacter(9, 0);



        //NIVEL 3:

        tile_lvl3[0]=tileSheet3.crop(0,0,64,64);
        tile_lvl3[1]=tileSheet3.crop(1,0,64,64);
        tile_lvl3[2]=tileSheet3.crop(2,0,64,64);

        brazi3[0]=braziSheet.crop(0,0,64,126);
        brazi3[1]=braziSheet.crop(1,0,64,126);
        brazi3[2]=braziSheet.crop(2,0,64,126);

        statui3[0]=statuiSheet.crop(0,0,47,64);
        statui3[1]=statuiSheet.crop(1,0,48,64);



        //PADURE NIVEL I TILES:

        grass=iarba.crop16(0,0); //nr 0

        grass_bulb[0]=grassSheet.crop16(0,0); //54
        grass_bulb[1]=grassSheet.crop16(1,0); //55
        grass_bulb[2]=grassSheet.crop16(2,0); //56


        path[0] = tiles.crop16(3, 3); // nr 1 // drum
        path[1] = tiles.crop16(4, 0); // nr 2 sus
        path[2] = tiles.crop16(4, 7); // nr 3 jos 4,7
        path[3] = tiles.crop16(5, 0); // nr 4 sus
        path[4] = tiles.crop16(5, 1); // nr 5 jos

        path[5] = tiles.crop16(6, 0); // nr 6 sus
        path[6] = tiles.crop16(5, 2); // nr 7 jos
        path[7] = tiles.crop16(6, 1); // nr 8 sus

        path[8] = tiles.crop16(6, 2); // nr 9 jos
        path[9] = tiles.crop16(6, 3); // nr 10 jos

        path[10] = tiles.crop16(6, 5); // nr 11 jos
        path[11] = tiles.crop16(7, 5); // nr 12 lateral

        path[12] = tiles.crop16(5, 5); // nr 13 jos
        path[13] = tiles.crop16(5, 6); // nr 14 jos

        path[14] = tiles.crop16(4, 6); // nr 15 jos
        path[15] = tiles.crop16(3, 6); // nr 16 jos

        path[16] = tiles.crop16(7, 2); // nr 17 jos
        path[17] = tiles.crop16(7, 3); // nr 18 lateral

        path[18] = tiles.crop16(7, 4); // nr 19 jos

        path[19] = tiles.crop16(7, 5); // nr 20 jos

        path[20] = tiles.crop16(6, 7); // nr 21 jos bun
        path[21] = tiles.crop16(6, 6); // nr 22 jos bun

        path[22] = tiles.crop16(5, 7); // nr 23 jos bun
        path[23] = tiles.crop16(6, 7); // nr 24 jos bun

        path[24] = tiles.crop16(2, 2); // nr 30 jos bun
        path[25] = tiles.crop16(2, 1); // nr 31 jos bun

        path[26] = tiles.crop16(1, 2); // nr 32 path
        path[27] = tiles.crop16(0, 3); // nr 33 path vertical
        path[28] = tiles.crop16(1, 3); // nr 34 path vertical
        path[29] = tiles.crop16(2, 6); // nr 35
        path[30] = tiles.crop16(2, 5); // nr 36
        path[31] = tiles.crop16(1, 5); // nr 37


        // cerc pe iarba și verdețea pe drum mici (32 pe 32)
        path[32] = tiles.crop(0, 4); // nr 38
        path[33] = tiles.crop(1, 4); // nr 39

        // cercuri
        path[34] = tiles.crop16(0, 8); // nr 40
        path[35] = tiles.crop16(1, 8); // nr 41
        path[36] = tiles.crop16(0, 9); // nr 42
        path[37] = tiles.crop16(1, 9); // nr 43

        // iarba pe drum
        path[38] = tiles.crop16(2, 8); // nr 44
        path[39] = tiles.crop16(3, 8); // nr 45
        path[40] = tiles.crop16(2, 9); // nr 46
        path[41] = tiles.crop16(3, 9); // nr 47


        path[42] = tiles.crop16(2, 7); // nr 48
        path[43] = tiles.crop16(1, 7); // nr 49
        path[44] = tiles.crop16(0, 5); // nr 50
        path[45] = tiles.crop16(0, 2); // nr 51
        path[46] = tiles.crop16(2, 0); // nr 52
        path[47] = tiles.crop16(1, 0); // nr 53


        fireIarba[0]=tiles.crop16(4,2); //25
        fireIarba[1]=tiles.crop16(0,7); //26
        fireIarba[2]=tiles.crop16(7,7); //27
        fireIarba[3]=tiles.crop16(3,5); //28
        fireIarba[4]=tiles.crop16(7,0); //29




        //NIVEL 2 PADURE INTUNECATA: copaci

        trees[0]=treesSheet.crop(0,0,96,112);
        trees[1]=treesSheet.crop(0,1,96,112);
        trees[2]=treesSheet.crop(1,0,96,112);
        trees[3]=treesSheet.crop(1,1,96,112);
        trees[4]=treesSheet.crop(0,2,96,112);
        trees[5]=treesSheet.crop(1,2,96,112);

        //lac
        water[0]=riverSheet.crop(1,1,32,32);
        water[1]=riverSheet.crop(1,2,32,32);
        water[2]=riverSheet.crop(2,1,32,32);
        water[3]=riverSheet.crop(2,2,32,32);

        butoi=butoiSheet.crop16(0,0);


        //bar enemy:
        barEnemy[0]=barSheet.crop(0,0,204,30);
        barEnemy[1]=barSheet.crop(1,0,204,30);
        barEnemy[2]=barSheet.crop(2,0,204,30);
        barEnemy[3]=barSheet.crop(3,0,204,30);
        barEnemy[4]=barSheet.crop(4,0,204,30);

    }
}
