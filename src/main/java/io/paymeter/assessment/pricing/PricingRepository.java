package io.paymeter.assessment.pricing;
/**
 * Interfaz que define un repositorio para obtener la información de tarifas de un estacionamiento
 */
public interface PricingRepository {
    Pricing get(String parkingID);
}