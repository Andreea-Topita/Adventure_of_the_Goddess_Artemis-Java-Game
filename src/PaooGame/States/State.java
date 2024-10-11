package PaooGame.States;

import java.awt.*;
import PaooGame.RefLinks;

//State Design Pattern
//clasa abstracta, ceea ce inseamna ca defineste o structura de baza pentru starile jocului
public abstract class State
{
    //static inseamna ca aceste variabile sunt comune pentru toate instantele
    private static State previousState  = null;
    private static State currentState   = null;

    //faciliteaza accesul la resursele comune
    protected RefLinks refLink;


    public State(RefLinks refLink)
    {
        this.refLink = refLink;
    }

    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
    }

    public static State GetState()
    {
        return currentState;
    }

    public abstract void Update();

    public abstract void Draw(Graphics2D g);
}
