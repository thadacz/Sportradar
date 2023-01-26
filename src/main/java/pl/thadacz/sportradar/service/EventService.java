package pl.thadacz.sportradar.service;

import org.springframework.stereotype.Service;
import pl.thadacz.sportradar.domain.Event;
import pl.thadacz.sportradar.domain.EventProbability;
import pl.thadacz.sportradar.repository.EventRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {


    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents (){
        return eventRepository.findAll();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void save(List<Event> events) {
        eventRepository.saveAll(events);
    }

    public List<Event> mostProbable(){
        List<Event> mostProbableEvents= eventRepository.findAll();
        List<EventProbability> probabilities = new ArrayList<>();
        for (Event e: mostProbableEvents) {
            List<Double> theHighestValue = new ArrayList<>();
            theHighestValue.add(e.getProbability_away_team_winner());
            theHighestValue.add(e.getProbability_home_team_winner());
            theHighestValue.add(e.getProbability_draw());
            double x = Collections.max(theHighestValue);
            probabilities.add(new EventProbability(e,x));
        }
        probabilities=probabilities.stream().sorted(Comparator.comparingDouble(EventProbability::getProbability).reversed()).limit(10).collect(Collectors.toList());
        for (EventProbability e : probabilities){
            System.out.print("Start date: "+ e.getEvent().getStart_date() +"," + "\n"+
            e.getEvent().getCompetitors().get(0).toString() +" vs " +e.getEvent().getCompetitors().get(1).toString()+"," +"\n"+
                    "Venue " +e.getEvent().getVenue().getName()+"," +"\n"+
                    "Highest probable result: "
            );
            if (e.getProbability()==e.getEvent().getProbability_away_team_winner()){
                System.out.println("AWAY_TEAM_WIN ("+e.getProbability()+')');
            } else if (e.getProbability()==e.getEvent().getProbability_draw()) {
                System.out.println("DRAW ("+e.getProbability()+')');
            }else if (e.getProbability()==e.getEvent().getProbability_home_team_winner()){
                System.out.println("HOME_TEAM_WIN ("+e.getProbability()+')');
            }
        }
        List<Event> result = new ArrayList<>();
        for (EventProbability probability : probabilities) {
            result.add(probability.getEvent());
        }
        return result;
    }
}
