package main.java.ds2022.measurementconsumer.Services;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


  public void configureMessageBroker(MessageBrokerRegistry config) {


    config.enableSimpleBroker("/topic/note");
    config.setApplicationDestinationPrefixes("/gkz");
  }


  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/gkz-stomp-endpoint").
    setAllowedOrigins("http://localhost:4200","https://localhost:4200").withSockJS();
  }

}
