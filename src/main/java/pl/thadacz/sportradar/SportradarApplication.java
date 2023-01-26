package pl.thadacz.sportradar;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.thadacz.sportradar.domain.Event;
import pl.thadacz.sportradar.domain.SportRadar;
import pl.thadacz.sportradar.service.EventService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class SportradarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportradarApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(EventService eventService) {
        return args -> {
            // Read json and write to database
            ObjectMapper mapper = new ObjectMapper();
            try {
                SportRadar sportRadar = mapper.readValue(Paths.get("src/main/resources/static/BE_data.json").toFile(), SportRadar.class);
                List<Event> events = sportRadar.getEvents();
                eventService.save(events);
                System.out.println("Events saved to database.");
            } catch (IOException e) {
                System.out.println("Unable to save events: " + e.getMessage());
            }
        };
    }

}
