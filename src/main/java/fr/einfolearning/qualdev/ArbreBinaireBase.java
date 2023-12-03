package fr.einfolearning.qualdev;

/* Classe ArbreBinaireBase (B.L. version 2023) */

/* Classe générique permettant de manipuler un arbre binaire.
   Chaque noeud (classe Noeud) contient
     - un identifiant (id de type int) et une valeur (valeur de type générique)
     - un arbre fils gauche et un arbre fils droit.

   Cette classe donne les éléments de base pour représenter et parcourir un arbre binaire.
   Elle ne comporte pas de méthode permettant de calculer des propriétés de l'arbre
*/

import java.util.Vector;

public class ArbreBinaireBase<T> {


    private Noeud<T> racine; /* racine de l'arbre */
    private ArbreBinaireBase<T> sousArbreFilsG; /* sous-arbre gauche de la racine */
    private ArbreBinaireBase<T> sousArbreFilsD; /* sous-arbre droit de la racine */


    /* permet de distinguer les deux positions possibles d'un noeud fils */
    public enum Position {gauche, droit};


    /* Constructeurs */
    public ArbreBinaireBase(T val) { this.racine = new Noeud<T>(val); }

    public ArbreBinaireBase(T val, ArbreBinaireBase<T> fg, ArbreBinaireBase<T> fd) {
        this.racine = new Noeud<T>(val);
        this.sousArbreFilsG = fg;
        this.sousArbreFilsD = fd;
    }

    /* Accesseurs */
    public Noeud<T> getRacine() { return this.racine; }

    public T getDonneeRacine() { return this.racine.getDonnee(); }

    public ArbreBinaireBase<T> getSAG() { return this.sousArbreFilsG; }

    public ArbreBinaireBase<T> getSAD() { return this.sousArbreFilsD; }

    public int getIdRacine(){ return this.racine.getId();};


    /* Mutateurs permettant d'ajouter un sous-arbre au noeud racine */
    /* Attention : s'il en existe déjà un à la même position, il est "écrasé" ! */
    public void setSAG(ArbreBinaireBase<T> fg) { this.sousArbreFilsG = fg; }
    public void setSAD(ArbreBinaireBase<T> fd) { this.sousArbreFilsD = fd;  }

    public void setFilsg(int id, T val) { this.sousArbreFilsG = new ArbreBinaireBase<T>(val); }

    public void setFilsd(int id, T val) { this.sousArbreFilsD = new ArbreBinaireBase<T>(val); }

    /* Rend en résultat le sous-arbre dont le noeud d'identifiant idNoeud est racine si le noeud
    existe dans l'arbre, rend null sinon. Méthode utilisée dans la première méthode d'ajout. */
    public ArbreBinaireBase<T> rechercheId(int idNoeud) {
        ArbreBinaireBase<T> sa;
        if (idNoeud == racine.getId()) {
            return this;
        }
        else {
            sa = null;
            if (sousArbreFilsG != null) {
                sa = sousArbreFilsG.rechercheId(idNoeud);
            }
            if ((sa == null) && (sousArbreFilsD != null)) {
                sa = sousArbreFilsD.rechercheId(idNoeud);
            }
            return sa;
        }
    }


    /* Recherche la première occurence d'une donnée dans l'arbre. Le résultat est le sous-arbre
    dont le noeud racine contient cette donnée. Le parcours est de type en profondeur d'abord
    gauche/droite. Si la donnée n'existe pas dans l'arbre, le résultat est null. Méthode utilisée
    notamment dans getIdDonnee. */
    public ArbreBinaireBase<T> rechercheDonnee(T donnee) {
        ArbreBinaireBase<T> sa;
        if (donnee.equals(racine.getDonnee())) {
            return this;
        }
        else {
            sa = null;
            if (sousArbreFilsG != null) {
                sa = sousArbreFilsG.rechercheDonnee(donnee);
            }
            if ((sa == null) && (sousArbreFilsD != null)) {
                sa = sousArbreFilsD.rechercheDonnee(donnee);
            }
            return sa;
        }
    }

    /* Facultatif */
    /* Peut-être utilisé dans le main pour ne pas avoir à calculer les identifiants des différents
    noeuds. Au lieu d'appeler ajout(5,8,2,Position.gauche) pour ajouter le fils d'identifiant 8
    avec la donnée 2 commefils gauche du noeud d'identifiant 5 dans l'arbre ab, on peut appeler :
    ajout(ab.getIdDonnée(4),8,2,Position.gauche) si 5 est l'identifiant d'un noeud dont la donnee
    est 4. */
    public int getIdDonnee(T donnee) {
        ArbreBinaireBase<T> sa;
        sa = rechercheDonnee(donnee);
        if (sa == null) {
            return -1;
        }
        else {
            return sa.getRacine().getId();
        }
    }

    /* Dans cette méthode d'ajout, on ajoute un sous-arbre a un noeud spécifié par son identifiant.
    La position du sous-arbre est passée en troisième paramètre. On ne vérifie pas s'il existe déja
    un sous-arbre à la position indiquee. Si oui, il est écrasé.
    Attention : la méthode peut cependant  retourner false et donc l'ajout ne pas se faire si
    l'identifiant du noeud père n'est pas trouvé dans l'arbre. */
    public boolean ajout(int idPere, ArbreBinaireBase<T> sa, Position p) {
        ArbreBinaireBase<T> sap;
        sap = rechercheId(idPere);
        if (sap != null) {
            /* on fait l'ajout */
            if (p == Position.gauche) {
                sap.setSAG(sa);
            }
            else {
                sap.setSAD(sa);
            }
            return true;
        } else {
            return false;
        }
    }

    public int ajout2(int idPere, T donnee, Position p) throws Exception {
        ArbreBinaireBase<T> sap;
        int ret;
        if (racine.getId() == idPere) {
            ArbreBinaireBase ab = new ArbreBinaireBase(donnee);
            if (p == Position.gauche) {
                sousArbreFilsG = ab;
            } else {
                sousArbreFilsD = ab;
            }
            return ab.racine.getId();
        }
        try {
            if (sousArbreFilsG != null) {
                ret = sousArbreFilsG.ajout2(idPere, donnee, p);
                return ret;
            }
        } catch (Exception e) {
        }
        try {
            if (sousArbreFilsD != null) {
                ret = sousArbreFilsD.ajout2(idPere, donnee, p);
                return ret;
            }
        } catch (Exception e) {
        }
        throw new Exception();
    }

    /* Similaire à la méthode précédente sauf qu'ici on ajoute un fils et non plus un sous-arbre. */
    public boolean ajout(int idPere, T fils, Position p) {
        return (ajout(idPere, new ArbreBinaireBase<T>(fils), p));
    }

    /*Affichage "textuel" du contenu de l'arbre. Le paramètre esp est une chaîne de caractères utilisée
    comme séparateur des différents éléments de l'arbre lors de l'affichage. */
    private void affiche(String esp) {
        System.out.println(esp + racine);
        if (sousArbreFilsG != null) {
            sousArbreFilsG.affiche(esp + " ");
        }
        if (sousArbreFilsD != null) {
            sousArbreFilsD.affiche(esp + " ");
        }
    }

    /*Affichage "textuel" du contenu de l'arbre. Le séparateur des différents éléments de l'arbre utilisé
    lors de l'affichage est une chaîne vide. */
    public void affiche() {
        System.out.println("----");
        affiche("");
    }

    /* Méthodes mettant en oeuvre les 3 types de parcours en profondeur d'abord d'un arbre. Elles renvoient
    un chaîne de caractères contenant la liste des données des noeuds dans l'ordre correspondant au parcours. */
    public String parcoursInfixe() {
        String s1, s2;
        if (sousArbreFilsG != null) {
            s1 = sousArbreFilsG.parcoursInfixe();
        } else {
            s1 = "";
        }
        if (sousArbreFilsD != null) {
            s2 = sousArbreFilsD.parcoursInfixe();
        } else {
            s2 = "";
        }
        return s1 + racine.toString() + s2;
    }

    public String parcoursPostfixe() {
        String s1, s2;
        if (sousArbreFilsG != null) {
            s1 = sousArbreFilsG.parcoursPostfixe();
        } else {
            s1 = "";
        }
        if (sousArbreFilsD != null) {
            s2 = sousArbreFilsD.parcoursPostfixe();
        } else {
            s2 = "";
        }
        return s1 + s2 + racine.toString();
    }

    public String parcoursPrefixe() {
        String s1, s2;
        if (sousArbreFilsG != null) {
            s1 = sousArbreFilsG.parcoursPrefixe();
        } else {
            s1 = "";
        }
        if (sousArbreFilsD != null) {
            s2 = sousArbreFilsD.parcoursPrefixe();
        } else {
            s2 = "";
        }
        return racine.toString() + s1 + s2;
    }

    /* Même principe que pour les méthodes précédentes mais pour le parcours en largeur.
    La file que nécessite la mise en oeuvre de ce parcours est mise en oeuvre par un vecteur
    de sous-arbres binaires. */
    public String largeur() {
        Vector<T> lues = new Vector<T>(); /* pour stocker le résultat du parcours */
        Vector<ArbreBinaireBase<T>> aTraiter = new Vector<ArbreBinaireBase<T>>(); /* la file */
        ArbreBinaireBase<T> courant;
        lues.add(racine.getDonnee());
        aTraiter.add(this);
        while (aTraiter.size() != 0) {
            courant = aTraiter.get(0);
            if (courant.sousArbreFilsG != null) {
                lues.add(courant.sousArbreFilsG.getRacine().getDonnee());
                aTraiter.add(courant.sousArbreFilsG);
            }
            if (courant.sousArbreFilsD != null) {
                lues.add(courant.sousArbreFilsD.getRacine().getDonnee());
                aTraiter.add(courant.sousArbreFilsD);
            }
            aTraiter.removeElementAt(0);
        }
        return lues.toString();
    }

    /**
     * la taille d'un arbre est le nombre de noeuds de l'arbre
     */
    public int taille() {
        int tailleSAG = 0;
        int tailleSAD = 0;
        if (sousArbreFilsG != null) {
            tailleSAG = sousArbreFilsG.taille();
        }
        if (sousArbreFilsD != null) {
            tailleSAD = sousArbreFilsD.taille();
        }
        return 1 + tailleSAG + tailleSAD;
    }

    public int hauteur() {
        int hauteurSAG = -1;
        int hauteurSAD = -1;
        if (sousArbreFilsG != null) {
            hauteurSAG = sousArbreFilsG.hauteur();
        }
        if (sousArbreFilsD != null) {
            hauteurSAD = sousArbreFilsD.hauteur();
        }
        return 1 + Math.max(hauteurSAG, hauteurSAD);
    }


    public int nbFeuilles() {
        int feuillesSAG = 0;
        int feuillesSAD = 0;
        if (isFeuille()) {
            return 1;
        }
        if (sousArbreFilsG != null) {
            feuillesSAG = sousArbreFilsG.nbFeuilles();
        }
        if (sousArbreFilsD != null) {
            feuillesSAD = sousArbreFilsD.nbFeuilles();
        }
        return feuillesSAG + feuillesSAD;
    }

    private boolean isFeuille() {
        return sousArbreFilsG == null && sousArbreFilsD == null;
    }

}