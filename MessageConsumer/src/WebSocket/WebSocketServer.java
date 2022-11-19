package WebSocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import models.Notification;
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
