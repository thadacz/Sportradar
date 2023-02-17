package pl.thadacz.sportradar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.thadacz.sportradar.domain.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
}
