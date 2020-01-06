package com.RaresProject12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Comenzi implements List<Comanda> {

    private List<Comanda> comenzi = new ArrayList<>();

    public void adaugareComanda(Comanda c){
        this.comenzi.add(c);
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public final Comparator<Comanda> SORTARE_VALOARE = new Comparator<Comanda>(){
        @Override
        public int compare(Comanda o1, Comanda o2) {
            if(o1.getValoare() < o2.getValoare()){
                return -1;
            }else if(o1.getValoare() > o2.getValoare()){
                return 1;
            }else {
                return 0;
            }
        }
    };

    private void printList(List<Comanda> l){
        for(Comanda c: l){
            System.out.println("Comanda"+ c.toString());
        }
    }

    public void sortareComenzi(){
        List<Comanda> listCopy = new ArrayList<>(this.comenzi);
        Collections.sort(listCopy, SORTARE_VALOARE);
        System.out.println("Comezi sortate====");
        this.printList(listCopy);
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    public void afisareComenzi(){
        System.out.println("Comenzile =====");
        System.out.println(this.comenzi.size());
        for(Comanda c: this.comenzi){
            System.out.println("Comanda:" + c.getData() + " "+ c.getValoare() + " " + c.getProduseToString());
        }
    }

    @Override
    public int size() {
        return this.comenzi.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Comanda> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Comanda comanda) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Comanda> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Comanda> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Comanda get(int index) {
        return null;
    }

    @Override
    public Comanda set(int index, Comanda element) {
        return null;
    }

    @Override
    public void add(int index, Comanda element) {

    }

    @Override
    public Comanda remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Comanda> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Comanda> listIterator(int index) {
        return null;
    }

    @Override
    public List<Comanda> subList(int fromIndex, int toIndex) {
        return null;
    }
}
