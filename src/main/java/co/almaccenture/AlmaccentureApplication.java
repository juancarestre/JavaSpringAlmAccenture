package co.almaccenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Clase base, contiene el main de la aplicacion he indica que se ejecutara con una aplicacion Spring
 * @author Administrator
 *
 */
@SpringBootApplication
public class AlmaccentureApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmaccentureApplication.class, args);
	}
}
