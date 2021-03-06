package com.p3212.EntityClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class for a message
 */
@Entity
@Table(name = "PrivateMessages")
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int message_id;
    /**
     * Message itself
     */

    @ManyToOne
    @JoinColumn(name = "receiver", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private
    User sender;


    @Column(name = "sending_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private
    Date sendingDate;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private
    String message;

    @Column(name = "is_read", nullable = false)
    private
    boolean isRead;

    public PrivateMessage(int key, String text) {
        this.message_id = key;
        this.message = text;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getMessage() {
        return message;
    }

    public PrivateMessage(User receiver, User sender) {
        this.sendingDate = new Date();
        this.receiver = receiver;
        this.sender = sender;
    }

    public PrivateMessage() {
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
//        return "{" +
//                "\"message_id\":" + message_id +
//                ", \"receiver\":" + receiver.getLogin() +
//                ", \"sender\":" + sender.getLogin() +
//                ", \"sendingDate\":" + sendingDate +
//                ", \"message\":\"" + message + '\"' +
//                ", \"isRead\":" + isRead +
//                '}';
    }
}
