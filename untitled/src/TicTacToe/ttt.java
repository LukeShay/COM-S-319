package TicTacToe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ttt extends Application {
    private Cell cells[][] = new Cell[3][3];
    private char turn = 'X';
    private Label status = new Label("X turn.");
    private Stage s;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        s = primaryStage;
        initGame(s);
    }

    public void initGame(Stage primaryStage) {
        turn = 'X';

        GridPane grid = new GridPane();
        primaryStage.setTitle("Tic-Tac-Toe");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
                grid.add(cells[i][j], j, i);
            }
        }

        BorderPane bpane = new BorderPane();
        bpane.setCenter(grid);
        bpane.setBottom(status);

        primaryStage.setScene(new Scene(bpane, 450, 450));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.show();
    }

    private boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getPlayer() == ' ') return false;
            }
        }
        return true;
    }

    private boolean hasWon(char c) {
        int k = 0, m = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getPlayer() == c) k++;
                if (cells[j][i].getPlayer() == c) m++;
            }
            if (k == 3 || m == 3) return true;
            k = m = 0;
        }

        if (cells[0][0].getPlayer() == c && cells[1][1].getPlayer() == c && cells[2][2].getPlayer() == c) return true;
        if (cells[0][2].getPlayer() == c && cells[1][1].getPlayer() == c && cells[2][0].getPlayer() == c) return true;

        return false;
    }

    public class Cell extends Button {
        private char player = ' ';

        public Cell() {
            this.setPrefSize(150, 150);
            this.setOnMouseClicked(e -> handleClick());
        }

        public char getPlayer() {
            return player;
        }

        public void setPlayer(char player) {
            this.player = player;

            Image i;
            ImageView iv;

            if (player == 'X') {
                i = new Image(getClass().getResourceAsStream("x.jpeg"));

                iv = new ImageView(i);
                iv.setFitHeight(130);
                iv.setFitWidth(130);

                this.setGraphic(iv);
            } else if (player == 'O') {
                i = new Image(getClass().getResourceAsStream("o.jpeg"));

                iv = new ImageView(i);
                iv.setFitHeight(130);
                iv.setFitWidth(130);

                this.setGraphic(iv);
            }
        }

        private void handleClick() {
            if (player == ' ' && turn != ' ') {
                setPlayer(turn);

                if (hasWon(turn)) {
                    Button newGame = new Button("New Game");
                    newGame.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            initGame(s);
                        }
                    });
                    newGame.setPrefSize(200, 100);

                    VBox vbox = new VBox(8);

                    final Text t = new Text("Congratulations, " + turn + " won the game!");
                    t.setFill(Color.BLACK);
                    t.setStyle("-fx-font: 24 arial;");

                    vbox.setAlignment(Pos.CENTER);
                    vbox.getChildren().addAll(t, newGame);

                    s.setScene(new Scene(vbox, 450, 450));
                    s.setTitle("Tic-Tac-Toe");
                    s.show();

                    turn = ' ';
                } else if (isFull()) {
                    Button newGame = new Button("New Game");
                    newGame.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            initGame(s);
                        }
                    });
                    newGame.setPrefSize(200, 100);

                    VBox vbox = new VBox(8);

                    final Text t = new Text("Draw");
                    t.setFill(Color.BLACK);
                    t.setStyle("-fx-font: 48 arial;");

                    vbox.setAlignment(Pos.CENTER);
                    vbox.getChildren().addAll(t, newGame);

                    s.setScene(new Scene(vbox, 450, 450));
                    s.setTitle("Tic-Tac-Toe");
                    s.show();

                    turn = ' ';
                } else {
                    turn = turn == 'X' ? 'O' : 'X';
                    status.setText(turn + " turn.");
                }
            }
        }
    }
}