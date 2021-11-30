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
    private ImageView player;

    @FXML
    private AnchorPane secretaryOffice;

    @FXML
    private AnchorPane startRoom;

    @FXML
    private GridPane roomGrid;

    @FXML
    private GridPane Room2Grid;

    @FXML
    private AnchorPane titleScreen;

    private Game game;
    private AnchorPane currentRoom;
    private GridPane currentRoomGrid;
    private PrintGrid grid;

    private void startGame() {
        this.game = new Game();
        this.grid = new PrintGrid();
    }

    private void changeRoom(AnchorPane pane) {
        this.currentRoom = pane;
        this.currentRoom.setVisible(true);
        this.currentRoom.setDisable(false);
    }

    private void disableTitleScreen() {
        this.titleScreen.setDisable(true);
        this.titleScreen.setVisible(false);
    }

    public void easy() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(10);
        changeRoom(this.startRoom);
    }

    public void medium() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(5);
        changeRoom(this.startRoom);
    }

    public void hard() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(2);
        changeRoom(this.startRoom);
    }

    private void masterKeyHandler(KeyEvent keyEvent, GridPane room) {
        int i;
        if (keyEvent.getCode() == KeyCode.S) {
            Command command = new Command(CommandWord.MOVE, "down");
            game.processCommand(command);
            if (room.getRowIndex(player) == null) {
                room.setRowIndex(player, 1);
            } else if (!(room.getRowIndex(player) == (room.getRowCount() - 1))) {
                room.setRowIndex(player, (room.getRowIndex(player) + 1));
            }
        }
        if (keyEvent.getCode() == KeyCode.W) {
            Command command = new Command(CommandWord.MOVE, "up");
            game.processCommand(command);
            if (room.getRowIndex(player) == null || room.getRowIndex(player) == 0 || room.getRowIndex(player) == 1) {
                i = 0;
            } else {
                i = room.getRowIndex(player) - 1;
            }
            room.setRowIndex(player, i);
        }
        if (keyEvent.getCode() == KeyCode.A) {
            Command command = new Command(CommandWord.MOVE, "left");
            game.processCommand(command);
            if (room.getColumnIndex(player) == 1 || room.getColumnIndex(player) == 0 || room.getColumnIndex(player) == null) {
                room.setColumnIndex(player, 0);
            } else {
                room.setColumnIndex(player, (room.getColumnIndex(player) - 1));
            }
        }
        if (keyEvent.getCode() == KeyCode.D) {
            Command command = new Command(CommandWord.MOVE, "right");
            game.processCommand(command);
            if (room.getColumnIndex(player) == null) {
                i = 1;
            } else if (!(room.getColumnIndex(player) == (room.getColumnCount() - 1))) {
                room.setColumnIndex(player, (room.getColumnIndex(player) + 1));
            }
        }

        if (keyEvent.getCode() == KeyCode.E){
            game.exitRoom();
            String a = game.getCurrentRoom().collectObject(game.getPlayerInventory(), game.getPlayer1());
            if (!(a == "df")) {
                room.getChildren().get(1).;
                room.getChildren().get(1).setDisable(true);
            } else {
            }

        }
        game.getCurrentRoom().constructGrid(game.getPlayer1());
        System.out.println(this.grid.printGrid(game));
    }

    public void keyHandlerStartingRoom(KeyEvent keyEvent) {
        masterKeyHandler(keyEvent, this.roomGrid);
    }

    public void keyHandler(KeyEvent keyEvent) {
        masterKeyHandler(keyEvent, this.roomGrid);
    }


}
