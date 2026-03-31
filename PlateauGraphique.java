import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlateauGraphique extends JPanel {
    private int caseSelectionneeX = -1;
    private int caseSelectionneeY = -1;
    private Plateau plateau;
    private ArrayList<String> historique = new ArrayList<>();

    public PlateauGraphique() {
        plateau = new Plateau();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int largeur = getWidth();
                int tailleCase = largeur * 2 / 3 / Plateau.TAILLE;
                int x = e.getX() / tailleCase;
                int y = e.getY() / tailleCase;
                // On ignore les clics hors du damier (2/3 de la largeur)
                if (e.getX() >= tailleCase * Plateau.TAILLE || x < 0 || x >= Plateau.TAILLE || y < 0 || y >= Plateau.TAILLE) {
                    return;
                }
                if (caseSelectionneeX == -1 && caseSelectionneeY == -1) {
                    // Sélection uniquement d'un pion du joueur courant
                    if (plateau.selectionValide(y, x)) {
                        caseSelectionneeX = x;
                        caseSelectionneeY = y;
                        repaint();
                    }
                    // Sinon, on ignore
                } else {
                    // Si déplacement valide, on joue
                    if (plateau.deplacementValide(caseSelectionneeY, caseSelectionneeX, y, x)) {
                        // Ajout à l'historique
                        String couleur = (plateau.getJoueurCourant() == Plateau.NOIR) ? "Noir" : "Rouge";
                        String coup = couleur + ": (" + caseSelectionneeY + "," + caseSelectionneeX + ") -> (" + y + "," + x + ")";
                        historique.add(coup);
                        plateau.deplacer(caseSelectionneeY, caseSelectionneeX, y, x);
                        caseSelectionneeX = -1;
                        caseSelectionneeY = -1;
                        repaint();
                    } else if (plateau.selectionValide(y, x)) {
                        // Permet de changer de sélection si on clique sur un autre pion à soi
                        caseSelectionneeX = x;
                        caseSelectionneeY = y;
                        repaint();
                    } else {
                        // Action invalide, on annule la sélection
                        caseSelectionneeX = -1;
                        caseSelectionneeY = -1;
                        repaint();
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int largeur = getWidth();
        int hauteur = getHeight();
        int tailleCase = largeur * 2 / 3 / Plateau.TAILLE;
        // Damier sur 2/3 de la largeur
        for (int i = 0; i < Plateau.TAILLE; i++) {
            for (int j = 0; j < Plateau.TAILLE; j++) {
                int x = j * tailleCase;
                int y = i * tailleCase;
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(x, y, tailleCase, tailleCase);

                // Surligne la case sélectionnée
                if (i == caseSelectionneeY && j == caseSelectionneeX) {
                    g.setColor(Color.YELLOW);
                    g.drawRect(x, y, tailleCase - 1, tailleCase - 1);
                    g.drawRect(x + 2, y + 2, tailleCase - 5, tailleCase - 5);
                }

                // Affiche les pions selon l'état du plateau
                if (plateau.getCase(i, j) == Plateau.NOIR) {
                    int marge = tailleCase / 8;
                    int diametre = tailleCase - 2 * marge;
                    g.setColor(Color.BLACK);
                    g.fillOval(x + marge, y + marge, diametre, diametre);
                } else if (plateau.getCase(i, j) == Plateau.ROUGE) {
                    int marge = tailleCase / 8;
                    int diametre = tailleCase - 2 * marge;
                    g.setColor(Color.RED);
                    g.fillOval(x + marge, y + marge, diametre, diametre);
                }
            }
        }
        // Affichage de l'historique à droite
        g.setColor(Color.BLACK);
        g.drawString("Historique des coups :", largeur * 2 / 3 + 10, 20);
        int yText = 40;
        for (int i = Math.max(0, historique.size() - 20); i < historique.size(); i++) {
            g.drawString(historique.get(i), largeur * 2 / 3 + 10, yText);
            yText += 18;
        }
    }
}
