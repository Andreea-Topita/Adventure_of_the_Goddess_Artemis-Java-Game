package PaooGame.States;

import PaooGame.RefLinks;
import PaooGame.UserInterface;

import java.awt.*;

//cand se castiga jocul
public class ArtemisWonState extends State {

    private UserInterface UI;
    public ArtemisWonState(RefLinks refLink) {
        super(refLink);

        this.UI = refLink.getUI();
    }

    @Override
    public void Update() {
        UI.Update();
    }

    @Override
    public void Draw(Graphics2D g) {
        UI.Draw(g);
    }

    @Override
    public String toString() {
        return "ArtemisWonState";
    }
}
