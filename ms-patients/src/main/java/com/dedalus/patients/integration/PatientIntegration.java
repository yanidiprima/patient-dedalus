package com.dedalus.patients.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient; 
import org.springframework.web.reactive.function.client.WebClientResponseException;
 
import com.dedalus.patients.dto.PatientResponseDto;
import com.dedalus.patients.mapper.PatientMapper;
import com.dedalus.patients.utils.Constants;
import com.dedalus.patients.utils.ThrowErrorResponse;
 

@Service
public class PatientIntegration {

    @Autowired
    protected WebClient.Builder builder; 
    
    @Value("${endpoint.fhir.patient}")
    private String base_api_patient;
    
    @Autowired
    private PatientMapper mapper;
    
    public PatientResponseDto getPatientById(String patientId) {
    	String  patient = getPatientFHIRById(patientId);
        return mapExternalToInternal(patient); 
    }
    
    private String getPatientFHIRById(String patientId) {

		WebClient webClient = builder.baseUrl(base_api_patient).build(); 
		try {
			return webClient.get()
	                .uri(base_api_patient+patientId)
	                .retrieve()
	                .bodyToMono(String.class)
	                .block(); 
		}catch (WebClientResponseException  e) {
			if (e.getStatusCode().is4xxClientError()) {
                throw new ThrowErrorResponse(HttpStatus.NOT_FOUND, Constants.RECURSO_NOT_FOUND);
            } else if (e.getStatusCode().is5xxServerError()) {
                throw new ThrowErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_ERROR);
            } else {
                throw new ThrowErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, Constants.SERVICE_UNAVAILABLE);
            }
		}
    }
    private PatientResponseDto mapExternalToInternal(String patient) { 
        return mapper.patientDtotoPatientResponseDto(patient); 
    }
}
