package io.paymeter.assessment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controlador de estado para verificar que la aplicación esté funcionando correctamente
 */
@RestController
public class HealthController {

	@GetMapping("/")
	public String index() {
		return "Everything Ok!";
	}

}
