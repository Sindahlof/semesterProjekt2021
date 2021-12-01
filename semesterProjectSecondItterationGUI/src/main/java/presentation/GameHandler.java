package presentation;

import domain.Game;
import domain.Player;

public class GameHandler {

    private Game game;

    public GameHandler(){
        this.game = new Game();
    }




    public String roomHandler (Game game){
        return game.getCurrentRoom().getShortDescription();
    }

    Player player = new Player("player 1",1,1,"D:\\Programs\\GitKraken\\semesterPojekt2021\\semesterProjectSecondItterationGUI\\src\\main\\java\\textUI\\sindahl.jpg");
}
