package modele;

public abstract class Piece {
    protected int couleur; // 1 = noir, 2 = rouge

    public Piece(int couleur) {
        this.couleur = couleur;
    }

    public int getCouleur() {
        return couleur;
    }

    public abstract boolean estDame();
}
