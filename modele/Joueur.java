<<<<<<<< HEAD:modele/Joueur.java
package modele;
========
package model;
>>>>>>>> 025c261 (dame promotion):model/Joueur.java

public class Joueur {
    private int couleur; // 1 = noir, 2 = rouge
    private String nom;

    public Joueur(int couleur, String nom) {
        this.couleur = couleur;
        this.nom = nom;
    }

    public int getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }
}
