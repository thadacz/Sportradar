package pl.thadacz.sportradar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thadacz.sportradar.domain.Event;
import pl.thadacz.sportradar.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/list")
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @GetMapping("/probable")
    public List<Event> mostProbable(){
        return eventService.mostProbable();
    }


}
