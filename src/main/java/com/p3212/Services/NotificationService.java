package com.p3212.Services;

import com.p3212.EntityClasses.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    public void notify(Message notification) {
        messagingTemplate.convertAndSend("/queue/notify", notification);
    }
    
}
