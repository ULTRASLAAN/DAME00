import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PageAccueil {
    private final JFrame frame;
    private final JTextField joueurNoirField;
    private final JTextField joueurRougeField;

    public PageAccueil() {
        frame = new JFrame("Accueil - Jeu de Dames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 380);
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        root.setBackground(new Color(246, 241, 232));

        JLabel titre = new JLabel("Jeu de Dames", SwingConstants.CENTER);
        titre.setFont(new Font("Serif", Font.BOLD, 40));
        titre.setForeground(new Color(44, 34, 24));
        root.add(titre, BorderLayout.NORTH);

        JPanel centre = new JPanel(new GridBagLayout());
        centre.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel sousTitre = new JLabel("Entrez les noms des joueurs", SwingConstants.CENTER);
        sousTitre.setFont(new Font("SansSerif", Font.PLAIN, 16));
        sousTitre.setForeground(new Color(70, 58, 43));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centre.add(sousTitre, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        centre.add(new JLabel("Joueur Noir :"), gbc);

        joueurNoirField = new JTextField("Joueur Noir", 18);
        gbc.gridx = 1;
        centre.add(joueurNoirField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        centre.add(new JLabel("Joueur Rouge :"), gbc);

        joueurRougeField = new JTextField("Joueur Rouge", 18);
        gbc.gridx = 1;
        centre.add(joueurRougeField, gbc);

        root.add(centre, BorderLayout.CENTER);

        JPanel bas = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 6));
        bas.setOpaque(false);

        JButton quitterBtn = new JButton("Quitter");
        quitterBtn.addActionListener(e -> frame.dispose());

        JButton lancerBtn = new JButton("Lancer la partie");
        lancerBtn.addActionListener(e -> lancerPartie());

        bas.add(quitterBtn);
        bas.add(lancerBtn);
        root.add(bas, BorderLayout.SOUTH);

        frame.setContentPane(root);
        frame.setVisible(true);
    }

    private void lancerPartie() {
        String nomNoir = joueurNoirField.getText().trim();
        String nomRouge = joueurRougeField.getText().trim();

        if (nomNoir.isEmpty()) {
            nomNoir = "Joueur Noir";
        }
        if (nomRouge.isEmpty()) {
            nomRouge = "Joueur Rouge";
        }

        Interfacegraphique.ouvrir(nomNoir, nomRouge);
        frame.dispose();
    }
}
