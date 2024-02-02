package com.example.demo;


import com.example.demo.redis.AlarmRedisService;
import com.example.demo.redis.ChatRedisService;
import com.example.demo.socket.Greeting;
import com.example.demo.socket.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AlarmRedisService alarmRedisService;

    @Autowired
    private ChatRedisService chatRedisService;

    @GetMapping("/")
    public String index() {
        return "Hellow world!";
    }

    @GetMapping("/check-mysql-connection")
    public String checkMysqlConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "MySQL 연결 확인: 성공";
        } catch (Exception ex) {
            return "MySQL 연결 확인: 실패 - " + ex.getMessage();
        }
    }

    @PostMapping("/setAlarmValue/{key}/{value}")
    public ResponseEntity<String> setAlarmValue(
            @PathVariable String key,
            @PathVariable String value
    ) {
        alarmRedisService.setValue(key, value);
        return ResponseEntity.ok("Value set successfully in alarm Redis");
    }

    @GetMapping("/getAlarmValue/{key}")
    public ResponseEntity<Object> getAlarmValue(
            @PathVariable String key
    ) {
        Object value = alarmRedisService.getValue(key);
        return ResponseEntity.ok(value);
    }

    @PostMapping("/setChatValue/{key}/{value}")
    public ResponseEntity<String> setChatValue(
            @PathVariable String key,
            @PathVariable String value
    ) {
        chatRedisService.setValue(key, value);
        return ResponseEntity.ok("Value set successfully in alarm Redis");
    }

    @GetMapping("/getChatValue/{key}")
    public ResponseEntity<Object> getChatValue(
            @PathVariable String key
    ) {
        Object value = chatRedisService.getValue(key);
        return ResponseEntity.ok(value);
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(
            HelloMessage message
    ) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}