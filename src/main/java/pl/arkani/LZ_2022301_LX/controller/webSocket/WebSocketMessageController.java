package pl.arkani.LZ_2022301_LX.controller.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.controller.ChatMessage;

@Controller
public class WebSocketMessageController {

    @RequestMapping(value = "/test/chat")
    public String page() {
         return "_public/websocket/chat.html";
    }

    @MessageMapping("/send-message")
    //@MessageMapping("/test/ws")
    @SendTo("/topic/livechat")
    public ChatMessage onMessage(@Payload ChatMessage chatMessage) {

//        if(chatMessage.username().equals("Byku")) {
//            costam costam
//        }
        System.out.println(chatMessage);
        return chatMessage;
    }

}
