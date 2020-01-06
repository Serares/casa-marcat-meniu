package com.RaresProject12;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner scan = new Scanner(System.in);
        Restaurant r1 = new Restaurant("Restaurant1");
//        r1.creareMeniu();

        r1.readMeniuFile();
        r1.readComenziFile();

        boolean quit =false;
        while(!quit){
            quit = true;
            optiuniUtilizator();
            int alegere = scan.nextInt();
            switch(alegere){
                case 1:
                    r1.creareComanda(scan);
                    break;
                case 2:
                    System.out.println("Admin");
                    r1.comenziAdministrator(scan);
                    break;
                default:
                    System.out.println("End..");
                    break;
            }
        }
        scan.close();
    }

    public static void optiuniUtilizator(){
        System.out.println("Utilizator:");
        System.out.println("1. client\n" +
                "2. Admin");
    }
}
