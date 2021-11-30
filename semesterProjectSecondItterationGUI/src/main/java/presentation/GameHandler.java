package presentation;

import domain.Game;

public class GameHandler {

    private Game game;

    public GameHandler(){
        this.game = new Game();
    }


    public String roomHandler (Game game){
        return game.getCurrentRoom().getShortDescription();
    }



}
