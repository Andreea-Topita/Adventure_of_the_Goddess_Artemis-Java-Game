package PaooGame.States;

import PaooGame.RefLinks;
import PaooGame.UserInterface;

import java.awt.*;
import java.util.ArrayList;

//starea: meniul jocului
//tranzitiile intre joc si alte stari, de ex la meniul principal la inceperea joculuo
public class MeniuState extends State{

    public UserInterface UI;

    public MeniuState(RefLinks refLink)
    {
        super(refLink);
        this.UI = new UserInterface(refLink);
        refLink.setUI(UI);
        refLink.currentLevel = 0;

        //pentru baza de date
        refLink.score = 0;
        refLink.levelTime=new ArrayList<>();
        refLink.username = null;

    }

    public void Update()
    {
        UI.Update();
    }

    public void Draw(Graphics2D g)
    {
        UI.Draw(g);
    }

    public String toString() {
        return "MeniuState";
    }

}
