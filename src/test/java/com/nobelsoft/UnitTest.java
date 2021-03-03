package com.nobelsoft;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UnitTest {

    @MockBean
    RestTemplate template;

    @Test
    public void testTemplate() {
        when(template.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity(new Object(), HttpStatus.OK));
    }
}
