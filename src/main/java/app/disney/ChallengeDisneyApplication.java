package app.disney;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Transactional
public class ChallengeDisneyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeDisneyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
