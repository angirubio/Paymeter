package io.paymeter.assessment.pricing;

import java.util.Objects;
/**
 * Clase que representa la información de tarifas de un estacionamiento
 */
public class Pricing {
    private String parkingID;
    private double hourlyRate;
    private Discounts discounts;

    // Constructor
    public Pricing(String parkingID, double hourlyRate, Discounts discounts) {
        this.parkingID = parkingID;
        this.hourlyRate = hourlyRate;
        this.discounts = discounts;
    }

    // Getters y Setters
    public String getParkingID() {
        return parkingID;
    }

    public void setParkingID(String parkingID) {
        this.parkingID = parkingID;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Discounts getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Discounts discounts) {
        this.discounts = discounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pricing pricing = (Pricing) o;
        return Double.compare(pricing.hourlyRate, hourlyRate) == 0 &&
                Objects.equals(parkingID, pricing.parkingID) &&
                Objects.equals(discounts, pricing.discounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingID, hourlyRate, discounts);
    }
}