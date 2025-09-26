package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.AppointmentRequestDto;
import com.vidhuras.hospital_app.Dto.AppointmentResponseDto;


import java.util.List;

public interface AppointmentService {


    AppointmentResponseDto createAppointment(AppointmentRequestDto dto);

    List<AppointmentResponseDto> getAllAppointments();

    AppointmentResponseDto getAppointmentById(Long appointmentId);

    AppointmentResponseDto updateAppointment(Long appointmentId, AppointmentRequestDto dto);

    void deleteAppointment(Long appointmentId);



}
