package com.dedalus.patients.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient; 
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.dedalus.patients.dto.PatientDto;
import com.dedalus.patients.dto.PatientResponseDto;
import com.dedalus.patients.mapper.PatientMapper;
import com.dedalus.patients.utils.ThrowErrorResponse;

import reactor.core.publisher.Mono; 

@Service
public class PatientAdapter {

    @Autowired
    protected WebClient.Builder builder; 
    
    @Value("${endpoint.fhir.patient}")
    private String base_api_patient;
    
    @Autowired
    private PatientMapper mapper;
    
    public PatientResponseDto getPatientById(String patientId) {
    	PatientDto  patient = getPatientFHIRById(patientId);
        return mapExternalToInternal(patient); 
    }
    
    private PatientDto getPatientFHIRById(String patientId) {

		WebClient webClient = builder.baseUrl(base_api_patient).build(); 
		try {
			return webClient.get()
	                .uri(base_api_patient+patientId)
	                .retrieve()
	                .bodyToMono(PatientDto.class)
	                .block(); 
		}catch (WebClientResponseException  e) {
			if (e.getStatusCode().is4xxClientError()) {
                throw new ThrowErrorResponse(HttpStatus.NOT_FOUND, "Recurso no encontrado");
            } else if (e.getStatusCode().is5xxServerError()) {
                throw new ThrowErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
            } else {
                throw new ThrowErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, "Servicio no disponible");
            }
		}
    }
    private PatientResponseDto mapExternalToInternal(PatientDto patient) { 
        return mapper.patientDtotoPatientResponseDto(patient);
    }
}
