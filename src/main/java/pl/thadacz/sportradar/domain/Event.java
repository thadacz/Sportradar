package pl.thadacz.sportradar.domain;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;


@Entity
public class Event {
    @Id
    private String sport_event_id;
    private String start_date;
    private String sport_name;
    private String competition_name;
    private String competition_id;
    private String season_name;
    @ElementCollection
    private List<Competitor> competitors;
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "id_venue", column = @Column(name = "id", insertable = false, updatable = false)),
            @AttributeOverride(name = "name", column = @Column(name = "name_venue")),
            @AttributeOverride(name = "capacity", column = @Column(name = "capacity")),
            @AttributeOverride(name = "city_name", column = @Column(name = "city_name")),
            @AttributeOverride(name = "country_name", column = @Column(name = "name")),
            @AttributeOverride(name = "map_coordinates", column = @Column(name = "map_coordinates")),
            @AttributeOverride(name = "country_code", column = @Column(name = "country_code"))
    })
    private Venue venue;
    private double probability_home_team_winner;
    private double probability_draw;
    private double probability_away_team_winner;


    public String getSport_event_id() {
        return sport_event_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getSport_name() {
        return sport_name;
    }

    public String getCompetition_name() {
        return competition_name;
    }

    public String getCompetition_id() {
        return competition_id;
    }

    public String getSeason_name() {
        return season_name;
    }

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public Venue getVenue() {
        return venue;
    }

    public double getProbability_home_team_winner() {
        return probability_home_team_winner;
    }

    public double getProbability_draw() {
        return probability_draw;
    }

    public double getProbability_away_team_winner() {
        return probability_away_team_winner;
    }

    @Override
    public String toString() {
        return "Events{" +
                "sport_event_id='" + sport_event_id + '\'' +
                ", start_date='" + start_date + '\'' +
                ", sport_name='" + sport_name + '\'' +
                ", competition_name='" + competition_name + '\'' +
                ", competition_id='" + competition_id + '\'' +
                ", season_name='" + season_name + '\'' +
                ", competitors=" + competitors +
                ", venue=" + venue +
                ", probability_home_team_winner='" + probability_home_team_winner + '\'' +
                ", probability_draw='" + probability_draw + '\'' +
                ", probability_away_team_winner='" + probability_away_team_winner + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return sport_event_id != null && Objects.equals(sport_event_id, event.sport_event_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
