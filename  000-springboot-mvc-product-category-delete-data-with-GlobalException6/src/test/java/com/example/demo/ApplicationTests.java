package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class ApplicationTests {

    @Test
    void contextLoads() {
        // Just checks context startup
    }
}
