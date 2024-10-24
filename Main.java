import java.lang.Math;

/**
 * Classe principale permettant l'exécution du jeu.
 * 
 * @author Paul P., Maxence P.
 */
public class Main {
  /**
   * Entier LARGEUR definit la largeur de la grille
   */
  static final int LARGEUR = 4;
  /**
   * Entier HAUTEUR definit la largeur de la grille
   */
  static final int HAUTEUR = 5;
  /**
   * Entier TAILLE_EQUIPES definit la taille des équipes
   */
  static final int TAILLE_EQUIPES = 4;

  /**
   * Objet de type Equipe, objet représentant l'équipe bleu
   */
  static Equipe blue = new Equipe(TAILLE_EQUIPES, "Blue");
	/**
   * Objet de type Equipe, objet représentant l'équipe rouge
   */
  static Equipe red = new Equipe(TAILLE_EQUIPES, "Red");

  /**
   * Objet de type Grille, objet représentant la grille de jeu
   */
  static Grille grille = new Grille(HAUTEUR, LARGEUR);


	/**
   * Permet d'initialiser et de lancer une partie
   * Instancie les personnages, les place et lance successivement les tours de jeu
   */
  public static void main(String[] args) {

    // Création personnages équipe Blue
    Personnage archer = new Personnage(0, 0, blue, 0, "a", grille);
    blue.addPerso(archer);
    Personnage guerrier = new Personnage(0, 1, blue, 1, "b", grille);
    blue.addPerso(guerrier);
    Personnage mage = new Personnage(0, 2, blue, 2, "c", grille);
    blue.addPerso(mage);
    Personnage archerbis = new Personnage(0, 3, blue, 0, "d", grille);
    blue.addPerso(archerbis);

    // Création personnages équipe Red
    Personnage archer2 = new Personnage(4, 3, red, 0, "A", grille);
    red.addPerso(archer2);
    Personnage guerrier2 = new Personnage(4, 2, red, 1, "B", grille);
    red.addPerso(guerrier2);
    Personnage mage2 = new Personnage(4, 1, red, 2, "C", grille);
    red.addPerso(mage2);
    Personnage archerbis2 = new Personnage(4, 0, red, 0, "D", grille);
    red.addPerso(archerbis2);

    //placement des personnages
    grille.setPerso(blue, red);
    Ecran.afficherln(grille);

    //Tours
    int nbTour = 0;
    while (joueurEnJeu(false) && nbTour < 100) {
      tour();
      nbTour++;
    }

    //phrase de fin du jeu
    if (joueurEnJeu(false)) {
      Ecran.afficherln("Tour 100 atteint, fin de la partie\nÉgalité.");
    } else {
      printWinner();
    }
  }

  /**
   * Permet de renvoyer si oui ou non il reste des joueurs en vie dans l'équipe bleu ou rouge.
   * 
   * @param isTeamB précise si l'on recherche que sur la team bleu ou les deux (true : team bleu)
   * @return boolean : il reste des joueurs en jeu
   */
  static boolean joueurEnJeu(boolean isTeamB) {
    boolean bAlive = false;
    boolean rAlive = false;
    for (int i = 0; i < TAILLE_EQUIPES; i++) {
      if (blue.getArray().get(i).isAlive())
        bAlive = true;
      if (red.getArray().get(i).isAlive())
        rAlive = true;
    }
    if (isTeamB) {
      return bAlive;
    }
    return bAlive && rAlive;
  }

  /**
   * Permet de lancer un tour (un mouvement de chaque personnage alternativement bleu puis rouge...)
   */
  static void tour() {
    for (int i = 0; i < TAILLE_EQUIPES * 2; i++) {
      if (i % 2 == 0) {
        Personnage p = blue.getArray().get(i / 2);
        if (p.isAlive() && joueurEnJeu(false)) {
          Ecran.afficherln("Appuyez sur entrée");
          Clavier.saisirString();
          p.movePerso();
          grille.setPerso(blue, red);
          Ecran.afficherln(grille);
        }
      } else {
        Personnage p2 = red.getArray().get((i - 1) / 2);
        if (p2.isAlive() && joueurEnJeu(false)) {
          Ecran.afficherln("Appuyez sur entrée");
          Clavier.saisirString();
          p2.movePerso();
          grille.setPerso(blue, red);
          Ecran.afficherln(grille);
        }
      }
    }
  }

  /**
   * Permet d'écrire à l'écran la team gagnante
   */
  static void printWinner() {
    if (joueurEnJeu(true)) {
      Ecran.afficherln("Team Bleue gagnante !");
    } else {
      Ecran.afficherln("Team Rouge gagnante !");
    }
    Ecran.afficherln("Fin du jeu...");
  }
}