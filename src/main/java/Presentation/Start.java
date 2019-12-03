package Presentation;

import Interface.IGame;
import Interface.IMetadata;
import worldofzuul.Game;
import worldofzuul.Metadata;

public class Start {

    public static final IGame game = new Game();
    
    public static void main(String[] args) {
        StartscreenController.load(args);
    }
    
}
