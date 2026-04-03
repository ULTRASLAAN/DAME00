package modele;

public class Jeu {
    private Plateau plateau;
    private Joueur joueurNoir;
    private Joueur joueurRouge;
    private int joueurCourant;

    public Jeu(String nomNoir, String nomRouge) {
        plateau = new Plateau();
        joueurNoir = new Joueur(1, nomNoir);
        joueurRouge = new Joueur(2, nomRouge);
        joueurCourant = 1;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur getJoueurCourant() {
        return (joueurCourant == 1) ? joueurNoir : joueurRouge;
    }

    public void changerJoueur() {
        joueurCourant = (joueurCourant == 1) ? 2 : 1;
    }
}
