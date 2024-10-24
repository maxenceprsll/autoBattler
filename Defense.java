/**
 * Classe permettant de stocker les attributs relatifs à la défense d'un
 * personnage.
 * 
 * @author Paul P., Maxence P.
 */
public class Defense {
  // Attributs de la classe
  /**
   * Entier représentant l'armure d'un personnage.
   */
  private int armure;
  /**
   * Entier représentant l'esquive d'un personnage.
   */
  private int esquive;

  // Constructeurs
  /**
   * Constructeur prenant deux entiers en paramètres.
   * 
   * @param a l'armure du personnage
   * @param e l'esquive du personnage
   */
  Defense(int a, int e) {
    set(a, e);
  }

  /**
   * Constructeur prenant un entier definissant les attributs en paramètre.
   * 
   * @param type le type du personnage
   */
  Defense(int type) {
    switch (type) {
      case 0:
        set(5, 20);
        break;
      case 1:
        set(15, 0);
        break;
      default:
        set(5, 15);
        break;
    }
  }

  /**
   * Constructeur principal appelé dans les constructeurs de la classe.
   * 
   * @param a l'armure du personnage
   * @param e l'esquive du personnage
   */
  private void set(int a, int e) {
    armure = a;
    esquive = e;
  }

  // Méthodes
  /**
   * Permet l'affichage des attributs de la classe à l'aide du retour d'une chaine
   * de caractères.
   * 
   * @return la chaine de caractères d'affichage mise en forme
   */
  public String toString() {
    return "(A:" + armure + ",E:" + esquive + ")";
  }

  /**
   * Getter permettant l'accès à la variable armure
   * 
   * @return la valeur de la variable armure
   */
  public int getArmure() {
    return armure;
  }

  /**
   * Getter permettant l'accès à la variable esquive
   * 
   * @return la valeur de la variable esquive
   */
  public int getEsquive() {
    return esquive;
  }
}