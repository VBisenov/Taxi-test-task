package com.company;

public class LoryTaxi extends Taxi {
    private int maxWeight;

    public LoryTaxi(long num, int placesQuantity) {
        super(num, placesQuantity);
    }

    public LoryTaxi(long num, int placesQuantity, int maxWeight){
        super(num, placesQuantity);
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    @Override
    public int compareTo(Taxi o) {
        return (getMaxWeight() - ((LoryTaxi) o).getMaxWeight());
    }

    @Override
    public String toString() {
        return String.format("Taxi n%d, places quantity: %d, max weight: %d", getNum(), getPlacesQuantity(), maxWeight);
    }
}
