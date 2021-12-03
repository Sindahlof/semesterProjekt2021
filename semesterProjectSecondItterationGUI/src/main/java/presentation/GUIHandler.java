package presentation;

import domain.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.controlsfx.control.spreadsheet.Grid;
import textUI.PrintGrid;

import java.util.HashMap;

public class GUIHandler {
    @FXML
    private GridPane secretaryOffice;

    @FXML
    private GridPane room;

    @FXML
    private AnchorPane titleScreen;

    @FXML
    private ImageView article1;

    private Game game;
    private GridPane currentRoom;
    private PrintGrid grid;
    private ImageView player;
    private HashMap<String,GridPane> rooms;
    private HashMap<String,ImageView> items;



    private void startGame() {
        this.game = new Game();
        this.grid = new PrintGrid();
        this.player = new ImageView(game.getPlayer1().getImage());
        this.player.setFitHeight(30);
        this.player.setFitWidth(30);
        this.rooms = new HashMap<>();
        this.items = new HashMap<>();
    }

    private void changeRoom(GridPane pane) {
        this.currentRoom = pane;
        this.currentRoom.setVisible(true);
        this.currentRoom.setDisable(false);
        int x = game.getPlayer1().getPosistion().getX();
        int y = game.getPlayer1().getPosistion().getY();
        this.currentRoom.add(this.player,x,y);
        System.out.println(this.grid.printGrid(game));
        addAllRooms();
        addAllItems();
    }

    private void addAllRooms(){
        this.rooms.put("secretaryOffice",this.secretaryOffice);
        this.rooms.put("room",this.room);
    }

    private void addAllItems(){
        this.items.put("Article-1",this.article1);
        this.items.put("Article-2",);
        this.items.put("Article-3",);
        this.items.put("Article-4",);
        this.items.put("Article-5",);
        this.items.put("Article-6",);
    }

    private void disableCurrentRoom(){
        this.currentRoom.setVisible(false);
        this.currentRoom.setDisable(true);
    }

    private void disableTitleScreen() {
        this.titleScreen.setDisable(true);
        this.titleScreen.setVisible(false);
    }

    public void easy() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(10);
        changeRoom(this.room);
    }

    public void medium() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(5);
        changeRoom(this.room);
    }

    public void hard() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(2);
        changeRoom(this.room);
    }

    @FXML
    public void keyHandler(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.S) {
            Command command = new Command(CommandWord.MOVE, "down");
            game.processCommand(command);
            this.currentRoom.setRowIndex(this.player,game.getPlayer1().getPosistion().getY());
            System.out.println("fejl 2");
        }

        if (keyEvent.getCode() == KeyCode.W) {
            Command command = new Command(CommandWord.MOVE, "up");
            game.processCommand(command);
            this.currentRoom.setRowIndex(this.player,game.getPlayer1().getPosistion().getY());
            System.out.println("fejl 3");
        }

        if (keyEvent.getCode() == KeyCode.A) {
            Command command = new Command(CommandWord.MOVE, "left");
            game.processCommand(command);
            this.currentRoom.setColumnIndex(this.player,game.getPlayer1().getPosistion().getX());
            System.out.println("fejl 4");
        }
        if (keyEvent.getCode() == KeyCode.D) {
            Command command = new Command(CommandWord.MOVE, "right");
            game.processCommand(command);
            this.currentRoom.setColumnIndex(player,game.getPlayer1().getPosistion().getX());
            System.out.println("fejl 5");
        }

        if (keyEvent.getCode() == KeyCode.E){
            String s = game.exitRoom();
            if (s.equals("3")) {
                disableCurrentRoom();
                changeRoom(this.rooms.get(game.getCurrentRoom().getShortDescription()));
            }
            String a = game.getCurrentRoom().collectObject(game.getPlayerInventory(), game.getPlayer1());
            if (!(a == "df")) {
                this.items.get(a).setDisable(false);
                this.items.get(a).setVisible(true);
            } else {
            }
        }
        game.getCurrentRoom().constructGrid(game.getPlayer1());
        System.out.println(this.grid.printGrid(game));
    }
}
