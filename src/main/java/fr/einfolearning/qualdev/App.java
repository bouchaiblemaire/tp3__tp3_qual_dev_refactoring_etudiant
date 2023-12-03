package fr.einfolearning.qualdev;

public class App {

    public static void main(String[] args) {
        /* l'instruction suivante utilise l'auto-boxing */
        ArbreBinaireBase<Integer> ai = new ArbreBinaireBase<Integer>(1); /* la racine de l'arbre est 1.
                                                                                son identifiant est 1. */

        ai.ajout(1, 4, ArbreBinaireBase.Position.gauche);
        ai.ajout(1, 5, ArbreBinaireBase.Position.droit);
        ai.ajout(2, 6, ArbreBinaireBase.Position.gauche); /* l'identifiant de 4 est 2 */
        ai.ajout(2, 7, ArbreBinaireBase.Position.droit);
        ai.ajout(3, 8, ArbreBinaireBase.Position.gauche); /* l'identifiant de 5 est 3 */
        ai.ajout(6, 9, ArbreBinaireBase.Position.gauche); /* l'identifiant de 8 est 6 */
        ai.ajout(6, 10, ArbreBinaireBase.Position.droit); /* l'identifiant de 8 est 6 */
        /* création du même arbre en utilisant la méthode getIdDonnee(). attention ! : cette façon de faire n'est pas
        efficace du tout car pour calculer l'identifiant de chaque noeud, on reparcourt l'arbre ! */
        /* ai.ajout(ai.getIdDonnee(1),4,Position.gauche); ai.ajout(ai.getIdDonnee(1),5,Position.droit);
         * ai.ajout(ai.getIdDonnee(4),6,Position.gauche); ai.ajout(ai.getIdDonnee(4),7,Position.droit);
         * ai.ajout(ai.getIdDonnee(5),8,Position.gauche); ai.ajout(ai.getIdDonnee(8),9,Position.gauche);
         * * ai.ajout(ai.getIdDonnee(8),10,Position.droit); */
        ai.affiche();
        System.out.println("*****************");
        System.out.println("infixe : "+ai.parcoursInfixe());
        System.out.println("*****************");
        System.out.println("postfixe : "+ai.parcoursPostfixe());
        System.out.println("*****************");
        System.out.println("prefixe : "+ai.parcoursPrefixe());
        System.out.println("*****************");
        System.out.println("largeur : "+ai.largeur());
        // Affichage de taille, nbFeuilles et hauteur
        System.out.println("taille : " + ai.taille());
        System.out.println("nb de feuilles : " + ai.nbFeuilles()); System.out.println("hauteur : " + ai.hauteur());
    }

}