package Presentation;

import Interface.IGame;
import worldofzuul.Game;

public class Start {

    public static final IGame game = new Game();

    public static void main(String[] args) {
        StartscreenController.load(args);
    }

}
