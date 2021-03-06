package com.p3212.EntityClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Used to operate on fight history data
 */
@Entity
@Table(name = "ai_fights")
public class FightVsAI extends Fight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Date of a fight
     */
    @Column(name = "fight_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fight_date;

    /**
     * Opponent
     * Either a character or an AI boss
     */

    @ManyToOne
    @JoinColumn(name = "boss", nullable = false)
    private Boss boss;

    @OneToMany(mappedBy = "fight")
    @JsonIgnore
    private List<UserAIFight> setFighters;

    
    
    public FightVsAI() {
        this.fight_date = new Date();
    }
    
    
    public List<UserAIFight> getSetFighters() {
        return setFighters;
    }

    public void setSetFighters(List<UserAIFight> fighters) {
        this.setFighters = fighters;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFight_date() {
        return fight_date;
    }

    public void setFight_date(Date fight_date) {
        this.fight_date = fight_date;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id +
                ", \"type\": \"pve\"" +
                ", \"timeLeft\":" + timeLeft +
                ", \"currentName\":\"" + currentName +
                "\", \"fighters1\":" + fighters +
                ", \"boss\":" + boss.toString() +
                ", \"animals1\":" + animals1 + "}";
    }
}