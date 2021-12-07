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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import textUI.Play;
import textUI.PrintGrid;

import java.util.HashMap;

public class GUIHandler {
    @FXML
    private AnchorPane titleScreen;

    @FXML
    private Text roomDescription;

    @FXML
    private VBox quiz1;

    @FXML
    private VBox quiz2;

    @FXML
    private VBox quiz3;

    @FXML
    private VBox quiz4;

    @FXML
    private VBox quiz5;

    @FXML
    private VBox quiz6;

    @FXML
    private GridPane secretaryOffice;

    @FXML
    private GridPane mayorOffice;

    @FXML
    private GridPane assemblyRoom;

    @FXML
    private GridPane harbor1;

    @FXML
    private GridPane restroom;

    @FXML
    private GridPane volkswagenMechanic;

    @FXML
    private GridPane playground;

    @FXML
    private GridPane university;

    @FXML
    private GridPane townSquare_;

    @FXML
    private GridPane park_;

    @FXML
    private GridPane cloverSt_;

    @FXML
    private GridPane harbor2;

    @FXML
    private GridPane queensST;

    @FXML
    private ImageView mayorOfficeBG;

    @FXML
    private ImageView SecretaryRoomBG;

    @FXML
    private ImageView harbor1BG;

    @FXML
    private ImageView restroomBG;

    @FXML
    private ImageView playgroundBG;

    @FXML
    private ImageView parkBG;

    @FXML
    private ImageView cloverStBG;

    @FXML
    private ImageView queensSTBG;

    @FXML
    private ImageView assemblyRoomBG;

    @FXML
    private ImageView article1;

    private Game game;
    private GridPane currentRoom;
    private ImageView currentBackgroundImage;
    private PrintGrid grid;
    private ImageView player;
    private HashMap<String, GridPane> rooms;
    private HashMap<String, ImageView> roomBackground;
    private HashMap<String, ImageView> items;
    private Play play;
    private String answer;
    private HashMap<String, VBox> quizs;
    private String quiz;


    private void startGame() {
        this.game = new Game();
        this.grid = new PrintGrid();
        this.player = new ImageView(game.getPlayer1().getImage());
        this.player.setFitHeight(30);
        this.player.setFitWidth(30);
        this.roomBackground = new HashMap<>();
        this.rooms = new HashMap<>();
        this.items = new HashMap<>();
        this.quizs = new HashMap<>();
        this.play = new Play();
        addAllRooms();
        addAllItems();
        addAllQuizs();
        addAllBackGrounds();
        this.titleScreen.setDisable(false);
        this.titleScreen.setVisible(true);
    }

    private void changeRoom(GridPane pane, ImageView image) {
        this.currentRoom = pane;
        this.currentBackgroundImage = image;
        this.currentRoom.setVisible(true);
        this.currentRoom.setDisable(false);
        this.currentBackgroundImage.setVisible(true);
        this.currentBackgroundImage.setDisable(false);
        this.currentRoom.toFront();
        int x = game.getPlayer1().getPosistion().getX();
        int y = game.getPlayer1().getPosistion().getY();
        this.currentRoom.add(this.player, x, y);
        this.roomDescription.setDisable(false);
        this.roomDescription.setVisible(true);
        this.roomDescription.setText(this.game.getCurrentRoom().getTitle());
        System.out.println(this.grid.printGrid(game));
    }

    private void addAllRooms() {
        this.rooms.put("secretary Office", this.secretaryOffice);
        this.rooms.put("Mayor Office", this.mayorOffice);
        this.rooms.put("green fields", this.assemblyRoom);
        this.rooms.put("Harbor", this.harbor1);
        this.rooms.put("public restroom", this.restroom);
        this.rooms.put("volkswagen mechanic", this.volkswagenMechanic);
        this.rooms.put("playground", this.playground);
        this.rooms.put("university of Engineering and Science", this.university);
        this.rooms.put("town square", this.townSquare_);
        this.rooms.put("park", this.park_);
        this.rooms.put("clover St", this.cloverSt_);
        this.rooms.put("harbor", this.harbor2);
        this.rooms.put("queens St", this.queensST);
    }

    private void addAllBackGrounds() {
        this.roomBackground.put("Mayor Office", this.mayorOfficeBG);
        this.roomBackground.put("secretary Office", this.SecretaryRoomBG);
        this.roomBackground.put("green fields", this.assemblyRoomBG);
        this.roomBackground.put("Harbor", this.harbor1BG);
        this.roomBackground.put("public restroom", this.restroomBG);
        //this.roomBackground.put("volkswagen mechanic", this.volkswagenMechanic);
        this.roomBackground.put("playground", this.playgroundBG);
        //this.roomBackground.put("university of Engineering and Science", this.university);
        //this.roomBackground.put("town square", this.townSquare_);
        this.roomBackground.put("park", this.parkBG);
        this.roomBackground.put("clover St", this.cloverStBG);
        this.roomBackground.put("harbor", this.harbor1BG);
        this.roomBackground.put("queens St", this.queensSTBG);
    }

    private void addAllQuizs() {
        this.quizs.put("1", this.quiz1);
        this.quizs.put("2", this.quiz2);
        this.quizs.put("3", this.quiz3);
        this.quizs.put("4", this.quiz4);
        this.quizs.put("5", this.quiz5);
        this.quizs.put("6", this.quiz6);
    }

    private void addAllItems() {
        this.items.put("Article-1", this.article1);
        /*this.items.put("Article-2",);
        this.items.put("Article-3",);
        this.items.put("Article-4",);
        this.items.put("Article-5",);
        this.items.put("Article-6",);*/
    }


    private void disableCurrentRoom() {
        this.currentRoom.setVisible(false);
        this.currentRoom.setDisable(true);
        this.currentBackgroundImage.setVisible(false);
        this.currentBackgroundImage.setDisable(true);
    }

    private void enableCurrentRoom() {
        this.currentRoom.setVisible(true);
        this.currentRoom.setDisable(false);
        this.currentBackgroundImage.setVisible(true);
        this.currentBackgroundImage.setDisable(false);
    }

    private void disableTitleScreen() {
        this.titleScreen.setDisable(true);
        this.titleScreen.setVisible(false);
    }

    public void easy() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(10);
        changeRoom(this.mayorOffice,this.mayorOfficeBG);
    }

    public void medium() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(5);
        changeRoom(this.mayorOffice,this.mayorOfficeBG);
    }

    public void hard() {
        startGame();
        disableTitleScreen();
        this.game.getPlayer1().setHealth(2);
        changeRoom(this.mayorOffice,this.mayorOfficeBG);
    }

    public void a() {
        this.answer = "A";
        getQuizHandler();
        if (this.game.getCurrentRoom().getQuizInRoom().isCompletion()) {
            this.quizs.get(quiz).setDisable(true);
            this.quizs.get(quiz).setVisible(false);
            enableCurrentRoom();
        }
    }

    public void b() {
        this.answer = "B";
        getQuizHandler();
        if (this.game.getCurrentRoom().getQuizInRoom().isCompletion()) {
            this.quizs.get(quiz).setDisable(true);
            this.quizs.get(quiz).setVisible(false);
            enableCurrentRoom();
        }
    }

    public void c() {
        this.answer = "C";
        getQuizHandler();
        if (this.game.getCurrentRoom().getQuizInRoom().isCompletion()) {
            this.quizs.get(quiz).setDisable(true);
            this.quizs.get(quiz).setVisible(false);
            enableCurrentRoom();
        }
    }

    private void getQuizHandler() {
        this.play.quizHandler(this.game.getCurrentRoom().getQuizInRoom(), this.game, this.answer);
    }

    @FXML
    public void keyHandler(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.S) {
            Command command = new Command(CommandWord.MOVE, "down");
            game.processCommand(command);
            this.currentRoom.setRowIndex(this.player, game.getPlayer1().getPosistion().getY());
            System.out.println("fejl 2");
        }

        if (keyEvent.getCode() == KeyCode.W) {
            Command command = new Command(CommandWord.MOVE, "up");
            game.processCommand(command);
            this.currentRoom.setRowIndex(this.player, game.getPlayer1().getPosistion().getY());
            System.out.println("fejl 3");
        }

        if (keyEvent.getCode() == KeyCode.A) {
            Command command = new Command(CommandWord.MOVE, "left");
            game.processCommand(command);
            this.currentRoom.setColumnIndex(this.player, game.getPlayer1().getPosistion().getX());
            System.out.println("fejl 4");
        }
        if (keyEvent.getCode() == KeyCode.D) {
            Command command = new Command(CommandWord.MOVE, "right");
            game.processCommand(command);
            this.currentRoom.setColumnIndex(player, game.getPlayer1().getPosistion().getX());
            System.out.println("fejl 5");
        }

        if (keyEvent.getCode() == KeyCode.E) {
            String s = game.exitRoom();
            if (s.equals("3")) {
                disableCurrentRoom();
                String room = game.getCurrentRoom().getShortDescription();
                changeRoom(this.rooms.get(room),this.roomBackground.get(room));
                System.out.println(game.getCurrentRoom().getShortDescription());
            }
            String a = game.getCurrentRoom().collectObject(game.getPlayerInventory(), game.getPlayer1());
            if (!(a == "df")) {
                this.items.get(a).setDisable(false);
                this.items.get(a).setVisible(true);
            } else {
            }
        }

        if (keyEvent.getCode() == KeyCode.SPACE) {
            this.quiz = game.getCurrentRoom().getQuizInRoom().getDescription();

            if (!(this.game.getCurrentRoom().getQuizInRoom().isCompletion())) {
                disableCurrentRoom();
                this.quizs.get(this.quiz).setDisable(false);
                this.quizs.get(this.quiz).setVisible(true);
            }
        }

        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            this.quizs.get(this.quiz).setVisible(false);
            this.quizs.get(this.quiz).setDisable(true);
            enableCurrentRoom();
        }


        game.getCurrentRoom().constructGrid(game.getPlayer1());
        System.out.println(this.grid.printGrid(game));
    }


}
