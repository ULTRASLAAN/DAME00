import javax.swing.*;

public class Interfacegraphique {
    public Interfacegraphique() {
        JFrame frame = new JFrame("Jeu de Dames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600); // Taille de la fenêtre
        frame.setLocationRelativeTo(null); // Centre la fenêtre
        PlateauGraphique plateau = new PlateauGraphique();
        frame.add(plateau);
        frame.setVisible(true); // Affiche la fenêtre
    }
}
