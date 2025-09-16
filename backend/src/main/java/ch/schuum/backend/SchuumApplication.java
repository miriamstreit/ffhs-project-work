package ch.schuum.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SchuumApplication ist der Startpunkt der Web-Applikation
 *
 * @author Miriam Streit
 *
 */
@SpringBootApplication
public class SchuumApplication {

	/**
	 * <p>Eintrittspunkt der Applikation</p>
	 * @param args Kommandozeilenargumente
	 * @author Miriam Streit
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(SchuumApplication.class, args);
	}

}
