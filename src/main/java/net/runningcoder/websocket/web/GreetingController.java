package net.runningcoder.websocket.web;

import com.alibaba.fastjson.JSON;
import net.runningcoder.websocket.domain.Greeting;
import net.runningcoder.websocket.domain.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangmaocheng on 2017/10/18.
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        simpMessagingTemplate.convertAndSend("/topic/greetings", new Greeting("这是通过http请求发送的广播！"));
        return "sucess";
    }
}
