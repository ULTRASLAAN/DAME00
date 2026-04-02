import javax.swing.*;
import java.awt.*;

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

        JPanel racine = new JPanel(new BorderLayout());
        JPanel barreActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));

        JButton recommencerBtn = new JButton("Recommencer");
        recommencerBtn.addActionListener(e -> plateau.recommencerPartie());

        barreActions.add(recommencerBtn);
        racine.add(barreActions, BorderLayout.NORTH);
        racine.add(plateau, BorderLayout.CENTER);

        frame.setContentPane(racine);
        frame.setVisible(true); // Affiche la fenêtre
    }
}
