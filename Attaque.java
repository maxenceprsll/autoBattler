/**
 * Classe permettant de stocker les attributs relatifs à l'attaque d'un
 * personnage.
 * 
 * @author Paul P., Maxence P.
 */
public class Attaque {
  // Attributs de la classe
  /**
   * Entier représentant l'habileté d'un personnage.
   */
  private int habilete;
  /**
   * Entier représentant la force d'un personnage.
   */
  private int force;

  // Constructeurs
  /**
   * Constructeur prenant deux entiers en paramètres.
   * 
   * @param h l'habilete du personnage
   * @param f la force du personnage
   */
  Attaque(int h, int f) {
    set(h, f);
  }

  /**
   * Constructeur prenant un entier definissant les attributs en paramètre.
   * 
   * @param type le type du personnage
   */
  Attaque(int type) {
    switch (type) {
      case 0:
        set(20, 25);
        break;
      case 1:
        set(15, 30);
        break;
      default:
        set(15, 25);
        break;
    }
  }

  /**
   * Constructeur principal appelé dans les constructeurs de la classe.
   * 
   * @param h l'habilete du personnage
   * @param f la force du personnage
   */
  private void set(int h, int f) {
    habilete = h;
    force = f;
  }

  // Méthodes
  /**
   * Permet l'affichage des attributs de la classe à l'aide du retour d'une chaine
   * de caractères.
   * 
   * @return la chaine de caractères d'affichage mise en forme
   */
  public String toString() {
    return "(H:" + habilete + ",F:" + force + ")";
  }

  /**
   * Getter permettant l'accès à la variable habilete.
   * 
   * @return la valeur de la variable habilete
   */
  public int getHabilete() {
    return habilete;
  }

  /**
   * Getter permettant l'accès à la variable force.
   * 
   * @return la valeur de la variable force
   */
  public int getForce() {
    return force;
  }
}