package application;

import java.io.File;
import java.util.Optional;

import javax.swing.ImageIcon;

import Piece.Cavalier;
import Piece.Fou;
import Piece.Pion;
import Piece.Reine;
import Piece.Roi;
import Piece.Tour;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
		top.setPadding(new Insets(15, 15, 15, 15));
		top.getChildren().add(buttonStart);
		top.getChildren().add(buttonReset);
		top.getChildren().add(player);
		top.getChildren().add(currentPlayer);

		// Set element mid
		createGrid();
		white.setStyle("-fx-background-color : white");
		black.setStyle("-fx-background-color : gray");

		// set PanelBlanc/Noir
		panelBlanc.getChildren().add(white);
		panelNoir.getChildren().add(black);

		// Set mid element
		mid.setPadding(new Insets(15, 15, 15, 15));
		mid.getChildren().add(chessGrid);
		mid.getChildren().add(panelBlanc);
		mid.getChildren().add(panelNoir);

		root.getChildren().add(top);
		root.getChildren().add(mid);
		// Event button
		buttonStart.setOnAction(event -> {
			initNewGame();
		});
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("ChessGame");
			Scene scene = new Scene(root, 700, 600);

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
				tab[i][j] = new Label();
				tab[i][j].setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						for (int i = 0; i < 8; i++) {
							for (int j = 0; j < 8; j++) {
								if (event.getSource().equals(tab[i][j])) {
									changePion(j, i);
								}
							}
						}
					}
				});

				if (i % 2 == 0 && j % 2 == 0) {
					tab[i][j].setStyle("-fx-background-color : gray");
				} else if (j % 2 != 0 && i % 2 == 0) {
					tab[i][j].setStyle("-fx-background-color : white");
				} else if (j % 2 == 0 && i % 2 != 0) {
					tab[i][j].setStyle("-fx-background-color : white");
				} else {
					tab[i][j].setStyle("-fx-background-color : gray");
				}
				tab[i][j].setPrefSize(60, 60);
				chessGrid.add(tab[i][j], i, j);
			}

		}
	}

	public void initNewGame() {
		/*
		 * Piece pieceTampon = null; ImageView iconeTampon; int ligneClic; int
		 * colonneClic; Position depart, arrivee; String couleurControle =
		 * "blanc"; Position temp = null;
		 */

		// Init J1
		currentPlayer.setText("J1");
		echiquier.debuter();
		String dossierIcone = "src/Icone/";
		char[] ordrePiece = { 'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T' };
		int increment = 1;
		int ligne = 0;
		char couleur = 'N';
		Piece tempo = null;
		echiquier.debuter();
		while (increment >= -1) {
			for (int i = 0; i < 8; i++) {
				tab[i][ligne].setGraphic(
						new ImageView(new File(dossierIcone + ordrePiece[i] + couleur + ".gif").toURI().toString()));
				switch (ordrePiece[i]) {
				case 'T':
					tempo = new Tour(ligne < 5 ? "noir" : "blanc");
					break;

				case 'C':
					tempo = new Cavalier(ligne < 5 ? "noir" : "blanc");
					break;

				case 'F':
					tempo = new Fou(ligne < 5 ? "noir" : "blanc");
					break;

				case 'D':
					tempo = new Reine(ligne < 5 ? "noir" : "blanc");
					break;

				case 'R':
					tempo = new Roi(ligne < 5 ? "noir" : "blanc");
					break;

				}
				echiquier.getCase(i, ligne).setPiece(tempo);
				tab[i][ligne + increment]
						.setGraphic(new ImageView(new File(dossierIcone + 'P' + couleur + ".gif").toURI().toString()));
				echiquier.getCase(i, ligne + increment).setPiece(new Pion(ligne < 5 ? "noir" : "blanc"));

			}
			couleur = 'B';
			increment -= 2;
			ligne = 7;
		}

	}

	ImageView iconeTampon;
	Piece pieceTampon = null;
	Position temp = null;

	@SuppressWarnings("unused")
	public void changePion(int ligne, int colonne) {

		int ligneClic;
		int colonneClic;
		Position depart, arrivee;
		String couleurControle = "blanc";

		if ((echiquier.getCase(colonne, ligne).getPiece() != null | pieceTampon != null)) {

			if (pieceTampon == null) {
				if (echiquier.getCase(colonne, ligne).getPiece().getCouleur().equals(couleurControle)) {
					pieceTampon = echiquier.getCase(colonne, ligne).getPiece();
					iconeTampon = (ImageView) tab[colonne][ligne].getGraphic();
					temp = new Position(colonne, ligne);
					System.out.println(tab[colonne][ligne].getStyle());
					String s = tab[colonne][ligne].getStyle();
					tab[colonne][ligne].setStyle("-fx-border-color: red;" + s);
					System.out.println(tab[colonne][ligne].getStyle());

				}

			} else {
				Deplacement deplacement = new Deplacement(temp, new Position(colonne, ligne));
				if ((pieceTampon.estValide(deplacement) && echiquier.cheminPossible(deplacement))
						| echiquier.captureParUnPionPossible(deplacement)) {
					Label manger = tab[colonne][ligne];
					// JLabel manger = new
					// JLabel(tab[colonne][ligne].getIcon());
					// manger.setHorizontalAlignment(SwingConstants.CENTER);

					if (couleurControle.equals("blanc"))
						panelBlanc.getChildren().add(manger);
					else
						panelNoir.getChildren().add(manger);
					if (echiquier.getCase(colonne, ligne).getPiece() instanceof Roi) {
						System.out.println("test");
						Alert dialog = new Alert(AlertType.CONFIRMATION);
						dialog.setTitle("Chess - End Game");
						dialog.setHeaderText("Chess - End Game");
						dialog.setContentText("Félicitation vous avez gagné ! Désirez vous recommencer?");
						Optional<ButtonType> result = dialog.showAndWait();
						if (result.get() == ButtonType.OK) {
							RAZ();
							tab[temp.getColonne()][temp.getLigne()].setStyle("-fx-border-color: white");
						} else
							System.exit(0);
					} else {
						echiquier.getCase(temp.getColonne(), temp.getLigne()).setPiece(null);
						System.out.println("dedew " + tab[temp.getColonne()][temp.getLigne()].getStyle());
						tab[temp.getColonne()][temp.getLigne()]
								.setStyle(tab[temp.getColonne()][temp.getLigne()].getStyle());
						tab[temp.getColonne()][temp.getLigne()]
								.setBorder(null);
						System.out.println("test " + tab[temp.getColonne()][temp.getLigne()].getStyle());


						tab[colonne][ligne].setGraphic(iconeTampon);
						echiquier.getCase(colonne, ligne).setPiece(pieceTampon);
						tab[temp.getColonne()][temp.getLigne()].setGraphic(null);

						pieceTampon = null;
						iconeTampon = null;
						temp = null;

						couleurControle = couleurControle.equals("blanc") ? "noir" : "blanc";
						if (currentPlayer.getText() == "J2") {
							currentPlayer.setText("J1");
						} else {
							currentPlayer.setText("J2");
						}
					}
				} else {
					tab[temp.getColonne()][temp.getLigne()].setStyle("-fx-border-color: white");
					pieceTampon = null;
					iconeTampon = null;
					temp = null;

				}

			}

		}
	}

	private void RAZ() {
		// TODO Auto-generated method stub

	}

}
