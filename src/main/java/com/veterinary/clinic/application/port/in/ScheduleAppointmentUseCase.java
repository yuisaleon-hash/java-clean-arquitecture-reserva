package com.veterinary.clinic.application.port.in;

import com.veterinary.clinic.application.dto.command.ScheduleAppointmentCommand;
import com.veterinary.clinic.application.dto.response.AppointmentResponse;

public interface ScheduleAppointmentUseCase {
    AppointmentResponse schedule(ScheduleAppointmentCommand command);
}