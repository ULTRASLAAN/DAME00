<<<<<<<< HEAD:modele/Case.java
package modele;
========
package model;
>>>>>>>> 025c261 (dame promotion):model/Case.java

public class Case {
    private Piece piece;

    public Case() {
        this.piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean estVide() {
        return piece == null;
    }
}
