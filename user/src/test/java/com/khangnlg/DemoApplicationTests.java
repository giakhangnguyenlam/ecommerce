package com.khangnlg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = Main.class)
@TestPropertySource("classpath:application.properties")
public class DemoApplicationTests {

    private static Calc calc;

    @BeforeAll
    public static void setup(){
        calc = new Calc();
    }

    @Test
    public void contextloads(){
        assertThat(calc.sum(1,1)).isEqualTo(2);
    }

}

class Calc{
    public int sum(int a, int b){
        return a+b;
    }
}
