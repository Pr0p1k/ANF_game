package com.p3212.EntityClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * Represents Bidju entity
 * Used to operate on AI bosses data
 */
@Entity
@Table(name = "Bidju")
public class Boss extends Creature implements Serializable {


    /**
     * id of the boss
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Name of the boss
     */
    @Column(length=30, nullable=false)
    private String name;

    /**
     * Number of tails. Responsible for damage, hp
     */
    @Column(nullable=false)
    private int numberOfTails;

    /**
     * Maximum mount of chakra (mana)
     */
    @Column(nullable=false)
    private int maxChakraAmount;

    /**
     * Getter
     * {@link Boss#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Setter
     * {@link Boss#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter
     * {@link Boss#numberOfTails}
     */
    public int getNumberOfTails() {
        return numberOfTails;
    }

    /**
     * Setter
     * {@link Boss#numberOfTails}
     */
    public void setNumberOfTails(int numberOfTails) {
        this.numberOfTails = numberOfTails;
    }

    /**
     * Getter
     * {@link Boss#maxChakraAmount}
     */
    public int getMaxChakraAmount() {
        return maxChakraAmount;
    }

    /**
     * Setter
     * {@link Boss#maxChakraAmount}
     */
    public void setMaxChakraAmount(int maxChakraAmount) {
        this.maxChakraAmount = maxChakraAmount;
    }


    public Boss() {
    }

    public Boss(String name, int tails, int chakra) {
        this.name = name;
        this.maxChakraAmount = chakra;
        this.numberOfTails = tails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void acceptDamage(int damage) {
        currentHP -= damage;
    }

    @Override
    public float getResistance() {
        return 0.4f;
    }

    @Override
    public int getLevel() {
        return numberOfTails;
    }

    @Override
    public int getMaxHp() {
        return numberOfTails * 700;
    }

    @Override
    public int getMaxChakra() {
        return maxChakraAmount;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
