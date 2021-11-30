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
import javafx.scene.shape.Circle;
import org.controlsfx.control.spreadsheet.Grid;

public class GUIHandler {
    private AnchorPane currentRoom;

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



    public void keyHandler(KeyEvent keyEvent) {
        int i;
        if (keyEvent.getCode() == KeyCode.S) {
            if (roomGrid.getRowIndex(player) == null) {
                roomGrid.setRowIndex(player, 1);
            } else if (!(roomGrid.getRowIndex(player) == (roomGrid.getRowCount() - 1))) {
                roomGrid.setRowIndex(player, (roomGrid.getRowIndex(player) + 1));
            }
        }
        if (keyEvent.getCode() == KeyCode.W) {
            if (roomGrid.getRowIndex(player) == null || roomGrid.getRowIndex(player) == 0 || roomGrid.getRowIndex(player) == 1) {
                i = 0;
            } else {
                i = roomGrid.getRowIndex(player) - 1;
            }
            roomGrid.setRowIndex(player, i);
        }
        if (keyEvent.getCode() == KeyCode.A) {
            if (roomGrid.getColumnIndex(player) == 1 || roomGrid.getColumnIndex(player) == 0 || roomGrid.getColumnIndex(player) == null) {
                roomGrid.setColumnIndex(player, 0);
            } else {
                roomGrid.setColumnIndex(player, (roomGrid.getColumnIndex(player) - 1));
            }
        }
        if (keyEvent.getCode() == KeyCode.D) {
            if (roomGrid.getColumnIndex(player) == null) {
                i = 1;
            } else if (!(roomGrid.getColumnIndex(player) == (roomGrid.getColumnCount() - 1))) {
                roomGrid.setColumnIndex(player, (roomGrid.getColumnIndex(player) + 1));
            }
        }

        /*if (keyEvent.getCode() == KeyCode.E){
            if (startRoom.isDisable()==true && startRoom.isVisible()==false ){
                Room2Grid.setVisible(true);
                Room2Grid.setDisable(false);
                roomGrid.setVisible(false);
                roomGrid.setDisable(true);
            }else if(roomGrid.isDisable()==true && roomGrid.isVisible()==false){
                roomGrid.setVisible(true);
                roomGrid.setDisable(false);
                Room2Grid.setVisible(false);
                Room2Grid.setDisable(true);
            }

        System.out.println(roomGrid.getRowIndex(player));
        System.out.println(this.startRoom.getChildren());
    }*/


    }
}