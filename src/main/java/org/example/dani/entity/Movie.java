package org.example.dani.entity;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
//    @Column(length = 4)
    private String year;
    private Integer categoryId;
    private Integer directorId;
    private String synopsis;

    public Movie() {
    }

    public Movie(Integer id, String name, String year, Integer categoryId, Integer directorId, String synopsis) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.categoryId = categoryId;
        this.directorId = directorId;
        this.synopsis = synopsis;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", categoryId=" + categoryId +
                ", directorId=" + directorId +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
