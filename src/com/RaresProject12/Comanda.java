package com.RaresProject12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Comanda implements Comparable<Comanda>{
    private String data;
    private Vector<Produs> produse;
    private float valoare;


    public Comanda(String data,float valoare) {
        this.data = data;
        this.valoare = valoare;
        this.produse = new Vector<>();
    }

    public Comanda() {
        this.data = this.getDayToString(0);
        this.produse = new Vector<>();
        this.valoare = 0;
    }

    public Vector<Produs> getProduse() {
        return produse;
    }

    public StringBuilder getProduseToString(){
        StringBuilder strProducts= new StringBuilder();
        if(this.produse.size() > 0){
         for(Produs p: this.produse){
            strProducts.append(p.getId()+":"+p.getNume()+":"+p.getPret()+",");
         }
        }
        return strProducts;
    }

    public int marimeComanda(){
        return this.produse.size();
    }

    private Date dayObject(int numarZileInUrma) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -numarZileInUrma);
        return cal.getTime();
    };

    private String getDayToString(int numarZileInUrma) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/hh/mm");
        return dateFormat.format(dayObject(numarZileInUrma));
    }

    public float getValoare() {
        return valoare;
    }

    public String getData(){
        return this.data;
    }

    public void afisareProduse(){
//        System.out.println("Produsele din comanda "+ " - "+ this.getData());
        for(Produs p : this.produse){
            System.out.println("-"+p.getPret() +" "+ p.getNume()+" "+ p.getCantitate());
        }
    }

    public void adaugaProdus(Produs p){
        if(p == null){
            System.out.println("Nu exista produsul");
        }else{
        this.produse.add(p);
        this.calculareValoareComanda(p);
//        System.out.println("Produs adaugat in comanda"+ this.getData());
        }
    }

    private void calculareValoareComanda(Produs p){
        this.valoare += p.getPret();
    }

    @Override
    public String toString() {
        return "Comanda{" +
                ", data='" + data + '\'' +
                ", valoare=" + valoare +
                '}';
    }

    @Override
    public int compareTo(Comanda o) {
        if(this.valoare < o.getValoare()){
            return -1;
        }else if(this.valoare > o.getValoare()){
            return 1;
        }else {
            return 0;
        }
    }
}
