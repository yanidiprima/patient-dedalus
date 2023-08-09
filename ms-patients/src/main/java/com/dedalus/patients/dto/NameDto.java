package com.dedalus.patients.dto;
 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameDto {
	
	private String use;
	private String family;
	private String[] given;
	
}
