package com.RaresProject12;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class Meniu implements Map<Integer, Produs> {
    private HashMap<Integer, Produs> meniu = new HashMap<>();

    public void afisareMeniu(){
        System.out.println("Meniul =====");
        for(Produs produs: meniu.values()){
            System.out.println("Produsul:" + produs.getId() + " :" + produs.getNume() + " " + produs.getPret());
        }
    }

    public HashMap<Integer, Produs> getMeniu() {
        return meniu;
    }

    public void setMeniu(HashMap<Integer, Produs> meniu){
        this.meniu = meniu;
    }

    @Override
    public int size() {
        return meniu.size();
    }

    @Override
    public boolean isEmpty() {
        return meniu.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Produs get(Object key) {
        return this.meniu.get(key);
    }

    @Override
    public Produs put(Integer key, Produs value) {
        return meniu.put(key, value);
    }

    @Override
    public Produs remove(Object key) {
        return meniu.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Produs> m) {

    }

    @Override
    public void clear() {
        meniu.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return meniu.keySet();
    }

    @Override
    public Collection<Produs> values() {
        return meniu.values();
    }

    @Override
    public Set<Entry<Integer, Produs>> entrySet() {
        return null;
    }
}
