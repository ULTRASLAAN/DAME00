<<<<<<<< HEAD:modele/Pion.java
package modele;
========
package model;
>>>>>>>> 025c261 (dame promotion):model/Pion.java

public class Pion extends Piece {
    public Pion(int couleur) {
        super(couleur);
    }

    @Override
    public boolean estDame() {
        return false;
    }
}
