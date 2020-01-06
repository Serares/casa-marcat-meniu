package com.RaresProject12;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class OperatiiFisier implements IOperatiiFisier{

    private Meniu meniu;
    private Comenzi comenzi;


    public void addMeniu(Meniu meniu){
        this.meniu = meniu;
    }

    public void addComenzi(Comenzi comenzi){
        this.comenzi = comenzi;
    }

    @Override
    public HashMap<Integer, Produs> citesteMeniuDinFisier(String numeFisier) throws IOException {
        HashMap<Integer, Produs> tempMeniu = new HashMap<>();
        try(Scanner fisier = new Scanner(new BufferedReader(new FileReader(numeFisier))) ){
            while(fisier.hasNextLine()){
                String input = fisier.nextLine();
                String[] data = input.split(",");
//                System.out.println("Produs==="+ data[0]+ " "+ data[1]+ " "+data[2]);
                int id = Integer.parseInt(data[0]);
                String nume = data[1];
                float pret =Float.parseFloat(data[2]);

                Produs p = new Produs(id,pret,nume);
                tempMeniu.put(id, p);
            }
            if(tempMeniu.size() < 1) return null;
            else return tempMeniu;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comanda> citesteComenziDinFisier(String numeFisier) throws IOException{
        List<Comanda> tempComenzi = new ArrayList<>();
        try(Scanner fisier = new Scanner(new FileReader(numeFisier))){
            while(fisier.hasNextLine()){

                String input = fisier.nextLine();
                String[] data = input.split(",");
                String dataComanda = data[0];
                // primele doua data or sa fie data si valoarea mereu
                Float valoare = Float.parseFloat(data[1]);
                Comanda tempCom = new Comanda(dataComanda, 0);
                // trebuie sa fac o bucla ca sa preiau produsele din comanda
                for(int i=2;i< Array.getLength(data); i++){
                    // data[i] va fi tot produsul in string trebuie sa extrag stringurile si sa le fac tipuri de date relevante
                    // 2:Supa:3.2
                    String[] stringProdusToArray = data[i].split(":");
                    int idProdus = Integer.parseInt(stringProdusToArray[0]);
                    String numeProdus = stringProdusToArray[1];
                    float pretProdus = Float.parseFloat(stringProdusToArray[2]);

                    Produs tempProd = new Produs(idProdus,pretProdus,numeProdus);
                    tempCom.adaugaProdus(tempProd);
                }
                // trebuie sa creez comanda din stringul din comenzi.txt ideea este ca trebuie sa fac arrayul de produse un Vector
                tempComenzi.add(tempCom);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        if(tempComenzi.size() > 0) return tempComenzi;
        else return null;
    }

    @Override
    public void scrieObiecteInFisier(String numeFisier) throws IOException {
        int date = numeFisier.equals("meniu.txt") ? 1 : -1;
        // gasesc fisierul din care trebuie sa scriu datele;
        try(BufferedWriter file = new BufferedWriter(new FileWriter(numeFisier)) ){
            if(date==1){
                // parcurge meniu
                for (Object o : this.meniu.values()){
                    Produs p = (Produs) o;
                    file.write(p.getId() + "," + p.getNume()+","+p.getPret()+"\n");
                }
            }else{
                // parcurge comenzi
                for (Object o : this.comenzi.getComenzi()){
                    // TO DO suprascrie mereu fisierul trebuie sa fac o copie dupa comenzile din restaurant si sa le trimit aici
                    Comanda c = (Comanda) o;
                    file.write(c.getData()+","+c.getValoare()+","+c.getProduseToString()+ "\n");
                }
            }
        }
    }

    // nu este in iterfata
    public void scrieFisierFrecventaProduse(HashMap<String,Integer> map, String numeFisier) throws IOException {

        try(BufferedWriter file = new BufferedWriter(new FileWriter(numeFisier))){
            if(map.size()>0){
                for (Map.Entry<String,Integer> c: map.entrySet()
                     ) {
                    file.write(c.getKey()+ " "+c.getValue()+"\n");
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void scrieFisierValoareComenziPerZile(HashMap<String, Float> map, String numeFisier){

        try(BufferedWriter file = new BufferedWriter(new FileWriter(numeFisier))){
            if(map.size()>0){
                for (Map.Entry<String, Float> c: map.entrySet()
                ) {
                    file.write("Ziua: "+c.getKey()+ " "+c.getValue()+"$"+"\n");
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
