package com.dedalus.patients.mapper;
 
import com.dedalus.patients.dto.PatientResponseDto;

public interface PatientMapper {
	
	PatientResponseDto patientDtotoPatientResponseDto(String patient);
	
}
