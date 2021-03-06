package com.p3212.EntityClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_aifight")
public class UserAIFight {
    
    public static enum Result {
        WON,
        LOST,
        DIED;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fIdentity;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fight_id")
    private FightVsAI fight;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id", referencedColumnName="id", nullable=false)
    private Character fighter;

    /**
     * Result - won, lost, died
     */
    @Column(name="fresult", length=4, nullable=false)
    @Enumerated(EnumType.STRING)
    private Result result;
    
    @Column(name="experience_gain", nullable=false)
    private int experience;
    
    public UserAIFight(){}
    
    public UserAIFight(Result result, FightVsAI fight, Character fighter, int exp) {
        this.experience = exp;
        this.fight = fight;
        this.fighter = fighter;
        this.result = result;
    }
    
    public int getId() {
        return fIdentity;
    }

    public FightVsAI getFight() {
        return fight;
    }

    public Character getFighter() {
        return fighter;
    }
    
    /**
     * Getter
     * {@link FightVsAI#result} 
     */
    public Result getResult() {
        return result;
    }

    /**
     * Setter
     * {@link FightVsAI#result} 
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getfIdentity() {
        return fIdentity;
    }

    public void setfIdentity(int fIdentity) {
        this.fIdentity = fIdentity;
    }

    public void setFight(FightVsAI fight) {
        this.fight = fight;
    }

    public void setFighter(Character fighter) {
        this.fighter = fighter;
    }
    
    
    
}
