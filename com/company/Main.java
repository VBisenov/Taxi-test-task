package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws NoFreeTaxiException{
        ArrayList<Taxi> taxis = new ArrayList<>();

        IDGenerator generator = new IDGenerator();

        Taxi taxi = new Taxi(generator.getId(), 4);
        Taxi babyTaxi = new BabyTaxi(generator.getId(), 4);
        Taxi secondBabyTaxi = new BabyTaxi(generator.getId(), 4);
        Taxi loryTaxi = new LoryTaxi(generator.getId(), 4, 500);

        taxis.add(taxi);
        taxis.add(secondBabyTaxi);
        taxis.add(babyTaxi);
        taxis.add(loryTaxi);


        Dispatcher dispatcher = new Dispatcher(taxis);

        dispatcher.changeBabyTaxi("Baker st. 221b");
        dispatcher.changeBabyTaxi("Privet drive 4");
        dispatcher.changeTaxi("That st. 25");
        dispatcher.changeLoryTaxi("Other st. 64", 50);

        dispatcher.changeStatusToFree(100001);
        dispatcher.changeStatusToFree(100002);
        dispatcher.changeStatusToFree(100003);
        dispatcher.changeStatusToFree(100004);

        System.out.println(dispatcher.freeBabyTaxi());
        System.out.println(dispatcher.freeLoryTaxi());
    }
}
