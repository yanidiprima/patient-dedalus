package com.dedalus.patients.mapper.impl;
   

import org.springframework.stereotype.Component;

import com.dedalus.patients.dto.NameDto;
import com.dedalus.patients.dto.PatientDto;
import com.dedalus.patients.dto.PatientResponseDto;
import com.dedalus.patients.mapper.PatientMapper; 

@Component
public class PatientMapperImpl implements PatientMapper  {

	@Override
	public PatientResponseDto patientDtotoPatientResponseDto(PatientDto patient) { 
		String name = ""; 
		if(patient != null && patient.getName() != null && !patient.getName().isEmpty()) {
			for (NameDto names : patient.getName()) {
				if(names != null && names.getGiven().length >= 0) {
					for (String item : names.getGiven()) {
						 name += item;						
					}
				}
	        }
		}
		return patient != null ? PatientResponseDto.builder()
				.id(patient.getId())
				.name(name)
				.surname(patient.getName() != null && !patient.getName().isEmpty() ? patient.getName().get(0).getFamily() : null)
				.nhc(patient.getIdentifier() != null && !patient.getIdentifier().isEmpty() ? patient.getIdentifier().get(0).getValue() : null)
 				.gender(patient.getGender())
 				.birthDate(patient.getBirthDate())
				.build() : null; 
	}

}
