package meghana.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import meghana.model.OutputMessage;
import meghana.model.Messagem;


@Controller
public class ChatController {
	
	@MessageMapping("/chat")
	  @SendTo("/topic/message")
	  public OutputMessage sendMessage(Messagem message) {
	    return new OutputMessage(message ,new Date());
	  }

	
	
}
