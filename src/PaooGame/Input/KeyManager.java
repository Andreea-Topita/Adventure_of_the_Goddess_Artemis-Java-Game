package PaooGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import PaooGame.Database;
import PaooGame.RefLinks;
import PaooGame.States.*;

public class KeyManager implements KeyListener {
    private final boolean[] keys;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean attack,circle;
    private final RefLinks reflink;
    public boolean save;
    public boolean meniu;
    public KeyManager(RefLinks refLinks)
    {
        keys = new boolean[256];
        this.reflink = refLinks;
    }
    public void Update()
    {
        up    = keys[KeyEvent.VK_UP];
        down  = keys[KeyEvent.VK_DOWN];
        left  = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];

        attack=keys[KeyEvent.VK_W];
        circle=keys[KeyEvent.VK_E];

        meniu=keys[KeyEvent.VK_M];

        if (meniu) {
            State.SetState(new MeniuState(reflink));
        }

        if (keys[KeyEvent.VK_S]) {
            if (!save) {
                save = true;
                Database db = new Database();
                PlayState currentState = (PlayState) State.GetState();
                reflink.score = reflink.GetHero().SCORE;



                /*db.saveGame(reflink.GetHero().GetX(), reflink.GetHero().GetY(), reflink.getLevelName(), reflink.currentLevel, reflink.score, reflink.GetHero().GetLife(),
                        currentState.getCoins(), currentState.getHearts(), currentState.getSnakes(), currentState.getEnemies());*/
                db.saveGame(reflink.GetHero().GetX(), reflink.GetHero().GetY(), reflink.getLevelName(), reflink.currentLevel, reflink.score, reflink.GetHero().GetLife(),
                        reflink.GetHero().getCoins(), reflink.GetHero().getSnakes(), // Transmiteți numărul de monede și șerpi
                        currentState.getCoins(), currentState.getHearts(), currentState.getSnakes(), currentState.getEnemies());
                db.closeConnection();
            }
        } else {
            save = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

        //meniu state
        if (State.GetState().toString() == "MeniuState") {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (reflink.getUI().command < 4) {
                    reflink.getUI().command++;
                    reflink.GetGame().playSoundEffect(2);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (reflink.getUI().command > 0) {
                    reflink.getUI().command--;
                    reflink.GetGame().playSoundEffect(2);
                }
            }

            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                switch (reflink.getUI().command) {
                    case 0:
                      // State.SetState(new UserInputState(reflink));

                        PlayState playState = new PlayState(reflink, "map1.txt");
                        playState.resetGameState();
                        State.SetState(new UserInputState(reflink));
                        reflink.GetGame().playSoundEffect(2);
                        break;
                    case 1:
                        System.out.println("nothing");
                        State.SetState(new DivineChampions(reflink));
                        reflink.GetGame().playSoundEffect(2);
                        break;
                    case 2:
                        System.out.println("Options");
                        State.SetState(new OptionsState(reflink));
                        reflink.GetGame().playSoundEffect(2);
                        break;
                    case 3:
                        System.out.println("load game");
                        Database db = new Database();
                        ResultSet rs = null;
                        try {
                            rs = db.loadGame();
                            if (rs != null && rs.next()) {
                                String levelName = rs.getString("levelName");
                                PlayState newState = new PlayState(reflink, levelName);
                                State.SetState(newState);
                                newState.loadGame();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } finally {
                            if (rs != null) try { rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                            db.closeConnection();
                        }
                        reflink.GetGame().playSoundEffect(2);
                        break;
                    case 4:
                        System.out.println("quit");
                        reflink.GetGame().playSoundEffect(2);
                        System.exit(0);
                        break;
                }
            }
        }

        //WINSTATE
        if(State.GetState().toString() == "ArtemisWonState" || State.GetState().toString() == "ArtemisDiedState" )
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                reflink.GetGame().playSoundEffect(2);
                State.SetState(new MeniuState(reflink));
            }
        }

        if(State.GetState().toString()== "DivineChampions" || State.GetState().toString()== "OptionsState")
        {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                reflink.GetGame().playSoundEffect(3);
                State.SetState(new MeniuState(reflink));
            }
        }

        if(State.GetState().toString() == "Options"){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                reflink.GetGame().playSoundEffect(2);
                State.SetState(new MeniuState(reflink));
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            State.SetState(new MeniuState(reflink));
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //se retine ca o tasta a fost eliberata
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
