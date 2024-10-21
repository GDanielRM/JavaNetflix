package org.example.dani.entity;

import javax.persistence.*;

@Entity
@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String countryOrigin;
    private Integer oscar;

    public Director() {
    }

    public Director(Integer id, String name, String countryOrigin, Integer oscar) {
        this.id = id;
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.oscar = oscar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public Integer getOscar() {
        return oscar;
    }

    public void setOscar(Integer oscar) {
        this.oscar = oscar;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryOrigin='" + countryOrigin + '\'' +
                ", oscar=" + oscar +
                '}';
    }
}
