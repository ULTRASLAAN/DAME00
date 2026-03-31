public class Plateau {
    public static final int TAILLE = 8;
    public static final int VIDE = 0;
    public static final int NOIR = 1;
    public static final int ROUGE = 2;

    private int[][] plateau;
    private int joueurCourant;

    public Plateau() {
        plateau = new int[TAILLE][TAILLE];
        initialiser();
        joueurCourant = NOIR;
    }

    public void initialiser() {
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
        return plateau[y][x] == joueurCourant;
    }

    public boolean deplacementValide(int fromY, int fromX, int toY, int toX) {
        if (plateau[toY][toX] != VIDE) return false;
        int dx = toX - fromX;
        int dy = toY - fromY;
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
        int dx = toX - fromX;
        int dy = toY - fromY;
        // Capture
        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            int midY = fromY + dy / 2;
            int midX = fromX + dx / 2;
            plateau[midY][midX] = VIDE; // élimine le pion adverse
        }
        plateau[toY][toX] = plateau[fromY][fromX];
        plateau[fromY][fromX] = VIDE;
        joueurCourant = (joueurCourant == NOIR) ? ROUGE : NOIR;
    }
}
