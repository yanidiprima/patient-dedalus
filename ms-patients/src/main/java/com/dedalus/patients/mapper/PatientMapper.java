package com.dedalus.patients.mapper;

import com.dedalus.patients.dto.PatientDto;
import com.dedalus.patients.dto.PatientResponseDto;

public interface PatientMapper {
	
	PatientResponseDto patientDtotoPatientResponseDto(PatientDto patient);
	
}
