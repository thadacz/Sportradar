package pl.thadacz.sportradar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.thadacz.sportradar.domain.Event;


public interface EventRepository extends JpaRepository<Event, String> {

}
