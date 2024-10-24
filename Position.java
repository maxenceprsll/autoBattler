/**
 * Classe permettant de stocker la position d'un personnage.
 * 
 * @author Paul P., Maxence P.
 */
class Position {
  // Attributs de la classe
  /**
   * Entier représentant la position verticale d'un personnage.
   */
  private int h;
  /**
   * Entier représentant la position horizontale d'un personnage.
   */
  private int l;

  // Constructeurs
  /**
   * Constructeur ne prenant pas de paramètres.
   */
  Position() {
    h = (int) (Math.random() * 2) - 1;
    l = (int) (Math.random() * 2) - 1;
  }

  /**
   * Constructeur prenant une position en paramètre.
   * 
   * @param pos la position à copier
   */
  Position(Position pos) {
    h = pos.getH();
    l = pos.getL();
  }

  /**
   * Constructeur prenant deux entiers en paramètres.
   * 
   * @param posh la position verticale du personnage
   * @param posl la position horizontale du personnage
   */
  Position(int posh, int posl) {
    h = posh;
    l = posl;
  }

  // Méthodes
  /**
   * Setter permettant de définir la variable h
   * 
   * @param newH la valeur de la position verticale
   */
  public void setH(int newH) {
    h = newH;
  }

  /**
   * Setter permettant de définir la variable l
   * 
   * @param newL la valeur de la position horizontale
   */
  public void setL(int newL) {
    l = newL;
  }

  /**
   * Getter permettant l'accès à la variable h
   * 
   * @return la valeur de la position verticale
   */
  public int getH() {
    return h;
  }

  /**
   * Getter permettant l'accès à la variable l
   * 
   * @return la valeur de la position horizontale
   */
  public int getL() {
    return l;
  }
}