package com.company;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Dispatcher {
    private ArrayList<Taxi> taxis;

    public Dispatcher (ArrayList<Taxi> taxis){
        this.taxis = new ArrayList<>();
        this.taxis.addAll(taxis);
    }

    public void add(Taxi taxi){
        this.taxis.add(taxi);
    }

    public void remove(long num){
        for (Taxi taxi: taxis){
            if (num == taxi.getNum()){
                taxis.remove(taxi);
            }
        }
    }

    public boolean changeBabyTaxi(String address) throws NoFreeTaxiException{
        if (!isExistFreeTaxi(TaxiType.BABY_TAXI)){
            throw new NoFreeTaxiException("No free baby taxi exist");
        }

        for (Taxi taxi: taxis){
            if (taxi instanceof BabyTaxi){
                if (taxi.isFree()) {
                    taxi.setFree(false);
                    record(taxi, address);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean changeTaxi(String address) throws NoFreeTaxiException{
        if (!isExistFreeTaxi(TaxiType.TAXI)){
            throw new NoFreeTaxiException("No free taxi exception");
        }

        for (Taxi taxi: taxis){
            if (!(taxi instanceof BabyTaxi) &&  !(taxi instanceof LoryTaxi)){
                if (taxi.isFree()){
                    taxi.setFree(false);
                    record(taxi, address);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean changeLoryTaxi(String address, int weight) throws NoFreeTaxiException {
        if (!isExistFreeTaxi(TaxiType.LORY_TAXI)){
            throw new NoFreeTaxiException("No free lory taxi exist");
        }

        for (Taxi taxi: taxis){
            if (taxi instanceof LoryTaxi){
                if (taxi.isFree()){
                    if (((LoryTaxi) taxi).getMaxWeight() > weight){
                        taxi.setFree(false);
                        record(taxi, address);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public ArrayList<LoryTaxi> freeLoryTaxi() {
        ArrayList<LoryTaxi> freeLoryTaxi = new ArrayList<>();
        for (Taxi taxi: taxis){
            if (taxi instanceof LoryTaxi && taxi.isFree()){
                freeLoryTaxi.add(((LoryTaxi) taxi));
            }
        }

        //прочесть наконец-то че такое лямда
        freeLoryTaxi.sort(new Comparator<LoryTaxi>() {
            @Override
            public int compare(LoryTaxi o1, LoryTaxi o2) {
                return (o1.getMaxWeight() - o2.getMaxWeight());
            }
        });
        return freeLoryTaxi;
    }

    public ArrayList<Taxi> freeTaxi(){
        ArrayList<Taxi> freeTaxi = new ArrayList<>();
        for(Taxi taxi: taxis){
            if (!(taxi instanceof LoryTaxi) && !(taxi instanceof BabyTaxi) && taxi.isFree()){
                freeTaxi.add(taxi);
            }
        }

        freeTaxi.sort(new Comparator<Taxi>() {
            @Override
            public int compare(Taxi o1, Taxi o2) {
                return (o1.getPlacesQuantity() - o2.getPlacesQuantity());
            }
        });
        return freeTaxi;
    }

    public ArrayList<BabyTaxi> freeBabyTaxi(){
        ArrayList<BabyTaxi> freeBabyTaxi = new ArrayList<>();
        for(Taxi taxi: taxis){
            if (taxi instanceof BabyTaxi && taxi.isFree()){
                freeBabyTaxi.add(((BabyTaxi) taxi));
            }
        }

        freeBabyTaxi.sort(new Comparator<BabyTaxi>() {
            @Override
            public int compare(BabyTaxi o1, BabyTaxi o2) {
                return (o1.getPlacesQuantity() - o2.getPlacesQuantity());
            }
        });
        return freeBabyTaxi;
    }


    public void changeStatusToFree(long numberOfTaxi) {
        for (Taxi taxi: taxis){
            if (taxi.getNum() == numberOfTaxi){
                taxi.setFree(true);
            }
        }
    }


    public boolean isExistFreeTaxi(TaxiType type) {
        for (Taxi taxi: taxis){
            if (type == TaxiType.BABY_TAXI){
                if (taxi instanceof BabyTaxi){
                    if (taxi.isFree()){
                        return true;
                    }
                }
            } else if (type == TaxiType.LORY_TAXI){
                if (taxi instanceof LoryTaxi){
                    if (taxi.isFree()){
                        return true;
                    }
                }
            } else if (type == TaxiType.TAXI){
                if (taxi.isFree()){
                    return true;
                }
            }
        }
        return false;
    }

    private void record(Taxi taxi, String address){
        File file = new File("C:\\Java\\Output\\Taxi.txt");

        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))){
            out.println(LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"
                    +LocalDateTime.now().getSecond()+" "+taxi.getNum()+" "+address);
        } catch (IOException ex){
            ex.getMessage();
        }
    }


}
