package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}