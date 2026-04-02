import javax.swing.*;

public class Interfacegraphique {
    private Interfacegraphique() {
    }

    public static void ouvrir(String nomNoir, String nomRouge) {
        String titre = "Jeu de Dames - " + nomNoir + " vs " + nomRouge;
        JFrame frame = new JFrame(titre);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600); // Taille de la fenêtre
        frame.setLocationRelativeTo(null); // Centre la fenêtre
        PlateauGraphique plateau = new PlateauGraphique();
        frame.add(plateau);
        frame.setVisible(true); // Affiche la fenêtre
    }
}
