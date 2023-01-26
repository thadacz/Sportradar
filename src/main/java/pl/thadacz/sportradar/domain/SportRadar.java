package pl.thadacz.sportradar.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class SportRadar {

    @JsonProperty("Events")
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Events=" + events ;
    }
}
