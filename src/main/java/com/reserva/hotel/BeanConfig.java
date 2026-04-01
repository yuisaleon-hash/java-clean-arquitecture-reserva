package com.reserva.hotel;

import com.reserva.hotel.application.port.in.CreateReservaUseCase;
import com.reserva.hotel.application.port.out.ReservaPersistencePort;
import com.reserva.hotel.application.service.CreateReservaHandler;
import com.reserva.hotel.domain.service.ReservaDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ReservaDomainService reservaDomainService() {
        return new ReservaDomainService();
    }

    @Bean
    public CreateReservaUseCase createReservaUseCase(
            ReservaPersistencePort port,
            ReservaDomainService domainService) {

        return new CreateReservaHandler(port, domainService);
    }
}
