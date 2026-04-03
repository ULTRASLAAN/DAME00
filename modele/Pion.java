package modele;

public class Pion extends Piece {
    public Pion(int couleur) {
        super(couleur);
    }

    @Override
    public boolean estDame() {
        return false;
    }
}
