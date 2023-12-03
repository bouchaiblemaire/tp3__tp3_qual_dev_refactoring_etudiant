package fr.einfolearning.qualdev;

import java.util.Objects;

public class Noeud<T> {
    private T valeur;

    private int id;

    private static int NOMBRE_NOEUDS;

    public Noeud(T valeur) {
        this.valeur = valeur;
        Noeud.NOMBRE_NOEUDS++;
        this.id = Noeud.NOMBRE_NOEUDS;
    }


    public int getId() { return this.id; }


    public T getDonnee() { return this.valeur;  }

    public void setId(int id) {this.id = id; }


    public void setValeur(T valeur) {
        this.valeur = valeur;
    }

    public static void resetCompteurInstance(){
        Noeud.NOMBRE_NOEUDS=0;
    }

    public String toString() { return String.valueOf(this.valeur); }


    public String toStringId(){
        return "("+this.getId()+":"+this.getDonnee()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Noeud<T> noeud = (Noeud<T>) o;
        return id == noeud.id && this.valeur.equals(noeud.getDonnee());
    }

    public boolean equalsDonnee(Noeud<T> n){ return this.getDonnee().equals(n.getDonnee()); }
    @Override
    public int hashCode() {
        return Objects.hash(valeur, id);
    }

}
