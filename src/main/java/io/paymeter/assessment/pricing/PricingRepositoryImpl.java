package io.paymeter.assessment.pricing;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
/**
 * Implementación de PricingRepository que utiliza un mapa para almacenar los datos
 */
@Component
public class PricingRepositoryImpl implements PricingRepository {
    private Map<String, Pricing> pricingMap;

    public PricingRepositoryImpl() {
        pricingMap = new HashMap<>();
        loadExamplePricings();
    }

    private void loadExamplePricings() {
        Pricing pricing1 = new Pricing("P000123", 10.0, new Discounts(15.0, 20.0, true));
        Pricing pricing2 = new Pricing("P000456", 8.0, new Discounts(12.0, 18.0, false));

        pricingMap.put(pricing1.getParkingID(), pricing1);
        pricingMap.put(pricing2.getParkingID(), pricing2);
    }

    @Override
    public Pricing get(String parkingID) {
        return pricingMap.get(parkingID);
    }
}