package pl.thadacz.sportradar.domain;

import jakarta.persistence.*;

@Embeddable
public class Venue {
    private String id;
    private String name;
    private int capacity;
    private String city_name;
    private String country_name;
    private String map_coordinates;
    private String country_code;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getMap_coordinates() {
        return map_coordinates;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public void setMap_coordinates(String map_coordinates) {
        this.map_coordinates = map_coordinates;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", city_name='" + city_name + '\'' +
                ", country_name='" + country_name + '\'' +
                ", map_coordinates='" + map_coordinates + '\'' +
                ", country_code='" + country_code + '\'' +
                '}';
    }
}
