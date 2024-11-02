# Snake Game en Java

Bienvenue sur la page de présentation de **Snake Game**, mon tout premier projet en Java ! 🐍 Ce projet est un jeu classique de Snake, entièrement codé en Java. Il m’a permis de me familiariser avec les bases de la programmation orientée objet, les structures de contrôle, et la gestion des événements en Java.

## Fonctionnalités du jeu

- **Déplacement** du serpent avec les touches directionnelles (haut, bas, gauche, droite)
- **Gestion des collisions** : fin de partie si le serpent touche les bords de l'écran ou se mord lui-même
- **Croissance du serpent** : le serpent s'allonge à chaque fois qu'il mange une pomme
- **Score** : suivi du score, avec une augmentation à chaque pomme mangée

## Technologies et outils utilisés

- **Langage** : Java
- **Environnement de développement** : IntelliJ IDEA
- **Concepts abordés** : Programmation orientée objet, gestion des événements, boucles, conditions, et utilisation de JFrame pour l’interface graphique

## Aperçu du code

Voici un extrait du code du jeu pour vous donner un aperçu :

```java
public class SnakeGame {
    public static void main(String[] args) {
        new GameFrame();
    }
}
