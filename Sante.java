/**
 * Classe permettant de stocker les attributs relatifs à la santé d'un
 * personnage.
 * 
 * @author Paul P., Maxence P.
 */
public class Sante {
  // Attributs de la classe
  /**
   * Entier représentant la vie d'un personnage.
   */
  private int vie;
  /**
   * Entier représentant la résurrection d'un personnage.
   */
  private int resurrection;

  // Constructeurs
  /**
   * Constructeur prenant deux entiers en paramètres.
   * 
   * @param v la vie du personnage
   * @param r la résurrection du personnage
   */
  Sante(int v, int r) {
    set(v, r);
  }

  /**
   * Constructeur prenant un entier definissant les attributs en paramètre.
   * 
   * @param type le type du personnage
   */
  Sante(int type) {
    switch (type) {
      case 0:
        set(10, 10);
        break;
      case 1:
        set(20, 20);
        break;
      default:
        set(10, 10);
        break;
    }
  }

  /**
   * Constructeur principal appelé dans les constructeurs de la classe
   * et les autres classes.
   * 
   * @param v la vie du personnage
   * @param r la résurrection du personnage
   */
  public void set(int v, int r) {
    vie = v;
    resurrection = r;
  }

  // Méthodes
  /**
   * Renvoie un boolean precisant si vrai ou faux la resurrection est possible
   *
   * @return le boolean
   */
  public boolean canRevive() {
    if (vie + resurrection >= 1) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Permet l'affichage des attributs de la classe à l'aide du retour d'une chaine
   * de caractères.
   * 
   * @return la chaine de caractères d'affichage mise en forme
   */
  public String toString() {
    return "(V:" + vie + ",R:" + resurrection + ")";
  }

  /**
   * Getter permettant l'accès à la variable vie
   * 
   * @return la valeur de la variable vie
   */
  public int getVie() {
    return vie;
  }

  /**
   * Getter permettant l'accès à la variable resurrection
   * 
   * @return la valeur de la variable resurrection
   */
  public int getResurrection() {
    return resurrection;
  }
}