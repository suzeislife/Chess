package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	private Echiquier echiquier;
	private Label[][] tab;

	private VBox root = new VBox(20);
	private HBox top = new HBox(20);
	private HBox mid = new HBox(20);

	// Top element
	private Button buttonStart = new Button("Start");
	private Button buttonReset = new Button("Reset");
	private Label player = new Label("Player: ");
	private Label currentPlayer = new Label();
	// Mid element
	private Label initGrid;
	private VBox panelBlanc = new VBox();
	private VBox panelNoir = new VBox();
	private GridPane chessGrid = new GridPane();
	private Label white = new Label();
	private Label black = new Label();

	@Override
	public void init() throws Exception {
		super.init();

		tab = new Label[8][8];
		echiquier = new Echiquier();

		// Set element top

		// Set top element
		top.setPadding(new Insets(15,15,15,15));
		top.getChildren().add(buttonStart);
		top.getChildren().add(buttonReset);
		top.getChildren().add(player);
		top.getChildren().add(currentPlayer);

		// Set element mid
		createGrid();
		white.setStyle("-fx-background-color : white");
		black.setStyle("-fx-background-color : black");
		
		//set PanelBlanc/Noir
		panelBlanc.getChildren().add(white);
		panelNoir.getChildren().add(black);
		
		// Set mid element
		mid.setPadding(new Insets(15,15,15,15));
		mid.getChildren().add(chessGrid);
		mid.getChildren().add(panelBlanc);
		mid.getChildren().add(panelNoir);

		root.getChildren().add(top);
		root.getChildren().add(mid);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("ChessGame");
			Scene scene = new Scene(root,700,600);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void createGrid() {
		chessGrid.setPrefSize(600, 600);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				initGrid = new Label();
				if (i % 2 == 0 && j % 2 == 0) {
					initGrid.setStyle("-fx-background-color : black");
				} else if (j % 2 != 0 && i % 2 == 0) {
					initGrid.setStyle("-fx-background-color : white");
				} else if (j % 2 == 0 && i % 2 != 0) {
					initGrid.setStyle("-fx-background-color : white");
				} else {
					initGrid.setStyle("-fx-background-color : black");
				}
				initGrid.setPrefSize(60, 60);
				chessGrid.add(initGrid, i, j);
			}

		}
	}

	public void initNewGame() {
		
	}

}
