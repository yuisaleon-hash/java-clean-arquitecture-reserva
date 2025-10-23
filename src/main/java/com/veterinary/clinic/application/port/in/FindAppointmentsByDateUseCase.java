package com.veterinary.clinic.application.port.in;

import com.veterinary.clinic.application.dto.query.FindAppointmentsByDateQuery;
import com.veterinary.clinic.application.dto.response.AppointmentResponse;
import java.util.List;

public interface FindAppointmentsByDateUseCase {
    List<AppointmentResponse> findByDate(FindAppointmentsByDateQuery query);
}