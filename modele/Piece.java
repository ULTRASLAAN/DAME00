<<<<<<<< HEAD:modele/Piece.java
package modele;
========
package model;
>>>>>>>> 025c261 (dame promotion):model/Piece.java

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
