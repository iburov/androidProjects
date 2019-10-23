package com.example.carwash;

public class WashPackage {
    static final int EXT = 5;
    static final int FULL = 10;

    private int numberOfWashes;
    private double pricePerWash;

    public void setNumberOfWashes(int numberOfWashes) {
        this.numberOfWashes = numberOfWashes;
    }

    public void setPricePerWash(double pricePerWash) {
        this.pricePerWash = pricePerWash;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        double discount = 0;

        if(numberOfWashes >= 12) {
            discount = 0.25;
        }

        totalPrice = numberOfWashes * pricePerWash;
        totalPrice = totalPrice - (totalPrice*discount);

        return totalPrice;
    }
}
