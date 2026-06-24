package org.scoula.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        // 구독시 사용할 토픽 접두어(/topic/news, /topic/sports, /topic/tour 등)
        // 클라이언트가 발행(채팅방에 글을 입력해서 서버로 보냈을 때) 시 사용해야하는 접두어
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 접속 엔드포인트, ws://localhost:8080/chat-app
        registry.addEndpoint("/chat-app")
                .setAllowedOrigins("*"); // CORS 허용
    }
}
