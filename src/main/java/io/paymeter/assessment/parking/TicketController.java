package io.paymeter.assessment.parking;
import io.paymeter.assessment.pricing.*;
import io.paymeter.assessment.pricing.Discounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@RestController
@RequestMapping("tickets")
public class TicketController {

	private final PricingRepository pricingRepository;
	private final Clock clock;

	@Autowired
	public TicketController(PricingRepository pricingRepository, Clock clock) {
		this.pricingRepository = pricingRepository;
		this.clock = clock;
	}

	/**
	 * Método para calcular la tarifa de estacionamiento
	 */
	@PostMapping("/calculate")
	public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {

		Pricing pricing = pricingRepository.get(request.getParkingID());
		if (pricing == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Instant from = Instant.parse(request.getFrom());
		Instant to = request.getTo() != null ? Instant.parse(request.getTo()) : clock.instant();

		long  durationMinutes = ChronoUnit.MINUTES.between(from, to);
		double price = calculatePrice(pricing, durationMinutes);

		CalculationResponse response = new CalculationResponse(request.getParkingID(), request.getFrom(), request.getTo(), durationMinutes, price);
		return ResponseEntity.ok(response);
	}

	private double calculatePrice(Pricing pricing, long durationMinutes) {
		double price = 0.0;

		if (durationMinutes > 0) {
			double hourlyRate = pricing.getHourlyRate();
			Discounts discounts = pricing.getDiscounts();

			// Check discounts and set max price
			double maxPrice = 0.0;
			if (discounts != null) {
				if (discounts.hasMaxPriceEvery12Hours()) {
					maxPrice = discounts.getMaxPriceEvery12Hours() * Math.ceil(durationMinutes / (60.0 * 12));
				} else if (pricing.getDiscounts().hasMaxPricePerDay()) {
					maxPrice = discounts.getMaxPricePerDay();
				}
			}
			// Calculate price without first hour free
			price = Math.min(maxPrice, Math.ceil(durationMinutes / 60.0) * hourlyRate);

			// Apply first hour free
			if (discounts != null && discounts.isFirstHourFree()) {
				price -= hourlyRate;
			}
		}
		return price;
	}
	/**
	 * Clase que representa la solicitud para calcular la tarifa de estacionamiento
	 */
	public static class CalculationRequest {
		private String parkingID;
		private String from;
		private String to;

		// Getters y Setters
		public  String getParkingID() {
			return parkingID;
		}

		public  void setParkingID(String parkingID) {
			this.parkingID = this.parkingID;
		}

		public  String getFrom() {
			return from;
		}

		public  void setFrom(String from) {
			this.from = this.from;
		}

		public  String getTo() {
			return to;
		}

		public  void setTo(String to) {
			this.to = this.to;
		}
	}
	/**
	 * Clase que representa la respuesta del cálculo de la tarifa de estacionamiento
	 */
	public static class CalculationResponse {
		private String parkingID;
		private String from;
		private String to;
		private long duration;
		private double price;

		// Constructor
		public CalculationResponse(String parkingID, String from, String to, long duration, double price) {
			this.parkingID = parkingID;
			this.from = from;
			this.to = to;
			this.duration = duration;
			this.price = price;
		}
	}
}
