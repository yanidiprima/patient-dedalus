package com.dedalus.patients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.dedalus.patients.dto.PatientResponseDto;
import com.dedalus.patients.service.PatientService;
import com.dedalus.patients.utils.ThrowErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam; 


@RestController 
@Api("PatientsController") 
@RequestMapping(value = {"/patients"})
public class PatientsController {
	
	@Autowired
	private PatientService service;
	
	
	/**
	 * find domiciliationGenerationDto by id
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/patient/{id}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ApiOperation(value = "Get a patient by ID", nickname = "get-patient-by-id", httpMethod = "GET",
			notes = "Given an ID a patient with that ID will be returned", response = PatientResponseDto.class)
	@Operation(summary = "Get a patient by ID", method = "GET")
	public ResponseEntity<PatientResponseDto> findDomiciliationGenerationById(
			@ApiParam(value = "ID of the patient that is returned", required = true)
			@PathVariable(name = "id") String id) {
		try {
			PatientResponseDto searchedDto = service.findPatientById(id); 
			return ResponseEntity.ok().body(searchedDto);
        } catch (ThrowErrorResponse ex) {
            return ResponseEntity.status(ex.getStatus()).body(null);
        }

	}
}
