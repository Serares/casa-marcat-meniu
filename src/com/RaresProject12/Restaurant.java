package com.RaresProject12;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.*;

public class Restaurant {

    private String nume;
    private Meniu meniu = new Meniu();
    private Comenzi comenzi = new Comenzi();

    public Restaurant(String nume) {
        this.nume = nume;
    }



    public void creareComanda(Scanner scan) throws IOException {
        // adaugare produse la comanda
//        afisareMeniu();
        System.out.println("0 pentru iesi");
        boolean quit = false;
        //iau produsele si le adaug intr-o comanda:
        Comanda comanda = new Comanda();
        while(!quit){
            quit = true;
            afisareMeniu();
            System.out.println("0 pentru a iesi");
            System.out.println("12 pentru afisare comanda actuala");
            int optiuneDinMeniu = scan.nextInt();
            if(optiuneDinMeniu == 0) {quit=true; break;};
            // afisare comanda actuala si preia bucla;
            if(optiuneDinMeniu == 12){
                quit = false;
                System.out.println("Comanda ta este: "+comanda.getProduseToString() + ": " + comanda.getValoare()+"$");
            }
            else{
                Produs tempProdus = this.meniu.get(optiuneDinMeniu);
                if(tempProdus != null){
                    scan.nextLine();
                    System.out.println("Esti sigur ca vrei DA/NU: " + tempProdus.getNume() +" "+ tempProdus.getPret());
                    String alegere  = scan.nextLine();
                    System.out.println(tempProdus.getNume());
                    if(alegere.equalsIgnoreCase("DA")){comanda.adaugaProdus(tempProdus);};
                    quit= false;

                }else{
                    quit=false;
                }
            }
        }
        // daca sunt produse in comanda
        if(comanda.marimeComanda() > 0){
            this.adaugaComanda(comanda);
        }
    }

    public void adaugaComanda(Comanda c) throws IOException {
        this.comenzi.adaugareComanda(c);
        OperatiiFisier opfisier = new OperatiiFisier();
        opfisier.addComenzi(this.comenzi);
        opfisier.scrieObiecteInFisier("comenzi.txt");

    }

    public void readComenziFile() throws IOException {
        OperatiiFisier opfisier = new OperatiiFisier();
        List<Comanda> tempComenzi = opfisier.citesteComenziDinFisier("comenzi.txt");
        if(tempComenzi != null){
            this.comenzi.setComenzi(tempComenzi);
//            this.comenzi.afisareComenzi();
        }else {
            // daca nu exista intrari in comenzi.txt atunci creez datele ..
//            this.creareComenzi();
        }
    }

    public void creareMeniu() throws IOException {
        System.out.println("Creare meniu..");
        HashMap<Float ,String> produse = new HashMap<Float, String>();
        int id =1;
        // trebuia sa fie invers keia sa fie Produsul iar pretul sa fie valoarea pentru ca nu pot sa exista chei dublicate..
        produse.put(3.2f ,"Supa");
        produse.put(3.9f,"Burger");
        produse.put(1.1f,"Cartofi");
        produse.put(2.7f,"Apa");
        produse.put(2.5f, "Legume Cuptor");
        produse.put(0.4f ,"Inghetata");
        produse.put(0.6f,"Papanasi");
        produse.put(8.1f,"Antricot");
        produse.put(0.7f,"Cozonac");
        produse.put(0.5f, "Bere");

        for(Float p : produse.keySet()){
            meniu.put(id, new Produs(id, p, produse.get(p), 0));
            id++;
        }

        OperatiiFisier opfisier = new OperatiiFisier();
        opfisier.addMeniu(this.meniu);
        opfisier.scrieObiecteInFisier("meniu.txt");
    }

    public void afisareMeniu() throws IOException {
        if(this.meniu.size() > 0){
            this.meniu.afisareMeniu();
        }else{
            System.out.println("Meniul nu exista");
            this.creareMeniu();
        }
    }

    public void readMeniuFile() throws IOException {
        OperatiiFisier opfisier = new OperatiiFisier();
        HashMap<Integer, Produs> tempMeniu;
        try{
            tempMeniu = opfisier.citesteMeniuDinFisier("meniu.txt");
            if(tempMeniu != null){
                this.meniu.setMeniu(tempMeniu);
            }
        }catch(NumberFormatException e){
            // in cazul in care nu este nimic scris in fisierul meniu.txt creez un meniu
            e.printStackTrace();
            this.creareMeniu();
        }
    }

    // optiuni pentru cazul in care alegi sa fi administrator sa returnez cea mai mare valoare a unei comenzi
    // sortare comenzi dupa valoare..
    // POATE optiunea de a modifica meniul..
    // valoarea comenzilor zilnice, cele mai vandute produse..
    public void comenziAdministrator(Scanner scan) throws IOException {
        //
        this.optiuniAdministrator();

        boolean flag = true;
        while(flag){
            flag=false;
            int optiune = scan.nextInt();
            switch (optiune){
                case 1:
                    this.optiuniAdministrator();
                    flag = true;
                    break;
                case 2:
                    System.out.println("Cele mai vandute produse..");
                    this.produseleCeleMaiVandute();
                    flag =true;
                    break;
                case 3:
                    System.out.println("Valoarea pe zile====");
                    this.valoareaComenzilorPeZile();
                    flag = true;
                    break;
                case 4:
                    System.out.println("Comenzi sortate crescator dupa valoare===");
                    this.comenzi.sortareComenzi();
                    break;
                case 0:
                    System.out.println("End..");
                    break;
            }
        }

    }

    public void produseleCeleMaiVandute() throws IOException {
        // trebuie sa citesc comenzile din txt si sa adaug toate produsele din comenzi intr-un vector si sa le caut pe cele mai frecvente
        // le adaug pe toate intr-un arraylist si parcurg arrayul apoi adaug intr-un hashmap
        this.readComenziFile();
        // voi avea comenzile citite si trebuie sa extrag produsele..
        // aici adaug produsele si incrementez valoarea cand se mai geseste un produs in hashmap
        // Key = Numele Produsului ;
        // Valoarea = Numarul Frecventei produsului
        HashMap<String, Integer> frecventaProduse = new HashMap<>();

        ArrayList<String> toateProduseleDinComenzi = new ArrayList<>();
        // parcurg comenzile citite din fisier
        if(this.comenzi.size() >0){
            for(Comanda c: this.comenzi.getComenzi()){
                //pentru fiecare comanda parcurg produsele si sunt adaugate in lista cu toate produsele
                for(Produs p: c.getProduse()){
                    toateProduseleDinComenzi.add(p.getNume());
                }
            }
        }

        // aici se parcurge lista cu toate produsele si se adauga in lista cu frecventa produselor aparute, in cazul in care se gaseste un produs:
        // i se incrementeaza valoarea.
        for(int i=0; i<toateProduseleDinComenzi.size();i++){
            if(frecventaProduse.containsKey(toateProduseleDinComenzi.get(i))){
                frecventaProduse.put(toateProduseleDinComenzi.get(i), frecventaProduse.get( toateProduseleDinComenzi.get(i) )+1);
            }else {
                frecventaProduse.put(toateProduseleDinComenzi.get(i), 1);
            }
        }
        // sortare
        // creez o lista cu elementele din hashmap frecventaproduse si sortez elementele in lista
        List<Map.Entry<String,Integer>> lista = new LinkedList<>(frecventaProduse.entrySet());
        Collections.sort(lista, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        // adaug datele din lista intr-un nou hashmap ca sa le trimit la filewriter;
        HashMap<String,Integer> mapForWrite = new LinkedHashMap<>();
        for(Map.Entry<String,Integer> aa : lista){
            mapForWrite.put(aa.getKey(), aa.getValue());
        };
        System.out.println("Frecventa produse:"+mapForWrite.toString());
        OperatiiFisier opfisier = new OperatiiFisier();
        opfisier.scrieFisierFrecventaProduse(mapForWrite, "produsefrecvente.txt");

    }

    public void valoareaComenzilorPeZile() throws IOException {

        // trebuie sa parcurg comenzile sa le iau pe toate intr-un List
        // parcurc lista si le adaug intr-un hashmap<String data, Float valoarea>
        // daca se regaseste aceeasi DD/MM in hashmap se incrementeaza valoarea in hashmap altfel adaug o intrare noua cu valoarea
        //
        this.readComenziFile();
        // Data , Valoare
        HashMap<String, Float> comenzi = new HashMap<>();
        List<Comanda> listaComenzi = new ArrayList<>(this.comenzi.getComenzi());

        // adaug toate comenzile in comenzi mapate pe data cu valoarea comenzii.
        for(int i=0;i<listaComenzi.size();i++){
            String[] dataSplit = listaComenzi.get(i).getData().split("/");
            String ziua = dataSplit[0];
            String luna = dataSplit[1];
            if(!comenzi.containsKey(ziua+"/"+luna)){
                comenzi.put(ziua +"/"+luna, listaComenzi.get(i).getValoare());
            }else{
                comenzi.put(ziua +"/"+luna, comenzi.get(ziua +"/"+luna) + listaComenzi.get(i).getValoare() );
            }

        }
        OperatiiFisier opfisier = new OperatiiFisier();
        opfisier.scrieFisierValoareComenziPerZile(comenzi, "comenziperzile.txt");
        System.out.println("Lista comenzi"+comenzi.toString());
    }



    // folosit in main
    public void optiuniAdministrator(){
        System.out.println("1. Afiseaza optiunile \n" +
                "2. Cele mai vandute produse \n"+
                "3. Valoarea comenzilor / zi \n"+
                "4. Sortare comenzi crescator \n"+
                "0. Iesi \n");
    }

}
