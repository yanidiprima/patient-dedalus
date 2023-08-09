package com.dedalus.patients.service;

import com.dedalus.patients.dto.PatientResponseDto;

public interface PatientService { 
	
	PatientResponseDto findPatientById(String id);

}
