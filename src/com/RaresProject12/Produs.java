package com.RaresProject12;

import java.util.Comparator;

public class Produs implements Comparable<Produs> {
    private int Id;
    private float pret;
    private String nume;
    private int cantitate;

    public Produs(int id, float pret, String nume, int cantitate) {
        this.Id = id;
        this.pret = pret;
        this.nume = nume;
        this.cantitate = cantitate;
        if(cantitate > 1){
            this.pret += pret;
        }
    }

    public Produs(int id, float pret, String nume){
        this.Id = id;
        this.pret = pret;
        this.nume = nume;
        this.cantitate =0;
    }

    public int getId() {
        return Id;
    }

    public float getPret() {
        return pret;
    }

    public String getNume() {
        return nume;
    }

    public int getCantitate() {
        return cantitate;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "Id=" + Id +
                ", pret=" + pret +
                ", nume='" + nume + '\'' +
                ", cantitate=" + cantitate +
                '}';
    }

    @Override
    public int compareTo(Produs o) {
        return Float.compare(this.pret, o.getPret());
    }
}
