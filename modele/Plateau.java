<<<<<<<< HEAD:modele/Plateau.java
package modele;
========
package model;
>>>>>>>> 025c261 (dame promotion):model/Plateau.java

public class Plateau {
    // Constantes du plateau et des pièces
    public static final int TAILLE = 8;
    public static final int VIDE = 0;
    public static final int NOIR = 1;
    public static final int ROUGE = 2;
    public static final int DAME_NOIR = 3;
    public static final int DAME_ROUGE = 4;

    // État du jeu
    private final Case[][] plateau;
    private int joueurCourant;

    // Création du plateau de départ
    public Plateau() {
        plateau = new Case[TAILLE][TAILLE];
        initialiser();
        joueurCourant = NOIR;
    }

    // Place les pions de départ sur le damier
    private void initialiser() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = new Case();
                if ((i + j) % 2 != 0) {
                    if (i < 3) plateau[i][j].setPiece(new Pion(NOIR));
                    else if (i > 4) plateau[i][j].setPiece(new Pion(ROUGE));
                }
            }
        }
    }

    // Accesseurs
    public Piece getPiece(int y, int x) {
        return plateau[y][x].getPiece();
    }

    public int getJoueurCourant() {
        return joueurCourant;
    }

    // Vérifie qu'une case contient bien une pièce du joueur courant
    public boolean selectionValide(int y, int x) {
        return estPieceDuJoueurCourant(plateau[y][x].getPiece());
    }

    // Vérifie si le déplacement demandé est autorisé
    public boolean deplacementValide(int fromY, int fromX, int toY, int toX) {
        if (!plateau[toY][toX].estVide()) return false;

        Piece piece = plateau[fromY][fromX].getPiece();
        if (!estPieceDuJoueurCourant(piece)) return false;

        int dx = toX - fromX;
        int dy = toY - fromY;

        if (estDame(piece)) {
            if (Math.abs(dx) != Math.abs(dy) || dx == 0) return false;

            int pasX = Integer.signum(dx);
            int pasY = Integer.signum(dy);
            int courantX = fromX + pasX;
            int courantY = fromY + pasY;
            int piecesRencontrees = 0;

            while (courantX != toX && courantY != toY) {
                Piece pieceCourante = plateau[courantY][courantX].getPiece();
                if (pieceCourante != null) {
                    if (estPieceDuJoueurCourant(pieceCourante)) {
                        return false;
                    }
                    piecesRencontrees++;
                    if (piecesRencontrees > 1) {
                        return false;
                    }
                }
                courantX += pasX;
                courantY += pasY;
            }

            return true;
        }

        // Déplacement simple d'un pion : une case en diagonale
        if (Math.abs(dx) == 1) {
            if (joueurCourant == NOIR && dy == 1) return true;
            if (joueurCourant == ROUGE && dy == -1) return true;
        }

        // Capture d'un pion : saut de 2 cases avec un adversaire au milieu
        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            int midY = fromY + dy / 2;
            int midX = fromX + dx / 2;
            Piece pieceMilieu = plateau[midY][midX].getPiece();
            int pionAdverse = (joueurCourant == NOIR) ? ROUGE : NOIR;
            if (pieceMilieu != null && pieceMilieu.getCouleur() == pionAdverse) {
                if (joueurCourant == NOIR && dy == 2) return true;
                if (joueurCourant == ROUGE && dy == -2) return true;
            }
        }
        return false;
    }

    // Applique réellement le déplacement sur le plateau
    public void deplacer(int fromY, int fromX, int toY, int toX) {
        Piece piece = plateau[fromY][fromX].getPiece();
        int dx = toX - fromX;
        int dy = toY - fromY;

        // Si c'est une dame, on enlève la première pièce adverse rencontrée sur la diagonale
        if (estDame(piece) && Math.abs(dx) == Math.abs(dy) && Math.abs(dx) > 1) {
            int pasX = Integer.signum(dx);
            int pasY = Integer.signum(dy);
            int courantX = fromX + pasX;
            int courantY = fromY + pasY;
            while (courantX != toX && courantY != toY) {
                Piece pieceCourante = plateau[courantY][courantX].getPiece();
                if (pieceCourante != null && !estPieceDuJoueurCourant(pieceCourante)) {
                    plateau[courantY][courantX].setPiece(null);
                    break;
                }
                courantX += pasX;
                courantY += pasY;
            }
        } else if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            // Pour un pion, la case du milieu est supprimée pendant la capture
            int midY = fromY + dy / 2;
            int midX = fromX + dx / 2;
            plateau[midY][midX].setPiece(null);
        }

        // Déplacement de la pièce sur la case d'arrivée
        plateau[toY][toX].setPiece(piece);
        plateau[fromY][fromX].setPiece(null);

        // Promotion : un pion devient dame en atteignant la dernière ligne
        if (piece != null && !piece.estDame()) {
            if (toY == 0 && piece.getCouleur() == ROUGE) {
                plateau[toY][toX].setPiece(new Dame(ROUGE));
            } else if (toY == TAILLE - 1 && piece.getCouleur() == NOIR) {
                plateau[toY][toX].setPiece(new Dame(NOIR));
            }
        }

        // Passage au joueur suivant
        joueurCourant = (joueurCourant == NOIR) ? ROUGE : NOIR;
    }

    // Vrai si la pièce appartient au joueur dont c'est le tour
    private boolean estPieceDuJoueurCourant(Piece piece) {
        return piece != null && piece.getCouleur() == joueurCourant;
    }

    // Vrai si la pièce est une dame
    private boolean estDame(Piece piece) {
        return piece != null && piece.estDame();
    }
}
