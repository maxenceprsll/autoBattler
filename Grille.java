import java.util.ArrayList;

/**
 * Classe permettant de stocker les attributs d'une grille et de modéliser la
 * grille de jeu.
 * 
 * @author Paul P., Maxence P.
 */
public class Grille {
  // Attributs de la classe
  /**
   * Entier représentant la largeur de la grille.
   */
  private int largeur;
  /**
   * Entier représentant la hauteur de la grille.
   */
  private int hauteur;
  /**
   * Tableau permettant de représenter les cases de la grille et les personnages.
   */
  private Personnage[][] cases;

  // Constructeurs
  /**
   * Constructeur ne prenant pas de paramètre.
   */
  Grille() {
    largeur = 10;
    hauteur = 10;
    cases = new Personnage[hauteur][largeur];
  }

  /**
   * Constructeur prenant deux entiers en paramètres.
   * 
   * @param h la hauteur de la grille
   * @param l la largeur de la grille
   */
  Grille(int h, int l) {
    largeur = l;
    hauteur = h;
    cases = new Personnage[hauteur][largeur];
  }

  // Méthodes
  /**
   * Getter permettant l'accès à la largeur de la grille.
   * 
   * @return la largeur de la grille
   */
  public int getL() {
    return largeur;
  }

  /**
   * Getter permettant l'accès à la hauteur de la grille.
   * 
   * @return la hauteur de la grille
   */
  public int getH() {
    return hauteur;
  }

  /**
   * Getter permettant l'accès à un personnage de la grille.
   * 
   * @param l la coordonnée verticale de la case
   * @param h la coordonnée horizontale de la case
   * @return le personnage de type Personnage aux coordonnées données
   */
  public Personnage getPerso(int h, int l) {
    return cases[h][l];
  }

  /**
   * Permet la suppression de tous les personnages de la grille.
   */
  private void resetGrille() {
    for (int h = 0; h < hauteur; h++) {
      for (int l = 0; l < largeur; l++) {
        cases[h][l] = null;
      }
    }
  }

  /**
   * Setter permettant de placer les personnages sur la grille grâce aux équipes.
   * 
   * @param a la première équipe de type Equipe
   * @param b la seconde équipe de type Equipe
   */
  public void setPerso(Equipe a, Equipe b) {
    resetGrille();
    for (int i = 0; i < a.getArray().size(); i++) {
      Personnage p = a.getArray().get(i);
      if (p.isAlive())
        cases[p.getPos().getH()][p.getPos().getL()] = p;
    }
    for (int i = 0; i < b.getArray().size(); i++) {
      Personnage p = b.getArray().get(i);
      if (p.isAlive())
        cases[p.getPos().getH()][p.getPos().getL()] = p;
    }
  }

  /**
   * Permet l'affichage de la grille et de ses personnages
   * 
   * @return la chaine de caractères d'affichage de la grille complète
   */
  public String toString() {
    String g = "";
    for (int h = -1; h <= hauteur; h++) {
      for (int l = -1; l <= largeur; l++) {
        if (h == -1 || h == hauteur) {
          if (l == -1 || l == largeur) {
            g += "+ ";
          } else {
            g += "- ";
          }
        } else {
          if (l == -1 || l == largeur) {
            g += "| ";
          } else {
            Personnage p = getPerso(h, l);
            if (p == null) {
              g += ". ";
            } else {
              g += p.getNomCouleur() + " ";
            }
          }
        }
      }
      g += "\n";
    }
    return g;
  }
}