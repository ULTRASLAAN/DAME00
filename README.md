# Jeu de Dames en Java

## Présentation
Ce projet est un jeu de dames classique, développé en Java avec une interface graphique Swing. Il permet à deux joueurs de s'affronter sur le même ordinateur, en respectant les règles traditionnelles du jeu de dames (déplacement diagonal, captures, alternance des joueurs, historique des coups).

## Installation et Lancement
1. **Prérequis** : Java JDK 8 ou supérieur installé.
2. **Compilation** :
   - Placez-vous dans le dossier du projet.
   - Compilez tous les fichiers Java :
     ```
     javac *.java
     ```
3. **Exécution** :
   - Lancez le jeu avec :
     ```
     java Main
     ```

## Justification du choix de Swing
Swing a été choisi pour l'interface graphique car :
- Il est inclus dans le JDK standard, donc aucune dépendance externe n'est nécessaire.
- Il est bien documenté et adapté à l'apprentissage de la programmation graphique en Java.
- Il permet de créer rapidement des interfaces réactives et personnalisées.
- La majorité des ressources pédagogiques pour débuter en Java utilisent Swing.

## Aperçu
![Aperçu du jeu de dames](screenshot.png)

## Structure du projet
- `Main.java` : point d'entrée du programme
- `Interfacegraphique.java` : création de la fenêtre principale
- `PlateauGraphique.java` : affichage du damier, gestion des clics, historique
- `Plateau.java`, `Case.java`, `Piece.java`, `Pion.java`, `Dame.java` : logique du jeu et des pièces
- `Joueur.java`, `Jeu.java` : gestion des joueurs et du déroulement

## Auteurs
- [À compléter avec les noms/prénoms du groupe]

## Licence
Projet pédagogique, usage libre dans le cadre du cours.
