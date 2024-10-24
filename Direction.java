/**
 * Classe permettant de stocker la direction d'un personnage.
 * 
 * @author Paul P., Maxence P.
 */
public class Direction {
  // Attributs de la classe
  /**
   * Entier représentant le déplacement vertical d'un personnage.
   */
  private int dh;
  /**
   * Entier représentant le déplacement horizontal d'un per.
   */
  private int dl;

  // Constructeurs
  /**
   * Constructeur prenant deux entiers en paramètres.
   * 
   * @param h le déplacement vertical
   * @param l le déplacement horizontal
   */
  Direction(int h, int l) {
    dh = h;
    dl = l;
  }

  /**
   * Constructeur prenant une position en paramètres.
   * 
   * @param p la position à convertir
   */
  Direction(Position p) {
    dh = p.getH();
    dl = p.getL();
  }

  /**
   * Constructeur prenant un entier en paramètre.
   * 
   * @param t le type du personnage
   */
  Direction(int t) {
    switch (t) {
      case 0:
        dh = 1;
        dl = 1;
        break;
      case 1:
        dh = 0;
        dl = -1;
        break;
      case 2:
        dh = -1;
        dl = 1;
    }
  }

  // Méthodes
  /**
   * Getter permettant l'accès à la variable dh
   * 
   * @return la valeur du déplacement vertical
   */
  public int getDh() {
    return dh;
  }

  /**
   * Getter permettant l'accès à la variable dl
   * 
   * @return la valeur du déplacement horizontal
   */
  public int getDl() {
    return dl;
  }
}