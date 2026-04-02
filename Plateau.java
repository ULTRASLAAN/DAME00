public class Plateau {
    public static final int TAILLE = 8;
    public static final int VIDE = 0;
    public static final int NOIR = 1;
    public static final int ROUGE = 2;
    public static final int DAME_NOIR = 3;
    public static final int DAME_ROUGE = 4;

    private final int[][] plateau;
    private int joueurCourant;

    public Plateau() {
        plateau = new int[TAILLE][TAILLE];
        initialiser();
        joueurCourant = NOIR;
    }

    private void initialiser() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = VIDE;
                if ((i + j) % 2 != 0) {
                    if (i < 3) plateau[i][j] = NOIR;
                    else if (i > 4) plateau[i][j] = ROUGE;
                }
            }
        }
    }

    public int getCase(int y, int x) {
        return plateau[y][x];
    }

    public int getJoueurCourant() {
        return joueurCourant;
    }

    public boolean selectionValide(int y, int x) {
        return estPieceDuJoueurCourant(plateau[y][x]);
    }

    public boolean deplacementValide(int fromY, int fromX, int toY, int toX) {
        if (plateau[toY][toX] != VIDE) return false;

        int piece = plateau[fromY][fromX];
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
                if (plateau[courantY][courantX] != VIDE) {
                    if (estPieceDuJoueurCourant(plateau[courantY][courantX])) {
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

        // Déplacement simple
        if (Math.abs(dx) == 1) {
            if (joueurCourant == NOIR && dy == 1) return true;
            if (joueurCourant == ROUGE && dy == -1) return true;
        }
        // Capture (saut de 2 cases)
        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            int midY = fromY + dy / 2;
            int midX = fromX + dx / 2;
            int pionAdverse = (joueurCourant == NOIR) ? ROUGE : NOIR;
            if (plateau[midY][midX] == pionAdverse) {
                if (joueurCourant == NOIR && dy == 2) return true;
                if (joueurCourant == ROUGE && dy == -2) return true;
            }
        }
        return false;
    }

    public void deplacer(int fromY, int fromX, int toY, int toX) {
        int piece = plateau[fromY][fromX];
        int dx = toX - fromX;
        int dy = toY - fromY;

        if (estDame(piece) && Math.abs(dx) == Math.abs(dy) && Math.abs(dx) > 1) {
            int pasX = Integer.signum(dx);
            int pasY = Integer.signum(dy);
            int courantX = fromX + pasX;
            int courantY = fromY + pasY;
            while (courantX != toX && courantY != toY) {
                if (plateau[courantY][courantX] != VIDE && !estPieceDuJoueurCourant(plateau[courantY][courantX])) {
                    plateau[courantY][courantX] = VIDE;
                    break;
                }
                courantX += pasX;
                courantY += pasY;
            }
        } else if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            int midY = fromY + dy / 2;
            int midX = fromX + dx / 2;
            plateau[midY][midX] = VIDE; // élimine le pion adverse
        }

        plateau[toY][toX] = piece;
        plateau[fromY][fromX] = VIDE;

        if (toY == 0 && piece == ROUGE) {
            plateau[toY][toX] = DAME_ROUGE;
        } else if (toY == TAILLE - 1 && piece == NOIR) {
            plateau[toY][toX] = DAME_NOIR;
        }

        joueurCourant = (joueurCourant == NOIR) ? ROUGE : NOIR;
    }

    private boolean estPieceDuJoueurCourant(int piece) {
        return (joueurCourant == NOIR && (piece == NOIR || piece == DAME_NOIR))
                || (joueurCourant == ROUGE && (piece == ROUGE || piece == DAME_ROUGE));
    }

    private boolean estDame(int piece) {
        return piece == DAME_NOIR || piece == DAME_ROUGE;
    }
}
