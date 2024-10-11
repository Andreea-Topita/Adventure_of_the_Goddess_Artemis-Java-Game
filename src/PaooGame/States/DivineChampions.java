package PaooGame.States;

import PaooGame.UserInterface;
import PaooGame.RefLinks;

import java.awt.*;

//starea jocului:divine champions
public class DivineChampions extends State{
    private UserInterface UI;
    public DivineChampions (RefLinks refLink) {
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
        return "DivineChampions";
    }
}
