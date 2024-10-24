import java.util.ArrayList;

/**
 * Classe permettant de représenter une équipe.
 * 
 * @author Paul P., Maxence P.
 */
public class Equipe {
  // Attributs de la classe
  /**
   * Entier représentant la taille de l'équipe
   */
  private int t;
  /**
   * Liste permettant le stockage de tous les personnages d'une équipe
   */
  ArrayList<Personnage> arrayPerso = new ArrayList<Personnage>();
  /**
   * Chaine de caractères, le nom de l'équipe
   */
  private String n;

  // Constructeurs
  /**
   * Constructeur prenant un entier et une chaine de caractères en paramètres.
   * 
   * @param taille la taille de l'équipe
   * @param nom    le nom de l'équipe
   */
  Equipe(int taille, String nom) {
    t = taille;
    n = nom;
  }

  // Méthodes
  /**
   * Permet l'ajout d'un personnage à une équipe.
   * 
   * @param p le personnage de type Personnage à ajouter
   */
  public void addPerso(Personnage p) {
    if (arrayPerso.size() < t) {
      arrayPerso.add(p);
    }
  }

  /**
   * Getter permettant l'acces à la liste des personnages d'une équipe.
   * 
   * @return la liste des personnages de l'équipe
   */
  public ArrayList<Personnage> getArray() {
    return arrayPerso;
  }

  /**
   * Getter permettant l'acces au nom de l'équipe.
   * 
   * @return la chaine de caractères du nom de l'équipe
   */
  public String getNom() {
    return n;
  }

}