package application;

public class Deplacement {

  private double   deplacementX;

  private double   deplacementY;

  private Position arrivee;

  private Position depart;

  public Deplacement(Position depart, Position arrivee) {
    this.arrivee = arrivee;
    this.depart = depart;
    this.deplacementX = arrivee.getColonne() - depart.getColonne();
    this.deplacementY = arrivee.getLigne() - depart.getLigne();
  }

  public double getDeplacementX() {
    return deplacementX;
  }

  public double getDeplacementY() {
    return deplacementY;
  }

  public Position getArrivee() {
    return arrivee;
  }

  public Position getDepart() {
    return depart;
  }

  public boolean isNul() {
    return deplacementX == 0 && deplacementY == 0;
  }

}
