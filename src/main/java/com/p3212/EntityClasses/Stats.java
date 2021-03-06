package com.p3212.EntityClasses;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Represents Stats entity. Used to operate on users' statistic data.
 */
@Entity
@Table(name = "Statistics")
public class Stats {

    /**
     * Rating of a user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "stats", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private User user;

    public String getLogin() {
        return user.getLogin();
    }

    @Column(nullable = false)
    private int rating;
    /**
     * Number of fights user took part in
     */
    @Column(nullable = false)
    private int fights;
    /**
     * Number of fights user won
     */
    @Column(nullable = false)
    private int wins;
    /**
     * Number of fights user lost
     */
    @Column(nullable = false)
    private int losses;
    /**
     * Number of fights during which user died
     * Death occurs when a character died in battle against AI, but his team won
     */
    @Column(nullable = false)
    private int deaths;

    /**
     * Level
     */
    @Column(name = "lvl", nullable = false)
    private int level;

    /**
     * Experience
     */
    @Column(nullable = false)
    private int experience;

    /**
     * Number of available upgrade points
     */
    @Column(nullable = false)
    private int upgradePoints;

    public Stats() {
    }


    public Stats(int rating, int fights, int wins, int losses, int deaths, int experience, int level, int points) {
        this.deaths = deaths;
        this.fights = fights;
        this.losses = losses;
        this.rating = rating;
        this.wins = wins;
        this.experience = experience;
        this.level = level;
        this.upgradePoints = points;
    }

    /**
     * Setter
     * {@link Stats#rating}
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Setter
     * {@link Stats#fights}
     */
    public void setFights(int fights) {
        this.fights = fights;
    }

    /**
     * Setter
     * {@link Stats#wins}
     */
    public void setWins(int wins) {
        this.wins = wins;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getUpgradePoints() {
        return upgradePoints;
    }

    public void setUpgradePoints(int upgradePoints) {
        this.upgradePoints = upgradePoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /**
     * Setter
     * {@link Stats#losses}
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Setter
     * {@link Stats#deaths}
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * Getter
     * {@link Stats#rating}
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Getter
     * {@link Stats#fights}
     */
    public int getFights() {
        return this.fights;
    }

    /**
     * Getter
     * {@link Stats#wins}
     */
    public int getWins() {
        return this.wins;
    }

    /**
     * Getter
     * {@link Stats#losses}
     */
    public int getLosses() {
        return this.losses;
    }

    /**
     * Getter
     * {@link Stats#deaths}
     */
    public int getDeaths() {
        return this.deaths;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
//        return "\"{ username\": \"" + user.getLogin() +
//                "\",\n  \"rating\":" + rating +
//                ",\n  \"fights\":" + fights +
//                ",\n  \"wins\":" + wins +
//                ",\n  \"losses\":" + losses +
//                ",\n  \"deaths\":" + deaths + " }";
    }
}
