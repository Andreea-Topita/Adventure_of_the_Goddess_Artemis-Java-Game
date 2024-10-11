package PaooGame.States;

import PaooGame.RefLinks;
import PaooGame.UserInterface;

import java.awt.*;

public class OptionsState extends State
{
    private UserInterface UI;
    public OptionsState(RefLinks refLink)
    {
        //Apel al construcotrului clasei de baza.
        super(refLink);
        this.UI = refLink.getUI();
    }

    @Override
    public void Update()
    {
        UI.Update();
    }

    @Override
    public void Draw(Graphics2D g)
    {
        UI.Draw(g);
    }

    @Override
    public String toString() {
        return "OptionsState";
    }
}
