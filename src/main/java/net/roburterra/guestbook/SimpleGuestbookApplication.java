package net.roburterra.guestbook;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import net.roburterra.guestbook.dao.GuestbookMessageRepository;
import net.roburterra.guestbook.domain.GuestbookMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SimpleGuestbookApplication {

	@Autowired
	GuestbookMessageRepository guestbookMessageRepository;

	public static void main(String[] args) {

		SpringApplication.run(SimpleGuestbookApplication.class, args);
	}

	@Component
	public class Runner implements CommandLineRunner{
		@Override
		public void run(String... args) throws Exception {
			guestbookMessageRepository.findAll().stream().forEach(System.out::println);
		}
	}

}
