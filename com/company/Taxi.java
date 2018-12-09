package com.company;

public class Taxi implements Comparable<Taxi> {
    private long num;
    private boolean isFree;
    private int placesQuantity;

    public Taxi(long num, int placesQuantity){
        this.num = num;
        this.placesQuantity = placesQuantity;
        isFree = true;
    }

    public long getNum() {
        return num;
    }

    public int getPlacesQuantity() {
        return placesQuantity;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public int compareTo(Taxi o) {
        return (getPlacesQuantity() - o.getPlacesQuantity());
    }

    @Override
    public String toString() {
        return String.format("Taxi n%d, places quantity: %d", num, placesQuantity);
    }
}
