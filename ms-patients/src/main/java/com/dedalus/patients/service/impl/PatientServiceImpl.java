package com.dedalus.patients.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dedalus.patients.dto.PatientResponseDto;
import com.dedalus.patients.integration.PatientIntegration;
import com.dedalus.patients.service.PatientService;


@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientIntegration adapter;

	@Override
	public PatientResponseDto findPatientById(String id) { 
		return adapter.getPatientById(id);
	}

}
