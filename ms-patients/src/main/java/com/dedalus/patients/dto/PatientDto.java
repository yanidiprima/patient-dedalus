package com.dedalus.patients.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer; 
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer; 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

	private String gender;
	private String id;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate birthDate;
	private List<IdentifierDto> identifier;
	private List<NameDto> name; 
}
