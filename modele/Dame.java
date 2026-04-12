<<<<<<<< HEAD:modele/Dame.java
package modele;
========
package model;
>>>>>>>> 025c261 (dame promotion):model/Dame.java

public class Dame extends Piece {
    public Dame(int couleur) {
        super(couleur);
    }

    @Override
    public boolean estDame() {
        return true;
    }
}
