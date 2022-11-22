package main.java.ds2022.measurementconsumer.Services;


import main.java.ds2022.measurementconsumer.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class WebSocketServer {
	  
	 public final SimpMessagingTemplate template;

	     @Autowired
	    public WebSocketServer(SimpMessagingTemplate template) {
			this.template = template;
	    
	    }
		  
	    public void sendNotification( Notification notification) {
	    	System.out.println("try to send notification to who listends");
	        this.template.convertAndSend("/topic/note", notification);
	    }
	    
	    
	    
	}
