package pl.thadacz.sportradar.controller;

import org.springframework.web.bind.annotation.*;
import pl.thadacz.sportradar.domain.Event;
import pl.thadacz.sportradar.service.EventService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public List<Event> getEvents() {
        return eventService.events();
    }

    @GetMapping("/probable/{n}")
    public List<Event> mostProbable(@PathVariable Integer n) {
        return eventService.mostProbable(n);
    }

    @GetMapping("/clubs")
    public Set<String> clubs() {
        return eventService.getClubs();
    }

    @GetMapping("/clubsAsc")
    public List<String> clubsASC() {
        return eventService.getClubsAsc();
    }

    @GetMapping("/matches")
    @ResponseBody
    public String getMatches(@RequestParam String id) {
        return "ID: " + id;
    }

    @GetMapping("/competitors")
    public List<String> getCompetitors(){
        return eventService.getCompetitors();
    }
}
