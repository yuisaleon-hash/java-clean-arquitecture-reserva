package com.veterinary.clinic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // 👈 ESTA LÍNEA SOLUCIONA TODO
class ClinicaVeterinariaApplicationTests {

    @Test
    void contextLoads() {
    }

}