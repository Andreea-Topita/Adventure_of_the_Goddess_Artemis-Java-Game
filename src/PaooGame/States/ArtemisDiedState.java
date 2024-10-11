package PaooGame.States;

import PaooGame.RefLinks;
import PaooGame.UserInterface;

import java.awt.*;

public class ArtemisDiedState extends State{
    private UserInterface UI;
    public ArtemisDiedState(RefLinks refLink) {
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
        return "ArtemisDiedState";
    }
}
