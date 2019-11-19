package worldofzuul;

import Presentation.App;

public class Start {

    public static void main(String[] args) {
        App.load(args);
        Game game = new Game();
        game.play();
    }
}