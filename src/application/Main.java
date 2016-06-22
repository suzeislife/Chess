package application;

import javafx.application.Application;
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
	private HBox top = new HBox();
	private HBox mid = new HBox();

	// Top element
	private Button buttonStart = new Button("Start");
	private Button buttonReset = new Button("Reset");
	// Mid element
	private Label initGrid;
	private VBox panelBlanc = new VBox();
	private VBox panelNoir = new VBox();
	private GridPane chessGrid = new GridPane();

	@Override
	public void init() throws Exception {
		super.init();

		tab = new Label[8][8];
		echiquier = new Echiquier();
		// Set top element
		top.getChildren().add(buttonStart);
		top.getChildren().add(buttonReset);

		createGrid();
		
		// Set mid element
		mid.getChildren().add(chessGrid);
		mid.getChildren().add(panelBlanc);
		mid.getChildren().add(panelNoir);
		
		root.getChildren().add(top);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();
			primaryStage.setTitle("ChessGame");
			Scene scene = new Scene(root);

			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void createGrid(){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				initGrid = new Label("test");
				chessGrid.add(initGrid, i, j);
			}
			
		}
	}
	
	public void initNewGame(){
		
	}
	
	
}
