package io.paymeter.assessment.pricing;

import java.util.Objects;
/**
 * Clase que representa los descuentos aplicables a la tarifa de estacionamiento
 */
public class Discounts {
    private double maxPricePerDay;
    private double maxPriceEvery12Hours;
    private boolean firstHourFree;

    // Constructor
    public Discounts(double maxPricePerDay, double maxPriceEvery12Hours, boolean firstHourFree) {
        this.maxPricePerDay = maxPricePerDay;
        this.maxPriceEvery12Hours = maxPriceEvery12Hours;
        this.firstHourFree = firstHourFree;
    }

    // Setters y Getters
    public double getMaxPricePerDay() {
        return maxPricePerDay;
    }

    public void setMaxPricePerDay(double maxPricePerDay) {
        this.maxPricePerDay = maxPricePerDay;
    }

    public double getMaxPriceEvery12Hours() {
        return maxPriceEvery12Hours;
    }

    public void setMaxPriceEvery12Hours(double maxPriceEvery12Hours) {
        this.maxPriceEvery12Hours = maxPriceEvery12Hours;
    }

    public boolean isFirstHourFree() {
        return firstHourFree;
    }

    public void setFirstHourFree(boolean firstHourFree) {
        this.firstHourFree = firstHourFree;
    }

    // Métodos
    public boolean hasMaxPricePerDay() {
        return maxPricePerDay > 0;
    }

    public boolean hasMaxPriceEvery12Hours() {
        return maxPriceEvery12Hours > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discounts discounts = (Discounts) o;
        return Double.compare(discounts.maxPricePerDay, maxPricePerDay) == 0 &&
                Double.compare(discounts.maxPriceEvery12Hours, maxPriceEvery12Hours) == 0 &&
                firstHourFree == discounts.firstHourFree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPricePerDay, maxPriceEvery12Hours, firstHourFree);
    }
}