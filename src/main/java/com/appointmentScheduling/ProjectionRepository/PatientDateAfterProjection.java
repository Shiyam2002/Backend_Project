package com.appointmentScheduling.ProjectionRepository;

import java.sql.Date;
import java.time.LocalDate;

public interface PatientDateAfterProjection {

    Integer getpatientId();

    String getpatientName();

    Date getpatientDob();
}
