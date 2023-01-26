package pl.thadacz.sportradar.domain;

public class EventProbability {
    private Event event;
    private double probability;

    public EventProbability(Event event, double probability) {
        this.event = event;
        this.probability = probability;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "EventProbability{" +
                "event=" + event +
                ", probability=" + probability +
                '}';
    }
}
