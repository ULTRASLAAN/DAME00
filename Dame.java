public class Dame extends Piece {
    public Dame(int couleur) {
        super(couleur);
    }

    @Override
    public boolean estDame() {
        return true;
    }
}
