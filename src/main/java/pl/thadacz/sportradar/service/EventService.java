package pl.thadacz.sportradar.service;

import org.springframework.stereotype.Service;
import pl.thadacz.sportradar.domain.Event;
import pl.thadacz.sportradar.domain.EventProbability;
import pl.thadacz.sportradar.repository.EventRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> events() {
        return eventRepository.findAll();
    }

/*    public Event save(Event event) {
        return eventRepository.save(event);
    }*/

    public void save(List<Event> events) {
        eventRepository.saveAll(events);
    }

    public List<Event> mostProbable(int n) {

        List<Event> mostProbableEvents = eventRepository.findAll();
        List<EventProbability> probabilities = new ArrayList<>();

        for (Event e : mostProbableEvents) {
            List<Double> theHighestValue = new ArrayList<>();
            theHighestValue.add(e.getProbability_away_team_winner());
            theHighestValue.add(e.getProbability_home_team_winner());
            theHighestValue.add(e.getProbability_draw());
            double x = Collections.max(theHighestValue);
            probabilities.add(new EventProbability(e, x));
        }
        if (n < mostProbableEvents.size()) {
            probabilities = probabilities
                    .stream()
                    .sorted(Comparator.comparingDouble(EventProbability::getProbability)
                            .reversed())
                    .limit(n)
                    .collect(Collectors.toList());

            for (EventProbability e : probabilities) {
                System.out.print("Start date: " + e.getEvent().getStart_date().split("\\+")[0].replace("T", " ") + "," + "\n" +
                        e.getEvent().getCompetitors().get(0).toString() + " vs " + e.getEvent().getCompetitors().get(1).toString() + "," + "\n" +
                        "Venue " + Optional.of(e.getEvent().getVenue().getName()) + "," + "\n" +
                        "Highest probable result: "
                );
                if (e.getProbability() == e.getEvent().getProbability_away_team_winner()) {
                    System.out.println("AWAY_TEAM_WIN (" + e.getProbability() + ')');
                } else if (e.getProbability() == e.getEvent().getProbability_draw()) {
                    System.out.println("DRAW (" + e.getProbability() + ')');
                } else if (e.getProbability() == e.getEvent().getProbability_home_team_winner()) {
                    System.out.println("HOME_TEAM_WIN (" + e.getProbability() + ')');
                }
            }
            List<Event> result = new ArrayList<>();
            for (EventProbability probability : probabilities) {
                result.add(probability.getEvent());
            }
            return result;
        } else {
            throw new IllegalArgumentException("The value too large! ");
        }
    }

    public Set<String> getClubs() {
        List<Event> competitors = eventRepository.findAll();
        Set<String> clubs = new HashSet<>();
        for (Event competitor : competitors) {
            clubs.add(String.valueOf(competitor.getCompetitors().get(0)));
            clubs.add(String.valueOf(competitor.getCompetitors().get(1)));
        }
        return clubs;
    }

    public List<String> getClubsAsc() {
        List<String> clubsAsc = new ArrayList<>(getClubs());
        return clubsAsc
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getCompetitors() {
        List<Event> events = eventRepository.findAll();
        List<String> names = new ArrayList<>();
        for (Event event : events ) {
            names.add(event.getCompetition_name());
        }
        Collections.sort(names);
        return names;
    }
}
