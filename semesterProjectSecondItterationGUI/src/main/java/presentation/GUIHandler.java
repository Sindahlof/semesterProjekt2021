package presentation;

import domain.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import textUI.Play;
import textUI.PrintGrid;

import java.util.HashMap;

public class GUIHandler {

    @FXML
    public AnchorPane lose;
    @FXML
    public AnchorPane win;
    @FXML
    public AnchorPane window;

    @FXML
    public Text health;

    @FXML
    public Button closeButton;

    @FXML
    private AnchorPane titleScreen;

    @FXML
    private AnchorPane info;

    @FXML
    private AnchorPane inventory;

    @FXML
    private Text roomDescription;

    @FXML
    private ImageView article1;

    @FXML
    private ImageView article2;

    @FXML
    private ImageView article3;

    @FXML
    private ImageView article4;

    @FXML
    private ImageView article5;

    @FXML
    private ImageView article6;

    @FXML
    private ImageView wing1;

    @FXML
    private ImageView wing2;

    @FXML
    private ImageView wing3;

    @FXML
    private ImageView beamTop;

    @FXML
    private ImageView beamBelow;

    @FXML
    private ImageView engine;

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
    private GridPane harbor;

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
    private GridPane harbor_st;

    @FXML
    private GridPane queensST;

    @FXML
    private Text inspect;

    @FXML
    private ImageView mayorOfficeBG;

    @FXML
    private ImageView SecretaryRoomBG;

    @FXML
    private ImageView harborBG;

    @FXML
    private ImageView harbor_stBG;

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
    private ImageView volkswagenMechanicBG;
    @FXML
    private ImageView townSquareBG;
    @FXML
    private ImageView universityBG;

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
    private HashMap<String, PlaceableObject> inspectItems;

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
        this.inspectItems = new HashMap<>();
        addAllRooms();
        addAllItems();
        addAllQuiz();
        addAllInspectItems();
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
    }

    private void addAllRooms() {
        this.rooms.put("secretary Office", this.secretaryOffice);
        this.rooms.put("Mayor Office", this.mayorOffice);
        this.rooms.put("green fields", this.assemblyRoom);
        this.rooms.put("Harbor", this.harbor);
        this.rooms.put("public restroom", this.restroom);
        this.rooms.put("volkswagen mechanic", this.volkswagenMechanic);
        this.rooms.put("playground", this.playground);
        this.rooms.put("university of Engineering and Science", this.university);
        this.rooms.put("town square", this.townSquare_);
        this.rooms.put("park", this.park_);
        this.rooms.put("clover St", this.cloverSt_);
        this.rooms.put("harbor St", this.harbor_st);
        this.rooms.put("queens St", this.queensST);
    }

    private void addAllBackGrounds() {
        this.roomBackground.put("Mayor Office", this.mayorOfficeBG);
        this.roomBackground.put("secretary Office", this.SecretaryRoomBG);
        this.roomBackground.put("green fields", this.assemblyRoomBG);
        this.roomBackground.put("Harbor", this.harborBG);
        this.roomBackground.put("public restroom", this.restroomBG);
        this.roomBackground.put("volkswagen mechanic", this.volkswagenMechanicBG);
        this.roomBackground.put("playground", this.playgroundBG);
        this.roomBackground.put("university of Engineering and Science", this.universityBG);
        this.roomBackground.put("town square", this.townSquareBG);
        this.roomBackground.put("park", this.parkBG);
        this.roomBackground.put("clover St", this.cloverStBG);
        this.roomBackground.put("harbor St", this.harbor_stBG);
        this.roomBackground.put("queens St", this.queensSTBG);
    }

    private void addAllQuiz() {
        this.quizs.put("Electricity production", this.quiz1);
        this.quizs.put("Sustainable energy", this.quiz2);
        this.quizs.put("Green transition cost", this.quiz3);
        this.quizs.put("Drawbacks of our energy production", this.quiz4);
        this.quizs.put("Green energy production", this.quiz5);
        this.quizs.put("Sustainable energy production", this.quiz6);
    }

    private void addAllInspectItems() {
        this.inspectItems.put("article1", this.game.getInformation1());
        this.inspectItems.put("article2", this.game.getInformation2());
        this.inspectItems.put("article3", this.game.getInformation3());
        this.inspectItems.put("article4", this.game.getInformation4());
        this.inspectItems.put("article5", this.game.getInformation5());
        this.inspectItems.put("article6", this.game.getInformation6());
        this.inspectItems.put("wing1", this.game.getWindMillPart1());
        this.inspectItems.put("wing2", this.game.getWindMillPart2());
        this.inspectItems.put("wing3", this.game.getWindMillPart3());
        this.inspectItems.put("engine", this.game.getWindMillPart4());
        this.inspectItems.put("beamTop", this.game.getWindMillPart5());
        this.inspectItems.put("beamBelow", this.game.getWindMillPart6());

    }

    private void addAllItems() {
        this.items.put("Article-1", this.article1);
        this.items.put("Article-2", this.article2);
        this.items.put("Article-3", this.article3);
        this.items.put("Article-4", this.article4);
        this.items.put("Article-5", this.article5);
        this.items.put("Article-6", this.article6);
        this.items.put("Windmill-wing-1", this.wing1);
        this.items.put("Windmill-wing-2", this.wing2);
        this.items.put("Windmill-wing-3", this.wing3);
        this.items.put("Windmill-motor", this.engine);
        this.items.put("Windmill-rod part 1", this.beamTop);
        this.items.put("Windmill-rod part 2", this.beamBelow);
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
        this.game.getPlayer1().setHealth(10);
        swapScenes();
        this.health.setText("" + this.game.getPlayer1().getHealth());
    }

    public void medium() {
        startGame();
        this.game.getPlayer1().setHealth(5);
        swapScenes();
        this.health.setText("" + this.game.getPlayer1().getHealth());
    }

    public void hard() {
        startGame();
        this.game.getPlayer1().setHealth(2);
        swapScenes();
        this.health.setText("" + this.game.getPlayer1().getHealth());
    }

    public void a() {
        this.answer = "A";
        getQuizHandler();
    }

    public void b() {
        this.answer = "B";
        getQuizHandler();
    }


    public void c() {
        this.answer = "C";
        getQuizHandler();
    }

    private void getQuizHandler() {
        this.play.quizHandler(this.game.getCurrentRoom().getQuizInRoom(), this.game, this.answer);
        this.health.setText("" + this.game.getPlayer1().getHealth());
        if (this.game.getCurrentRoom().getQuizInRoom().isCompletion()) {
            this.quizs.get(quiz).setDisable(true);
            this.quizs.get(quiz).setVisible(false);
            enableCurrentRoom();
            enableWindMillParts();
        }
        if (this.game.getPlayer1().getHealth() <= 0) {
            endgame();
        }
    }

    @FXML
    public void keyHandler(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.S) {
            Command command = new Command(CommandWord.MOVE, "down");
            game.processCommand(command);
            this.currentRoom.setRowIndex(this.player, game.getPlayer1().getPosistion().getY());
        }

        if (keyEvent.getCode() == KeyCode.W) {
            Command command = new Command(CommandWord.MOVE, "up");
            game.processCommand(command);
            this.currentRoom.setRowIndex(this.player, game.getPlayer1().getPosistion().getY());
        }

        if (keyEvent.getCode() == KeyCode.A) {
            Command command = new Command(CommandWord.MOVE, "left");
            game.processCommand(command);
            this.currentRoom.setColumnIndex(this.player, game.getPlayer1().getPosistion().getX());
        }
        if (keyEvent.getCode() == KeyCode.D) {
            Command command = new Command(CommandWord.MOVE, "right");
            game.processCommand(command);
            this.currentRoom.setColumnIndex(player, game.getPlayer1().getPosistion().getX());
        }

        if (keyEvent.getCode() == KeyCode.E) {
            String s = game.exitRoom();
            if (s.equals("3")) {
                disableCurrentRoom();
                String room = game.getCurrentRoom().getShortDescription();
                changeRoom(this.rooms.get(room), this.roomBackground.get(room));
                System.out.println(this.currentRoom.getId());
            }
            String a = game.getCurrentRoom().collectObject(game.getPlayerInventory(), game.getPlayer1());
            if (!(a.equals("df"))) {
                System.out.println(a);
                this.items.get(a).setDisable(false);
                this.items.get(a).setVisible(true);
                for (Node node : this.currentRoom.getChildren())
                    if (this.currentRoom.getColumnIndex(node) == this.game.getPlayer1().getPosistion().getX() && this.currentRoom.getRowIndex(node) == this.game.getPlayer1().getPosistion().getY()) {
                        node.setDisable(true);
                        node.setVisible(false);
                        this.player.setDisable(false);
                        this.player.setVisible(true);
                    }
            }
        }

        if (keyEvent.getCode() == KeyCode.SPACE) {
            if (!(this.game.getCurrentRoom().getQuizInRoom() == null)) {
                this.quiz = game.getCurrentRoom().getQuizInRoom().getDescription();

                if (!(this.game.getCurrentRoom().getQuizInRoom().isCompletion())) {
                    disableCurrentRoom();
                    this.quizs.get(this.quiz).setDisable(false);
                    this.quizs.get(this.quiz).setVisible(true);
                }
            }

            if (this.currentRoom.equals(this.assemblyRoom)){
                int i = this.game.successfulAssemble();
                if (i == 1){
                    victory();
                }else if(i == 2){
                    this.roomDescription.setText("You have not collected all windmill-parts");
                }
            }
        }

        game.getCurrentRoom().constructGrid(game.getPlayer1());
        System.out.println(this.grid.printGrid(game));
    }

    public void quizKeyHandler(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            this.quizs.get(this.quiz).setVisible(false);
            this.quizs.get(this.quiz).setDisable(true);
            enableCurrentRoom();
        }
    }

    public void enableWindMillParts() {
        if (this.game.getCurrentRoom().getQuizInRoom().isCompletion()) {
            for (Node node : this.currentRoom.getChildren()) {
                node.setDisable(false);
                node.setVisible(true);
            }
        }

    }

    public void clickGrid(javafx.scene.input.MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (!(clickedNode.getId() == null)) {
            String id = clickedNode.getId();
            System.out.println(id);
            System.out.println(this.inspectItems.get(id).print());
            this.inspect.setText(this.inspectItems.get(id).print());
        }
    }

    private void endgame() {
        this.quizs.get(quiz).setDisable(true);
        this.quizs.get(quiz).setVisible(false);
        this.info.setVisible(false);
        this.inventory.setVisible(false);
        this.lose.setDisable(false);
        this.lose.setVisible(true);
        this.inspect.setVisible(false);
        this.inspect.setVisible(false);
        this.inspect.setDisable(true);
    }

    private void victory(){
        disableCurrentRoom();
        this.info.setVisible(false);
        this.inventory.setVisible(false);
        this.win.setDisable(false);
        this.win.setVisible(true);
        this.inspect.setVisible(false);
        this.inspect.setDisable(true);

    }

    private void swapScenes() {
        disableTitleScreen();
        changeRoom(this.mayorOffice, this.mayorOfficeBG);
        this.inventory.setVisible(true);
        this.inventory.setDisable(false);
        this.info.setVisible(true);
    }

    public void quit() {
        Stage stage = (Stage) this.window.getScene().getWindow();
        stage.close();
    }
}
