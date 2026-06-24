package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.domain.ChatMessage;
import org.scoula.domain.GreetingMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class ChatController {

    //topic마다 설정
    // /app/hello로 설정됨.
    // 브라우저에서 메세지보낼 때는
    // /app/hello로 보내야함.
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingMessage greeting(GreetingMessage message){
        System.out.println("서버에서 전달받은 이름은 " + message);
        return message; //리턴이 브로드캐스팅할 데이터임.
        //가입한 브라우저들에게 모두 등록한 이름(홍길동)을 보내게 됨.
        //브라우저는 이름을 받아서 화면에 "홍길동님이 입장하셨습니다."라고 보이면 됨.(js로 구현)
    }


    @MessageMapping("/chat") // /app/chat
    @SendTo("/topic/chat")
    public ChatMessage chat(ChatMessage message){
        System.out.println("서버에서 전달받은 이름과 메시지는 " + message);
        return message; //리턴이 브로드캐스팅할 데이터임.
        //가입한 브라우저들에게 모두 등록한 이름과 메시지(홍길동, 안녕하세요)을 보내게 됨.
        //브라우저는 이름과 메시지를 받아서 화면에 보이게 됨.(js로 구현)
    }
}
