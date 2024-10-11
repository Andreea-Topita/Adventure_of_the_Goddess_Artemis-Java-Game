package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class SpriteSheet
{
    private final BufferedImage       spriteSheet;              /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private static final int    tileWidth   = 32;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight  = 32;   /*!< Inaltime unei dale din sprite sheet.*/

    private static final int    tileWidth1   = 16;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight1  = 16;   /*!< Inaltime unei dale din sprite sheet.*/

    private static final int    tileWidth2   = 48;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight2  = 48;   /*!< Inaltime unei dale din sprite sheet.*/


    private static final int heroWidth = 32;
    private static final int heroHeight =32;

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param img Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg)
    {
        /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }

    /*! \fn public BufferedImage crop(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.
     */
    public BufferedImage crop(int x, int y)
    {
            /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
            /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
            /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
    public BufferedImage cropCharacter(int x, int y)
    {
        return spriteSheet.getSubimage(x*heroWidth,y*heroHeight,heroWidth,heroHeight);
    }

    //pt 16
    public BufferedImage crop16(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * tileWidth1, y * tileHeight1, tileWidth1, tileHeight1);
    }

    public BufferedImage crop48(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * tileWidth2, y * tileHeight2, tileWidth2, tileHeight2);
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x * width, y * height, width, height);
    }
}
