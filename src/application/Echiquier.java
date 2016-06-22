package application;

import Piece.*;

public class Echiquier implements MethodesEchiquier {

  private Case[][] location;

  public Echiquier() {
    location = new Case[8][8];
    for (int ctr = 0; ctr <= 7; ctr++)
      for (int ctr1 = 0; ctr1 <= 7; ctr1++)
        location[ctr][ctr1] = new Case();

  }

  public boolean captureParUnPionPossible(Deplacement deplacement) {
    // Je vérifie si la pice est un pion
    if (location[deplacement.getDepart().getColonne()][deplacement.getDepart()
        .getLigne()].getPiece() instanceof Pion) {
      // initialisation des variables dont j'aurai besoin dans mes conditions, ˆ
      // savoir la couleur de la pice de dŽpart et la case d'arrivé.
      Case Arrive = location[(int) deplacement.getArrivee()
          .getColonne()][(int) deplacement.getArrivee().getLigne()];
      String couleurDepart = location[(int) deplacement.getDepart()
          .getColonne()][(int) deplacement.getDepart().getLigne()].getPiece()
              .getCouleur();

      // je vérifie d'abord si la pice d'arrivé existe et si elle est de la
      // couleur contraire de celle de dŽpart.
      if (Arrive.estOccupe(couleurDepart.equals("blanc") ? "noir" : "blanc"))
        /*
         * Je vérifie si le déplacement est valide, Le déplacement est valide si
         * le produits du déplacement x et y donne 1 si la couleur de départ est
         * noir ou -1 si la pice de dŽpart est blanche.
         */
        return (deplacement.getDeplacementY() * Math
            .abs(deplacement.getDeplacementX()) == (couleurDepart.equals("noir")
                ? 1 : -1));
    }
    return false;

  }

  public boolean cheminPossible(Deplacement deplacement) {
    Piece pieceDepart = location[(int) deplacement.getDepart()
        .getColonne()][(int) deplacement.getDepart().getLigne()].getPiece();
    if (!location[(int) deplacement.getArrivee().getColonne()][(int) deplacement
        .getArrivee().getLigne()].estOccupe(
            pieceDepart.getCouleur().equals("blanc") ? "blanc" : "noir")
        | deplacement.isNul()) {
      if (!(pieceDepart instanceof Cavalier)) {
        if (!(pieceDepart instanceof Pion)) {
          if (!(Math.abs(deplacement.getDeplacementX())
              - Math.abs(deplacement.getDeplacementY()) <= 1
              && Math.abs(deplacement.getDeplacementX())
                  + Math.abs(deplacement.getDeplacementY()) <= 1)) {

            int jumpX = deplacement.getDeplacementX() == 0 ? 0
                : (int) (deplacement.getArrivee().getColonne()
                    - deplacement.getDepart().getColonne())
                    / Math.abs((int) (deplacement.getArrivee().getColonne()
                        - deplacement.getDepart().getColonne()));
            int jumpY = deplacement.getDeplacementY() == 0 ? 0
                : (int) (deplacement.getArrivee().getLigne()
                    - deplacement.getDepart().getLigne())
                    / Math.abs((int) (deplacement.getArrivee().getLigne()
                        - deplacement.getDepart().getLigne()));

            for (int ctrX = (int) deplacement.getDepart().getColonne()
                + jumpX, ctrY = (int) deplacement.getDepart().getLigne()
                    + jumpY; ctrX != (int) deplacement.getArrivee().getColonne()
                        | ctrY != (int) deplacement.getArrivee()
                            .getLigne(); ctrX += jumpX, ctrY += jumpY) {
              if (location[ctrX][ctrY].estOccupe()) {
                return false;
              }
            }
            return true;
          } else
            return true;
        } else
          return !location[(int) deplacement.getArrivee()
              .getColonne()][(int) deplacement.getArrivee().getLigne()]
                  .estOccupe();

      } else
        return true;
    } else
      return false;

  }

  public Case getCase(int colonne, int ligne) {
    return location[colonne][ligne];
  }

  public void debuter() {
    int ligne = 7;
    for (String couleur = "noir"; !couleur
        .equals("blanc"); couleur = "blanc", ligne = 0) {
      location[0][ligne].setPiece(new Tour(couleur));
      location[1][ligne].setPiece(new Cavalier(couleur));
      location[2][ligne].setPiece(new Fou(couleur));
      location[3][ligne].setPiece(new Reine(couleur));
      location[4][ligne].setPiece(new Roi(couleur));
      location[5][ligne].setPiece(new Fou(couleur));
      location[6][ligne].setPiece(new Cavalier(couleur));
      location[7][ligne].setPiece(new Tour(couleur));
      ligne += couleur.equals("noir") ? -1 : 1;
      for (int ctr = 0; ctr <= 7; ctr++)
        location[ctr][ligne].setPiece(new Pion(couleur));
    }
  }

}
