/**
 * Classe permettant de représenter un personnage, le faire bouger, attaquer, etc.
 * 
 * @author Paul P., Maxence P.
 */
public class Personnage {
	// Attributs de la classe
  /**
   * Objet de type Position représentant la position du personnage.
   */
  private Position p;
	/**
   * Objet de type Attaque permettant de stocker les informations relatives à l'attaque du personnage.
   */
  private Attaque a;
  /**
   * Objet de type Defense permettant de stocker les informations relatives à la défense du personnage.
   */
  private Defense d;
  /**
   * Objet de type Sante permettant de stocker les informations relatives à la santé du personnage.
   */
  private Sante s;
  /**
   * Objet de type Equipe correspondant à l'équipe du personnage.
   */
  private Equipe e;
  /**
   * Booléen à "true" si le personnage est en vie, "false" sinon.
   */
  private boolean v;
  /**
   * Chaine de caractères permettant le stockage du nom du personnage.
   */
  private String n;
  /**
   * Chaine de caractères permettant le stockage du nom du personnage agrémenter d'une couleur en fonction de l'équipe.
   */
  private String nCouleur;
  /**
   * Objet de type Direction indiquant le mouvement d'un personnage.
   */
  private Direction dir;
  /**
   * Objet de type Grille 
   */
  private Grille g;

  // Constructeurs
  /**
   * Constructeur prenant tous les attributs d'un personnage en paramètres hors son nom coloré, ses objets attaque defense santé, son état de vie et sa direction.
   * 
   * @param h la position h
   * @param l la position l
   * @param equipe son équipe donnée par le type équipe
   * @param type son type (la classe du personnage parmis archer, guerrier et mage donnés par 0, 1 et 2)
   * @param nom son nom (une lettre)
   * @param grille l'objet grille sur laquelle le personnage est
   */
  Personnage(int h, int l, Equipe equipe, int type, String nom, Grille grille) {
    p = new Position(h, l);
    a = new Attaque(type);
    d = new Defense(type);
    s = new Sante(type);
    e = equipe;
    v = true;
    n = nom;
    if (e.getNom() == "Blue") {
      nCouleur = "\u001B[34m" + nom + "\u001B[0m";
    } else {
      nCouleur = "\u001B[31m" + nom + "\u001B[0m";
    }
    dir = new Direction(type);
    g = grille;
  }

  // Méthodes
  /**
   * Permet l'affichage des attributs du personnage à l'aide du retour d'une chaine de caractères.
   * 
   * @return la chaine de caractères d'affichage mise en forme
   */
  public String toString() {
    return "[" + nCouleur + ": " + a + "," + d + "," + s + "]";
  }

  /**
   * Permet de gérer la prise de dégat d'un personnage
   * verifie si le personnage meurt et gère sa résurrection si elle est possible
	 * @param degats le nombre de points de dégat qu'il subit
   */
  private void takeDmg(int degats) {
    s.set(s.getVie() - degats, s.getResurrection());
    if (s.getVie() <= 0) {
      if (s.canRevive()) {
        int minRes = s.getVie() * (-1) + 1;
        int resTaken = randint(minRes, s.getResurrection());
        s.set(s.getVie() + resTaken, s.getResurrection() - resTaken);
      } else {
        s.set(0, 0);
        v = false;
        p = new Position(-1, -1);
      }
    }
  }

  /**
   * Permet de renvoyer un entier entre min et max inclus
   * 
	 * @param min la borne basse
	 * @param max la borne haute
	 * @return un entier entre min et max
   */
  private int randint(int min, int max) {
    return (int) (Math.random() * (max - min + 1) + min);
  }

  /**
   * Permet de renvoyer un lancer de dé classique
   * 
	 * @return un entier entre 1 et 6
   */
  private int dice() {
    return randint(1, 6);
  }

  /**
   * Permet de gerer tout le processus d'attaque du personnage vers un autre (B)
   * verifie si l'attaque porte, si l'attaque fait des dégats, auquel cas appelle takeDmg pour B
	 * @param B le personnage attaqué
   * @return true si l'attaque tue B false sinon
   */
  private boolean attack(Personnage B) {
    if (a.getHabilete() > B.getDefense().getEsquive() - dice()) {
      int degats = a.getForce() + dice() - B.getDefense().getArmure();
      if (degats > 0) {
        B.takeDmg(degats);
        if (!B.isAlive()) {
          Ecran.afficherln("Après l'attaque " + B + " est mort");
          return false;
        } else {
          Ecran.afficherln("Après l'attaque " + B + " s'en sort avec "+ phraseDmg());
          return true;
        }
      } else {
        Ecran.afficherln("L'attaque n'a pas fait de dégats, pensez à vous muscler c'est pratique en combat...");
        return true;
      }
    } else {
      Ecran.afficherln("L'attaque ne porte pas, apprendre à viser peut s'avérer utile...");
      return true;
    }
  }

  /**
   * Permet de gérer le mouvement d'un personnage
   * si le personnage est en collision avec l'équipe adverse lance une attaque
   */
  public void movePerso() {
    p.setH(p.getH() + dir.getDh());
    p.setL(p.getL() + dir.getDl());
    Direction d = dir;
    if ((p.getH() == -1) || (p.getH() == g.getH())) {
      if (p.getH() == -1) {
        p.setH(1);
      } else {
        p.setH(g.getH() - 2);
      }
      d = new Direction(-dir.getDh(), dir.getDl());
    }
    if ((p.getL() == -1) || (p.getL() == g.getL())) {
      if (p.getL() == -1) {
        p.setL(1);
      } else {
        p.setL(g.getL() - 2);
      }
      d = new Direction(d.getDh(), -d.getDl());
    }
    dir = d;
    if (enCollision(p)) {
      Personnage B = g.getPerso(p.getH(), p.getL());
      if (B.getE() != e) {
        Ecran.afficherln(this + " attaque " + B);
        if (attack(B)) {
          seekNewPos();
        }
      } else {
        Ecran.afficherln(this);
        seekNewPos();
      }
    } else {
      Ecran.afficherln(this);
    }
  }

  /**
   * Permet de gerer le rebond d'un personnage sur un autre
   * modifie sa position et sa direction en conséquence
   */
  private void seekNewPos() {
    Position newPos;
    newPos = newPos(p);
    dir = new Direction(diffPos(newPos, p));
    p = newPos;
  }

  /**
   * Permet de donner sous la forme d'une position (à des fin pratique) la différence entre deux positions
   * 
	 * @param newPos la nouvelle position
	 * @param pos la position actuelle
	 * @return la position qui représente l'écart entre newPos et pos (l'équivalent d'une Direction)
   */
  public Position diffPos(Position newPos, Position pos) {
    return new Position(newPos.getH() - pos.getH(), newPos.getL() - pos.getL());
  }

  /**
   * Permet de véfifier qu'une position est valide
   * c'est à dire dans les bornes et sur une case vide
   *
	 * @param p la position à vérifier
	 * @return un boolean true si la pos est valide
   */
  private boolean isValid(Position p) {
    if (p.getH() < 0 || p.getH() > g.getH() - 1 || p.getL() < 0 || p.getL() > g.getL() - 1 || enCollision(p))
      return false;
    return true;
  }

  /**
   * Permet de renvoyer une position valide aléatoire autour d'une position
   * 
	 * @param p la position de départ
	 * @return la nouvelle position valide
   */
  private Position newPos(Position p) {
    Position newPos = new Position(-1, -1);
    int cpt = 0;
    while (!isValid(newPos) && cpt < 32) {
      cpt++;
      if (Math.random() < 0.5) {
        if (Math.random() < 0.5) {
          newPos = new Position(p.getH() + ((int) (Math.random() * 2) - 1), p.getL() + 1);
        } else {
          newPos = new Position(p.getH() + ((int) (Math.random() * 2) - 1), p.getL() - 1);
        }
      } else {
        if (Math.random() < 0.5) {
          newPos = new Position(p.getH() + 1, p.getL() + ((int) (Math.random() * 2) - 1));
        } else {
          newPos = new Position(p.getH() - 1, p.getL() + ((int) (Math.random() * 2) - 1));
        }
      }
    }
    if (!isValid(newPos)) {
      return p;
    }
    return newPos;
  }

  /**
   * Permet de contrôler si à la position p se trouve un personnage existant et différent de lui-même.
   * 
	 * @param p la position à contrôler
	 * @return true si la condition est validée
   */
  private boolean enCollision(Position p) {
    if (g.getPerso(p.getH(), p.getL()) != this && g.getPerso(p.getH(), p.getL()) != null) {
      return true;
    }
    return false;
  }

  /**
   * Permet de renvoyer une phrase aléatoire 
   * La phrase est à caractère humoristique utilisée lorsqu'un personnage prend des dégats sans décéder
   * 
	 * @return le String humoristique 
   */
	private String phraseDmg() {
		String tab[] = {"une fracture de la mandibule", "un ongle cassé", "les métacarpiens en miettes", "un bleu au tibia", "trois points de suture", "le nez qui saigne", "le coeur brisé", "un rendez-vous chez l'ostéopathe"};
		int i = randint(0,7);
		return tab[i];
	}
	
  /**
   * Getter permettant l'accès à l'attaque du personnage
   * 
   * @return la valeur de la variable a
   */
  public Attaque getAttaque() {
    return a;
  }

  /**
   * Getter permettant l'accès à la défense du personnage
   * 
   * @return la valeur de la variable d
   */
  public Defense getDefense() {
    return d;
  }

  /**
   * Getter permettant l'accès à la santé du personange
   * 
   * @return la valeur de la variable s
   */
  public Sante getSante() {
    return s;
  }

  /**
   * Getter permettant l'accès au nom du personnage
   * 
   * @return la valeur de la variable n
   */
  public String getNom() {
    return n;
  }

  /**
   * Getter permettant l'accès au nom 'coloré' du personnage
   * 
   * @return la valeur de la variable nCouleur
   */
  public String getNomCouleur() {
    return nCouleur;
  }
	
  /**
   * Getter permettant l'accès à la position du personnage
   * 
   * @return la valeur de la variable p
   */
  public Position getPos() {
    return p;
  }

  /**
   * Getter permettant l'accès à la direction du personnage
   * 
   * @return la valeur de la variable dir
   */
  public Direction getDir() {
    return dir;
  }

  /**
   * Getter permettant l'accès à l'équipe du personnage
   * 
   * @return la valeur de la variable e
   */
  public Equipe getE() {
    return e;
  }

  /**
   * Getter permettant l'accès l'état de la vie du personnage
   * 
   * @return la valeur de la variable v
   */
  public boolean isAlive() {
    return v;
  }
}